package org.hsmak

/**
  * also called partially applied function. is this doable in Java8? yes:
  * https://dzone.com/articles/partially-applied-functions-in-java
  * https://www.pgrs.net/2015/04/23/partial-function-application-in-java-8/
  *
  * What are the applications of it?
  */
object ConvertingMethodsToFunctions extends App {

  /**
    * Class Foo has methods whose params are regular vals; i.e. not Lambdas
    */
  class Foo(x: Int) {
    /**
      *
      * @param y <- regular val; not lambda
      */
    def bar(y: Int) = x + y

    /**
      *
      * @param z <- regular val; not lambda
      * @param a <- regular val; not lambda
      */
    def gym(z: Int, a: Int) = x + z + a
  }

  /**
    * Class Baz has methods whose params are Lambdas
    * functions are expected to be passed
    *     - strategy design pattern,
    *     - behavior parametrization
    */
  class Baz(z: Int) {

    /**
      * a function is a param. aka a lambda param
      *
      * @param f <- a lambda with one param
      */
    def qux(f: Int => Int) = f(z) // call the function on the z value. So it's like we are performing a separate/outside process within the class itself

    /**
      *
      * @param f a lambda with two params
      * @return
      */
    def jam(f: (Int, Int) => Int) = f(z, 10)
  }


  println("################################### Converting A Method To A Function ###################################")


  object ConvMethToFun {

    val x = new Foo(10)
    val f = x.bar _ // the underscore '_' is a placeholder. and that's how to convert a method (of an object) into a function

    //The above line is expanded to the following under the hood
    val fExpanded:Function1[Int, Int] = x.bar _
    println("fExpanded: " + fExpanded(20))

    /**
      * In console:
      * scala> val f = x.bar _
      * f: Int => Int = $$Lambda$1196/132423149@2c6c302f
      */

    println("------------------------ Method Foo.bar(y: Int) to a function ------------------------")
    println(f.apply(20))
    println(f(20))

    println("------------------------ Method Foo.gym(z: Int, a: Int)) to a function ------------------------")

    // more than a param
    val f2 = x.gym(40, _: Int) // so this is now a function 'f2: Int => Int'

    //partially applied functions for two params
    val f3 = x.gym _ // Now the underscore '_' is expecting two params: 'f3:(Int, Int) => Int'


    val z = new Baz(20)
    // passing the partially applied function
    println("z.qux(f) ", z.qux(f)) // from above: 'val f = x.bar _'
    println("z.qux(x.bar _) ", z.qux(x.bar _)) // without calling the val f

    // no you can't use a method as a function. there is more going on under the hood. Remember this a partially applied function happening midway
    println("z.qux(x.bar) ", z.qux(x.bar))


    println(z.qux(f2)) // because f2 is still expecting one param of type Int it matches the lambda param of qux


    println(z.jam(f3))// 'f3:(Int, Int) => Int'
    println(z.jam(x.gym _)) // without calling the val f3

    // no you can't use a method as a function. there is more going on under the hood. Remember this a partially applied function happening midway
    println(z.jam(x.gym))


  }

  ConvMethToFun
  println

  println("------------------ CalcProductPrice --------------------------")

  object CalcProductPrice {
    //clear application of the above concept
    def calculateProductPrice(discount: Double, productPrice: Double): Double = (1 - discount / 100) * productPrice

    val discount_30 = calculateProductPrice(30, _: Double)
    println(discount_30(100))
  }

  CalcProductPrice
  println

}
