package org.hsmak

/**
  * Notice how it's done the functional way
  * In Scala:
  *   - If statements are called expressions not statements; same for "For". "While" is an exception to the rule, it's for imperative style
  *   - Control Structures return values
  *
  */
object ControlStructures extends App {

  println("----------------- Condition ---------------------")

  //the functional way

  object IfElse {

    val a = 10
    val result = //notice the val to avoid the mutable reassignment i.e. var
      if (a < 10) "a is less than 10"
      else if (a < 10) "a is grerater than 10"
      else "a is 10"

    println(result)
  }

  IfElse
  println

  println("----------------- Looping - While ---------------------")

  object LoopingWhile {
    var a = 10
    while (a > 0) {
      // there are no increment/decrement operators in Scala. b-- --b won't work
      a = a - 1
      println(a)
    }
  }

  LoopingWhile
  println

  println("----------------- Looping Do/While---------------------")

  object DoWhile {
    var a = 0
    do {
      println(a)
      a = a + 1
    } while (a < 10)
  }

  DoWhile
  println

  println("----------------- Looping - For---------------------")

  object ForLoop {

    for (i <- 1 to 10) {
      // Ranges can only be done with For loops
      println(i)
    }

    val xs = List(1, 2, 3, 4, 5)

    //imperative way

    var prependedList = List[Int]()
    var appendedList = List[Int]()
    for (i <- xs) {
      appendedList = appendedList :+ (i * 2) //append to list. note List must be var for append to work
      prependedList = (i * 2) +: prependedList // ':' the colon is right associative operator
    }
    println(appendedList)
    println(prependedList)

    // The Functional Way

    val result = for (i <- xs;
                      j = i * 2) yield (j)
    println(result)

    var resultL = List[Int]()
    for (a <- xs) {
      resultL = resultL :+ (a + 1) //append to list. note List must be var for append to work
    }
    println(resultL)
  }

  ForLoop
  println

  println("--------------------- nested loop --------------------")

  object NestedLoop {
    /*
    equivalent to nested loop
    useful in multi dimentional matrices
   */
    for (i <- 1 to 5; j <- 6 to 10) {
      println("i: " + i)
      println("j: " + j)
    }
  }

  NestedLoop
  println()

  println("----------------- Looping the Functional Way---------------------")

  object FunctionalLooping {
    val result = (1 to 100).reverse.mkString(",")
    println(result)

    val steppingBy2 = (1 to 10 by 2).mkString(", ")
    println(steppingBy2)

    val result2 = (100 to 1 by -1).mkString(",")
    println(result2)
  }

  FunctionalLooping
  println

}
