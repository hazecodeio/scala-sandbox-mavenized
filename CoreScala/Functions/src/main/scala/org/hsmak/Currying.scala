package org.hsmak

object Currying extends App {

  /**
    * https://stackoverflow.com/questions/13793756/implementing-a-higher-order-function-that-performs-currying-in-scala
    */
  object ImplementingPartialCurrUncurr {

    def partial1[A, B, C](a: A, f: (A, B) => C): B => C = {
      (b: B) => f(a, b)
    }

    def curry[A, B, C](f: (A, B) => C): A => (B => C) = {
      (a: A) => (b: B) => f(a, b)
    }

    def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
      (a: A, b: B) => f(a)(b)
    }

    def compose[A,B,C](f: B => C, g: A => B): A => C = {
      (a: A) => f(g(a))
    }

  }

  println("################################### Curried Methods ###################################")

  object CurriedMethods {

    /**
      * Currying: named after Haskell Curry.
      *   - Haskell lang was named after his first name and
      *   - Currying concept was named after his last name!
      *
      * Currying breaks the function into parts so we can feed it in parts. I.e. a sequence of functions returning functions
      *   - similar to nested functions
      *
      * g -> returns a function/lambda expression
      * currying three param function '(Int, Int, Int)' will convert it to 'Int => (Int => (Int => Int))'
      *   - notice how functions are nested
      *
      * again don't confuse methods with functions
      */
    val g = (x: Int) => (y: Int) => x + y
    println(g(2)(5))

    val g3 = (x: Int) => ((y: Int) => (z: Int) => x + y + z) // returns a function that returns another function
    println(g3(2)(3)(5))

    val q = g _

    //multiple-param function. signature from the REPL: '(Int, Int) => Int'
    val f = (x: Int, y: Int) => x + y

    val fc = f.curried // Aftwr currying, fc is exactly as same as g

    // convert a regular function into a curried one. f will become: val f = (x:Int) => ((y:Int) => x + y). Signature: 'Int => (Int => Int)'
    val uncurFC = Function.uncurried(fc)
    // uncurrying a curried function


  }

  CurriedMethods
  println

  println("################################### Curried Parameters ###################################")

  object CurriedParamters {

    /**
      * - Remember these are methods not functions
      * - You have to use '_' to convert them to functions
      */

    def foo(x: Int, y: Int, z: Int) = x + y + z //a method not a function

    def bar(x: Int)(y: Int)(z: Int) = x + y + z //a method with 3, or 2?, curried params <- fully curried method (note: not a function)

    def baz(x: Int, y: Int)(z: Int) = x + y + z //a method with one curried param

    //remember: '_' converts a method into a function
    val fo = foo _ // REPL: (Int, Int, Int) => Int <- converting to a regular function with 3 params

    /**
      * This is the Partially Applied Function
      */
    println("----- Partially Applied Function -----> foo(2, _: Int, _: Int) ----- ")
    val fo2 = foo(2, _: Int, _: Int) //we have to specify all params if not curried.
    println(s"fo2(3,4): ${fo2(3, 4)}")

    /**
      * - Curried Method: you have to us '_' to convert it to a function first
      * - 1st convert method to a function then convert it to a curried one.
      */
    val curriedFoo = (foo _).curried
    // After currying: Int => (Int => (Int => Int))
    val appliedCurriedFoo = curriedFoo(1)(2)(3)
    println(s"appliedCurriedFoo: $appliedCurriedFoo")

    println("--------- bar(x: Int)(y: Int)(z: Int) -----------")
    /**
      * - a curried method (still not a function too!).
      * - used by implicit params a lot
      * - implicits are always called by a curried param
      */
    val br = bar _ // REPL: Int => (Int => (Int => Int)) <- fully curried function!
    val br2 = bar(2) _ // partially applied function!
    println(s"br2(3)(10): ${br2(3)(10)}") //br2.apply(3).apply(10)

    println("--------- baz(x: Int, y: Int)(z: Int) -----------")
    /**
      * both:
      *     - Partially Applied Function via (_: Int), and
      *     - Curried via '_' after the signature '(...) _'
      */
    val bz = baz _ // REPL: (Int, Int) => Int => Int
    val bz2 = baz(2, _: Int) _ // REPL: Int => (Int => Int)
    println(s"bz2(3)(3): ${bz2(3)(3)}")
  }

  CurriedParamters
  println

}
