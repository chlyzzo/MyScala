package scalaCookBook
/*
 reduce and fold 
 the same
 reduce 
 in collection,the element 1 operate 2,get result 1',and result 1' operate element 3,
 until,all element operate;
 have n elements
 // you provide the sequence 'seq' and the function 'f'
var result = seq(0)
for (i <- 1 until seq.length) {
val next = seq(i)
result = f(result, next)
}

 
 but fold,have a x value,and for first element
in collection,the x operate element 1,get result 1',and result 1' operate element 2,
 have n+1 elements.
*/
object ex10_20reduceAndfold {
  
  val product = (x: Int, y: Int) => {
       val result = x * y
       println(s"multiplied $x by $y to yield $result")
       result
}
  
  def main(args:Array[String]):Unit={
    val a = Array(1, 2, 3)
    val b=a.scanLeft(10)(product)
    println(b.toList) 
    
    println(a.reduce(_+_))
    println(a.foldLeft(20)(_+_))//
    
  }
  
}