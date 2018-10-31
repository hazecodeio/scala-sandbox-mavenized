package org.hsmak


object RiskOfMutablility extends App {

  def elapsedTime[T](result: => T): Unit = {
    val start = System.currentTimeMillis()
    result
    val end = System.currentTimeMillis()
    println(end - start)
  }

  object MutabelVsImmutable {
    val iterations = 1 to 1000
    val range = 1 to 1000

    println("With Immutable Map:")
    elapsedTime(iterations.foreach(_ => range.foldLeft(scala.collection.immutable.Map[String, Int]()) { (map, i) => map + (i.toString -> i) })) // Remember "+" will generate a new Map

    println("With Mutable Map: ..... Dead Lock!?")
    //commented because it's a dead lock
    //  elapsedTime(iterations.foreach(_ => range.foldLeft(scala.collection.mutable.Map[String, Int]()){ (map, i) => map + (i.toString -> i)}))

    println("With Mutable Map (using +=):")
    elapsedTime(iterations.foreach(_ => range.foldLeft(scala.collection.mutable.Map[String, Int]()) { (map, i) => map += (i.toString -> i) })) // "+" will modify the same Map
  }

  MutabelVsImmutable
  println

  println("-------------- ThreadSafety Issues ---------------------")

  /**
    * copy it in the REPL
    *
    */
  //TODO:
  object ThreadSafetyIssues {

    import scala.collection.{immutable, mutable}
    import scala.concurrent.ExecutionContext.Implicits.global
    import scala.concurrent.Future

    val ranges = List(1 to 1000, 1 to 1000, 1 to 1000, 1 to 1000)
    val mMap = mutable.Map[String, Int]()
    Future.sequence(ranges.map(range => Future(range.foreach {
      i =>
        mMap.get(i.toString)// why is this?
        mMap += (i.toString -> i) // mutable Map
    }))).onComplete(_ => println("Done from mMap!"))

    // notice the var
    var iMap = immutable.Map[String, Int]()
    Future.sequence(ranges.map(range => Future(range.foreach { i =>
      iMap.get(i.toString)// why is this?
      iMap += (i.toString -> i) // possible because it's var not val!!
    }))).onComplete(_ => println("Done from iMap!"))

    //    Thread.sleep(5000)
    println("mMap Size: " + mMap.size)// Try it in the REPL
    println("iMap Size: " + iMap.size)// Try it in the REPL
  }

  ThreadSafetyIssues
  println

//  Thread.sleep(50000)

}
