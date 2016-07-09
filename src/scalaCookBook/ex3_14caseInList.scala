package scalaCookBook

//List must have Nil
object ex3_14caseInList {
  
  def listToString(list:List[String]):String=list match{
    case s::rest=>s+" "+listToString(rest)
    case Nil=>""
  }
  
  def sum(list:List[Int]):Int=list match{
    case Nil=>1
    case n::rest=>n+sum(rest)
  }
  
  def multiply(list:List[Int]):Int=list match{
    case Nil=>1
    case n::rest=>n*multiply(rest)
  }
  
  def main(args:Array[String]):Unit={
    val ls="Apples" :: "Bananas" :: "Oranges" :: Nil
    val nums=List(1,2,3,4,5)
    println(listToString(ls))
    println(sum(nums))
    println(multiply(nums))
  }
}