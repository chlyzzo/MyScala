package scala
import java.io.FileWriter
import java.io.File
import scala.util.Random


object tr {
  def main(args:Array[String]):Unit={
      println("4")
      val writer = new FileWriter(new File("d:\\tr.txt"),false)
      val rand = new Random()
      for ( i <- 1 to 10) {
          writer.write( i + " " + rand.nextInt(100))
          writer.write(System.getProperty("line.separator"))
       }
       println("3")
       writer.flush()
       writer.close()
       println("5")
    
   }
}