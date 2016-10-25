package sparkMilib

import java.text.BreakIterator
import scala.collection.mutable
import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.mllib.clustering.{DistributedLDAModel, LDA}
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.rdd.RDD
import java.io.File
import org.slf4j.LoggerFactory

object useldaModel {
  
  implicit val formats = org.json4s.DefaultFormats
  val logger = LoggerFactory.getLogger(getClass)
  
  def main(args:Array[String]):Unit={
    
     val conf = new SparkConf().setAppName("use lda").setMaster("local")
    val sc = new SparkContext(conf)
     
    val paths = getFilesNameFromDir(new File("/home/min/workspace/MyJava/LDA4j/data/mini"))
                   .map { file => file.getParent+"/"+file.getName }
                   .toSeq
                   
    val stopwordFile = "/home/min/workspace/MyJava/bin/data/dictionary/stopwords (origin).txt"     
    val (corpus, vocabArray, actualNumTokens) = preprocess(sc, paths, -1, stopwordFile)
    
    val ldaModel = new LDA().setK(40).run(corpus)
    
    //ldaModel.save(sc,"myLDAModel")

    //val sameModel=DistributedLDAModel.load(sc,"myLDAModel")
    
            
    
    
  }//end main
  
  private def getFilesNameFromDir(dir: File): Iterator[File] = {
		val d = dir.listFiles.filter(_.isDirectory)
		val f = dir.listFiles.filter(_.isFile).toIterator
		
		f ++ d.toIterator.flatMap(getFilesNameFromDir _)		
  }
  
  //1,train 语料是分好词的,每篇文档一行,词以空格隔开;
     //1.1 预处理,停用词表,去除训练集中的停用词,得到新的train训练集,即没篇文档去除停用词
  private class SimpleTokenizer(sc: SparkContext, stopwordFile: String) extends Serializable {
    
     private val stopwords: Set[String] = if (stopwordFile.isEmpty) {
                      Set.empty[String]
                      } else {
                           val stopwordText = sc.textFile(stopwordFile).collect()
                           stopwordText.flatMap(_.stripMargin.split("\\s+")).toSet
                     }
          // Matches sequences of Unicode letters
     private val allWordRegex = "^(\\p{L}*)$".r
         // Ignore words shorter than this length.
     private val minWordLength = 2
         
     def getWords(text: String): IndexedSeq[String] = {
         val words = new mutable.ArrayBuffer[String]()
         // Use Java BreakIterator to tokenize text into words.
         val wb = BreakIterator.getWordInstance
         wb.setText(text)
         // current,end index start,end of each word
         var current = wb.first()
         var end = wb.next()
         while (end != BreakIterator.DONE) {
              // Convert to lowercase
             val word: String = text.substring(current, end).toLowerCase
             // Remove short words and strings that aren't only letters
             word match {
                case allWordRegex(w) if w.length >= minWordLength && !stopwords.contains(w) =>
                       words += w
                case _ =>
             }
             current = end
             try {
               end = wb.next()
             } catch {
                   case e: Exception =>
                   // Ignore remaining text in line.
                   // This is a known bug in BreakIterator (for some Java versions),
                   // which fails when it sees certain characters.
                           end = BreakIterator.DONE
             }
          }
          words
      }
  }
     //1.2 得到1.1result后,计算train的词典(后续预测有用),并且计算每篇文档的词频向量,
     //    即向量的维度是词典大小,数值是该词在该文档中出现的次数,
     //vocabSize是词典的大小,-1是全部的词,否则用自定义的个数,按照词频排序,
  
 private def preprocess(sc: SparkContext,paths: Seq[String],vocabSize: Int,stopwordFile: String)
                      : (RDD[(Long, Vector)], Array[String], Long) = {
    // Get dataset of document texts
    // One document per line in each text file. If the input consists of many small files,
    // this can result in a large number of small partitions, which can degrade performance.
    // In this case, consider using coalesce() to create fewer, larger partitions.
    val textRDD: RDD[String] = sc.textFile(paths.mkString(","))
    // Split text into words
    val tokenizer = new SimpleTokenizer(sc, stopwordFile)
    val tokenized: RDD[(Long, IndexedSeq[String])] = textRDD.zipWithIndex().map { case (text, id) =>
      id -> tokenizer.getWords(text)
    }
    tokenized.cache()
    // Counts words: RDD[(word, wordCount)]
    val wordCounts: RDD[(String, Long)] = tokenized
      .flatMap { case (_, tokens) => tokens.map(_ -> 1L) }
      .reduceByKey(_ + _)
    wordCounts.cache()
    val fullVocabSize = wordCounts.count()
    // Select vocab
    //  (vocab: Map[word -> id], total tokens after selecting vocab)
    val (vocab: Map[String, Int], selectedTokenCount: Long) = {
      val tmpSortedWC: Array[(String, Long)] = if (vocabSize == -1 || fullVocabSize <= vocabSize) {
        // Use all terms
        wordCounts.collect().sortBy(-_._2)
      } else {
        // Sort terms to select vocab
        wordCounts.sortBy(_._2, ascending = false).take(vocabSize)
      }
      (tmpSortedWC.map(_._1).zipWithIndex.toMap, tmpSortedWC.map(_._2).sum)
    }
    val documents = tokenized.map { case (id, tokens) =>
      // Filter tokens by vocabulary, and create word count vector representation of document.
      val wc = new mutable.HashMap[Int, Int]()
      tokens.foreach { term =>
        if (vocab.contains(term)) {
          val termIndex = vocab(term)
          wc(termIndex) = wc.getOrElse(termIndex, 0) + 1
        }
      }
      val indices = wc.keys.toArray.sorted
      val values = indices.map(i => wc(i).toDouble)
      val sb = Vectors.sparse(vocab.size, indices, values)
      (id, sb)
    }
    val vocabArray = new Array[String](vocab.size)
    vocab.foreach { case (term, i) => vocabArray(i) = term }
    (documents, vocabArray, selectedTokenCount)
  }
 
  //(需要保存词典,即词--index的k-v值)
  
  //2,训练,lda训练,
    //保存模型,以便后续使用
  
  //3,加载模型,预测新的文档topic分布,必须将模型转换为localldamodel,
  
}//end object