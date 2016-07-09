package scalaCookBook

import akka.actor._


case object PingMessage
case object PongMessage
case object StartMessage
case object StopMessage

class Ping(pong: ActorRef) extends Actor {
     var count = 0
     def incrementAndPrint { count += 1; println("ping") }
     def receive = 
     {
           case StartMessage =>
              incrementAndPrint
              pong ! PingMessage
           case PongMessage =>
               incrementAndPrint
               if (count > 9) 
               {
                     sender ! StopMessage
                     println("ping stopped")
                     context.stop(self)
               } else 
               {
                      sender ! PingMessage
               }
            case _ => println("Ping got something unexpected.")
     }
}

class Pong extends Actor 
{
    def receive = 
    {
       case PingMessage =>
            println(" pong")
            sender ! PongMessage
       case StopMessage =>
             println("pong stopped")
             context.stop(self)
       case _ => println("Pong got something unexpected.")
    }
}

object ex13_3communicateActors {
  
  def main(args:Array[String]):Unit={
    // an actor needs an ActorSystem
   val system = ActorSystem("PingPongSystem")
    // create and start the actor,do not use start method,
   val pong =system.actorOf(Props[Pong],name="pong")
   val ping=system.actorOf(Props(new Ping(pong)),name="ping")
   
   // send the actor two messages
    ping! StartMessage
  }
}


