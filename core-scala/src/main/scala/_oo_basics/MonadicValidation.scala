package _oo_basics

/**
  * Using Options and Either is more Monadic
  * process flow:
  *     - input in raw format
  *     - pass to a Validator that
  *         - accept the raw format input
  *         - returns Option[Error] if invalid
  *         - returns Email if valid
  */
object MonadicValidation extends App {


  case class Email(name: String, domain: String)


  println("----------------------------- ProceduralWay ----------------------------")

  object ImperativeWay {

    try {
      val email = extractFromEmailString("EmailID@MyDomain.com")
      println(email)
    } catch {
      case e: Throwable => e.printStackTrace()
    }

    def extractFromEmailString(email: String): Email = {
      if (!email.contains("@")) {
        throw new IllegalArgumentException("This is not an email!") // instead use options and either to handle errors and validation
      }
      val splitEmail = email.split("@")
      Email(splitEmail(0), splitEmail(1))
    }
  }

  ImperativeWay
  println


  println("---------------------- Functional Way----------------------------")

  case class ValidationError(message: String)

  /**
    * List of validators
    * notice the lambda type of the list: 'String => Option[ValidationError]'
    */
  val validators: List[String => Option[ValidationError]] = List(isEmail_?)

  /**
    * this is one validator
    *
    * @param input
    * @return
    */
  def isEmail_?(input: String): Option[ValidationError] = {
    if (input.contains("@"))
      None //No error
    else
      Some(ValidationError("ValidationError for not having @")) //return error message
  }

  println("---------------------- FunctionalWay01 ----------------------------")

  object FunctionalWay01 {

    def validateViaValidators(input: String): Either[List[ValidationError], Email] = {
      //filter the errors out
      validators.map(_ (input)).filter(_.isDefined).map(_.get) match {

        case Nil => {
          //empty list means no errors. All validators passed
          val splitInput = input.toLowerCase.split("@")
          Right(Email(splitInput(0), splitInput(1)))
        }

        // retrieve the error messages
        case validationList => Left(validationList)
      }
    }

    println(validateViaValidators("foo"))
    println(validateViaValidators("foo@domain.com"))

  }

  FunctionalWay01
  println


  println("----------------- FunctionalWay02 Simpler---------------------")

  object FunctionalWay02 {

    def validateViaValidators(input: String): Either[List[ValidationError], Email] = {
      // filter the errors out
      //      validators.map(_(input)).filter(_.isDefined).map(_.get) match {
      validators.flatMap(_ (input)) match {// flatten List[Option[ValidationError]] to List[ValidationError]
        case Nil => {
          //empty list means no errors. All validators passed
          val splitInput = input.toLowerCase.split("@")
          Right(Email(splitInput(0), splitInput(1)))
        }

        // retrieve the error messages
        case validationList => Left(validationList)
      }
    }

    println(validateViaValidators("foo"))
    println(validateViaValidators("foo@domain.com"))

  }

  FunctionalWay02
  println


  println("----------------- FunctionalWay03 - Weird ---------------------")

  object FunctionalWay03 {

    def validate(input: Option[String]): Either[ValidationError, Email] = {
      input.map(str => {
        //what if this fails???
        val splitInput = str.toLowerCase.split("@")
        Email(splitInput(0), splitInput(1))
      })
        .toRight(ValidationError("Missing ID"))
    }

    println("---")

    println(validate(None))
    //    println(totheRight(Some("foo")))
    println(validate(Some("foo@domain.com")))
  }

  FunctionalWay03
  println
}
