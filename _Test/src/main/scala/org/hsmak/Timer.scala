package org.hsmak

/*
 * Perhaps more surprising for the Java programmer, functions are also objects in Scala.
 * It is therefore possible to pass functions as arguments, to store them in variables, and to return them from other functions.
 * This ability to manipulate functions as values is one of the cornerstone of a very interesting programming paradigm called functional programming.
 *
 * the type Unit is similar to void in C/C++
 */
object Timer {
  def main(args: Array[String]) {
    oncePerSecond(timeFlies)
  }

  def oncePerSecond(callback: () => Unit) {
    while (true) {
      callback();
      Thread sleep 1000
    }
  }

  def timeFlies() {
    println("time flies like an arrow...")
  }
}

