package scalaCookBook
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

//message, string is well
//must implement receive,must have _,others condition,
//class HelloActor extends Actor {
//  
//    def receive = {
//       case "hello" => println("hello back at you")
//       case _       => println("huh?")
//    }
//}

//take a parameter
class HelloActor(myName:String)extends Actor{
  def receive = {
    case "hello" => println(s"hello from $myName")
    case _       => println(s"'huh?',said $myName")
  }
}

object ex13_1acctorStart {
  
  def main(args:Array[String]):Unit={
    // an actor needs an ActorSystem
   val system = ActorSystem("HelloSystem")
    // create and start the actor,do not use start method,
   //val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
   val helloActor = system.actorOf(Props(new HelloActor("Fred")), name = "helloactor")
   // send the actor two messages
    helloActor ! "hello"
    
    helloActor ! "buenos dias"
    // shut down the system
    system.shutdown
  }
  
}