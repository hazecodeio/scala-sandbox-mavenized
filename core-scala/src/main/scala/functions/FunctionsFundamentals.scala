package functions

/**
 * Java8 Functions have a major impact on how Scala implements its functions
 * Functions are traits that we instantiate anonymously.
 */
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

    println("------------------------ FunctionWithTrait ---------------------")

    object FunctionWithTrait {

        val f1: Function1[Int, Int] = new Function[Int, Int] { // An alias as in: type Function[-A, +B] = Function1[A, B]
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

    FunctionWithTrait

    println

    /**
     * replace the return type with the lambda expression
     */
    println("--------------------- FunctionWithLambda_01 ----------------------------")

    object FunctionWithLambda_01 {

        val f0: () => Int = new Function0[Int] {
            override def apply(): Int = 1
        }

        val f1: Int => Int = new Function[Int, Int] { // An alias as in: type Function[-A, +B] = Function1[A, B]
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

    FunctionWithLambda_01

    println

    /**
     * replace the abstract method implementation with the lambda expression too
     * return types are lambda expressions
     */
    println("---------------------- FunctionWithLambda_02 --------------------------")

    object FunctionWithLambda_02 {


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

    FunctionWithLambda_02

    println

    /**
     * remove the WithTrait var type because type inference will kick in
     */
    println("----------------------- FunctionWithLambda_03 -----------------------------")

    object FunctionWithLambda_03 {


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

    FunctionWithLambda_03

    println("---------------------- RecapFunctions_0: zero param and one return type -----------------------")

    object RecapFunctions_0 {

        //This is how it will be wherever you code
        val f0_1 = () => 1

        // ValName:(Type) = Lambda <- which equivalent to implementing a trait that has one method
        val f0_2: (() => Int) = () => 2

        // Similar to creating an anonymous class by implementing an interface; in this case it's a Trait that has one method 'apply()'
        val f0_3: () => Int = new Function0[Int] {
            override def apply(): Int = 3
        }
        //same as the previous one but with specifying the trait type instead of the lambda type
        val f0_4: Function0[Int] = new Function0[Int] {
            override def apply(): Int = 4
        }

        List(f0_1, f0_2, f0_3, f0_4).foreach(println);
        println
        List(f0_1.apply(), f0_2.apply(), f0_3.apply(), f0_4.apply()).foreach(println);
        println
        List(f0_1(), f0_2(), f0_3(), f0_4()).foreach(println);
        println
    }

    RecapFunctions_0
    println

    println("---------------------- RecapFunctions_1: zero param and one return type -----------------------")

    object RecapFunctions_1 {

        //This is how it will be wherever you code
        val f1_1 = (x: Int) => x + 1

        // ValName:(Type) = Lambda <- which equivalent to implementing a trait that has one method
        val f1_2: (Int => Int) = (x: Int) => x + 1

        // Similar to creating an anonymous class by implementing an interface; in this case it's a Trait that has one method 'apply()'
        val f1_3: (Int => Int) = new Function1[Int, Int] {
            override def apply(x: Int) = x + 1
        }

        //same as the previous one but with specifying the trait type instead of the lambda type
        val f1_4: Function1[Int, Int] = new Function1[Int, Int] {
            override def apply(x: Int) = x + 1
        }

        List(f1_1, f1_2, f1_3, f1_4) foreach println
        List(f1_1.apply(1), f1_2.apply(2), f1_3.apply(3), f1_4.apply(4)) mkString (" ** ") foreach print
        println
        List(f1_1(1), f1_2(2), f1_3(3), f1_4(4)) mkString (" ** ") foreach print
        println

        val fs = List(f1_1, f1_2, f1_3, f1_4)
        (0 to fs.length - 1).map(i => (i -> fs(i))).map(t => t._2(t._1 + 1)).mkString(" ** ").foreach(print)
        println

    }

    RecapFunctions_1
    println

    println("-----------------------NoMoreThanFunc22 ------------------------")

    object NoMoreThanFunc22 {
        val funct22: (Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int, Int) => String
        = (x1: Int, x2: Int, x3: Int, x4: Int, x5: Int, x6: Int, x7: Int, x8: Int, x9: Int, x10: Int, x11: Int, x12: Int, x13: Int, x14: Int, x15: Int, x16: Int, x17: Int, x18: Int, x19: Int, x20: Int, x21: Int, x22: Int) => "no more than 22 param"

        println(funct22(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 28, 29, 20, 21, 22))
    }

    NoMoreThanFunc22
    println

    /**
     * Tuples as a return type is the key to make a function return multiple values
     * Anyway, you can also think of it as an object wrapper
     */

    println("--------------------- FunctionWithMultipleReturnValues using Tuples -------------------------")

    object FunctionWithMultipleReturnValues {

        val f: String => (String, Int) = (x: String) => (x, x.size)
        println(f("hh"))

        //we can remove the val type due to type inference
        val f1 = (x: String) => (x, x.size)
        println(f1("hh1"))

    }

    FunctionWithMultipleReturnValues
    println

    object TrimmingWithUnderscore {
        /**
         * The first _ represents the first argument
         * The second _ represents the second argument
         * The third _ represents the third argument
         */
        val ff: (Int, Int, String) => String = _ + _ + _
    }

    TrimmingWithUnderscore
}
