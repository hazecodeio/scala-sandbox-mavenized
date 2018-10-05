package org.hsmak

//################################### Right Associative Colons ###################################

object RightAssociativeColon extends App {

  val foo = new Foo(10)

  class Foo(x: Int) {
    def ~:(y: Int) = x + y
  }

  println(foo.~:(5))
  println(5 ~: foo) //colon is right associative

  println("----------------------- ':' in Collections --------------------")

  println(1 :: 2 :: 3 :: Nil) //prepend
  println(1 +: 2 +: 3 +: Nil) //prepend
  println(Nil :+ 1 :+ 2 :+ 3) //append


  println("----------------------- ':' in Nonempty Collections --------------------")

  val ls = List(1,2)

  println(11 :: 21 :: 31 :: ls) //prepend
  println(11 +: 21 +: 31 +: ls) //prepend
  println(ls :+ 11 :+ 21 :+ 31) //append

}
