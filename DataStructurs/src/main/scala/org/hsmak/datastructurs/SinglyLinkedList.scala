package org.hsmak.datastructurs

/**
  * This is how a List is represented internally by Scala
  */
sealed trait SinglyLinkedList[+A]

case object SLLNil extends SinglyLinkedList[Nothing]

case class Cons[+A](head: A, next: SinglyLinkedList[A]) extends SinglyLinkedList[A]{
  def headOption = Some(head)
  def tail = next
}

object Cons {
  /**
    * Construct a list via a repeating parameter
    *
    * @param as
    * @tparam A
    * @return
    */
  def apply[A](as: A*): SinglyLinkedList[A] = {
    if (as.isEmpty) SLLNil
    else Cons(as.head, apply(as.tail: _*))
  }
}

object Runner extends App{
  println(Cons(1,2,3,4))
}