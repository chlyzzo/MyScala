package practice

import org.apache.spark._
/*
 * reduceByKey    
 * aggregateByKey  排序的话，更具有优势
 * 都是对<k,v>操作，把相同k的v进行计算，
 * 
 * */
object ex712aggregateByKey {
  
  def main(args:Array[String]):Unit={
    
     val conf = new SparkConf().setAppName("matrix").setMaster("local")         
     val sc = new SparkContext(conf) 
     val pairs = sc.parallelize(Array(("a", 3), ("a", 1), ("b", 7), ("a", 5)))

     val resReduce = pairs.reduceByKey(_ + _) //the same operation for everything
     resReduce.foreach(println) // (a,9) (b,7)

      //0 is initial value, _+_ inside partition, _+_ between partitions
     val resAgg = pairs.aggregateByKey(0)(_+_,_+_)
     resAgg.foreach(println) //(a,9) (b,7)

     import scala.collection.mutable.HashSet
    //the initial value is a void Set. Adding an element to a set is the first  _+_ 
    //Join two sets is the  _++_
     val sets = pairs.aggregateByKey(new HashSet[Int])(_+_, _++_)
     sets.foreach(println)//(a,Set(1, 5, 3)) (b,Set(7))
    
  }
}