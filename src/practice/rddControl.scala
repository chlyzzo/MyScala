package practice
//cache,chectpoint
import org.apache.spark._
object rddControl {
  
  def main(args:Array[String]):Unit={
    println("*********")
    val conf = new SparkConf().setAppName("matrix").setMaster("local")         
    val sc = new SparkContext(conf) 
    
    val rdd1=sc.makeRDD(1 to 10)
    //设置checkpoint的目录，不然出错，
    sc.setCheckpointDir("hdfs://localhost:9000/tmp")  
    rdd1.checkpoint()
    rdd1.foreach { x => print(x) }
    
  }
}