package org.hsmak

/**
  * Value Classes:
  *     - extends AnyVal
  *     - takes only 'val; one param
  *     - compiler can optimize them
  *         - there will be no need to create instances
  *         - i.e. they will be treated like primitives
  *         - do they have to do with AutoBoxing?
  *     - instance fields are not allowed
  */
object ValueClasses extends App {

  println("############## UsingValueClasses ##################")

  object UsingValueClasses {

    case class MyValue(int: Int) extends AnyVal

    /**
      *
      * @param int -> must be 'val'
      */
    class MyValue2(val int: Int) extends AnyVal


    case class ValueWithMethods(int: Int) extends AnyVal {
      //    val myInstance = 12// not allowed for value classes
      def withExtra5 = int + 5
    }

    /**
      * Compiler will
      *   - not create an instance out of the class
      *   - unbox into (6 + 5)
      *   - precisely: 6.+5
      */
    println((new ValueWithMethods(6)).withExtra5)

  }

  UsingValueClasses
  println

  println("############## UsingValueTraits ##################")

  object UsingValueTraits {

    /**
      * val traits allowed to only have method definitions
      */
    trait ValueTrait extends Any {
      def int: Int

      def withExtra5 = int + 5
    }

    class ValueWithTrait(val int: Int) extends AnyVal with ValueTrait

    println(new ValueWithTrait(6).withExtra5)

    println("Exception to the Instantiation Rule: Pattern Matching")

    case class ValueCaseClass(int: Int) extends AnyVal with ValueTrait

    /**
      * compiler will create an instance
      */
    ValueCaseClass(5) match {
      case ValueCaseClass(int) => int
    }

    /**
      * how about the '@annotation.switch'
      */
    (ValueCaseClass(5): @annotation.switch) match {
      case ValueCaseClass(int) => int
    }

  }

  UsingValueTraits
  println

  println("################### `Option[T] Of Some[T] & None is using Any & AnyVal` ##################")

  /**
    * this is how Scala is implementing
    */
  object `Option[T] Of Some[T] & None is using Any & AnyVal` {

    sealed trait Option[T] extends Any

    case class Some[T](value: T) extends AnyVal with Option[T]

    case object None extends Option[Nothing]

  }

  `Option[T] Of Some[T] & None is using Any & AnyVal`
  println
}
