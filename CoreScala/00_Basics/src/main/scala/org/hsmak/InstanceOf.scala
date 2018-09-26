package org.hsmak

/**
  * isInstanceOf and asInstanceOf are methods in class Any,
  * i.e. all references/objects and primitives can use these two methods
  *
  */
object InstanceOf extends App{


  //IsInstanceOf is equivalent to Java's instanceof
  println(2.isInstanceOf[Int])//true
  println(2.isInstanceOf[Boolean])//false
  println(2.isInstanceOf[Any])//true

  println("Hello".isInstanceOf[String])//true
  println("Hello".isInstanceOf[CharSequence])//true

  //AsinstanceOf is to down cast
  val ss: Any = "ha ha ha" // of type Any
//  val g:String = ss;// compile error. It must be downcasted
  val casted: String = ss.asInstanceOf[String]// downcasting to String
  println(casted)


  def decide(x:Any) = if(x.isInstanceOf[Int]) x.asInstanceOf[Int] + 1 else -1
  println(decide(2))
  println(decide("hello"))


}
