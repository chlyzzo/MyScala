package scalaCookBook

object ex10_19splitCollection {
  
  def main(args:Array[String]):Unit={
    val x=List(15,10,5,8,20,12,36,2,9,7)
    val y=x.groupBy(_>10)
    println(y)
    println(y(true))//true 
    println(y(false))//false
    val (a,b) = x.partition(_ > 10)
    
    //sliding (size,step)
    val nums=(1 to 5).toArray
    // size = 2
    nums.sliding(2).toList
    //List[Array[Int]] = List(Array(1, 2), Array(2, 3), Array(3, 4), Array(4, 5))
// size = 2, step = 2
    nums.sliding(2,2).toList
///List[Array[Int]] = List(Array(1, 2), Array(3, 4), Array(5))
// size = 2, step = 3
    nums.sliding(2,3).toList
////List[Array[Int]] = List(Array(1, 2), Array(4, 5))
    
    //zip and unzip
    val listOfTuple=List((1,2),('a','b'))
    val tr=listOfTuple.unzip
    //val (tr1,tr2)=listOfTuple.unzip
    
    val women=List('a','b')
      val men=List('c','d')
        val couples= women zip men
    
    println(couples)
    
  }
}