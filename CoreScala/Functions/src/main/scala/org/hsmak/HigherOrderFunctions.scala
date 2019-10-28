package org.hsmak

import scala.annotation.tailrec

object HigherOrderFunctions extends App {

  //################################### Functions with Functions, AKA Higher Order Functions ###################################

  object FuncWithFunc {

    val f_Explicit: (Int, Int => Int) => Int = (x: Int, y: Int => Int) => y(x) // Notice the colon ':' <- again this is to be explicit with the type
    val f = (x: Int, y: Int => Int) => y(x) // y is of type function that expects an Int and return an Int

    println(f(3, (m: Int) => m + 1)) // the 2nd param is a lambda expression which is an implementation of the function y. Here m is x which is the input and that is 3
    println(f(3, m => m + 1)) // by inference type will be inferred
    println(f(3, _ + 1)) // a placeholder will do just fine as well
    println(f(3, 1 + _)) // by commutative property of the addition process

    import scala.language.postfixOps
    // turn on the postfixOps flag to turn off the warning
    println(f(3, 1+)) // if the '_' is the last param we can take it away


    // return a function from a function
    val g = (x: Int) => (y: Int) => x + y // this introduces Currying. this is also a closure; a function is being returned but is closing around the x
    println(g(4)(5)) // currying!!
    println(g.apply(4).apply(5)) // this is what it really is

  }

  FuncWithFunc
  println

  object SumExample {

    /**
      * Abstract function that accept a custom operation via a Lambda Expression
      *
      * @param f
      * @param a
      * @param b
      * @return
      */
    def sum(f: Int => Int, a: Int, b: Int): Int = {
      if (a > b) 0
      else f(a) + sum(f, a + 1, b) // passing the function reference 'f'
    }

    ///////////Specific Method Implementation////////////////

    def id(x: Int): Int = x

    def sumInts(a: Int, b: Int) = sum(id, a, b)

    def sumIntsWithAnonymousFun(a: Int, b: Int) = sum(x => x, a, b)

    def cube(x: Int): Int = x * x * x

    def sumCubes(a: Int, b: Int) = sum(cube, a, b)

    def sumCubesWithAnonymousFun(a: Int, b: Int) = sum(x => x * x * x, a, b)

    def factorial(n: Int): Int = {
      @tailrec
      def fact(n: Int, accum: Int): Int = if (n == 0 || n == 1) accum else fact(n - 1, n * accum)

      fact(n, 1)
    }

    def sumFactorials(a: Int, b: Int) = sum(factorial, a, b)

  }

  SumExample
  println
}
