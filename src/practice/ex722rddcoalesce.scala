package practice

import org.apache.spark._

object ex722rddcoalesce {
  
  def main(args:Array[String]):Unit={
    
     val conf = new SparkConf().setMaster("local").setAppName("save")   
     val sc = new SparkContext(conf)
     
    val uid=sc.parallelize(("gfd"))
    
    val tr=sc.parallelize("12"+"\t"+"3"+"\t"+"t"+"\t"+"m"+"\t"+"1"+"\t"+"236")

    uid.saveAsTextFile("uid")
    tr.saveAsTextFile("tr")
    println("fdsdf")
    
  }
}