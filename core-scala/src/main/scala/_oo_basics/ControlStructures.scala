package _oo_basics

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
      else if (a > 10) "a is grerater than 10"
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

    // For loops accept iterative construct such as: Range, List, etc
    for (i <- 1 to 10) { // via Range
      println(i)
    }

    val xs = List(1, 2, 3, 4, 5)

    //imperative way 01

    val result = for (i <- xs; // notice the semicolon ';' because we are using parens (). no need for ';' if curly braces {} are used
                      j = i * 2) yield (j)
    println(s"result: $result")


    var resultL = List[Int]()
    for (a <- xs) {
      resultL = resultL :+ (a + 1) //append to list. note List must be var for the reassignment to work
    }
    println(resultL)


    // The Functional Way??? <- I don't think this is.
    // I'd avoid 'var' and use FunctionalOps such as map, fold, reduce, etc
    /*
     * val appendedList = xs.foldLeft(List[Int]())((acc, nxt) => acc:+nxt*2)
     * val prependedList = xs.foldLeft(List[Int]())((acc, nxt) => nxt*2+:acc)
     *
     * the same can be done using foldRight().
     */

    var prependedList = List[Int]()
    var appendedList = List[Int]()
    for (i <- xs) {
      appendedList = appendedList :+ (i * 2) //append to list. note List must be var for append to work
      prependedList = (i * 2) +: prependedList // ':' the colon is right associative operator
    }
    println(s"appendedList: $appendedList")
    println(s"prependedList: $prependedList")

    // I'd avoid 'var' and use FunctionalOps such as map, fold, reduce, etc
    /*
     * val appendedList = xs.foldLeft(List[Int]())((acc, nxt) => acc:+nxt*2)
     * val prependedList = xs.foldLeft(List[Int]())((acc, nxt) => nxt*2+:acc)
     *
     * the same can be done using foldRight().
     */

     val appendedList2 = xs.foldLeft(List[Int]())((acc, nxt) => acc:+nxt*2)
     val prependedList2 = xs.foldLeft(List[Int]())((acc, nxt) => nxt*2+:acc)

  }

  ForLoop
  println

  println("--------------------- nested loop --------------------")

  object NestedLoop {
    /*
    equivalent to nested loop
    useful in multi dimensional matrices
   */

    // notice the semicolons ";"
    for (i <- 0 to 2;
         j <- 0 to 3;
         k <- 0 to 4)
      println(s"($i, $j, $k)")

  }

  NestedLoop
  println()

  println("----------------- Looping the Functional Way---------------------")

  object FunctionalLooping {
    val result = (1 to 10).reverse.mkString(",")
    println(result)

    val steppingBy2 = (1 to 10 by 2).mkString(", ")
    println(steppingBy2)

    val result2 = (10 to 1 by -1).mkString(",")
    println(result2)

    val reverse2 = (1 to 10).reverse.by(-2).mkString(",")
    println(reverse2)
  }

  FunctionalLooping
  println

}
