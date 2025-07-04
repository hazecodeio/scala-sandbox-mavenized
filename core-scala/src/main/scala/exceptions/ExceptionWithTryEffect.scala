package exceptions

object ExceptionWithTryEffect extends App {

    println("--------------- WithoutTryObject ------------------")

    object WithoutTryEffect {
        val goodInt = try {
            asInt("5")
        } catch {
            case nfe: NumberFormatException => 0
        }
        val badInt = try {
            asInt("w")
        } catch {
            // why not let the user deal with the exception hte way they want? the functional way as well?
            case nfe: NumberFormatException => 0
        }
        println("goodInt: " + goodInt)

        def asInt(s: String): Int = s.toInt

        println("badInt: " + badInt)

        println
    }

    WithoutTryEffect
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


        // no need to do Try/Catch
        val successAsInt = asIntWithTry("5")
        val failureAsInt = asIntWithTry("w")

        println("successAsInt: " + successAsInt)
        println("failureAsInt: " + failureAsInt)

        /**
         * Wrap the output in a Try object; either Success or Failure
         *
         * @param s
         * @return
         */
        def asIntWithTry(s: String): Try[Int] = Try(s.toInt)


    }

    TryWithSuccessFailure
    println

    println("------------------ TryWithSuccessFailureAndPatternMatcher ---------------------")

    object TryWithSuccessFailureAndPatternMatcher {

        import scala.util.{Failure, Success, Try}

        val successAsInt = asIntWithTry("5") match {
            case Success(value) => value
            case Failure(ex) => 0
        }
        val failureAsInt = asIntWithTry("w") match {
            case Success(value) => value
            case Failure(ex) => 0
        }
        println("successAsInt: " + successAsInt)

        /**
         * Wrap the output in a Try object; either Success or Failure
         *
         * @param s
         * @return
         */
        def asIntWithTry(s: String): Try[Int] = Try(s.toInt)

        println("failureAsInt: " + failureAsInt)

    }

    TryWithSuccessFailureAndPatternMatcher
    println

    println("------------------ TryWithSuccessFailureAndOption ---------------------")

    object TryWithSuccessFailureAndOption {

        import scala.util.Try

        val successAsInt = asIntWithTry("5").toOption // return Some(5)
        val failureAsInt = asIntWithTry("w").toOption // return None
        println("successAsInt: " + successAsInt)
        println("extract some: " + successAsInt.getOrElse(0))

        /**
         * Wrap the output in a Try object; either Success or Failure
         *
         * @param s
         * @return
         */
        def asIntWithTry(s: String): Try[Int] = Try(s.toInt)

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


    println("------------------ TryWithSuccessFailureAndEither (Left | Right) ---------------------")

    object TryWithSuccessFailureAndEither {

        import scala.util.{Failure, Success, Try}

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

        import scala.util.{Failure, Success, Try}

        def asIntWithTryAndEither(s: String): Either[String, Int] = Try(s.toInt) match {
            case Success(value) => Right(value)
            case Failure(_) => Left(s"Invalid Int [${s}]!")
        }

        println(asIntWithTryAndEither("5") match {
            case Left(errMsg) => errMsg
            case Right(intVal) => intVal
        })
        println(asIntWithTryAndEither("W") match {
            case Left(errMsg) => errMsg
            case Right(intVal) => intVal
        })

    }

    TryWithSuccessFailureAndEitherAndPatternMatcher
    println

    println("------------------ TryWithSuccessFailureAndEitherAndForComprehension ---------------------")

    object TryWithSuccessFailureAndEitherAndForComprehension {

        import scala.util.{Failure, Success, Try}

        /**
         * Either is “right biased”
         *
         * @param s
         * @return
         */
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

    object RightLeftWithEither {

        def tripleEither(x: Either[String, Int]): Either[String, Int] =
            /*x.right.map(triple)*/
            // right() is deprecated. Either is right biased now!
            x.map(triple)

        def triple(x: Int): Int = 3 * x

        println(tripleEither(Right(1)))
        println(tripleEither(Left("not a number")))
    }

    RightLeftWithEither
    println
}
