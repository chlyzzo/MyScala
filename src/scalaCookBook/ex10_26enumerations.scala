package scalaCookBook
//create a Enumeration
object Margin extends Enumeration{
    type Margin = Value
    val TOP,BOTTOM,LEFT,RIGHT=Value
  }

object ex10_26enumerations {
  
  import Margin._//import a Enumeration
  
  def main(args:Array[String]):Unit={
    //use a Enumeration value in a test
    val currentMargin=TOP
    //later in the code ....
    if (currentMargin==TOP) println("working on top.")
    //output all Margin

    //Margin.Value foreach println
    
  }
}