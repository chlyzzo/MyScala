package scala

object Test1 {
  def main(args:Array[String]):Unit={
    
     println(~(2 & 0xff)+1);
     //println(ChecksumAccumulator.calculate("every "))
  }
  
  def max(x:Int,y:Int):Int={
    if(x>y)
      x
    else
      y
  }
}