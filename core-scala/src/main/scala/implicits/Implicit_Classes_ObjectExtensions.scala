package implicits

object Implicit_Classes_ObjectExtensions extends App {

    object ImplicitInterpolator {

        /**
         * implicit interpolator
         *
         * @param sc
         */
        implicit class MagicInterpolator(val sc: StringContext) {

            /**
             * method name 'm' will be used to call the interpolator
             *
             * @param parameters
             */
            def m(parameters: Any*) = {
                println(sc.parts)
                println(parameters)
            }
        }

        val name = "Scala"

        /**
         * WrappedArray(Hello , )
         * WrappedArray(Scala)
         */
        m"Hello $name"
        println


        /**
         * WrappedArray(Hello ,  , )
         * WrappedArray(Scala, Scala)
         */
        m"Hello $name $name"
        println

    }

    ImplicitInterpolator
    println

    println("------------- ImplicitInterpolator_WithPatternMatchers ---------------")

    object ImplicitInterpolator_WithPatternMatchers {

        implicit class MagicInterpolator(val sc: StringContext) {

            def m(parameters: Any*) = {

                //This Zip emits a Seq[Tuple2]
                sc.parts.zipAll(parameters, "", "").map {
                    case ("", param) => param
                    case (part, "") => part
                    case (part, param) => s"$part:magic:$param"
                }.mkString("\n")
            }

        }

        val name = "Scala"
        println(m"Hello $name") //parts = params
        println

        println(m"Hello There $name") //parts > params
        println

        println(m"Hello $name $name") //parts < param
        println


    }

    ImplicitInterpolator_WithPatternMatchers
    println


    println("--------------- ImplicitClassesAndObject -------------------")

    object ImplicitClassesAndObject {

        /**
         * String objects will have this new method "poundify"
         *
         * @param str
         */
        implicit class PoundifyString(val str: String) {
            def poundify: String = str.replace(" ", "$")
        }

        val statement = "Hello this is a statement"
        println(statement)
        println(statement.poundify) // notice method "poundify" is recognized
    }

    ImplicitClassesAndObject
}
