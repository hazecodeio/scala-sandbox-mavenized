package org.hsmak

object Currying extends App {

  println("################################### Currying ###################################")

  object MyCurrying {
    /**
      * Currying: named after Haskell Curry. Haskel lang was named after his first name and Currying concept was named after his last name!
      * Currying breaks the function into parts so we can feed it in parts. I.e. a sequence of functions returning functions
      * g -> returns a function/lambda expression
      * currying three param function '(Int, Int, Int)' will convert it to 'Int => (Int => (Int => Int))'
      * again don't confuse methods with functions
      */
    val g = (x: Int) => (y: Int) => x + y
    println(g(2)(5))

    val g3 = (x: Int) => ((y: Int) => (z: Int) => x + y + z) // returns a function that returns another function
    println(g3(2)(3)(5))

    val q = g _

    //multiple-param function. signature from the REPL: '(Int, Int) => Int'
    val f = (x: Int, y: Int) => x + y

    val fc = f.curried// Aftwr currying, fc is exactly as same as g

    // convert a regular function into a curried one. f will become: val f = (x:Int) => ((y:Int) => x + y). Signature: 'Int => (Int => Int)'
    val uncurFC = Function.uncurried(fc)
    // uncurrying a curried function
    val fo = foo _ // REPL: (Int, Int, Int) => Int <- converting to a regular function with 3 params
    val fo2 = foo(2, _: Int, _: Int) //we have to specify all params if not curried. This is the Partially Applied Function
    //Curried Method: well you reall have to convert it to a function first using '_'
    val curriedFoo = (foo _).curried // 1st convert method to a function then convert it to a curried one. Now this similiar to the following.
    // a curried method (still not a function too!). used by implicit params a lot
    val br = bar _ // REPL: Int => (Int => (Int => Int)) <- fully curried function!
    val br2 = bar(2) _ // partially applied function!
    val bz = baz _ // REPL: (Int, Int) => Int => Int
    val bz2 = baz(2, _: Int) _
    println(br2(3)(10)) //br2.apply(3).apply(10)

    //remember: '_' converts a method into a function
    def foo(x: Int, y: Int, z: Int) = x + y + z // a method not a function

    def bar(x: Int)(y: Int)(z: Int) = x + y + z

    def baz(x: Int, y: Int)(z: Int) = x + y + z

    println(bz2(3)(3))


  }

  MyCurrying

}
