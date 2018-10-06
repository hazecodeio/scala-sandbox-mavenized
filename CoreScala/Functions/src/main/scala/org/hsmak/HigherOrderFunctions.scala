package org.hsmak

object HigherOrderFunctions extends App {

  //################################### Functions with Functions, AKA Higher Order Functions ###################################

  object FuncWithFunc {

    val f_Explicit: (Int, Int => Int) => Int = (x: Int, y: Int => Int) => y(x) // Notice the colon ':' <- again this is to be explicit with the type
    val f = (x: Int, y: Int => Int) => y(x) // y is of type function that expects an Int and return an Int

    println(f(3, (m: Int) => m + 1)) // the 2nd param is a lambda expression which is an implementation of the function y. Here m is x which is the input and that is 3
    println(f(3, m => m + 1)) // by inference type will be inferred
    println(f(3, _ + 1)) // a placeholder will do just fine as well
    println(f(3, 1 + _)) // by commutative property of teh addition process

    import scala.language.postfixOps
    // turn on the postfixOps flag to turn off the warning
    println(f(3, 1 +)) // if the '_' i sthe last param we can take it away


    // return a function from a function
    val g = (x: Int) => (y: Int) => x + y // this introduces Currying. this is also a closure; a function is being returned but is closing around the x
    println(g(4)(5)) // currying!!
    println(g.apply(4).apply(5)) // this is what it really is

  }

  FuncWithFunc
  println
}
