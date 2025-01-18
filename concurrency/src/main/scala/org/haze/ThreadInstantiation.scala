package org.haze

//ToDo - To be revised
object ThreadInstantiation {

  def main(args: Array[String]): Unit = {
    fun()
  }

  def fun() = {
    new Thread { // <- Notice the curly brace {
      println(s"Do something ")
    }.start()

    new Thread( // <- notice the paren (
      // Lambda is an implementation of the 'void run()" coming from Runnable Java interface
      () => {
        println(s"Do something ")
      }).start()


    new Thread() {
      //
      override def run(): Unit = {

        println(s"Do something ")
      }
    }.start()


  }

}
