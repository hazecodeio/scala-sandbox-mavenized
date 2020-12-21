package _oo_basics

import java.time.LocalDate

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

  import java.util.{Date, Locale}

  /*
   * when importing all the names of a package or class, one uses the underscore character (_) instead of the asterisk (*)
   * the asterisk is a valid Scala identifier (e.g. method name)
   */


  /**
    * Created by hsmak on 2/16/17.
    */
  println("--------------- FrenchDate_ViaWeirdWay -------------")

  object FrenchDate_ViaWeirdWay {

    import java.text.DateFormat._

    val date = new Date
    val df = getDateInstance(LONG, Locale.FRANCE)

    // less verbose than "df.format(now)"
    println(df format date)
  }

  FrenchDate_ViaWeirdWay
  println

  println("--------------- FrenchDate_ViaJava8DateTimeAPI -------------")

  // Another better way using the Java 8's Date/Time API
  object FrenchDate_ViaJava8DateTimeAPI {

    import java.time.format.DateTimeFormatter._

    val withFrenchLocale = ofPattern("dd MMMM yyyy").withLocale(Locale.FRENCH)
    val date = LocalDate.now()
    println(date format withFrenchLocale)
    println(withFrenchLocale format date)
  }

  FrenchDate_ViaJava8DateTimeAPI
  println

  println("---------------- USDate_WeirdWay ------------------")

  object USDate_WeirdWay {

    import java.text.DateFormat

    val now = new Date
    val df = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH)
    println(df.format(now))
  }

  USDate_WeirdWay
  println

  println("---------------- USDate_WeirdWay ------------------")

  object USDate_ViaJava8DateTimeAPI {

    import java.time.format.DateTimeFormatter._

    val withUSLocale = ofPattern("dd MMMM yyyy").withLocale(Locale.US)
    val now = LocalDate.now();
    println(now format withUSLocale)
    println(withUSLocale format now)
  }

  USDate_ViaJava8DateTimeAPI
  println

  println("---------------- DateOps -----------------")
  object DateOps {

    import java.time._

    println(LocalDate.now.plusDays(2))
  }

  DateOps
  println
}
