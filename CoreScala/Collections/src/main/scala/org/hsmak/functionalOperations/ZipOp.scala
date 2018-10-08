package org.hsmak.functionalOperations

object ZipOp extends App {

  /**
    * Zip[: intertwine elements of two collections, and produces a collection of tuples of the same type
    */
  object Zipping {
    val a = (1 to 4).toList
    val b = (5 to 8).toList
    println(a zip b)// List((1,5), (2,6), (3,7), (4,8))

    val c = (9 to 12).toList
    println(a zip b zip c)// List(((1,5),9), ((2,6),10), ((3,7),11), ((4,8),12))

    println((1 to 5) zip (6 to 9)) // constrained by the collection of the least elements
    println((1 to 3) zip (4 to 9)) // constrained by the collection of the least elements
  }

  Zipping
  println
}
