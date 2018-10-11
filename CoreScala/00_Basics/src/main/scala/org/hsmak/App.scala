package org.hsmak

/**
  * @author ${user.name}
  */
object App {

  def main(args: Array[String]) {
    println("Hello World!")
    println("concat arguments = " + foo(args))
    println(MyJava.callJavaMethod())



    val f = null
    val ff: Null = null // null is a subtype of

    List(1, 2) :+ 3 //append and create new list
    1 +: List(2, 3) //prepend and create new list
    1 +: List(2, 3) :+ 4


  }

  def foo(x: Array[String]) = x.foldLeft("")((a, b) => a + b)
}

