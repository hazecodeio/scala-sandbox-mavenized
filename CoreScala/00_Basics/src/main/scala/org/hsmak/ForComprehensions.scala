package org.hsmak

object ForComprehensions extends App {

  /**
    * There was a talk about Monadic and Monads at this section
    *     - Monad is really a flatMap with mathematical laws
    *     - Option is an example of a Monad
    *     - links:
    *         - https://dzone.com/articles/simplifying-monads-in-scala
    *         - https://medium.com/@sinisalouc/demystifying-the-monad-in-scala-cc716bb6f534
    *         - http://appliedscala.com/blog/2016/understanding-monads/
    *
    * ForComprehensions is immutable
    */
  object MyForComprehension {

    println("------------------ yield <=> map() using Ranges ---------------")

    val result1 = for (i <- 1 to 10) yield (i + 1) //This For Comprehensions. Yield really is similar to map()
    println("yield: " + result1) //Vector(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

    val result2 = (1 to 10).map(x => x + 1)
    println("map(): " + result2) //Vector(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

    println

    println("------------------ yield <=> map() using Option ---------------")

    val result3 = for (i <- Some(100)) yield (i + 40)
    println("yield: " + result3)

    val result4 = Some(100).map(x => x + 40)
    println("map(): " + result4)

    println

    println("------------------ yield <=> map() using two Lists ---------------")

    val result5 = for (i <- List(1, 2, 3, 4);
                       j <- List(5, 6, 7, 8)) yield (i, j) //list of tuples <- similar to nested for loops
    println("yield: " + result5)

    val result6 = List(1, 2, 3, 4).map(i => List(5, 6, 7, 8).map(j => (i, j)))
    println("map().flatten: " + result6.flatten)

    val resultFaltMap6 = List(1, 2, 3, 4).flatMap(i => List(5, 6, 7, 8).map(j => (i, j)))
    println("flatMap(): " + resultFaltMap6)

    val result7 = for (i <- List(1, 2, 3, 4)
                       if i % 2 == 0; // if is part of the previous line so why the senicolon ';' is here
                       j <- List(5, 6, 7, 8)) yield (i, j) //couple even numbers from i with every number in j
    println("Another yield 1: " + result7)

    val result8 = for (i <- List(1, 2, 3, 4); //this is the end of this line so why the semicolon ';' is here
                       j <- List(5, 6, 7, 8)
                       if (j < 7)) yield (i, j)
    println("Another yield 2: " + result8)

    println


    println("---------------- map() and filter() Vs withFilter() -----------------")

    ///////using map & filter // one caveat with filter is the performance <- use withFilter instead because it's lazy call

    val result9 = (1 to 4)
      .filter(_ % 2 == 0)
      .flatMap(i => (5 to 8)
        .map(j => (i, j)))
    println("map(): " + result9)

    val result10 = (1 to 4)
      .flatMap(i => (5 to 8)
        .filter(_ < 7)
        .map(j => (i, j)))
    println("map(): " + result10)

    ////// using withFilter <- lazy evaluation
    val result11 = (1 to 4)
      .withFilter(_ % 2 == 0)
      .flatMap(i => (5 to 8)
        .map(j => (i, j))) //removing toList will return into vector
    println("map(): " + result11)

    val result12 = (1 to 4)
      .flatMap(i => (5 to 8)
        .withFilter(_ < 7)
        .map(j => (i, j)))
    println("map(): " + result12)
  }

  MyForComprehension
  println
}
