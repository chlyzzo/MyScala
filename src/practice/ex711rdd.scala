package practice

import org.apache.spark._

case class Pro2(cityId:String,proId:String,commId:String)

object ex711rdd {
  
  def main(args:Array[String]):Unit={
    val conf = new SparkConf().setAppName("matrix").setMaster("local")         
    val sc = new SparkContext(conf) 
        
    val pro1=Pro2("14","p1","12")
    val pro2=Pro2("14","p2","13")
    val pro3=Pro2("18","p3","14")
    val rdd1=sc.parallelize(Array((pro1.cityId,pro1),(pro2.cityId,pro2),(pro3.cityId,pro3)))
    rdd1.foreach(println(_))
    
    val rdd2=rdd1.map(f => f._2.commId -> f).groupByKey //
   
    rdd2.foreach(println(_))
    
    var mapt=Map(("a","b")->1,("b","t")->4,("a","v")->2)
    println(mapt)
    mapt= mapt.map{ f => 
      if (f._1._2=="b")
      {
        (f._1,14)
      }
      else
      {
        f
      }
    }
     println(mapt)
   
    
    val data=List(("1"->"a"),("2"->"b"),("1"->"c"),("2"->"f"))
    val rdd3=sc.parallelize(data)
   
    val res=rdd3.reduceByKey{ (x,y)=> 
       println("x="+x+"y="+y)
       x+"-"+y
    }
     res.foreach(println)
//    val rdd3groupBy=rdd3.groupByKey()
//    rdd3groupBy.foreach(println)
    
  }
}