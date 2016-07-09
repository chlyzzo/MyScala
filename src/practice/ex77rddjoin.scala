package practice

import org.apache.spark._

object ex77rddjoin {
  
  def main(args:Array[String]):Unit={
    val conf = new SparkConf().setAppName("matrix").setMaster("local");          // create a spark config object
    val sc = new SparkContext(conf) 
    val rdd1=sc.parallelize(Array((4,List(11,21,34)),(5,List(12,27,35)),(6,List(1,2,3))))
    //rdd1.foreach(f=>println(f))
    val rdd2=sc.makeRDD(Array((4,(4,List(11,21,34))),(4,(78,List(12,27,35))),(3,(56,List(1,2,3)))))
    //rdd2.foreach(f=>println(f))
    val newrdd=(rdd1 join rdd2).groupByKey()
    
    newrdd.foreach(f=>println(f))
    
  }
}