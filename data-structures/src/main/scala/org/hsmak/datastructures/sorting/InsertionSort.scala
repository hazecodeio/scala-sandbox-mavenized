package org.hsmak.datastructures.sorting

object InsertionSort extends App {


  def insertionSort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case y :: ys => insert(y, insertionSort(ys))// re-inserting the first element in the correct position, recursively
  }


  val cond: (Int, Int) => Boolean = (a, b) => a < b

  def insert(x: Int, xs: List[Int]): List[Int] =
    xs match {
      case List() => x :: Nil // if empty then just add the element x to the empty list; empty can result from recursively retrieving the tail

      case y :: ys =>
        if (cond(x, y)) x :: y :: ys // if condition is satisfied then simply prepend x
        else y :: insert(x, ys) // else recursively call insert() on tail[] till the previous condition is satisfied
    }

  /**
    * testing insert; not sorting
    */
  println(insert(2, 1 :: 3 :: Nil))
  println(insert(2, 3 :: 1 :: Nil))
  println(insert(1, 2 :: 3 :: Nil))
  println(insert(1, 3 :: 2 :: Nil))
  println(insert(3, 1 :: 2 :: Nil))
  println(insert(3, 2 :: 1 :: Nil))

  println(insert(8, 7 :: 3 :: 1 :: 8 :: 10 :: Nil))
  println

  /**
    * testing InsertionSort
    */
  println(insertionSort(3 :: 1 :: 7 :: 2 :: 99 :: 22 :: 0 :: 11 :: 7 :: Nil))
}
