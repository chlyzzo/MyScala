package practice

import org.apache.spark._

case class Pro(cityId:String,proId:String)
case class Bro(cityId:String,broId:String,prefs:List[Pref])
case class Pref(
                 pretype: String,
                 preid: String,
                 preval: Double
                 )

object ex77rddjoin {
  val RECOMM_BROKER_NUM =1 
  def main(args:Array[String]):Unit={
    val conf = new SparkConf().setAppName("matrix").setMaster("local")         
    val sc = new SparkContext(conf) 
    //1,构建pro的rdd，(city,pro)
    println("fdsf")
    val pro1=Pro("14","p1")
    val pro2=Pro("14","p2")
    val pro3=Pro("18","p3")
    val rdd1=sc.parallelize(Array((pro1.cityId,pro1),(pro2.cityId,pro2),(pro3.cityId,pro3))).groupByKey()
    
     //rdd1.foreach(f=>println(f))
    //2,构建broker的rdd，RDD[(String, (String, List[Pref], Int))]，city，broId，List[Pref]，Int
    val pref1=Pref("118","1225",0.12)
    val pref2=Pref("134","14422",0.25)
    val pref3=Pref("213","42454",0.254)
    
    //
    val bro1=Bro("14","b1",List(pref1,pref2))
    val bro2=Bro("18","b2",List(pref2,pref3))
    val bro3=Bro("14","b3",List(pref1))
    //("18",("1254",List(pref1),1)),("18",("453",List(pref1,pref2,pref3),0)),("14",("4586",List(pref2,pref3),2)),("14",("766",List(pref3),2))
    val rdd2=sc.parallelize(Array((bro1.cityId,bro1),(bro2.cityId,bro2),(bro3.cityId,bro3))).groupByKey()
    //rdd2.foreach(f=>println(f))
    val newrdd=(rdd1 join rdd2).groupByKey()// RDD[(String, Iterable[(Iterable[Pro], Iterable[Bro])])]
    // newrdd (city [p1,p2] [b1,b3])  即（city，List（Pro），List（Bro））

    newrdd.foreach(f=>println(f))
    
    ///对key下的值进行计算，同一个key只有一个值
    val brokerScoreRdd=newrdd.mapValues{ f=> //Iterable[(Iterable[Pro], Iterable[Bro])]
      val tm=f.map { listPro=> //(Iterable[Pro], Iterable[Bro])
       val listProAndListBro= listPro._1.map { pro => //Pro
          val listScore=listPro._2.map { bro =>
            val score = calculateScore(pro,bro.prefs)
            (bro,score)
          }//pro + list(bro),Iterable[(Double, Bro)]
          //按分数排序
          val listScoreSort=listScore.toSeq.sortWith(_._2 > _._2).map{x=>x}
          (pro,listScoreSort)
       }// List(pro)+List(bro)
        listProAndListBro
      }// 
      tm
    }//mapvalues
 brokerScoreRdd.foreach{f =>
   println("city="+f._1+"'s pros recomm start")
   f._2.foreach{ ff =>
     ff.foreach{ proAndBro=>
       print("pro="+proAndBro._1+"recomm brolers:")
       proAndBro._2.foreach{fff =>
            print("broker="+fff)
          }
       println("")
       }
   } 
 }
    
    
    
//    //rdd RDD[String]计数，filter > 3的
//    val rdd3=sc.parallelize(Array("a","b","a","a","b","b","c","d"))
//    val rdd4=rdd3.groupBy(x=>x).map(x=>(x._1,x._2.size)).filter(_._2>=3).map(_._1)
//    rdd4.foreach(f=>println(f))
//   
  }
  
  def calculateScore(pro:Pro,listPref:List[Pref]):Double={
    
    1.0
  }
}