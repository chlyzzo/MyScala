package scalaCookBook

import scala.io.Source
import java.io.File 

object ex12_1openAndreadFile {
  
  def main(args:Array[String]):Unit={
    
//    val filename = "/home/min/workspace/MyScala/src/scalaCookBook/ex12_1openAndreadFile.scala"
//    //val filename = "ex12_1openAndreadFile.scala"
//    for (line<- Source.fromFile(filename).getLines())
//    {
//      println(line)
//    }
    
    val allFiles = getFilesNameFromDir(new File("/home/min/workspace/MyJava/LDA4j/data/mini"))
                   .map { file => file.getName }.toSeq
    
    println(allFiles)
  }
  
  def getFilesNameFromDir(dir: File): Iterator[File] = {
		val d = dir.listFiles.filter(_.isDirectory)
		val f = dir.listFiles.filter(_.isFile).toIterator
		
		f ++ d.toIterator.flatMap(getFilesNameFromDir _)		
	}
  
}