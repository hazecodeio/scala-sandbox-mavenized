package org.hsmak

import scala.annotation.tailrec

object Division extends App {

  def divide(num: Int, denom: Int): Option[Int] = {

    if (denom == 0) return None

    @tailrec
    def divHelper(num: Int, acc: Int): Option[Int] = {
      (num - denom) match {
        case n if n < 0 => Some(acc)
        case _ => divHelper(num - denom, acc + 1)
      }
    }

    divHelper(num, 0)
  }

  println(divide(10, 5))
  println(divide(8, 0))
  println(divide(5, 2))
  println(divide(11, 5))
  println(divide(0, 0))
  println(divide(0, 10))

}
