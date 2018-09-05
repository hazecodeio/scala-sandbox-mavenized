package org.hsmak

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

/**
  * Hello world!
  *
  */
object App extends App {
  println("Hello World!")
  val doubleExplicit: Double = 400
  println(doubleExplicit.getClass)
  val doubleCoerced = 400: Double // coercing
  println(doubleCoerced.getClass)


  lazy val n = {
    println("wwwww");
    5
  } //instantiated when it's first called hence lazy val. This is only for val not var
  println(n)

  lazy val a1 = 10 + b1 // without lazy this won't be possible
  lazy val b1 = 5
  println(s"a1 + b1 = ${a1}")

  //bending var/val to a free text
  val `This is a val` = 123 // the backticks also allow to use keywords as var/val such as `import`
  println(`This is a val`)

  //the use of OPCHAR by appending '_' right before the OPCHAR such as '?' '!' etc
  val hola_? = 5555 // without the '_', it's a compile error

  //functional loop
  val result = (1 to 5).reverse.mkString(", ")
  println(result)
  val steppingBy2 = (1 to 10 by 2).mkString(", ")
  println(steppingBy2)


  val xs = List(1, 2, 3, 4, 5)
  var resultL = List[Int]()
  for (a <- xs) {
    resultL = resultL :+ (a + 1) //append to list. note List must be var for append to work
  }
  println(resultL)

  /*
    notice how the for loop is used here to do formatting/filtering
    notice the {} which can be replaced by () with a little modification (Maybe??)
   */
  val even = for {i <- 1 to 20
                  if (i % 2) == 0
  } yield i // yield to a collection. This is hoe to makwe a Functional for loop. Notice even is a val not var

  for (i <- even)
    println(i)
  //print the functional way
  println("use ForEach:")
  even.foreach(println) //inferred lamda expression
  even.foreach(a => println(a)) //expanded lamda expression

  println(
    """Hey this is amultiline string
      |this is a 2nd line
    """.stripMargin)

  println(
    """This si another multiline string
      @margin is not | but @
    """.stripMargin('@'))


  //################################### RegEx ###################################
  val message = "we are meetin on June 13th of this year, and having lunch at 12:30PM"
  val regex = """(\s|[0-9])?[0-9]:[0-5][0-9]\s*(AM|PM)""".r
  println("Found RegEx: " + regex.findAllMatchIn(message).toList)


  //################################### String formatting ###################################
  val str = String.format("This is a %s", "Test1")
  // Java and C way. Not Functional!!
  val str2 = "This is a %s".format("Test2") // Scala way, the functional way!!
  println(str)
  println(str2)

  import java.time._

  println(LocalDate.now.plusDays(2))


  //################################### S Interpolation Vs F interpolation ###################################
  val q = 50.126456
  println(s"This an S Interpolation: ${q}")
  println(f"This an F Interpolation: ${q}%1.2f") //notice the floating format "${val}%..."
  //also you can mix up multil-line string with the above interpolations
  println(
    s"""kjjgf
       |sdfgsdfg ${q}
     """.stripMargin)


  //Different Types returned by a method/function
  def add(x: Int, y: Int) = { // Remember that 'Any' is the the parent of AnyRef and AnyVal. Therefore, the return common type for both here is 'Any'
    if (x > 10) (x + y).toString //Return String!!
    else
      x + y // return Int!!
  }


  //################################### Recursion ###################################

  def factorial_n(n: Int): Int = if (n == 0 || n == 1) 1 else n * factorial_n(n - 1)

  println(factorial_n(5))
  println(factorial_n(100))

  import scala.annotation.tailrec

  //  @tailrec//an annotation to check if it's Optimized Recursion at compile time
  // this is unoptimized recursion because of the n * fac(n-1)
  def factorialWithBigInt(n: BigInt): BigInt = if (n == 0 || n == 1) 1 else n * factorialWithBigInt(n - 1)

  println(factorialWithBigInt(5))
  println(factorialWithBigInt(10))
  println(factorialWithBigInt(1000))
  println

  //################################### Optimized Recursion  ###################################

  //this is an optomized recursion. accumulate the values ina val
  @tailrec
  def factorialWithBigInt_Optim(n: BigInt, acc: BigInt): BigInt = if (n == 0 || n == 1) acc else factorialWithBigInt_Optim(n - 1, n * acc)

  println(factorialWithBigInt_Optim(10000, 1)) //stack overvlow. you can either use optimized recursion or increase the stack size at the startup


  //have another fucntion that calls the above with the accumulator
  def factorialWithBigInt_Optim_WithHiddenAccum(n: BigInt) = factorialWithBigInt_Optim(n, 1)

  println(App.factorialWithBigInt_Optim_WithHiddenAccum(10000))


  //################################### Methods Inside Methods ###################################

  def factorial(n: BigInt): BigInt = {

    @tailrec //keeping the optimized version
    def fact(n: BigInt, accum: BigInt): BigInt = if (n == 0 || n == 1) accum else fact(n - 1, n * accum)

    fact(n, 1)
  }

  println(factorial(10000))

  //IsInstanceOf and AsInstanceOf <- they're function under 'Any'

  //IsInstanceOf is equivalent to Java's instanceof
  println(2.isInstanceOf[Int])


  //AsinstanceOf is to down cast
  val ss: Any = "ha ha ha"
  val casted: String = ss.asInstanceOf[String]
  println(casted)


  //parametrized types on methods. Similar to Java's generics

  def parameterizedFunction[T](param: T) = param //param is of the type being passed during invocation
  println(parameterizedFunction[Boolean](true)) //brackets might be unnecessary if compiler can infer types
  println(parameterizedFunction(true))


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
    * case classes will automatically add: toString(), equals(), hashCode(), copy(), apply() so no need to use 'new' keyword
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
    def swap() = new Couple[B, A](second, first)// return type will be inferred
  }

  println(Couple(10, "Scala"))
  println(new Couple(10, "Scala"))
  println(new Couple(10, "Scala").toString)
  println(Couple[Int, String](10, "Scala"))
  val couple = new Couple(10, "Scala")
  println(couple)
  Couple("Hello", Couple(3, 22.2))

  //################################### Parametrized Methods in their Classes ###################################


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


object doWhileReadFromConsole extends App {
  var numGuess = 0
  do {
    print("Guess a number: ")
    numGuess = StdIn.readLine.toInt


  } while (numGuess != 15)

  printf("You guess a number %d", 15)
}

object InterpolationWithPrintandStyling extends App {
  val name = "Husain"
  val age = 32
  val weight = 155.5

  println(s"$name")
  println(f"I am ${age + 1} and weigh $weight%.2f")

  printf("'%5s'\n", 5)
  printf("'%-5d'\n", 5)
  printf("'%05d'\n", 5)

  //right justification
  printf("'%20s'\n", "name Husain")
  printf("'%20s'\n", "age 30")

  // left justification
  printf("'%-20s'\n", "name Husain")
  printf("'%-20s'\n", "age 30")
}

object DefFunctions extends App {
  var sum = getSum()
  var sum2 = getSum(1, 2, 3, 4)
  println(sum)

  sum = getSum(2, 10)
  println(sum)

  /**
    *
    * @param num1 defaults to 1
    * @param num2 defaults to 1
    * @return
    */
  def getSum(num1: Int = 1, num2: Int = 1): Int = {
    //return is valid but not necessary unless you want to use it to break out of a bloak of statements e.g. loop
    //    return num1 + num2
    num1 + num2
  }

  def getSum(args: Int*): Int = {

    var sum: Int = 0
    for (num <- args)
      sum += num
    sum
  }

  println(sum2)
}


object Recursion extends App {
  var fact = factorial(10)

  def factorial(num: BigInt): BigInt = {
    if (num <= 1)
      1
    else
      num * factorial(num - 1)
  }

  println("Factorial of 4 = " + factorial(4))
}


object Arrays extends App {
  val myArray = new Array[Int](10)
  // can't change size once instantiated
  val anotherArr = Array("Tom", "Huss") // can't change size once instantiated

  anotherArr(0) = "alkhamis"
  println(anotherArr(0))
  println

  var buffArr = new ArrayBuffer[String]() //since it is a buffer no need to specify the size
  buffArr.insert(0, "Husain")
  buffArr += "Russ" // inserting a string at the end of the array
  buffArr ++= Array("Hard", "Hashir") // appending another array
  buffArr.insert(1, "Inserted at 1")

  for (fr <- buffArr)
    println(fr)

  println
  buffArr.foreach(println)

  println("anothBuffArr")
  var anothBuffArr = for (i <- buffArr) yield i + " y" // or you can use curly braces instead of parentheses
  anothBuffArr.foreach(println)

  println
  var multTable = Array.ofDim[Int](10, 10)
  for (i <- 0 until multTable.length; j <- 0 until multTable(0).length) {
    multTable(i)(j) = i * j
  }
  for (i <- 0 until multTable.length; j <- 0 until multTable(0).length) {
    printf("%d * %d = %d\n", i, j, multTable(i)(j))
  }
}

object ArrayUtils extends App {
  var nums = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  println(nums.sum)
  println(nums.max)
  println(nums.min)

  //  var sortedNums = nums.sortWith((s1, s2) => s1>s2)
  var sortedNums = nums.sortWith(_ > _)
  sortedNums.foreach(println)
  println
  sortedNums = nums.sortWith(_ < _)
  sortedNums.foreach(println)

  sortedNums.deep.mkString(", ").foreach(print)
}

object MyMap extends App {
  //immutable map
  var employees = Map(
    "Manager" -> "Husain",
    "Secretary" -> "Sue")

  if (employees.contains("Manager")) {
    println(employees("Manager"))
  }

  var immMap = collection.immutable.Map(100 -> "Paul", 101 -> "Sally")
  println(immMap(100))

  for ((k, v) <- immMap) {
    printf("%d : %s\n", k, v)
  }
}


object Tuples extends App {
  var myTuple = (103, "Husain", 3.5);
  println(myTuple._1)
  myTuple.productIterator.foreach(println)
  println(myTuple)
}

object PassingFunctionsAsParam extends App {

  def times3(num: Int) = num * 3

  def times4(num: Int) = num * 4

  def multIt(func: (Int => Double), num: Int): Double = {
    func(num)
  }

  println(multIt(times3, 4))
}

object Closures extends App {
  val divisorVal = 5
  val divisor5 = (num: Double) => num / divisorVal

  println(divisor5(5.0))
}

object Exceptions extends App {


  //how about return type?
  def divideNum(num1: Int, num2: Int) = try {

    num1 / num2
  } catch {
    case ex: ArithmeticException => "Can't divde by zero" // Exceptions are of type Nothing!
  } finally {
    //clean up code
  }
}


object Demo {
  def main(args: Array[String]) {
    val p1 = new Point(2, 3)
    val p2 = new Point(2, 4)
    val p3 = new Point(3, 3)

    println(p1.isNotEqual(p2))
    println(p1.isNotEqual(p3))
    println(p1.isNotEqual(2))
  }


  trait Equal {
    def isEqual(x: Any): Boolean

    def isNotEqual(x: Any): Boolean = !isEqual(x)
  }

  class Point(xc: Int, yc: Int) extends Equal {
    var x: Int = xc
    var y: Int = yc

    def isEqual(obj: Any) = obj.isInstanceOf[Point] && obj.asInstanceOf[Point].x == y
  }

}

object Matches {
  def main(args: Array[String]): Unit = {
    val firstArg = if (args.length > 0) args(0) else ""

    //printing
    firstArg match {
      case "salt" => println("pepper")
      case "chips" => println("salsa")
      case "eggs" => println("bacon")
      case _ => println("huh?")
    }

    // returning
    val friend =
      firstArg match {
        case "salt" => "pepper"
        case "chips" => "salsa"
        case "eggs" => "bacon"
        case _ => "huh?"
      }

    println(friend)
  }


}