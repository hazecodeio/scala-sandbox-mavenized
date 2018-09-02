package org.hsmak

/*
 * Multiple classes can be imported from the same package by enclosing them in curly braces as on the first line.
 */
import java.text.DateFormat
import java.util.{Date, Locale}

/*
 * when importing all the names of a package or class, one uses the underscore character (_) instead of the asterisk (*)
 * the asterisk is a valid Scala identifier (e.g. method name)
 */
import java.text.DateFormat._

/**
  * Created by hsmak on 2/16/17.
  */
object FrenchDate {
  def main(args: Array[String]) {
    val now = new Date
    val df = getDateInstance(LONG, Locale.FRANCE)

    // less verbose than "df.format(now)"
    println(df format now)
  }
}

object USDate extends App {
  val now = new Date
  val df = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH)
  println(df.format(now))

}