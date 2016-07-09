package scalaCookBook
//add qualifying logic to a case
//in case 

object ex3_13AddifIncaseInMatch {
 
  def main(args:Array[String]):Unit={
    
    var i= 56
    i match {
       case a if 0 to 9 contains a => println("0-9 range: " + a)
       case b if 10 to 19 contains b => println("10-19 range: " + b)
       case c if 20 to 29 contains c => println("20-29 range: " + c)
       case _ => println("Hmmm...")
       }
    var num=3
    num match {
          case x if x == 1 => println("one, a lonely number")
          case x if (x == 2 || x == 3) => println(x)
          case _ => println("some other value")
        }

     /*
     stock match{
      case x if (x.symbol == "XYZ" && x.price < 20) => buy(x)
      case x if (x.symbol == "XYZ" && x.price > 50) => sell(x)
      case _ => // do nothing
      }
       */
      
    /*
     def speak(p: Person) = p match {
       case Person(name) if name =="Fred" => println("Yubba dubba doo")
       case Person(name) if name =="Bam Bam" => println("Bam bam!")
       case _ => println("Watch the Flintstones!")
     }
     **/
    
    /*
     match one type, or multiple different types.
     
     */

  }
  
}