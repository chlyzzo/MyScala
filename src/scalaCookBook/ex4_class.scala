package scalaCookBook
/*
 If a field is declared as a var , Scala generates both getter and setter methods for that
field.
• If the field is a val , Scala generates only a getter method for it.
• If a field doesn’t have a var or val modifier, Scala gets conservative, and doesn’t
generate a getter or setter method for the field.
• Additionally, var and val fields can be modified with the private keyword, which
prevents getters and setters from being generated.

Visibility                                Accessor?    Mutator?
  var                                        Yes          Yes
  val                                        Yes          No
Default visibility (no var or val )           No          No
Adding the private keyword to var or val      No          No

class constructor parameters are val by default.

*/

class Person(var firstName: String, var lastName: String) {
  //var available changed;val do not available changed;
  //if not var/val,do not changed
  
     println("the constructor begins")
     // some class fields
     private val HOME = System.getProperty("user.home")
     var age = 0// available changed
     // some methods
     override def toString = s"$firstName $lastName is $age years old"
     def printHome { println(s"HOME = $HOME") }
     def printFullName { println(this) } // uses toString
     printHome
     printFullName
     println("still in the constructor")
}

case class Person2(name:String)

object ex4_class {
  
  def main(args:Array[String]):Unit={
         val p=new Person("Adam","Meyer")
         p.firstName="dfsa"
         p.lastName="12"
         println(p)
         
         val per2=Person2("23")
          println(per2.name)
         // per2.name="58" error
          
  }
  
  
}