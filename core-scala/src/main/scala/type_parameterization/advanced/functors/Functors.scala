package type_parameterization.advanced.functors

import type_parameterization.advanced.AdvancedTypeSystem.Hom

/**
 * A functor:
 *     - is a data type that defines how a transformation known as a map applies to it.
 *     - Scala implements functors as type classes with a map method.
 */

trait Functor[M[_]] {
    def map[A, B](m: M[A])(f: A => B): M[B]
}

trait ObsFunctor[T] extends Functor[(Hom[T])#Left] {
    self =>
    override def map[A, B](vu: Function1[T, A])(f: A => B): Function1[T, B] = f.compose(vu)
}

trait CoFunctor[M[_]] {
    def map[A, B](m: M[A])(f: B => A): M[B]
}

trait CoObsFunctor[T] extends CoFunctor[(Hom[T])#Right] {
    self =>
    override def map[A, B](vu: Function1[A, T])(f: B => A):
    Function1[B, T] = f.andThen(vu)
}

//TODO
object Functors extends App {
}
