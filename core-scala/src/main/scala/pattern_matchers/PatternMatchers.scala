package pattern_matchers

object PatternMatchers extends App {

    case class Foo(num: Int, str: String)

    println("------------------ PatternMatchWithGuards ---------------------")

    object PatternMatchWithGuards {

        def returnPatternMatch(foo: Foo) = foo match {
            case Foo(num, str) => true
            case _ => false
        }

        println("returnPatternMatch:")
        println(returnPatternMatch(Foo(1, "hh")))

        def extractFieldsIntoTuples(foo: Foo) = foo match {
            case Foo(num, str) => (num, str) //return Tuple2
            case _ => (0, "NA")
        }

        print("extractFieldsIntoTuples:")
        println(extractFieldsIntoTuples(Foo(1, "hh")))

        def extractFieldsWithGuards(foo: Foo) = foo match {
            case Foo(num, str) if str.trim.nonEmpty => (num, str) //return Tuple2
            case _ => (0, "NA")
        }

        print("extractFieldsWithGuards:")
        println(extractFieldsWithGuards(Foo(1, " ")))


        def ORingGuards(foo: Foo) = foo match {
            case Foo(0 | 2, "H" | "A") => (-1, "match") //return Tuple2
            case Foo(num, str) if str.trim.nonEmpty => (num, str) //return Tuple2
            case _ => (0, "NA")
        }

        print("ORingGuards:")
        println(ORingGuards(Foo(1, "A")))
        print("ORingGuards:")
        println(ORingGuards(Foo(2, "A")))

        val accepted = List("accepted1", "accepted2")

        def ORingGuards2(foo: Foo) = foo match {
            case Foo(0 | 2, s) if accepted.contains(s) => (-1, "match") //return Tuple2
            case Foo(num, str) if str.trim.nonEmpty => (num, str) //return Tuple2
            case _ => (0, "NA")
        }

        print("ORingGuards2:")
        println(ORingGuards2(Foo(1, "A")))
        print("ORingGuards2:")
        println(ORingGuards2(Foo(2, "accepted1")))

    }

    PatternMatchWithGuards
    println

    println("-------------- PatternMatchWithOptions -----------------")

    object PatternMatchWithOptions {
        def fun(input: Option[String]) = input match {
            case Some(s) if s.trim.nonEmpty => Some(s.length)
            case _ => None
        }

        println(fun(Some("Hello")))
        println(fun(Some("")))
        println(fun(None))
        println

        println("--matchTheFunctionalWay--")

        def matchTheFunctionalWay(input: Option[String]) = input.map(_.trim).filter(_.nonEmpty).map(_.length)

        println(matchTheFunctionalWay(Some("Hello")))
        println(matchTheFunctionalWay(Some("")))
        println(matchTheFunctionalWay(Some("  ")))
        println(matchTheFunctionalWay(Some("  WW  ")))
        println(matchTheFunctionalWay(None))
    }

    PatternMatchWithOptions
    println

    println("-------------- PatternMatchWithTypeCasting -----------------")

    /**
     * Casting is powerful when done using Pattern Matching.
     * It's done this way in all Scala
     */
    object PatternMatchWithTypeCasting {
        def fun(input: Any) = input match {
            case in: String => Some(in) //if input is of type String then return something
            case _ => None

        }

        println(fun("Hello World"))
        println(fun(3))
    }

    PatternMatchWithTypeCasting
    println

    println("-------------- PatternMatchWithOperator_@ -----------------")

    object PatternMatchWithOperator_@ {
        def fun(input: Int): String = {
            input match {
                case num@(1 | 5) => s"retrieved num is $num"
                case _ => "retrieve num is neither 1 nor 5"
            }
        }

        print("PatternMatchWithOperator_@: ")
        println(fun(1))
        print("PatternMatchWithOperator_@: ")
        println(fun(2))
    }

    PatternMatchWithOperator_@
    println

    println("-------------- PatternMatchWithCollections -----------------")

    object Listing {
        val list = List(1, 2)
        val t = list match {
            // match the exact number of elements in the list
            case List(a, b) => (a, b) // extract the 2 elements and return a Tuple2

        }
        println(t)

        val t2 = list match {
            // exactly same as previous case
            case a :: b :: Nil => (a, b)
        }

        val list2 = List(1, 2, 3, 4, 5)
        val t3 = list2 match {
            case a :: b :: tail => s"extracting named variables a=$a and b=$b and the tail stored in named variable rest=$tail"
        }
        println(t3)

        val skippingElem = list2 match {
            case a :: _ :: b :: tail => s"extracting named variables of 1st elem (a=$a) and 3rd elem (b=$b) and the tail stored in named variable rest=$tail"
        }
        println(skippingElem)

        val skippingElemAndNotacreaAboutLast = list2 match {
            // the last underscore '_' means either Nil, or 1+ elements
            case a :: _ :: b :: _ => s"extracting named variables of 1st elem (a=$a) and 3rd elem (b=$b)"
        }
        println(skippingElemAndNotacreaAboutLast)
    }

    Listing
    println

    println("----------------- SpecialMatchingForSequence -------------------")

    object SpecialMatchingForSequence {

        val sequence = Seq(1, 2, 3, 4, 5, 6)
        val s = sequence match {
            case Seq(a, b, _*) => s"extracting named variables of elem (a=$a) and elem (b=$b)"
        }
        println(s)

        // notice the '@_*'
        val s2 = sequence match {
            case Seq(a, b, tail@_*) => s"extracting named variables of elem (a=$a) and elem (b=$b) and the tail stored in named variable tail=$tail"
        }
        println(s2)
    }

    SpecialMatchingForSequence
    println

    println("----------------- Matching with Map <- really matching with the extracted Tuple2 -------------------")

    object MatchingWithMap {
        val m = Map("Huss" -> 22, "Fred" -> 40)

        val list = m.map {
            case (name, age) => s"name($name) and age($age)"
        } //return list of concatenated key/value pairs
        println(list)

        val newMap = m.map {
            case (name, age) => (name + " Last" -> age)
        } //return map of the new tuples
        println(newMap)
    }


    MatchingWithMap
    println
}
