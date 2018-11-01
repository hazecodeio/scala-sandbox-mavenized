package org.hsmak


/**
  * @author ${user.name}
  */
object StringInterpolation extends App {

  object Interpolations {

    println("################################### S Interpolation Vs F interpolation ###################################")

    val num = 53.126456
    println(s"This an S Interpolation: ${num}")
    println(f"This an F Interpolation: ${num}%1.2f") //notice the floating format "${val}%..." <- ${val}%width.precision

    //also you can mix up multil-line string with the above interpolations
    println(
      s"""This is the first line
         |And this is the second line with interpolating this val: ${num}
     """.stripMargin)


  }

  Interpolations
  println

  println("############################### ArithmeticCalcViaStringInterpolation ############################### ")

  /**
    * This is very simple
    * not taking precedence into account
    */
  object ArithmeticCalcViaStringInterpolation {

    val add = (a: Int, b: Int) => a + b
    val multiply = (a: Int, b: Int) => a * b

    implicit class FoldInterpolator(val sc: StringContext) extends AnyVal {

      def fold(parameters: Any*) = {

        //        (sc.parts, parameters)

        /////// refactor //////////////

        val ints = sc.parts.map(_.trim.toInt)
        (ints, parameters)

        ////////// refactor ///////////////

        val ops = parameters.collect {
          case binary: Function2[_, _, _] => binary.asInstanceOf[Function2[Int, Int, Int]]
        }

//        ops(0).apply(ints(0), ints(1))

        /////refactor///

        // Similar to induction//
        // base step: first operator and the two operands
        val start = ops(0).apply(ints(0), ints(1))

        // subsequent steps: starting from the previous step, fold the subsequent ones on it
        ops.drop(1).zip(ints.drop(2)).foldLeft(start){
          case (latest, (op, int)) => op(latest, int)
        }
      }


    }


//    println(fold"1 $add 2 $add 3 $multiply 5"._1)
//    println(fold"1 $add 2 $add 3 $multiply 5"._2)

    //// refactor

    println(fold"1 $add 2 $add 3 $multiply 5")


  }

  ArithmeticCalcViaStringInterpolation
  println

  println("####################### XMLInterpolator #####################")
  object XMLInterpolator {

    implicit class XmlInterpolator(val sc: StringContext) extends AnyVal {
      def xml(paramaters: Any*) = {
        val str = sc.parts.zipAll(paramaters, "", "").collect{
          case (part, param) => part + param.toString
        }.mkString("")

        import scala.xml.XML
        XML.loadString(str)// string must contain some xml elements such as <div>
      }
    }

    println(xml"<div>a b c</div>")

    println(xml"<div>a ${5} b c</div>")

    println(xml"<div>a ${5} ${"hello".split("e").toList} b c</div>")

    println(xml"<div>a ${5} ${<b>thing</b>} b c</div>")

  }
  XMLInterpolator
  println

}
