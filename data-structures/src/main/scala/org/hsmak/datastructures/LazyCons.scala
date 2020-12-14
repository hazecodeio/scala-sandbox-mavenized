package org.hsmak.datastructures

sealed trait LazyCons[+A]

case object Empty extends LazyCons[Nothing]

case class LZCons[+A](h: () => A, t: () => LazyCons[A]) extends LazyCons[A]

object LazyCons {
  /**
    * Smart Constructor: this is a function 'def' that represents a smart constructor
    *
    * @param h
    * @param t
    * @tparam A
    * @return
    */
  def lzCons[A](h: => A, t: => LazyCons[A]) = {
    lazy val head = h
    lazy val tail = t
    LZCons(() => head, () => tail)
  }

  //ToDo - what will happen if explicit type is removed
  def empty[A]: LazyCons[A] = Empty

  def apply[A](as: A*): LazyCons[A] = {
    if (as.isEmpty) Empty else lzCons(as.head, apply(as.tail: _*))
  }
}