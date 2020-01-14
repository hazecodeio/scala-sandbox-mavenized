package org.hsmak.datastructurs

/**
  * This is how a List is represented internally by Scala
  */
sealed trait SinglyLinkedList[+A]

case object SLLNil extends SinglyLinkedList[Nothing]

case class SLLCons[+A](head: A, next: SinglyLinkedList[A]) extends SinglyLinkedList[A]{
  def headOption = Some(head)
  def tail = next
}

object SLLCons {
  /**
    * Construct a list via a repeating parameter
    *
    * @param as
    * @tparam A
    * @return
    */
  def apply[A](as: A*): SinglyLinkedList[A] = {
    if (as.isEmpty) SLLNil
    else SLLCons(as.head, apply(as.tail: _*))
  }
}

object Runner extends App{
  println(SLLCons(1,2,3,4))
}