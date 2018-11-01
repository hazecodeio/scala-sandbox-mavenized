package org.hsmak.streams

/**
  * - lazy collections
  * - similar to Java8's Stream:
  *     - Intermediate Operation
  *     - Terminal operation
  */
object Streams extends App {

  println("without Streams:")
  //If we only need the first 3 items then why the iteration till the last element
  val withoutStreams: List[Unit] = (1 to 5).toList.map(_ => println("Hello")).take(3) // notice the type
  withoutStreams.foreach(println) // map, take, and the rest are all terminal operations

  //  (1 to 5).map(_ => ()).foreach(println)

  println("with Streams:")
  val withStreams: Stream[Unit] = (1 to 5).toList.toStream.map(_ => println("Hello")).take(3) // notice the type
  withStreams.foreach(println) // foreach is a terminal operation while map, take and some others are intermediate operations

  println("Using Stream CompanionObject")
  Stream(1, 2, 3, 4, 5).map(_ * 2).take(3).foreach(println)
  println
  Stream.apply(1, 2, 3, 4, 5).map(_ * 2).take(3).foreach(println)
  println

  println("Continually")
  Stream.continually(5).take(3).foreach(println)
  println

  Stream.continually(6).take(20).foreach(println)
  println

  println("Stream concat:")
  Stream.concat(List(1, 2, 3, 4), List(1, 2, 3, 4, 5, 6), List(8, 9)).take(5).foreach(println)
  println

  Stream.concat((1 to 5), (4 to 8)).foreach(println)
  println

  Stream.concat((1 to 5)).foreach(println)
  println

  println("Stream Ranges:")
  Stream.range(1, 100, 3).take(10).foreach(println)
  println

  println(Stream.range(1, 100, 3).length)
  println

}
