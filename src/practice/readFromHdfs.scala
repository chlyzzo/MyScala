package practice

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
object readFromHdfs {
  
  def main(args:Array[String]):Unit={
    
    val conf = new SparkConf().setAppName("read hdfs").setMaster("local")
    val sc = new SparkContext(conf);
    val data = sc.textFile("")  
    data.foreach { x => println(x) }
  }
}