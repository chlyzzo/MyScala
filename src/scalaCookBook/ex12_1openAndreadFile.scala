package scalaCookBook

import scala.io.Source

object ex12_1openAndreadFile {
  
  def main(args:Array[String]):Unit={
    val filename = "/home/min/workspace/MyScala/src/scalaCookBook/ex12_1openAndreadFile.scala"
    //val filename = "ex12_1openAndreadFile.scala"
    for (line<- Source.fromFile(filename).getLines())
    {
      println(line)
    }
  }
  
}