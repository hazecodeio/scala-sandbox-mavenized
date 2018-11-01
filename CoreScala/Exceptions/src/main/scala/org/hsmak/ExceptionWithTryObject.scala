package org.hsmak

object ExceptionWithTryObject extends App {

  println("--------------- WithoutTryObject ------------------")

  object WithoutTryObject {
    def asInt(s: String): Int = s.toInt

    val goodInt = try {
      asInt("5")
    } catch {
      case nfe: NumberFormatException => 0
    }
    println("goodInt: " + goodInt)

    val badInt = try {
      asInt("w")
    } catch {
      // why not let the user deal with the exception hte way they want? the functional way as well?
      case nfe: NumberFormatException => 0
    }
    println("badInt: " + badInt)

    println
  }

  WithoutTryObject
  println

  println("--------------- TryWithSuccessFailure ------------------")

  //ToDo: Chained fluid exception handling: TryWithSuccessFailure
  /**
    * handle exception the functional way
    * let the user
    */
  object TryWithSuccessFailure {


    ///////////better way///////////

    import scala.util.Try


    /**
      * Wrap the output in a Try object; either Success or Failure
      *
      * @param s
      * @return
      */
    def asIntWithTry(s: String): Try[Int] = Try(s.toInt)

    // no need to do Try/Catch
    val successAsInt = asIntWithTry("5")
    println("successAsInt: " + successAsInt)

    val failureAsInt = asIntWithTry("w")
    println("failureAsInt: " + failureAsInt)

  }

  TryWithSuccessFailure
  println

  println("------------------ TryWithSuccessFailureAndPatternMatcher ---------------------")

  object TryWithSuccessFailureAndPatternMatcher {

    import scala.util.{Failure, Success, Try}

    /**
      * Wrap the output in a Try object; either Success or Failure
      *
      * @param s
      * @return
      */
    def asIntWithTry(s: String): Try[Int] = Try(s.toInt)


    val successAsInt = asIntWithTry("5") match {
      case Success(value) => value
      case Failure(exception) => 0
    }
    println("successAsInt: " + successAsInt)


    val failureAsInt = asIntWithTry("w") match {
      case Success(value) => value
      case Failure(exception) => 0
    }
    println("failureAsInt: " + failureAsInt)

  }

  TryWithSuccessFailureAndPatternMatcher
  println

  println("------------------ TryWithSuccessFailureAndOption ---------------------")

  object TryWithSuccessFailureAndOption {

    import scala.util.Try

    /**
      * Wrap the output in a Try object; either Success or Failure
      *
      * @param s
      * @return
      */
    def asIntWithTry(s: String): Try[Int] = Try(s.toInt)


    val successAsInt = asIntWithTry("5").toOption // return Some(5)
    println("successAsInt: " + successAsInt)
    println("extract some: " + successAsInt.getOrElse(0))


    val failureAsInt = asIntWithTry("w").toOption // return None
    println("failureAsInt: " + failureAsInt)
    println("extract for None: " + failureAsInt.getOrElse(0))

  }

  TryWithSuccessFailureAndOption
  println


  println("------------------ TryWithSuccessFailureAndForComprehension ---------------------")

  object TryWithSuccessFailureAndForComprehension {

    import scala.util.Try

    /**
      * Wrap the output in a Try object; either Success or Failure
      *
      * @param s
      * @return
      */
    def asIntWithTry(s: String): Try[Int] = Try(s.toInt)


    for (successAsInt <- asIntWithTry("5")) println("successAsInt: " + successAsInt)

    //this will print nothing because it returned None!!
    for (failureAsInt <- asIntWithTry("w")) println("failureAsInt: " + failureAsInt)

  }

  TryWithSuccessFailureAndForComprehension
  println


  println("------------------ TryWithSuccessFailureAndEither (Right | Left) ---------------------")

  object TryWithSuccessFailureAndEither {

    import scala.util.{Try, Success, Failure}

    val r = Right("R")
    val l = Left("L")
    val list = List(Try(r), Try(l))
    println(list.map(_.toString))

    def asIntWithTryAndEither(s: String): Either[String, Int] = Try(s.toInt) match {
      case Success(value) => Right(value)
      case Failure(_) => Left(s"Invalid Int [${s}]!")
    }

    println(asIntWithTryAndEither("5"))
    println(asIntWithTryAndEither("W"))

  }

  TryWithSuccessFailureAndEither
  println

  println("------------------ TryWithSuccessFailureAndEitherAndPatternMatcher ---------------------")

  object TryWithSuccessFailureAndEitherAndPatternMatcher {

    import scala.util.{Try, Success, Failure}

    def asIntWithTryAndEither(s: String): Either[String, Int] = Try(s.toInt) match {
      case Success(value) => Right(value)
      case Failure(_) => Left(s"Invalid Int [${s}]!")
    }

    println(asIntWithTryAndEither("5") match {
      case Left(error) => error
      case Right(int) => int
    })
    println(asIntWithTryAndEither("W") match {
      case Left(error) => error
      case Right(int) => int
    })

  }

  TryWithSuccessFailureAndEitherAndPatternMatcher
  println

  println("------------------ TryWithSuccessFailureAndEitherAndForComprehension ---------------------")

  object TryWithSuccessFailureAndEitherAndForComprehension {

    import scala.util.{Try, Success, Failure}

    def asIntWithTryAndEither(s: String): Either[String, Int] = Try(s.toInt) match {
      case Success(value) => Right(value)
      case Failure(_) => Left(s"Invalid Int [${s}]!")
    }

    for (int <- asIntWithTryAndEither("5")) println(int)

    //this will print nothing because Left acts like None!!
    for (int <- asIntWithTryAndEither("W")) println(int)

  }

  TryWithSuccessFailureAndEitherAndForComprehension
  println

}
