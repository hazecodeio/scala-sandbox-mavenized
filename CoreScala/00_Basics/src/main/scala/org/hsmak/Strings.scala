package org.hsmak

/**
  * Strings in Scala are the same String in Java
  */
object Strings extends App {


  println("----------------------- Instantiating Strings --------------------")
  val a = "Scala"
  val b: String = "Scala"
  val c = "Scala": String

  val str: java.lang.String = "Scala"

  val unpooledStr = new String("Scala") //will fail the equality test because this is a reference to another object
  println(unpooledStr == str)

  val d = "\t\n\r" //tab, newline, carriage return

  val e = "\u03BB" //unit code dor lambda
  println(e)

}
