package org.hsmak

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

/**
  * Hello world!
  *
  */
object App extends App {


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


  import java.time._

  println(LocalDate.now.plusDays(2))


}








object doWhileReadFromConsole extends App {
  var numGuess = 0
  do {
    print("Guess a number: ")
    numGuess = StdIn.readLine.toInt


  } while (numGuess != 15)

  printf("You guess a number %d", 15)
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