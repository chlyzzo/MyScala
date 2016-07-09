package practice

import org.apache.spark._

object operateExces {
  
  case class PE(userId: String, userType: String)
   case class PP(name: String, ege: String)
      
  
  def main(args:Array[String]):Unit={
//    val pe=PE("a","b")
//    val pp= PP("c","12")
//    print(Some(pe -> pp))

    //解析出文章id
    val url="http://sh.fang.anjuke.com/news/16-07-01/348529.html?from=leading_list"
    val urlPatern = "(\\d+)\\.".r//纯数字并以.结尾。。。即是文章article_id，
    urlPatern.findFirstMatchIn(url) match{
         case Some(o) => println(o.group(1))              
         case _ => println("none")
       }  
    println (Set("pro_id", "pro_type", "sim_score", "city_id"))
    //
    println(212.0/60)
  }
}