package pattern_matchers

object TryCatchMatchers extends App {

  try {
    throw new Exception("Hello")
  } catch {
    case e => println(e)
  }

  object CustomException {

    // why not case class??
    class MyException(message: String) extends Exception(message)

    val msg = try {
      throw new MyException("Hola!!")
    } catch {
      case e: MyException => s"This is my custom exception: $e"
      case e: Exception => s"oops! Not expected: $e"
      case e => s"Others: $e"
    }

    println(msg)

    val msgAndUnderscor = try {
      throw new MyException("Hola!!")
    } catch {
      case _: MyException => s"This is my custom exception!"
      case _: Exception => s"oops! Not expected!"
      case _ => s"Others!"
    }

    println(msgAndUnderscor)
  }

  CustomException
  println

  object ExceptionWithCaseClass {

    case class Animal(name: String, age: Int)

    // Now this is a case class exception
    case class AnimalTooOldException(name: String, age: Int) extends Exception(s"$name is too old because its age is $age")

    def checkAnimalAge(animal: Animal): String = {
      // since it's a case exception no need to use the keyword 'new'
      if (animal.age > 18) throw AnimalTooOldException(animal.name, animal.age)
      else s"${animal.age} is young!"
    }

    val msg = try {
      checkAnimalAge(Animal("Meow", 15))
    } catch {
      case e: AnimalTooOldException => e.getMessage
      case m => m
    }

    println(msg)

    val msg2 = try {
      checkAnimalAge(Animal("Meow", 19))
    } catch {
      case e: AnimalTooOldException => e.getMessage
      case e => s"Unknown Exception!!!!"
    }

    println(msg2)
  }

  ExceptionWithCaseClass
  println

}
