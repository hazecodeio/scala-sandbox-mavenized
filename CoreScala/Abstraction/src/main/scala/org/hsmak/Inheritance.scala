package org.hsmak


object Inheritance extends App {

  object AbstractClass {
    // remember: inheritance inherits both State & Behaviour from the parent (to doscuss further later on private access)
    // val is simply to add an accessor
    // same Java's polymorphism rules apply such object type, reference type, variable type, Is-A relationship etc


    println("################################### Abstract Classes ###################################")

    abstract class Person {
      //abstract methods.. compared to Java we only add 'abstract' to the class. abstract methods don't make use of this keyword
      def firstName: String

      def lastName: String

      //some random concrete methods
      def t(): Unit = {

      }
    }

    case class ConcretePerson(firstName: String, lastName: String) extends Person

    //this would compile because case classes already add accessor for the params. I.e. the params are 'val'
    class AnotherConcretePerson(val firstName: String, val lastName: String) extends Person

    // Notice the 'val', again this is to accessor methods, hence compiler will automatically add the implementation. if param names were different this wouldn't compile!
  }

  AbstractClass
  println

  object tt {

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


    println("################################### Subclassing and Inheritance, overriding methods ###################################")

    /**
      * case classes will automatically add: toString(), equals(), hashCode(), copy(), apply() so no need to use 'new' keyword, CompanionObject?
      * 'val' is the default to constructor params
      * if you don't like what case provides you can always override
      * used in pattern matching
      * it makes the following possiple: val Department(n) = dept// param already assigned in dept will be extracted and assigned to 'n'
      * and so on
      *
      * however, case classes can't be extended/subclassed, maybe because the copy() will always return an instance of the superclass; which violate Liskov Substitution principle
      *
      * @param name
      */
    case class Department(val name: String)

    class Manager(firstName: String,
                  lastName: String,
                  val department: Department) extends Employee(firstName, lastName) {

      //override with keeping default values
      override def copy(firstName: String = this.firstName,
                        lastName: String = this.lastName,
                        age: Int = this.age) = new Manager(firstName, lastName, new Department("temp"))

      //overloading: this will not work because it uses default parameters <- an issue in Scala!!
      //link showing explanation response by Martin: https://stackoverflow.com/questions/4652095/why-does-the-scala-compiler-disallow-overloaded-methods-with-default-arguments
      /*def copy(firstName: String = this.firstName,
               lastName: String = this.lastName,
               age: Int = this.age,
               department: Department = this.department) = new Manager(firstName, lastName, department)*/

      //after removing the default arguments
      def copy(firstName: String,
               lastName: String,
               age: Int,
               department: Department) = new Manager(firstName, lastName, department)

      // using the 'String.equals()' .. However, Scala's '==' is actually a value equality rather than reference equality!!
      /*override def equals(obj: Any): Boolean = {
        //Notice the 'Any' instead of Object in Java
        if (!obj.isInstanceOf[Manager]) false
        else {
          val other = obj.asInstanceOf[Manager]

          //this is a boolean expression if you look carefully, and it's the return value
          other.firstName.equals(this.firstName) &&
            other.lastName.equals(this.lastName) &&
            other.age.equals(this.age)
        }
      }*/

      //Using '==' instead of '.equals()'
      //Scala's '==' is actually a value equality rather than reference equality!!
      // the operator 'eq' is used for reference equality
      /*
      scala> val str1 = new String("a")
      str1: String = a

      scala> val str2 = new String("a")
      str2: String = a

      scala> str1 eq str2
      res5: Boolean = false

      scala> str1 equals str2
      res6: Boolean = true

      scala> str1 == str2
      res7: Boolean = true

       */
      override def equals(obj: Any): Boolean = {
        //Notice the 'Any' instead of Object in Java
        if (!obj.isInstanceOf[Manager]) false
        else {
          val other = obj.asInstanceOf[Manager]

          //this is a boolean expression if you look carefully, and it's the return value
          other.firstName == this.firstName &&
            other.lastName == this.lastName &&
            other.age == this.age
        }
      }

      /**
        * overriding hashCode()
        *
        * @return
        */
      override def hashCode(): Int = {
        //generated by IntelliJ
        /*val state = Seq(department)
        state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)*/

        var result = 19 //var will be used only within this methos so there will be no side effect
        result = 31 * result + firstName.hashCode
        result = 31 * result + lastName.hashCode
        result = 31 * result + age.hashCode
        result

      }
    }
  }

  tt
  println
}
