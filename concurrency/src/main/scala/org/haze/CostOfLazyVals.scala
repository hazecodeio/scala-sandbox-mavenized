package org.haze

/**
  * Lazy vals are instantiation involves locking mechanism
  */
object CostOfLazyVals { //} extends App {
  /** Update: Actually it was a var ordering issue of "threadName"
    * This caused a NullPointerException when all Methods/Threads-Creation where part of main
    */
  // object CostOfLazyVals extends App {

  def main(args: Array[String]): Unit = {
    myFunction()
    separator()
    myFunction2()
  }


  def separator() = {
    for (i <- 0 until 10)
      print("-")
    println()
  }


  /**
    * 1st function
    */
  def myFunction() = {
    lazy val variable = {
      Thread.sleep((1000))
      "foo"
    }

    val t1 = System.currentTimeMillis()
    println("t1: " + t1)
    println(variable)

    val t2 = System.currentTimeMillis()
    println("t2: " + t2)
    println(variable)
    println("Time taken lazy val is referenced: " + (t2 - t1))
    println

    val t3 = System.currentTimeMillis()
    println("t3: " + t3)
    println("Time taken lazy val is RE-referenced: " + (t3 - t2))
  }


  /**
    * 2nd function
    */
  def myFunction2() = {

    lazy val variable = {
      Thread.sleep(1000)
      "foo"
    }


    val threadName = {
      val tn = new ThreadLocal[String]()
      tn.set(Thread.currentThread().getName)
      tn
    }

    //This will finish quickly
    println(s"before creating the new thread: ${threadName.get()}: ${System.currentTimeMillis()} ")

    /**
      * Create a new thread by passing Runnable block to the constructor
      *
      */

    /*new Thread { // <- Notice the curly brace {
      println(s"inside1: ${threadName.get()}: ${System.currentTimeMillis()} ")
      println(s"inside2: ${threadName.get()}: $variable")
      println(s"inside3: ${threadName.get()}: ${System.currentTimeMillis()} ")
    }.start()*/

    /**
      * The below is equivalent to the above
      * the Runnable Lambda is expanded
      */
    /*new Thread( // <- notice the paren (
      // Lambda is an implementation of the 'void run()" coming from Runnable Java interface
      () => {
        println(s"inside1: ${threadName.get()}: ${System.currentTimeMillis()} ")
        println(s"inside2: ${threadName.get()}: $variable")
        println(s"inside3: ${threadName.get()}: ${System.currentTimeMillis()} ")
      }).start()*/

    new Thread() { // Anonymous Inner Class; i.e. extending Thread class
      override def run(): Unit = {
        println(s"inside1: ${threadName.get()}: ${System.currentTimeMillis()} ")
        println(s"inside2: ${threadName.get()}: $variable")
        println(s"inside3: ${threadName.get()}: ${System.currentTimeMillis()} ")
      }
    }.start()

    println(s"outside: ${threadName.get}: $variable")
    println(s"outside: ${threadName.get}: ${System.currentTimeMillis()}")

  }

}
