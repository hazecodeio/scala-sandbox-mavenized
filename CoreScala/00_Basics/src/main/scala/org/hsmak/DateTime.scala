package org.hsmak

/*
 * ToDo:
 *    - Apply the Java 8's new Date/Time API
 *    - Apply the Java 9's factory methods such as "ofInstant()"
 *    - Convert between different formats: SQL, old formats, etc
 *    - Others?
 */
object DateTime extends App {

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
  println("--------------- FrenchDate -------------")
  object FrenchDate {

    val now = new Date
    val df = getDateInstance(LONG, Locale.FRANCE)

    // less verbose than "df.format(now)"
    println(df format now)
  }

  FrenchDate
  println

  println("---------------- USDate ------------------")
  object USDate {
    val now = new Date
    val df = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH)
    println(df.format(now))
  }

  USDate
  println

  import java.time._
  println(LocalDate.now.plusDays(2))
}
