package practice

object urlExtract {
  
  def extractPcUrl():Unit={
    //pc解析出文章id
    // 根据url的形式，分为三类，
    //1,
    //pageid 388 
    //url http://cp.fang.anjuke.com/louping/16-07-05/349472-pan251435.html?from=leading_list
    //pageid 884 
    //url http://cp.fang.anjuke.com/louping/16-07-05/349937-pan251435.html?from=leading_list
   //2,
    //pageid 761
    //url http://sh.fang.anjuke.com/news/16-07-05/349871.html?from=leading_list    
    
  //3,
    //pageid 2622 
    //url http://sh.fang.anjuke.com/loupan/pingce-230223-788.html?from=leading_list

  //1和2,解析出文章id，即url中的16-07-05/349472-pan251435.html，349472
    
    //val url="http://cp.fang.anjuke.com/louping/16-07-05/349472-pan251435.html?from=leading_list"
    val url="http://sh.fang.anjuke.com/loupan/pingce-230223-788.html?from=leading_list"
    val urlPatern = "(\\d{2}-\\d{2}-\\d{2}/\\d+)".r//时间格式的后面紧接着的纯数字。。。即是文章article_id，
    //得到的是16-07-05/349472，然后再进行截取即可得到id，
    val urlPatern2 = "-(\\d+)\\.".r
    urlPatern.findFirstMatchIn(url) match {
         case Some(o) => println(o.group(1).substring(9))              
         case _ => 
           urlPatern2.findFirstMatchIn(url) match{
               case Some(o) => println(o.group(1))          
               case _ => println("none")
           }  
       }  
  }//end pc 
  
    def extractTwUrl():Unit={
      //tw 解析，拿到table_type和文章id
    /*
    http://m.anjuke.com/sh/daogou/view/5-58413/?from=xf_zixun_tw_news_rd  table_type=5
    http://m.anjuke.com/sh/daogou/view/1-355878/?from=xf_zixun_view_aboutnew table_type=1
    http://m.anjuke.com/sh/daogou/view/2-357912/?from=xf_zixun_guide_click   table_type=2
    http://m.anjuke.com/sh/daogou/specialztview/5200/?from=xf_zixun_tw_special_zt  table_type=2 暂时先别用
    http://m.anjuke.com/hf/loupan/406106/pingce-1147/?from=app_index_detai    table_type=3
    */
    val url ="http://m.anjuke.com/sh/daogou/view/2-357912/?from=xf_zixun_guide_click"
    val urlPatern1 = "/(\\d+-\\d+)/".r
    val urlPatern2 = "(\\d+/[?])".r
    val typeAndarticle= urlPatern1.findFirstMatchIn(url) match {
         case Some(o) => val typeItem=o.group(1).split("-")(0).toInt
                     val article_id=o.group(1).split("-")(1).toInt
                     Some(typeItem,article_id)
         case _ => urlPatern2.findFirstMatchIn(url) match{
               case Some(a) =>  
                     val article_id = a.group(1).split("/")(0).toInt
                     val typeItem = if (url.contains("-")) 3 else 2
                     Some(typeItem,article_id)
               case _ => None
           }  
       }              
    println(typeAndarticle)
  }
    
  //获取url后面的参数，也就是?之后的连接参数
  def getParams(url: String): Map[String, String] = {
    val index = url.indexOf("?")
    if (index == -1) {
      return Map.empty
    }

    url.substring(index + 1).split("&").filter(_.nonEmpty).flatMap { kv =>
      val segs = kv.split("=").filter(_.nonEmpty)
      if (segs.length > 1) {
        Some(segs(0), segs(1))
      } else {
        None
      }
    }.toMap
  }
  
  def main(args:Array[String]):Unit={
     //extractTwUrl()
     extractTwUrl()

  }//end main
  
  
}//end object