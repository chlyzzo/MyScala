package practice
/*
 * 一个List[Map[String,Any]]
 * 根据Map中的某个属性,进行分组,并排序,取前n个
 * 
 * 
 * Map("article_id"->a.getOrElse("article_id","0"),
            "table_type"->a.getOrElse("table_type","0"),
            "city_id"->a.getOrElse("city_id","0"),
           "sim_score"->RecommendUtils.roundScore(x._2,4))
 * */
object ex914ListGetDefine {
  
  def main(args:Array[String]):Unit={
    var source = List.empty[(String,Map[String,Any])]
    val map1 = Map(
               "article_id"->46,
               "table_type"->3,
               "city_id"->12,
               "sim_score"->0.36
              )
     val map2 = Map(
               "article_id"->89,
               "table_type"->1,
               "city_id"->12,
               "sim_score"->0.16
              )
     val map3 = Map(
               "article_id"->36,
               "table_type"->3,
               "city_id"->12,
               "sim_score"->0.76
              )
      val map4 = Map(
               "article_id"->63,
               "table_type"->1,
               "city_id"->12,
               "sim_score"->0.06
              )
      val map5 = Map(
               "article_id"->78,
               "table_type"->3,
               "city_id"->12,
               "sim_score"->0.45
              )
       val map6 = Map(
               "article_id"->785,
               "table_type"->1,
               "city_id"->12,
               "sim_score"->0.96
              )
      val map7 = Map(
               "article_id"->413,
               "table_type"->3,
               "city_id"->12,
               "sim_score"->0.20
              )
      source = List((map1.get("table_type").get.toString(),map1),
          (map2.get("table_type").get.toString(),map2),
         (map3.get("table_type").get.toString(),map3),
          (map4.get("table_type").get.toString(),map4),
      (map5.get("table_type").get.toString(),map5),
       (map6.get("table_type").get.toString(),map6),
       (map7.get("table_type").get.toString(),map7)
          )
      val fromZx_id = 12
      val fromZx_type = "2"
      source.groupBy(_._1).foreach { x => // (String, List[(String, Map[String, Any])])
           val seq=x._2.sortWith{ (x,y)=>
                 val score1= x._2.get("sim_score").get.toString().toFloat
                 val score2= y._2.get("sim_score").get.toString().toFloat
                 score1 > score2
        }.slice(0,6)
        .map{x=>(x._2.get("article_id").get.toString(),x._2.get("sim_score").get.toString())}
        println(fromZx_id,fromZx_type,x._1)
        println(seq)

    }
  }
}