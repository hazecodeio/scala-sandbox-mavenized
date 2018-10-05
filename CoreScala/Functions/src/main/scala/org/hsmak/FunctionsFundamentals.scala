package org.hsmak

object FunctionsFundamentals extends App {

  //################################### Higher Order Functions ###################################
  /**
    * e.g.: f(x) = x + 1
    */

  /**
    * Function1 is a trait that has one abstract method apply()
    * remember what is so special about apply() is that there is no need to use its name to call it
    * match with the parametrized type:
    *   - params type and
    *   - return type
    *
    * Similar to Tuples, we can create Functions; lambda expression, with up to 22 params
    *
    * As of scala 2.12 functions will turn into Java8 functions (java.util.function) by the compiler
    * As a matter of coincidence java8 function has apply() so this makes scala/java8 very interchangeable
    *
    * Link:
    *   - https://www.scala-lang.org/api/current/scala/Function1.html
    */

  println("------------------------ FunctionExplicit ---------------------")

  object FunctionExplicit {

    val f1: Function1[Int, Int] = new Function[Int, Int] {
      def apply(x: Int): Int = x + 1
    }

    val f0: Function0[Int] = new Function0[Int] {
      override def apply(): Int = 1
    }

    val f2: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
      override def apply(x: Int, y: Int): Int = x + y + 1
    }

    val concatF2: Function2[Int, String, String] = new Function2[Int, String, String] {
      override def apply(x: Int, str: String): String = str + x
    }

    println(f1.apply(3))
    println(f1(3)) // the magic about apply()

    println(f0())
    println(f2(3, 5))
    println(concatF2(3, "wow"))

  }

  FunctionExplicit

  println

  /**
    * replace the return type with the lambda expression
    */
  println("--------------------- FunctionImplicit_01 ----------------------------")

  object FunctionImplicit_01 {

    val f0: () => Int = new Function0[Int] {
      override def apply(): Int = 1
    }

    val f1: Int => Int = new Function[Int, Int] {
      def apply(x: Int): Int = x + 1
    }


    val f2: (Int, Int) => Int = new Function2[Int, Int, Int] {
      override def apply(x: Int, y: Int): Int = x + y + 1
    }

    val concatF2: (Int, String) => String = new Function2[Int, String, String] {
      override def apply(x: Int, str: String): String = str + x
    }

    println(f0())

    println(f1.apply(3))
    println(f1(3)) // the magic about apply()

    println(f2(3, 5))
    println(concatF2(3, "wow"))

  }

  FunctionImplicit_01

  println

  /**
    * replace the abstract method implementation with the lambda expression too
    * return types are lambda expressions
    */
  println("---------------------- FunctionImplicit_02 --------------------------")

  object FunctionImplicit_02 {


    val f1: Int => Int = (x: Int) => x + 1

    val f0: () => Int = () => 1

    val f2: (Int, Int) => Int = (x: Int, y: Int) => x + y + 1

    val concatF2: (Int, String) => String = (x: Int, str: String) => str + x

    println(f1.apply(3))
    println(f1(3)) // the magic about apply()

    println(f0())
    println(f2(3, 5))
    println(concatF2(3, "wow"))

  }

  FunctionImplicit_02

  println

  /**
    * remove the explicit var type because type inference will kick in
    */
  println("----------------------- FunctionImplicit_03 -----------------------------")

  object FunctionImplicit_03 {


    val f1 = (x: Int) => x + 1

    val f0 = () => 1

    val f2 = (x: Int, y: Int) => x + y + 1

    val concatF2 = (x: Int, str: String) => str + x

    println(f1.apply(3))
    println(f1(3)) // the magic about apply()

    println(f0())
    println(f2(3, 5))
    println(concatF2(3, "wow"))

  }

  FunctionImplicit_03

  println("---------------------- RecapFunctions -----------------------")
  object RecapFunctions{
    val f0_1 = () => 1
    val f0_2: () => Int = () => 2
    val f0_3: () => Int = new Function0[Int] {
      override def apply(): Int = 3
    }

    val f0_4: Function0[Int] = new Function0[Int] {
      override def apply(): Int = 4
    }
    List(f0_1, f0_2, f0_3, f0_4).foreach(println)
    List(f0_1.apply(), f0_2.apply(), f0_3.apply(), f0_4.apply()).foreach(println)
    List(f0_1(), f0_2(), f0_3(), f0_4()).foreach(println)
  }
  RecapFunctions
  println
  /**
    * Tuples as a return type is the key to make a function return multiple values
    * Anyway, you can also think of it as an object wrapper
    */

  println("--------------------- FunctionWithMultipleReturnValues -------------------------")

  object FunctionWithMultipleReturnValues {

    val f: String => (String, Int) = (x: String) => (x, x.size)
    println(f("hh"))

    //we can remove the val type due to type inference
    val f1 = (x: String) => (x, x.size)
    println(f1("hh1"))
  }

  FunctionWithMultipleReturnValues
  println

}
