package practice

import org.apache.spark._
import org.apache.spark.util._

object rddGet {
  
  def main(args:Array[String]):Unit={
    print("gfdg")
    val conf =new SparkConf()  
    conf.setAppName("My first Test in class of the Spark")  
    conf.setMaster("local")  
    val sc = new SparkContext(conf)  
    val number = 1 to 10  
    val rdd =sc.parallelize(number)  
    print(rdd)
    
  }
}