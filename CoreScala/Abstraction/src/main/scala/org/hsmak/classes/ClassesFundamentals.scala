package org.hsmak.classes

object ClassesFundamentals extends App {

  object ClassSimple {
    println("################################### Classes and Constructors ###################################")

    /**
      *
      * @param firstName //val will create an accessor method firstName()
      * @param lastName  //var will create both accessor & mutator methods lastName() & lastName(String). Precisely, "lastName_=" run javap to see lastName_$eq
      */
    class Employee(val firstName: String, //val will generate only getter
                   var lastName: String)

    //var will generate both setters/getters

    val employee = new Employee("MyFirstName", "MyLastName")


    println(employee) //print address location for toString() isn't overloaded
    println(employee.firstName) //getter the ScalaStyle way
    println(employee.lastName)
    employee.lastName = "ModifiedMyLastName"
    println(employee.lastName)
  }

  ClassSimple
  println


  println("################################### Overloading Constructor using 'keyword this' ###################################")

  object ConstructorOverloadingUsingKeywordThis {

    /**
      * overloading constructor using 'this' in a block
      * -
      *     - link: https://www.safaribooksonline.com/videos/learning-path-scala/9781491970850/9781491970850-video256883
      *
      * @param firstName
      * @param lastName
      * @param age
      */
    class Employee(val firstName: String,
                   var lastName: String,
                   val age: Int) {

      //loaded constructor comes here

      /**
        * Overloaded constructor
        *
        * @param firstName
        * @param lastName
        * @return
        */
      def this(firstName: String, lastName: String) = this(firstName, lastName, -1)

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

  }

  ConstructorOverloadingUsingKeywordThis
  println

  println("########################## ConstructorWithNamedArgument #################################")

  object ConstructorWithNamedArgument {

    case class Employee(val firstName: String, var lastName: String)

    println(Employee(lastName = "llll", firstName = "ffff"))
  }

  ConstructorWithNamedArgument
  println


  println("########################## ConstructorWithDefaultArgument #################################")

  object ConstructorWithDefaultArgument {

    case class Employee(val firstName: String, var lastName: String, age:Int = 30)//supplying default value


    println(Employee(lastName = "llll", firstName = "ffff"))// this actually calls the constructor with the age argument
  }

  ConstructorWithDefaultArgument
  println

}
