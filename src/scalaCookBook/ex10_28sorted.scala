package scalaCookBook
//sorted a collection,or sorted method by yourself,<,<=,>,>=
object ex10_28sorted {
  
  def main(args:Array[String]):Unit={
    val a =List(0,8,2,3,98).sorted
    println(a)
    val b= List("bad","padf","ap","dfdfd").sorted
    println(b)
    //sortWith,lets you provide your own sorting function
    println(a.sortWith(_<_))//up
    println(a.sortWith(_>_))//down
    
    //
    println(b.sortWith(_.length<_.length))//up
    println(b.sortWith(_.length>_.length))//down
    
    //if sorting method is long ,write a method
    println(b.sortWith(sortByLength))
    
    /*
    //if a new type,example a class by create,the sort method must change
    case class Person(var name:String)
    val ty=Person("Tyler")
    val al=Person("Al")
    val paul=Person("Paul")
    val dudes=List(ty,al,paul)
    //dudes.sorted is error
    val sortdudes=dudes.sortWith(_.name>_.name)
    println(sortdudes)
    */
    
    //let new class can use >,<
  case class Person(var name:String)extends Ordered[Person]
  {
      override def toString =name
      def compare(that:Person)=this.name.compare(that.name)
  }
    val ty=Person("Tyler")
    val al=Person("Yl")
    if(ty>al)println("Al")else println("TY")
    
    //collection to String,use mkString
    val v=Vector("ap","ba","pea")
    println(v.mkString(","))
    println(v.toString())//returns the name of the collection with the elements
  }
  def sortByLength(s1:String,s2:String)={
    println("comparing %s and %s".format(s1,s2))
    s1.length > s2.length
  }
}