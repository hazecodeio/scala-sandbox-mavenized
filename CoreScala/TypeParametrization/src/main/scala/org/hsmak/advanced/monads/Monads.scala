package org.hsmak.advanced.monads

/**
  * A monad:
  *     - is a wrapper around an existing data type.
  *     - It applies a transformation to a data of wrapper type and returns a value of the same wrapper type.
  *     - Scala implements monads as type classes with unit and flatMap methods.
  *     - Monads extends functors in Scala.
  */
//TODO
object Monads extends App {

  trait Monad[M[_]] {

    def unit[T](a: T): M[T]

    def map[U, V](m: M[U])(f: U => V): M[V]

    def flatMap[U, V](m: M[U])(f: U => M[V]): M[V]
  }


}
