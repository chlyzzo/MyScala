package scala
import java.io.FileWriter
import java.io.File
import scala.util.Random
object ex87 {
  def main(args:Array[String]):Unit={
//    var argss=Array("1","2","3")
//    printArgs1(argss)
//    printArgs2(argss)
//    printArgs3(argss)
//    println(printArgs4(argss))
     val writer = new FileWriter(new File("d:\\tr.txt"),false)
     val rand = new Random()
     for ( i <- 1 to 10000000) {
          writer.write( i + " " + rand.nextInt(100))
          writer.write(System.getProperty("line.separator"))
      }
      println("4")
      writer.flush()
      writer.close()
  }
  //不同的输出格式
  def printArgs1(args:Array[String]):Unit={
    var i=0
    while(i<args.length){
      println(args(i))
      i+=1
    }
  }
  
  def printArgs2(args:Array[String]):Unit={
      for(arg<-args)
         println(arg)
  }
  
  def printArgs3(args:Array[String]):Unit={
      args.foreach(println)
  }
   
  def printArgs4(args:Array[String])=args.mkString("\n")//以/n连接串
  
  
}