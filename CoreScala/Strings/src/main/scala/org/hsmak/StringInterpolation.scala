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


}
