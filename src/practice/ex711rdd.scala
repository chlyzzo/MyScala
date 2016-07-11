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
    //               allCityBroPreRdd=allCityBroPreRdd.map{ f => //(Int, Iterable[(String, List[Pref], Int)])
//                 println("****cityId="+f._1)
//                 if(f._1.equals(pro._1)) //cityId
//                 {
//                   println("*****pro="+pro._2.prop_id)
//                       val listScore=f._2.map{ broker => //(String, List[Pref], Int)
//                       if (broker._3 <= BROKER_RECOMM_PRO_NUM)
//                       {
//                          (broker,caculateScore(pro._2,broker._2))
//                       }
//                       else
//                       {
//                         (broker,0.0)
//                       }
//                     }//Iterable[(bro, Double)]
//                     //排序
//                     val result=listScore.toSeq.sortWith(_._2 > _._2).map{x=>x._1._1}.take(PRO_RECOMM_BROKER_NUM)
//                     println("***recomm brokers==="+result)
//                     //update 
//                     val iterBros=f._2.map{ broker =>
//                          if (result.containsSlice(broker._1))
//                          {
//                              (broker._1,broker._2,broker._3+1)
//                          }
//                          else
//                          {
//                            (broker._1,broker._2,broker._3)
//                          }
//                     }//end update
//                     (f._1,iterBros)
//                 }//end city if 
//                 else
//                 { f }
//               }//end map 
    
    
  }
}