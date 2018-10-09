package org.hsmak.mutable

import scala.collection._

object ArrayBuffers extends App {

  /**
    * Unlike other collections, Arrays are:
    *   - WARNING: Mutable by default.... !
    *   - converted into primitive Arrays of [] by the JVM under the hood
    */

  object MyArrayBuffer {
    val ab: mutable.ArrayBuffer[Int] = mutable.ArrayBuffer(1, 2, 3)

    println("------------- Mutating Operation by adding/removing elements --------------")

    ab += 200 // Mutate the ArrayBuffer. This can't be done for the immutable Array
    println("a += 200 : " + ab)

    ab.append(34)
    ab.prepend(-34)
    println("append()/prepend(): " + ab)

    ab -= 1 //Remove an element from ArrayBuffer if exists
    println("a -= 1 : " + ab)
    println

    println("------------- Mutating by adding/removing other collections --------------")
    ab ++= List(11, 22)
    println("appending list             : " + ab)

    ab ++= Set(33, 44, 33)
    println("appending set              : " + ab)

    ab --= List(11, 22)
    println("removing list of elements  : " + ab)

    ab --= Set(33, 44, 33)
    println("removing set of elements   : " + ab)

    println

    println("---------------- ArrayBuffers Operations and the Colon ':' ------------")

    123 +: ab
    println("unchanged by ':'         : " + ab)
    println("creates a new ArrayBuffer: " + (123 +: ab))

    ab :+ 321
    println("unchanged by ':'         : " + ab)
    println("creates a new ArrayBuffer: " + (ab :+ 321))

    println


    println("---------------- ArrayBuffers Operations (Same as Array) ------------")
    //same list operations can be done on arrays
    println("head       : " + ab.head)
    println("tail       : " + ab.tail)
    println("a.init     : " + ab.init)
    println("a.last     : " + ab.last)
    println("a.max      : " + ab.max)
    println("a.min      : " + ab.min)
    println("a(2)       : " + ab(2))
    println("a.isEmpty  : " + ab.isEmpty)
    println("a.nonEmpty : " + ab.nonEmpty)

    println


  }

  MyArrayBuffer
  println

}
