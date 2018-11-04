package org.hsmak.advanced.functors

/**
  * A functor:
  *     - is a data type that defines how a transformation known as a map applies to it.
  *     - Scala implements functors as type classes with a map method.
  */
//TODO
object Functors extends App {

  import org.hsmak.advanced.AdvancedTypeSystem.Hom

  trait Functor[M[_]] {
    def map[U, V](m: M[U])(f: U => V): M[V]
  }

  trait ObsFunctor[T] extends Functor[(Hom[T])#Left] {
    self =>
    override def map[U, V](vu: Function1[T, U])(f: U => V): Function1[T, V] = f.compose(vu)
  }

  trait CoFunctor[M[_]]{ def map[U,V](m: M[U])(f: V=>U): M[V]}

  trait CoObsFunctor[T] extends CoFunctor[(Hom[T])#Right] {
    self =>
    override def map[U,V](vu: Function1[U,T])(f: V =>U):
    Function1[V,T] = f.andThen(vu)
  }
}
