package practice
//scala 获取时间
import java.text.SimpleDateFormat

import java.util.Date

import org.apache.spark._
object scalaTime {
  def main(args:Array[String]):Unit={
    
    var now:Date = new Date() //日期格式
    println(now)
   var now2=System.currentTimeMillis //毫秒
    println(now2)
  }
}