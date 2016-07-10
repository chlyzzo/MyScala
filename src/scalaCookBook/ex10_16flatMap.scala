package scalaCookBook

//flatMap run map followed by flatten,每个元素进行一对多转换
//situation
//1,use map (or for/yield) create new collection from an collection
//2,the result collection is list of lists
//3,can flatten immediately after map (for/yield)
object ex10_16flatMap {
  
  def toInt(in:String):Option[Int]={
    try{
      Some(Integer.parseInt(in.trim))
    }catch{
      case e:Exception => None
    }
  }
  
  def subWords(word:String)=List(word,word.tail,word.take(word.length-1))
  
  def main(args:Array[String]):Unit={
    val bag=List("1","2","three","4","one hundred seventy file")
    //把List中的数字进行累加，然后是string串，并且有些不是数字
    //需要对每个元素进行类型转换，不是数字和是数字的区分，
    println(bag.flatMap(toInt).sum)
    
    //工作的原理
    val step1=bag.map(toInt)//返回的是collection,Some and None
    println(step1)
    //List(Some(1), Some(2), None, Some(4), None)
    val step2=bag.map(toInt).flatten//返回的是collection,去除None
    //flatten works well Some and None
    println(step2)
    //List(1,2,4)
    val step3=bag.map(toInt).flatten.sum//求和
    println(step3)
    //7
    
    //3步就是flatMap，，其实就是map flat
    
    //others operate
    val r1=bag.flatMap(toInt).filter(_>1)
    println(r1)//List(2, 4)
    val r2=bag.flatMap(toInt).filter(_<4)
    println(r2)//List(1, 2)
    val r3=bag.flatMap(toInt).partition(_>3)
    println(r3)//(List(4),List(1, 2))
    
    //subWords
    
    val tr=subWords("then")
    
    val words=List("band","start","then")
    println(words)
    //List(band, start, then)
    val wdn=words.map(subWords)
    println(wdn)
    //List(List(band, and, ban), List(start, tart, star), List(then, hen, the))
    val allwd=words.map(subWords).flatten
    println(allwd)
    //List(band, and, ban, start, tart, star, then, hen, the)
    val allwdfm=words.flatMap(subWords)
    println(allwdfm)
    //List(band, and, ban, start, tart, star, then, hen, the)
    
    
  }
}