package org.hsmak

object Exceptions extends App {

  object ThrowingException {

    class Employee(val firstName: String, var lastName: String, val age: Int) {
      //most loaded constructor comes here

      require(firstName.nonEmpty, "First name can't be empty!") // this will throw an exception if first name is empty
      require(lastName.nonEmpty, "last name can't be empty!")

      if (age <= 0)
        throw new IllegalArgumentException("Age can't be negative!")

    }

    println("################################### Exception handling ###################################")
    try {
      new Employee("", "", -1)
    } catch {
      case iae: IllegalArgumentException => println(iae.getMessage) //use Pattern matching for exception. here we're matching on the exception type
    } finally { //optional just as in Java
      println("continuing without issues")
    }

  }

  ThrowingException
  println

}
