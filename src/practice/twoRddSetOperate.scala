package practice

import org.apache.spark._
/*
 * two rdd operates,
 * 交,并,差,5
 * */
object twoRddSetOperate {
  def main (args:Array[String]):Unit={
    val conf = new SparkConf().setAppName("rdd operate").setMaster("local");          // create a spark config object
    val sc = new SparkContext(conf) 
    val rdd1=sc.makeRDD(1 to 3,2)
    println("rdd1")
    rdd1.foreach { f => print(f+",") }
    println("")
    val rdd2=sc.makeRDD(2 to 4,3)
     println("rdd2")
    rdd2.foreach { f => print(f+",") }
    println("")
    
    //1,
    val rdd3=rdd1.union(rdd2)
    println("union")
    rdd3.foreach { f => print(f+",") }
    println("")
    
      //2,
    val rdd4=rdd1.intersection(rdd2)
    println("intersection")
    rdd4.foreach { f => print(f+",") }
    println("")
    
      //3,
    val rdd5=rdd1.subtract(rdd2)
    println("subtract")
    rdd5.foreach { f => print(f+",") }
    println("")
    
    //mapPartitions,与map类似，但是，分区公用一个对象显得更高效。
    //mapPartitions，就是对每个分区rdd共享，提高性能
    println("*******mapPartitions*******")
    val rdd1MapPar=sc.makeRDD(1 to 30,1)
    val mapRDD=rdd1MapPar.map(x=>(x,x))
    val groupRDD=mapRDD.groupByKey(3)
    val mapPartRDD=groupRDD.mapPartitions(iter=>iter.filter(_._1>3))
    mapPartRDD.foreach { f => print(f+",") }
    println("")
    
    //zip,zipPartitions
    //zip,组合成key-value形式，两个rdd的分区数量和元素数量相同
    //zipPartitions，rdd的相同分区数，每个分区的元素数量没有要求相同
    println("*******zip and zipPartitions*******")
    val rdd=sc.makeRDD(1 to 5,2)
    val mapRdd=rdd.map{x=>(x+1.0)}
    val zipRdd=rdd.zip(mapRdd)
    println("zip")
    zipRdd.foreach { f => print(f+",") }
    println("")
    val rddz=sc.makeRDD(Array("1","2","3","4","5","6"),2)
    val zipParRdd=rdd.zipPartitions(rddz)(
        (i:Iterator[Int],s:Iterator[String]) =>
                  {Iterator(i.toArray.size,s.toArray.size)}
      )
    println("zipPartitions")
    zipParRdd.foreach { f => print(f+",") }
    println("")
  }
}