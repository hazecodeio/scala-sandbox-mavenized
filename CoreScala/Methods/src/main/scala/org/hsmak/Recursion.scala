package org.hsmak

import scala.annotation.tailrec

object Recursion extends App {

  println("################################### Recursion ###################################")

  def factorial_n(n: Int): Int = if (n == 0 || n == 1) 1 else n * factorial_n(n - 1)

  println(factorial_n(5))
  println(factorial_n(100))


  //  @tailrec//an annotation to check if it's Optimized Recursion at compile time
  // this is non-optimized recursion because of the n * fac(n-1)
  def factorialWithBigInt(n: BigInt): BigInt = if (n == 0 || n == 1) 1 else n * factorialWithBigInt(n - 1) //non-optimized recursion

  println(factorialWithBigInt(5))
  println(factorialWithBigInt(10))
  println(factorialWithBigInt(1000))
  println

  println("################################### Optimized Recursion  ###################################\n")

  //this is an optomized recursion. accumulate the values ina val
  @tailrec
  def factorialWithBigInt_Optim(n: BigInt, acc: BigInt): BigInt = if (n == 0 || n == 1) acc else factorialWithBigInt_Optim(n - 1, n * acc)

  println(factorialWithBigInt_Optim(10000, 1)) //stack overvlow. you can either use optimized recursion or increase the stack size at the startup


  //have another fucntion that calls the above with the accumulator
  def factorialWithBigInt_Optim_WithHiddenAccum(n: BigInt) = factorialWithBigInt_Optim(n, 1)

  println(factorialWithBigInt_Optim_WithHiddenAccum(10000))


  println("################################### Methods Inside Methods ###################################")

  def factorial(n: BigInt): BigInt = {

    @tailrec //keeping the optimized version
    def fact(n: BigInt, accum: BigInt): BigInt = if (n == 0 || n == 1) accum else fact(n - 1, n * accum)

    fact(n, 1)
  }

  println(factorial(10000))

}
