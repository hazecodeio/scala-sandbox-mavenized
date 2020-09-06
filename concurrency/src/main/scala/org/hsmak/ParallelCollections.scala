package org.hsmak

// in order to use '.par' as of scala 2.13
import scala.collection.parallel.CollectionConverters._
import scala.collection.parallel.immutable.ParSeq

/**
  * Note: placing a threadable block within an object didn't work!! Not sure why??
  */
object ParallelCollections extends App {

  /**
    * printing will show that threads are running in no particular order
    * however, once done running results are back in order
    * Scala uses a ThreadPool under the hood
    * WARNING: pay attension to ThreadSafety when it comes to modifying mutable constructs
    *
    * @return
    */
  def test = (1 to 8).toList.par.map { i =>
    println(Thread.currentThread.getName + ": " + i)
    i + 5
  }

  println(test) // ParVector
  println

  println("----------------- NotThreadSafe -----------------")

  /**
    * def vs val:
    *     - def: needs to be called in order to execute the block -> deferred call; lazy call
    *     - val: it will be initialized right away upon scala app startup -> eager call
    */
  def notThreadSafe = {

    //mutable var
    var counter = 0

    def test = (1 to 100).toList.par.map { i =>
      counter += 1
      i + 5
    }

    test
    println(counter) // counter won't be the same as the list size!!
  }

  notThreadSafe
  println

  println("------------------ convert Par[Vector|List|etc] to regular ones ---------------")

  def convertBack = {

    def returnParSeq = (1 to 8).toList.par.map { i =>
      println(Thread.currentThread.getName + ": " + i)
      i + 5
    }

    val parSeq = returnParSeq
    println

    println(parSeq)
    val seq = parSeq.seq// Note: toSeq will return ParSeq
    println(seq)

  }
  convertBack
  println

}
