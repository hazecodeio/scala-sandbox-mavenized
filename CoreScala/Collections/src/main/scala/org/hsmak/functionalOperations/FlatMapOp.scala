package org.hsmak.functionalOperations

/**
  * x => x this is the identity function
  */
object FlatMapOp extends App {

  object MyFlatMapOp {

    println("----------------- Intro ----------------")
    println(List(1, 2, 3).flatMap(x => List(x)))// same as below. List[List] is happening in the lambda body
    println(List(List(1), List(2), List(3)).flatMap(x => x)) //same as above. List[List] is already being passed from the original List
    println

    val l = List(1, 2, 3, 4, 5)

    println("------------FlatMap on List[List] ----------------")

    println(l.map(x => List(-x, 0, x))) // produce List of Lists
    println("flatten(): " + l.map(x => List(-x, 0, x)).flatten) // flattened list without performing a function on it
    println("flatMap(): " + l.flatMap(x => List(-x, 0, x))) //flatMap = Map + Flatten <- Perform a function/operation then Flatten
    println

    println("------------FlatMap on List[List[List]] ----------------")

    //notice the explicit type for illustration
    val b: List[List[List[Int]]] =
      List(
        List(
          List(1, 2, 3),
          List(4, 5, 6),
          List(7, 8, 9),
          List(10, 11, 12)))

    println(b)
    println("flatMap():                                " + b.flatMap(c => c)) // one list is reduced
    println("flatMap().flatMap():                      " + b.flatMap(c => c).flatMap(c => c)) // one more list is reduced so now we only have one list containing all elements

    //  println(b.flatMap(c => c).flatMap(c => c).flatMap(c => c))// if we add one more flatMap call by mistake it won't work because lambda expression in flatMap is expecting 'f: (A) => GenTraversableOnce[B}'
    println("flatMap().flatMap().flatMap(List(-c, c)): " + b.flatMap(c => c).flatMap(c => c).flatMap(c => List(-c, c))) //extract, modify, add to list, flatten again to a general list
    println

    /* Alternative to the previous - Since (c => c) is simply returning itself and not performing any kind of operation, we can replace it by either of the following:
     *    - flatMap(identity _)
     *    - flatten
     *
     * The Identity Function:
     *    - The identity function is take the input and make it the output
     *    - Instead of writing x ⇒ x, you can opt for identity(x)
     *    - identity comes from the Predef
     */


    println("------------FlatMap on Set[Set] ----------------")

    val s = Set(2, 4, 10, 11)
    println(s.flatMap(x => Set(x, x * 5))) // same thing. Couple the modification using Sets so you will have a Set of Sets then use flatMap to flatten them out
    println

    println("------------FlatMap on List[Map[Tuple2]] ----------------")

    val m = Map(1 -> "One", 2 -> "Two", 3 -> "Three", 4 -> "Four")

    //Re-adding Key-Value pairs and their Hundreds
    //this will generate List[Map]
    println(m.map(t => Map(
      t._1 -> t._2, //Key-Value
      (t._1 * 100) -> (t._2 + " Hundred")))) //Key-Value

    println(m.flatMap(t => Map(
      t._1 -> t._2, //Key-Value
      (t._1 * 100) -> (t._2 + " Hundred")))) //Key-Value

    println

    println("------------FlatMap on Option[Option] ----------------")

    println(Some(4).map(x => x + 19))
    println(Some(4).map(x => Some(x + 19)))
    println(Some(4).flatMap(x => Some(x + 19)))

    //    println(None.flatMap(x => Some(x + 19)))// Compile error for None returns nothing
    println(None.asInstanceOf[Option[Int]].flatMap(x => Some(x + 19))) //Return None
    println

    println("------------FlatMap on List[Option] ----------------")
    println(List(Some(4), None, None, Some(2), None, Some(55)).flatMap(x => x)) // result: List(4, 2, 55) <- Somes were stripped of and Nones disappeared!
    println

    println("------------FlatMap on List[Set] ----------------")

    println(List(Set(1, 2, 3), Set(3, 4, 5)).flatMap(x => x)) // List(1, 2, 3, 3, 4, 5)
    println(List(Set(1, 2, 3), Set(3, 4, 5)).flatten) // same as previous line
    println
  }

  MyFlatMapOp
  println

}
