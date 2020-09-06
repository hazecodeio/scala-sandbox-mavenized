package methods

import scala.annotation.tailrec

object BasicMethods extends App {


  println("################################### Methods Inside Methods ###################################")

  object MethInsideMeth {

    def factorial(n: BigInt): BigInt = {
      /*
       * defining a method inside a method
       */
      @tailrec //keeping the optimized version
      def fact(n: BigInt, accum: BigInt): BigInt = if (n == 0 || n == 1) accum else fact(n - 1, n * accum)

      fact(n, 1)
    }

    println(factorial(10))

  }

  MethInsideMeth
  println()

  object BasicMethod {
    /**
      * '=' sign is very importatn. it states the method will return something
      *
      * @param x
      * @param y
      * @return
      */
    def add(x: Int, y: Int): Int = {
      return x + y;
    }

    println(add(6, 7))

    /**
      * Consequences of forgetting the "=" is it will always return Unit
      *
      * @param x
      * @param y
      */
    def badAdd(x: Int, y: Int) {
      x + y
    }

    println("------------ bad add --------------")
    println(badAdd(1, 5))

    def addUnit(x: Int, y: Int): Unit = x + y

    println("------------ Add Unit --------------")
    println(addUnit(1, 5))
  }

  BasicMethod
  println


  println("################################### bending method names to your will ###################################")

  object BendingNamesToYourWill {
    def `summation of`(x: Int, y: Int) = x + y

    println(`summation of`(4, 5))

    println("---------- a method that takes no param --------------")


    def willYouPrintMe_? = true // use '_' to use OpChar. It looks like a val but it's really a method.

    println(willYouPrintMe_?)

  }

  BendingNamesToYourWill
  println

  println("----------------------- Named Arguments ------------------------")

  object NamedArg {
    def processNumber(b: Boolean, x: Int, y: Int) = if (b) x else y

    println(processNumber(true, 10, 2))
    println(processNumber(x = 10, y = 2, b = true)) //named arguments

  }

  NamedArg
  println

  object PrintingPrimes {
    def printPrimes() {
      val primeList = List(1, 2, 3, 5, 7, 11)
      for (i <- primeList) {
        if (i == 11)
          return
        if (i != 1)
          println(i)
      }
    }

    Console println "\nPrimes:"
    printPrimes

  }

  PrintingPrimes
  println


  println("----------------------- Default Arguments ------------------------")

  object MethodWithDefaultArgs {

    def processNumberWithDefaultArgument(b: Boolean = true, x: Int, y: Int) = if (b) x else y

    //  println(processNumberWithDefaultArgument(10, 4))//It won't work because default value works if it's the last argument only!!
    println(processNumberWithDefaultArgument(x = 10, y = 4)) // solution is to use named argument as well

    def add(x: Int, y: Int = 10) = x + y // default value works if it's the last argument only!!


    println(add(3, 4))
    println(add(30))

  }

  MethodWithDefaultArgs
  println


  println("--------------------- Default and Named Argumnets together -------------------")

  object MethodWithInference {

    def add(x: Int, y: Int) = x + y;


    println(add(6, 7))

    def numberStatus(a: Int) = {
      if (a < 10) "Less than 10"
      else if (a > 10) "Greater than 10"
      else "It is 10"
    }

    println(numberStatus(10))

    def numberStatusWithoutBraces(a: Int) =
      if (a < 10) "Less than 10"
      else if (a > 10) "Greater than 10"
      else "It is 10"


    println(numberStatusWithoutBraces(10))
  }


  MethodWithInference
  println


  /////////////////////////////////////////////////////////////////////////////////


  println("--------------- SideEffect -----------------")

  object WithSideEffect {
    var a = 0

    def sideEffect() {
      a = a + 1
    }

    println(a)
  }

  WithSideEffect
  println


}

