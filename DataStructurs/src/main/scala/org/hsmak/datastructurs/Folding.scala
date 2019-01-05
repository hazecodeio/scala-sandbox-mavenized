package org.hsmak.datastructurs

import scala.annotation.tailrec

//ToDo - move this to anew repository "functional-data-structures"
object Folding extends App {


  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
    as match {
      case Nil => z
      case x :: xs => f(x, foldRight(xs, z)(f)) //non-tail recursive
    }
  }

  val frResult = foldRight(1 :: 2 :: 3 :: Nil, 0)(_ + _)
  println(frResult)


  @tailrec
  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
    as match {
      case Nil => z
      case x :: xs => foldLeft(xs, f(z, x))(f) //tail recursive
    }
  }

  val flResult = foldLeft(1 :: 2 :: 3 :: Nil, 0)(_ + _)
  println(flResult)


  def map[A, B](as: List[A])(f: A => B): List[B] = {

    //    foldLeft(as,Nil:List[B])((xs,x) => f(x) :: xs)
    // or replace this with foldLeft/foldRight
    as match {
      case Nil => Nil
      case x :: xs => f(x) :: map(xs)(f) //non-tail recursive
    }
  }

  val mResult = map(1 :: 2 :: 3 :: Nil)(_ + 1)
  println(mResult)


}
