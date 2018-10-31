package org.hsmak

object Implicit_Classes_ObjectExtensions extends App {

  object ImplicitInterpolator {

    implicit class MagicInterpolator(val sc: StringContext) {
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

  println("------------- ImplicitInterpolator_02 ---------------")

  object ImplicitInterpolator_02 {

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

    println(m"Hello There $name")//parts > params
    println

    println(m"Hello $name $name")//parts < param
    println


  }

  ImplicitInterpolator_02
  println

  object ExperimentingZippingAll {

    def `LeftSeq = RightSeq`: Unit = println(Seq(1, 2, 3, 4).zipAll(Seq(5, 6, 7, 8), "L", "R"))

    def `LeftSeq > RightSeq`: Unit = println(Seq(1).zipAll(Seq(2, 3, 4, 5, 6, 7, 8), "L", "R"))

    def `LeftSeq < RightSeq`: Unit = println(Seq(1, 2, 3, 4, 5, 6, 7).zipAll(Seq(8), "L", "R"))

    `LeftSeq = RightSeq`
    `LeftSeq > RightSeq`
    `LeftSeq < RightSeq`
  }

  ExperimentingZippingAll

  println("--------------- ImplicitObject_IE_Wrappers_!? -------------------")
  object ImplicitObject_IE_Wrappers_!? {

    /**
      * String objects will have this new method "poundify"
      *
      * @param str
      */
    implicit class PoundifyString(val str: String){
      def poundify: String = str.replace(" ", "$")
    }

    val statement = "Hello this is a statement"
    println(statement)
    println(statement.poundify)// notice method "poundify" is recognized
  }
  ImplicitObject_IE_Wrappers_!?
}
