package scalaCookBook
//flatten ,list of lists ,create one list
object ex285 {
  def main(args:Array[String]):Unit={
   val lol=List(List(1,2),List(3,4,5),List(9,74))
   println(lol)//List(List(1, 2), List(3, 4, 5), List(9, 74))
   val result=lol.flatten
   println(result)//List(1, 2, 3, 4, 5, 9, 74)
   
   //flatten,held inside the outer list into one list
   //it works Array,ArrayBuffer,Vector,etc
  
   val a=Array(Array(1),Array(3,4,5),Array(9))
   //a ,Array[Array[Int]] = Array(Array(1), Array(3, 4, 5), Array(9))
   a.flatten//Array[Int] = Array(1, 3, 4, 5, 9)
   
   //把掺假婚礼的夫妇名单，转为一个list
   val couples=List(List("kim","al"),List("julia","terry"),List("chly","izzo"))
   val people=couples.flatten
   println(people)
   //List(kim, al, julia, terry, chly, izzo)
   val people2=couples.flatten.map(_.capitalize).sorted
   println(people2)
   //List(Al, Chly, Izzo, Julia, Kim, Terry)
   
   //ex286
   //the same friends with list 
   
   val myFriends=List("a","b","c","d")
   val adamsFriends=List("a","e","f","d")
   val friendsOfFriends=List(myFriends,adamsFriends)
   val unpquefriendsOfFriends=friendsOfFriends.flatten.distinct
   println(unpquefriendsOfFriends)
   //List(a, b, c, d, e, f)
   
   //usefull in two situations
   //one,String is sequence of Char,flatten list of strings into list of characters
   val list=List("Hello","Word")
   println(list)
   //List(Hello, Word)
   val listChars=list.flatten
   println(listChars)
   //List(H, e, l, l, o, W, o, r, d)
   
   //two,Option can hava one or zero elements,flatten can drops None elements
   val x=Vector(Some(1),None,Some(4),None,None)
   println(x)
   //Vector(Some(1), None, Some(4), None, None)
   val xs=x.flatten
   println(xs)
   //Vector(1, 4)
   
   //
  }
}