package org.hsmak.mutable

import scala.collection._

object ListBuffers extends App {

  object MyListBuffer {

    // using the CompanionObject
    val l1 = mutable.ListBuffer(1, 2, 3, 4)


    // using the CompanionObject.apply()
    val l2 = mutable.ListBuffer.apply(1, 2, 3, 4)

    // '::' is right associative operator
    val lColon = 1 :: 2 :: 3 :: 4 :: Nil
    println(s"lColon: ${lColon}")

    val lColon2 = Nil.::(4).::(3).::(2).::(1)
    println(s"lColon2: ${lColon2}")

    println(s"head: ${l1.head}")
    println(s"tail: ${l1.tail}")
    println(s"init: ${l1.init}")
    println(s"last: ${l1.last}")

    println

    println(l1.max)
    println(l1.min)
    println(l1.isEmpty)
    println(l1.nonEmpty)

    println(l1(3))// apply() is what is being called
    println(l1.apply(3))

    println(l1.updated(0, 32))

    println(l1.mkString(","))
    println(l1.mkString("**"))
    println(l1.mkString("[", "**", "]")) // bound the list by []

    println("------------- Combining ListBuffers using '++' ---------")

    println(l1 ++ l2)

  }

  MyListBuffer
  println
}
