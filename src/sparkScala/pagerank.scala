package sparkScala
import org.apache.spark._

object pagerank {
  def main(args:Array[String]):Unit={
   
    val conf = new SparkConf().setAppName("Spark Pi").setMaster("local")
    val sc = new SparkContext(conf)
    
    // 生成网页边的关系
    val links = sc.parallelize(Array(('A',Array('D')),('B',Array('A')),
       ('C',Array('A','B')),('D',Array('A','C'))),2).map(x => (x._1, x._2)).cache()

    // 初始化rank值，2表示分两个partition
    var ranks = sc.parallelize(Array(('A',1.0),('B',1.0),('C',1.0),('D',1.0)), 2)

    // 迭代10次
    for ( i <- 1 to 10){
       val contribs = links.join(ranks, 2)
       val flatMapRDD = contribs.flatMap {
           case (url,(links,rank)) => links.map(dest => (dest, rank/links.size))
        }.reduceByKey(_ + _, 2)
       ranks = flatMapRDD.mapValues(0.15 + 0.85 * _)
    }
    ranks.foreach{
      x=>println(x._1+"="+x._2)}
  }
}