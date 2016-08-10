package practice

import org.apache.spark._
import org.apache.spark.util._
import scala.math._
import org.apache.spark.mllib.linalg.Vectors

object scalaMatrix {
  
  //余弦距离，余弦相似度
  def cosDist(v1:Vector,v2:Vector):Double={
    val cos=v1.dot(v2)
    val v1len=sqrt(v1.dot(v1))
    val v2len=sqrt(v2.dot(v2))
    val res=cos/(v1len*v2len)
    return res
  }
  
  def main(args:Array[String]):Unit={
      println("sdfsdf")
      val conf = new SparkConf().setAppName("matrix").setMaster("local");          // create a spark config object
      val sc = new SparkContext(conf) 

      val paramatrix1=sc.parallelize(List(Vector(2,2,4),Vector(3,1,2),Vector(1,3,2)))
      val vec1=Vector(1,2,4)
      val m1=paramatrix1.map(_.dot(vec1))//点积
      val m2=paramatrix1.map(_.squaredDist(vec1))//欧拉距离
      //余弦相似度
      val m3=paramatrix1.map(cosDist(_,vec1))
      m3.foreach { x => println(x) }    
      
      
      //map 存储Vector
      val map1=Map(
       "vec"-> "[-0.3409798, 0.23111226, 0.12990808]" ,                            
       "id"->"1"
      )
      val map2=Map(
       "vec"-> "[-0.3409798, 0.23111226, 0.12990808]" ,                            
       "id"->"2"
      )
      val map3=Map(
       "vec"-> "[-0.3409798, 0.23111226, 0.12990808]" ,                            
       "id"->"3"
      )
      
      val mapList=List(map1,map2,map3)
      val mapn=mapList.map{x=>
        val vec=Vectors.dense(x.get("vec").get.replace("[","").replace("]","").split(",").toArray.map(_.toDouble))
        Map(
           "id"->x.getOrElse("id", ""),
           "vec"->vec
        )
      }

  }
}