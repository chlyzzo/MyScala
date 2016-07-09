package scalaCookBook

//tuple2 ---tuple22
//tuple is not collection
object ex10_27tuple {

  def main(args:Array[String]):Unit={
    val d= ("ads",25)
    println (d)
    case class Person(name:String)
    val t=(3,"df",Person("fdsf"))
    println (t)
    println(t._1)
    val (x,y,z)=(3,"df",Person("fdsf"))
    val (x1,_,x3)=t
    val (_,x2,_)=t
    // tuple can treat as a collection,use productIterator
    val it=t.productIterator
    for(e<-it)println(e)
    //tuple to a collection
    val tr=t.productIterator.toArray
  }
}