package scalaCookBook
//case have class,
//match ,
trait Animal
case class Dog(name: String) extends Animal
case class Cat(name: String) extends Animal
case object Woodpecker extends Animal

object ex3_12caseClassInMatch {
  
  def determineType(x: Animal): String = x match {
   case Dog(moniker) => "Got a Dog, name = " + moniker
   case _:Cat => "Got a Cat (ignoring the name)"
   case Woodpecker => "That was a Woodpecker"
   case _ => "That was something else"
}
  
  def main(args:Array[String]):Unit={
    
    println(determineType(new Dog("Rocky")))
    println(determineType(new Cat("Rusty the Cat")))
    println(determineType(Woodpecker))
    
  }
}