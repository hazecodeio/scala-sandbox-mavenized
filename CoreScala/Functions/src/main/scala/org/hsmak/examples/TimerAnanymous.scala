package org.hsmak.examples

object TimerAnonymous {
  def main(args: Array[String]) {
    oncePerSecond(() => println("time flies like an arrow..."))
  }

  /*
   * from Java 8 perspective, callback is of type lambda expression that takes no-arg and returns void
   *
   * the type Unit is similar to void in C/C++
   */
  def oncePerSecond(callback: () => Unit) {
    while (true) {
      callback();
      Thread sleep 1000
    }
  }
}