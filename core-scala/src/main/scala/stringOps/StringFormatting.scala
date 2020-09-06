package stringOps

/**
  * @author ${user.name}
  */
object StringFormatting extends App {

  object StringFormatting {

    println("################################### String formatting ###################################")

    // Java and C way. Not Functional!!
    val str = String.format("This is a %s", "Test1")

    val str2 = "This is a %s".format("Test2") // Scala way, the functional way!!
    println(str)
    println(str2)


  }

  StringFormatting
  println


  /**
    * notice the
    *
    * %1 %2 %3 for ordering
    *
    * $s for String format
    * %t for Time format
    *
    * general Format: %{position}${format}
    */
  object StringFormattingWithOrderedArguments {

    println("################################### StringInterpolationWithOrderedArguments ###################################")

    println("This is the 3rd argument (%3$s), the 2nd (%2$s), and the 1st (%1$s)".format("FirstArg", "SecondArg", "ThirdArg"))

  }

  StringFormattingWithOrderedArguments
  println

  /**
    * notice the '%1' because we only have one argument
    */
  object DateFormattingAndOrderedArguments {

    println("################################### DateFormatting ###################################")

    import java.time._

    println("We will be eating lunch on (%1$tB) the (%1$te) in the year (%1$tY).".format(LocalDate.now()))
    printf("We will be eating lunch on (%1$tB) the (%1$te) in the year (%1$tY).", LocalDate.now())
    println("\n")

    val str =
      """We will be eating lunch on (%1$tB)
        |the (%1$te)
        |in the year (%1$tY).""".stripMargin.format(LocalDate.now())
    println(str)

  }

  DateFormattingAndOrderedArguments
  println

}
