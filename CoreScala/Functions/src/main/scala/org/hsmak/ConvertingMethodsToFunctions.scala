package org.hsmak

/**
  * also called partially applied function. is this doable in Java8? yes:
  * https://dzone.com/articles/partially-applied-functions-in-java
  * https://www.pgrs.net/2015/04/23/partial-function-application-in-java-8/
  *
  * What are the applications of it?
  */
object ConvertingMethodsToFunctions extends App {

  class Foo(x: Int) {
    def bar(y: Int) = x + y

    def gym(z: Int, a: Int) = x + z + a
  }

  class Baz(z: Int) {
    //a function is a param. aka a lambda param
    def qux(f: Int => Int) = f(z) // call the function on the z value. So it's like we are performing a separate/outside process within the class itself
    def jam(f: (Int, Int) => Int) = f(z, 10)
  }


  println("################################### Converting A Method To A Function ###################################")


  object ConvMethToFun {

    val x = new Foo(10)
    val f = x.bar _ // the underscore '_' is a placeholder. and that's how to convert a method (of an object) into a function

    val z = new Baz(20)
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
    val f2 = x.gym(40, _: Int) // so this is now a function 'f: Int => Int'

    //partially applied functions for two params
    val f3 = x.gym _ // Now the underscore '_' is expecting two params: 'f3:(Int, Int) => Int'

    // passing the partially applied function
    println("z.qux(f) ", z.qux(f)) // from above: 'val f = x.bar _'
    println("z.qux(x.bar _) " , z.qux(x.bar _)) // without calling the val f
    println("z.qux(x.bar) ", z.qux(x.bar)) // no you can't use a method as a function. there is more going on under the hood. Remember this a partially applied function happening midway

    val discount_30 = calculateProductPrice(30, _: Double)
    println(z.qux(f2)) // because f2 is still expecting one param of type Int it matches the lambda param of qux

    //clear application of the above concept
    def calculateProductPrice(discount: Double, productPrice: Double): Double = (1 - discount / 100) * productPrice

    println(z.jam(f3))
    println(z.jam(x.gym _)) //without calling the val f3
    println(z.jam(x.gym)) // no you can't use a method as a function. there is more going on under the hood. Remember this a partially applied function happening midway


    println(discount_30(100))

  }

  ConvMethToFun
  println

}
