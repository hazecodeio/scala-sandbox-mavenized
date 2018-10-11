package org.hsmak

object PatternMatchers extends App {

  case class Foo(num: Int, str: String)

  object PatternMatchWithGuards {

    def returnPatternMatch(foo: Foo) = foo match {
      case Foo(num, str) => true
      case _ => false
    }

    println(returnPatternMatch(Foo(1, "hh")))

    def extractFieldsIntoTuples(foo: Foo) = foo match {
      case Foo(num, str) => (num, str) //return Tuple2
      case _ => (0, "NA")
    }

    println(extractFieldsIntoTuples(Foo(1, "hh")))

    def extractFieldsWithGuards(foo: Foo) = foo match {
      case Foo(num, str) if str.trim.nonEmpty => (num, str) //return Tuple2
      case _ => (0, "NA")
    }

    println(extractFieldsWithGuards(Foo(1, " ")))


    def ORingGuards(foo: Foo) = foo match {
      case Foo(0 | 2, "H" | "A") => (-1, "match") //return Tuple2
      case Foo(num, str) if str.trim.nonEmpty => (num, str) //return Tuple2
      case _ => (0, "NA")
    }

    println(ORingGuards(Foo(1, "A")))
    println(ORingGuards(Foo(2, "A")))

    val accepted = List("accepted1", "accepted2")

    def ORingGuards2(foo: Foo) = foo match {
      case Foo(0 | 2, s) if accepted.contains(s) => (-1, "match") //return Tuple2
      case Foo(num, str) if str.trim.nonEmpty => (num, str) //return Tuple2
      case _ => (0, "NA")
    }

    println(ORingGuards2(Foo(1, "A")))
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

}
