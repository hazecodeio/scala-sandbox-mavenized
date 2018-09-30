package org.hsmak

object RegularClasses extends App {

  object ClassSimple {
    println("################################### Classes and Constructors ###################################")

    val employee = new Employee("MyFirstName", "MyLastName")

    /**
      *
      * @param firstName //val will create an accessor method firstName()
      * @param lastName  //var will create both accessor & mutator methods lastName() & lastName(String). Precisely, "lastName_=" run javap to see lastName_$eq
      */
    class Employee(val firstName: String, var lastName: String)

    println(employee) //print address location for toString isn't overloaded
    println(employee.firstName) //getter the ScalaStyle way
    println(employee.lastName)
    employee.lastName = "ModifiedMyLastName"
    println(employee.lastName)
  }

  ClassSimple
  println


  println("################################### Overloading Constructor ###################################")

  object ClassOverloading {

    /**
      * overloading constructor using 'this' in a block
      * -
      *     - link: https://www.safaribooksonline.com/videos/learning-path-scala/9781491970850/9781491970850-video256883
      *
      * @param firstName
      * @param lastName
      * @param age
      */
    class Employee(val firstName: String, var lastName: String, val age: Int) {

      /**
        * Overloaded constructor
        *
        * @param firstname
        * @param lastName
        * @return
        */
      def this(firstname: String, lastName: String) = this(firstname, lastName, -1)

      require(firstName.nonEmpty, "First name can't be empty!") // this will throw an exception if first name is empty

      if (age <= 0)
        throw new IllegalArgumentException("Age can't be negative!")


      //all the above lines/spave is part of the constructor!

      println("################################### Methods in a class ###################################")


      /**
        * create a new object by cloning this current object using the supplied default values or any explicitly passed is param
        *
        * @param firstName
        * @param lastName
        * @param age
        * @return
        */
      def copy(firstName: String = this.firstName,
               lastName: String = this.lastName,
               age: Int = this.age) = new Employee(firstName, lastName, age)
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

  ClassOverloading
  println

}
