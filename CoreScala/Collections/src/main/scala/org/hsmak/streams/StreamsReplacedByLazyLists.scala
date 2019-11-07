package org.hsmak.streams

/**
  * - lazy collections
  * - DELAYED EVALUATION: Avoid computing the tail of a sequence until it is needed for the evaluation result (which might be never)
  * - similar to Java8's Stream:
  *     - Intermediate Operation
  *     - Terminal operation
  *
  *  @deprecated("Use LazyList instead of Stream", "2.13.0")
  *  Streams
  */
object StreamsReplacedByLazyLists extends App {

  println("without Streams:")
  //If we only need the first 3 items then why the iteration till the last element
  val withoutStreams: List[Unit] = (1 to 5).toList.map(_ => println("Hello")).take(3) // notice the type
  withoutStreams.foreach(println) // map, take, and the rest are all terminal operations

  //  (1 to 5).map(_ => ()).foreach(println)

  println("with Streams:")

  // @deprecated("Use LazyList instead of Stream", "2.13.0")
  // val withStreams: Stream[Unit] = (1 to 5).toList.toStream.map(_ => println("Hello")).take(3) // notice the type

  val withLazyList: LazyList[Unit] = (1 to 5).to(LazyList).map(_ => println("Hello")).take(3) // notice the type
  withLazyList.foreach(println) // foreach is a terminal operation while map, take and some others are intermediate operations

  println("Using Stream CompanionObject")
//  Stream(1, 2, 3, 4, 5).map(_ * 2).take(3).foreach(println)
  LazyList(1, 2, 3, 4, 5).map(_ * 2).take(3).foreach(println)
  println
//  Stream.apply(1, 2, 3, 4, 5).map(_ * 2).take(3).foreach(println)
  LazyList.apply(1, 2, 3, 4, 5).map(_ * 2).take(3).foreach(println)
  println

  println("Continually")
//  Stream.continually(5).take(3).foreach(println)
  LazyList.continually(5).take(3).foreach(println)
  println

//  Stream.continually(6).take(20).foreach(println)
  LazyList.continually(6).take(20).foreach(println)
  println

  println("Stream concat:")
  LazyList.concat(List(1, 2, 3, 4), List(1, 2, 3, 4, 5, 6), List(8, 9)).take(5).foreach(println)
  println

  LazyList.concat((1 to 5), (4 to 8)).foreach(println)
  println

  LazyList.concat((1 to 5)).foreach(println)
  println

  println("Stream Ranges:")
  LazyList.range(1, 100, 3).take(10).foreach(println)
  println

  println(LazyList.range(1, 100, 3).length)
  println

  println("Using #::")
  val stream = 0 #:: 1 #:: 2 #:: 3 #:: 4 #:: LazyList.empty[Int]
  println(stream.filter(_%2 == 0).toList)
  println

  println("------------- StreamsUnderTheHood -----------")

  /**
    * DEFINING STREAMS
    * Streams are defined from a constant Stream.empty and a constructor Stream.cons.
    */
  object StreamsUnderTheHood {

    /**
      * @see [[scala.collection.Iterator.toStream]]
      *
      *      {{{
      *       def toStream: Stream[A] =
      *          if (self.hasNext) Stream.cons(self.next(), self.toStream)
      *          else Stream.empty[A]
      *      }}}
      */
//    val xs = Stream.cons(1, Stream.cons(2, Stream.cons(3, Stream.empty)))
    val xs = LazyList.cons(1, LazyList.cons(2, LazyList.cons(3, LazyList.empty)))
    println(xs)

    //similarity to the Nil List instantiation
    val lxs = new ::(0, new ::(1, new ::(2, new ::(3, Nil))))
    println(lxs)

    // creating stream ranges
    def streamRange(lo: Int, hi: Int): LazyList[Int] =
      if (lo >= hi) LazyList.empty
      else LazyList.cons(lo, streamRange(lo + 1, hi))// Recursive call. Note this is not a tail recursion

    println(streamRange(4, 10))

  }

  StreamsUnderTheHood

}
