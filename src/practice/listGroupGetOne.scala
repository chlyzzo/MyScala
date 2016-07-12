package practice

case class Bro2(cityId:Int,broId:Int)

object listGroupGetOne {
  
  def main(args:Array[String]):Unit={
    
    val listr=List(Bro2(1,2),Bro2(1,3),Bro2(2,4)).map(x=>(x.cityId,x)).groupBy(_._1).flatMap{x=>
      x._2.map(_._2).slice(0, 1)
    }
    println(listr)
    
  }
}