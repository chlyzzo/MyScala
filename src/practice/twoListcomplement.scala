package practice

object twoListcomplement {
  
  def twoListcomple(xxlist:List[Any],yylist:List[Any]):List[Any]={
    
    var xlist=xxlist
    var ylist=yylist
    val looklist = new scala.collection.mutable.ListBuffer[Any]()
    val likelist = new scala.collection.mutable.ListBuffer[Any]()
    
    val size = 5
    
    while (looklist.size!=size && xlist.size!=0){
      looklist.append(xlist.head)
      xlist=xlist.drop(1)
    }
    if (looklist.size==size){
      
      while (likelist.size!=size && ylist.size!=0){
          likelist.append(ylist.head)
          ylist=ylist.drop(1)
       }
      if (likelist.size==size){
        
      }
      else if (ylist.size==0){
        while (likelist.size!=size && xlist.size!=0) {
            likelist.append(xlist.head)
            xlist=xlist.drop(1)
         }
      }
    }
    else if (xlist.size==0){
      //ylist 补
       while (looklist.size!=size && ylist.size!=0){
         looklist.append(ylist.head)
         ylist=ylist.drop(1)
       }
       while (likelist.size!=size && ylist.size!=0){
          likelist.append(ylist.head)
          ylist=ylist.drop(1)
       }
    }
    return (looklist++likelist).toList
 }
  
  def twoListcomple2(xlist:List[Any],ylist:List[Any]):List[Any]={
    
    val result = new scala.collection.mutable.ListBuffer[Any]()
    val size = 5
    result.appendAll(xlist.slice(0, size))
    result.appendAll(ylist)
    result.appendAll(xlist.slice(size, xlist.size))
    return result.toList.slice(0, size*2)
 }
  
  def main(args:Array[String]):Unit={
    val xlist=List(1,2,3,4,5,6,8,9)
    val ylist=List("c","t","y","fh","ghf","gh")

    
   println(twoListcomple(xlist,ylist))
   println(twoListcomple2(xlist,ylist))
    
    
  }//
}