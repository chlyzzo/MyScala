package scalaCookBook
// more constructors class body
//use this

// primary constructor
class Pizza (var crustSize: Int, var crustType: String) {
    // one-arg auxiliary constructor
    def this(crustSize: Int) 
    {
         this(crustSize, Pizza.DEFAULT_CRUST_TYPE)
    }
// one-arg auxiliary constructor
    def this(crustType: String) 
    {
         this(Pizza.DEFAULT_CRUST_SIZE, crustType)
     }
// zero-arg auxiliary constructor
     def this() 
     {
           this(Pizza.DEFAULT_CRUST_SIZE, Pizza.DEFAULT_CRUST_TYPE)
     }
     override def toString = s"A $crustSize inch pizza with a $crustType crust"
}
//auxiliary constructor,create by this
//must call primary constructor
//have different signature
//one calls another use this

object Pizza {
       val DEFAULT_CRUST_SIZE = 12
       val DEFAULT_CRUST_TYPE = "THIN"
}

//case use apply,but you donot see
//if want to change apply,then override it
case class Person3(var name:String,var age:Int)
//the companion object
object Person3
{
  def apply()=new Person3("<no name>",0)
  def apply(name:String)=new Person3(name,0)
 }


//default value in class constructor

class Socket(val timeout:Int=10000)


//一个类中的参数是一个类
case class Person4(var userName:String,var passwd:String)
{
   var age=0
   var firstname=""
   var lastname=""
   var address=None:Option[Address]
}
case class Address(city:String,state:String,zip:String)


//extend a class
class Person5 (var name: String, var address: Address) {
       override def toString = if (address == null) name else s"$name @ $address"
}
class Employee (name: String, address: Address, var age: Int) extends Person5 (name, address) {
   // rest of the class
}
//The name and address parameters are common to the parent

object ex4_3ClassThis {
  
   def main(args:Array[String]):Unit={
     
     val p1=new Pizza(Pizza.DEFAULT_CRUST_SIZE,Pizza.DEFAULT_CRUST_TYPE)
     println(p1)
     val p2=new Pizza(Pizza.DEFAULT_CRUST_SIZE)
     println(p2)
     val p3=new Pizza(Pizza.DEFAULT_CRUST_TYPE)
     println(p3)
     val p4=new Pizza
     println(p4)
     
     //case apply
     val a=Person3()
     val b=Person3("Pam")
     val c=Person3("W s",82)
     println(a)
     println(b)
     println(c)
     
     //set person3,use var,available change
     a.name="L N"
     a.age=82
     println(a)
     
     //default value for class
     val s=new Socket
     println(s.timeout)
     val s2=new Socket(500)
     println(s2.timeout)
     
     //class inner have class
     val p = Person4("alvinalexander", "secret")
     p.address = Some(Address("Talkeetna", "AK", "99676"))
     
     //extend a class
              
   }
}