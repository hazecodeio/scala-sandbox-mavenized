


//################################### Collections ###################################

////////////////// List



////////////////// Set



////////////////// Map



//######################################## Arrays and Repeated params ################################



//######################################## Ranges ################################



//######################################## Functional Operations <- Streams in Java ################################






//######################################## For Comprehensions using yield ################################

/**
  * There was a talk about Monadic and Monads at this section
  * For Compr is immutable
  */
object MyForComprehension {
  val result1 = for (i <- 1 to 10) yield (i + 1) //This For Comprehensions. Yield really is similar to map()
  println(result1) //Vector(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

  val result2 = (1 to 10).map(x => x + 1)
  println(result2) //Vector(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

  val result3 = for (i <- Some(100)) yield (i + 40)
  println(result3)

  val result4 = Some(100).map(x => x + 40)
  println(result4)

  val result5 = for (i <- List(1, 2, 3, 4);
                     j <- List(5, 6, 7, 8)) yield (i, j) //list of tuples <- similar to nested for loops
  println(result5)

  val result6 = List(1, 2, 3, 4).map(i => List(5, 6, 7, 8).map(j => (i, j)))
  println(result6.flatten)

  val resultFaltMap6 = List(1, 2, 3, 4).flatMap(i => List(5, 6, 7, 8).map(j => (i, j)))
  println(resultFaltMap6)

  val result7 = for (i <- List(1, 2, 3, 4) if i % 2 == 0; j <- List(5, 6, 7, 8)) yield (i, j) //couple even numbers from i with every number in j
  println(result7)

  val result8 = for (i <- List(1, 2, 3, 4); j <- List(5, 6, 7, 8) if (j < 7)) yield (i, j)
  println(result8)

  ///////using map & filter // one caveat with filter is the performance <- use withFilter instead because it's lazy call

  val result9 = (1 to 4)
    .toList.filter(_ % 2 == 0)
    .flatMap(i => (5 to 8)
      .toList
      .map(j => (i, j))) //removing toList will return into vector
  println(result9)

  val result10 = (1 to 4)
    .toList
    .flatMap(i => (5 to 8)
      .filter(_ < 7)
      .map(j => (i, j)))
  println(result10)

  ////// using withFilter <- lazy evaluation
  val result11 = (1 to 4)
    .toList
    .withFilter(_ % 2 == 0)
    .flatMap(i => (5 to 8)
      .toList.map(j => (i, j))) //removing toList will return into vector
  println(result11)

  val result12 = (1 to 4)
    .toList
    .flatMap(i => (5 to 8)
      .withFilter(_ < 7)
      .map(j => (i, j)))
  println(result12)
}

MyForComprehension
println


