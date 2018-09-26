package org.hsmak

object Classes extends App {

  //################################### Classes and Constructors ###################################

  /**
    *
    * @param firstName //val will create an accessor method firstName()
    * @param lastName  //var will create both accessor & mutator methods lastName() & lastName(String). Precisely, "lastName_=" run javap to see lastName_$eq
    */
  class Employee(val firstName: String, var lastName: String)


  import scala.beans.BeanProperty

  /**
    * Notice @BeanProperty: this will generate the Java style getters/setters; beside the scala style
    * run 'javap -p EmployeeJavaStyle'
    *
    * @param firstName
    * @param lastName
    */
  class EmployeeJavaStyle(@BeanProperty val firstName: String, @BeanProperty var lastName: String)

  /**
    * overloading constructor using 'this' in a block
    * link: https://www.safaribooksonline.com/videos/learning-path-scala/9781491970850/9781491970850-video256883
    *
    * @param firstName
    * @param lastName
    * @param age
    */
  class EmployeeOverload(val firstName: String, var lastName: String, val age: Int) {
    def this(firstname: String, lastName: String) = this(firstname, lastName, -1)

    require(firstName.nonEmpty, "First name can't be empty!") // this will throw an exception if first name is empty

    if (age <= 0)
      throw new IllegalArgumentException("Age can't be negative!")


    //all the above lines/spave is part of the constructor!

    //################################### Methods in a class ###################################


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
             age: Int = this.age) = new EmployeeOverload(firstName, lastName, age)
  }

  //################################### Exception handling ###################################
  try {
    new EmployeeOverload("", "", -1)
  } catch {
    case iae: IllegalArgumentException => println(iae.getMessage) //use Pattern matching for exception. here we're matching on the exception type
  } finally { //optional just as in Java
    println("continuing without issues")
  }
  ///////////////////

  /*
    equivalent to nested loop
    useful in multi dimentional matrices
   */
  for (i <- 1 to 5; j <- 6 to 10) {
    println("i: " + i)
    println("j: " + j)
  }


  //################################### Subclassing and Inheritance, overriding methods ###################################

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
                val department: Department) extends EmployeeOverload(firstName, lastName) {

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

  // remember: inheritance inherits both State & Behaviour from the parent (to doscuss further later on private access)
  // val is simply to add an accessor
  // same Java's polymorphism rules apply such object type, reference type, variable type, Is-A relationship etc


  //################################### Abstract Classes ###################################

  abstract class Person {
    //abstract methods.. compared to Java we only add 'abstract' to the class. abstract methods don't make use of this keyword
    def firstName: String

    def lastName: String

    //some random concrete methods
    def t(): Unit = {

    }
  }

  case class ConcPer(firstName: String, lastName: String) extends Person

  //this would compile because case classes already add accessor for the params. I.e. the params are 'val'
  class AnotherConc(val firstName: String, val lastName: String) extends Person

  // Notice the 'val', again this is to accessor methods, hence compiler will automatically add the implementation. if param names were different this wouldn't compile!


  //################################### Parametrized Classes ###################################

  case class Box[T](t: T) {
    //    def coupleWith[U](u:U):Box[Couple[T, U]] = new Box(new Couple(t, u)) //we can remove the 'new' keyword since Box is a case clase
    def coupleWith[U](u: U): Box[Couple[T, U]] = Box(Couple(t, u))
  }

  // in Java -> public class Box<T>{public Box(T t)}

  val intBox1 = new Box(1)
  // 'new' keyword is unnecessary
  val intBox2 = Box[Int](1)
  val intBox3: Box[Int] = Box(1)
  val intBox4 = Box(1): Box[Int] // coercion

  val doubleBoxBox = Box(Box(4.0))
  println(s"doubleBoxBox: ${doubleBoxBox.t.t}")

  case class Couple[A, B](first: A, second: B) {
    //    def swap(): Couple[B, A] = new Couple[B, A](second, first)
    def swap() = new Couple[B, A](second, first) // return type will be inferred
  }

  println(Couple(10, "Scala"))
  println(new Couple(10, "Scala"))
  println(new Couple(10, "Scala").toString)
  println(Couple[Int, String](10, "Scala"))
  val couple = new Couple(10, "Scala")
  println(couple)
  Couple("Hello", Couple(3, 22.2))

  //################################### Parametrized Methods in their Classes ###################################
  // covered already


  /////////////////////////////////////////////////////////////////////////////////

  /*
    defining a function inside a function
   */
  def printPrimes() {
    val primeList = List(1, 2, 3, 5, 7, 11)
    for (i <- primeList) {
      if (i == 11)
        return
      if (i != 1)
        println(i)
    }
  }

  Console println "\nPrimes:"
  printPrimes


}
