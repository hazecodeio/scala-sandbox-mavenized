package org.hsmak

//################################### Functions: By-Name Parameters //###################################

/**
  * Eager vs. lazy eval of param
  * Notice that params are curried in all the three
  *
  * ByValue     -> eager
  * ByFunction  -> Lazy
  * ByName      -> Lazy
  */
object ByNameByValueByFunctionParameter extends App {

  println("--------------- ByValueParm - Eager Evaluation ---------------")

  object ByValueParam {
    def byValue(x: Int)(y: Int) = {
      // '(y:Int)' will be evaluated first, then the below block will be executed
      println("By Value: ")
      x + y
    }

    val a = byValue(3) {
      //Eager evaluation of '(y:Int)'
      println("In call")
      19
    }
  }

  ByValueParam
  println


  println("-------------- ByFunctionParm ---------------")

  object ByFunctionParam {

    //The 2nd param is a regular lambda expression
    def byFunction(x: Int)(y: () => Int) = {
      println("By Function: ")
      x + y()
    }


    val b = byFunction(3)(() => {
      //Lazy evaluation -> evaluate when it's called <- the body of the called function needs to be instantiated first then the below will be executed
      println("In call")
      19
    })

  }

  ByFunctionParam
  println

  println("--------------- ByNameParam ---------------")

  /**
    * It's as same as ByFunction
    * It just provides easiness beside the laziness
    *
    * Can be called by black directly since no need to prepend the '() =>'
    * Good to catch exceptions and clean up resource as in try/catch blocks below.
    * Other benefits?
    */
  object ByNameParam {

    // ByName param. Notice there is no () in the lambda expression
    def byName(x: Int)(y: => Int) = {
      println("By Name: ")
      x + y
    }

    val c = byName(3) {
      //Lazy evaluation -> evaluate when it's called <- the body of the called function needs to be instantiated first then the below will be executed
      println("In call")
      19
    }
    println(c)
    println

    println("-- divideSafely --")

    /**
      * real world example for ByName params
      * Notice there is no 'f:()' instead it is 'f:'
      *
      * @param f
      * @return
      */
    def divideSafely(f: => Int): Option[Int] = {
      try {
        Some(f)// f will be initialized here
      } catch {
        case ae: ArithmeticException => None
      }
    }


    val d = divideSafely {
      val x = 10
      val y = 5
      x / y
    }
    println(d)

    val e = divideSafely {
      val x = 100
      val y = 0
      x / y
    }
    println(e)

  }

  ByNameParam
  println

}
