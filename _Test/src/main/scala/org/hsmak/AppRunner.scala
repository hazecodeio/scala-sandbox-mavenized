package org.hsmak

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

/**
  * To run a scala script from Intellij
  *   - Link: https://www.youtube.com/watch?v=phjxbbLk4WM
  */
object AppRunner extends App {


  /*
    notice how the for loop is used here to do formatting/filtering
    notice the {} which can be replaced by () with a little modification (Maybe?? The use of semicolon ';'??)
   */
  val even = for {i <- 1 to 20
                  if (i % 2) == 0
                  } yield i // yield to a collection. This is how to make a Functional for loop. Notice even is a val not var

  for (i <- even)
    println(i)
  //print the functional way
  println("use ForEach:")
  even.foreach(println) //inferred lambda expression
  even.foreach(a => println(a)) //expanded lambda expression

  import cats.Semigroup

  print("Semigroup[Int => Int]:")
  println(Semigroup[Int => Int].combine(_+1, _*10).apply(6))

}


object doWhileReadFromConsole extends App {
  var numGuess = 0
  do {
    print("Guess a number: ")
    numGuess = StdIn.readLine.toInt


  } while (numGuess != 15)

  printf("You guess a number %d", 15)
}


object DefMethods extends App {

  /**
    *
    * @param num1 defaults to 1
    * @param num2 defaults to 1
    * @return
    */
  def getSum(num1: Int = 1, num2: Int = 1): Int = {
    //return is valid but not necessary unless you want to use it to break out of a block of statements e.g. loop
    //    return num1 + num2
    num1 + num2
  }

  def getSum(args: Int*): Int = {

    var sum: Int = 0
    for (num <- args)
      sum += num
    sum
  }

  var sum = getSum()
  var sum2 = getSum(1, 2, 3, 4)
  println(sum)

  sum = getSum(2, 10)
  println(sum)


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


  /**
    * 0 1 2 3 4 5 6  7  8
    * 0 1 1 2 3 5 8 13 21
    *
    * @param n
    * @return
    */
  def fibonacci(n: Long): Long = {

    import scala.annotation.tailrec
    @tailrec
    def fib(n: Long, prev: Long, curr: Long): Long = {
      n match {
        case 0 => prev
        case 1 => curr
        case _ => fib(n - 1, curr, prev + curr)
      }
    }

    fib(n, 0, 1)
  }

  println("Fibonacci of 0th: " + fibonacci(0))
  println("Fibonacci of 1st: " + fibonacci(1))
  println("Fibonacci of 2nd: " + fibonacci(2))
  println("Fibonacci of 3rd: " + fibonacci(3))
  println("Fibonacci of 5th: " + fibonacci(5))
  println("Fibonacci of 6th: " + fibonacci(6))
  println("Fibonacci of 10th: " + fibonacci(10))

}


/**
  *
  * Array:
  *     - Mutable: only updates to elements
  *     - Fixed Size: must specify size at instantiation.
  *     - No Ops to append arrays:
  *       - unless it's a "var" so there a reassignment another the hood?
  *
  * ArrayBuffer:
  *     - Mutable
  *     - Dynamic Size: has Ops to append arrays.
  *       - It doesn't matter if it's "var" or "val"
  *
  * Note the instantiation via: "new" key word vs CompanionObject
  *
  */
object Arrays extends App {

  println("------------------- Array --------------------")
  //Fixed Size
  // can't change size once instantiated
  val arr1 = new Array[Int](10) // reserve 10 elements
  val arr2 = Array(1, 2, 3) // 3 elements

  val arr3 = Array("Tom", "Huss")
  arr3(0) = "John" // mutable
  println(arr3(0))
  println

  // create a "var" Array
  var varArr = Array(1, 2)
  varArr ++= Array(3, 4) // append another array via reassignment
  println("varArr:")
  varArr.foreach(println)
  println

  println("------------------- ArrayBuffer --------------------")

  //Dynamic Size
  val arrBuff = new ArrayBuffer[String]() //since it is a buffer no need to specify the size
  arrBuff.insert(0, "Husain")
  arrBuff += "Russ" // inserting a string at the end of the array
  arrBuff ++= Array("Ben", "Tom") // appending another array
  arrBuff.insert(1, "Inserted at 1")

  for (fr <- arrBuff)
    println(fr)
  println

  arrBuff.foreach(println)
  println

  println("------------ anotherArrBuff -------------")
  var anotherArrBuff = for (i <- arrBuff) yield i + " y" // or you can use curly braces instead of parentheses
  anotherArrBuff.foreach(println)
  println

  println("---------------------- Array.ofDim() ---------------")

  // 10 x 10 matrix
  var multTable = Array.ofDim[Int](10, 10)

  // via curly braces
  for {i <- 0 until multTable.length
       j <- 0 until multTable(0).length} {

    multTable(i)(j) = i * j
  }

  // via parens. notice the semicolon ';'
  for (i <- 0 until multTable.length;
       j <- 0 until multTable(0).length) {

    printf("%d * %d = %d\n", i, j, multTable(i)(j))
  }
}

object ArrayUtils extends App {
  var nums = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  println(nums.sum)
  println(nums.max)
  println(nums.min)
  println

  //  var sortedNums = nums.sortWith((s1, s2) => s1>s2)
  var sortedNums = nums.sortWith(_ > _)
  println(sortedNums mkString " ")
  println

  sortedNums = nums.sortWith(_ < _)
  println(sortedNums mkString " ")
  println

  println(nums.sorted.mkString(" "))
  println(nums.sorted.reverse.mkString(" "))

}

object MyMap extends App {

  //immutable map
  val employees = Map(
    "Manager" -> "Husain",
    "Secretary" -> "Sue")

  if (employees.contains("Manager")) {
    println(employees("Manager"))
  }

  // explicit
  val immMap = collection.immutable.Map(100 -> "Paul", 101 -> "Sally")
  println(immMap(100))

  for ((k, v) <- immMap) {
    printf("%d : %s\n", k, v)
  }

  val mutableMap = collection.mutable.Map(100 -> "aaa", 200 -> "bbb")
  mutableMap(300) = "ccc" // you can't do this with immutable
  mutableMap.foreach(println)
}


object Tuples extends App {

  var explicitTupleV1:Tuple3[Int, String, Double] = (103, "Husain", 3.5)
  var explicitTupleV2:(Int, String, Double) = (103, "Husain", 3.5)

  var t = (103, "Husain", 3.5)
   println(t._1)
  t.productIterator.foreach(println)
  println(t)
}

object PassingFunctionsAsParam extends App {

  def times3(num: Int) = num * 3

  def times4(num: Int) = num * 4

  /**
    *
    * @param func
    * @param num
    * @return
    */
  def multIt(func: Int => Double, num: Int): Double = {
    func(num)
  }

  println(multIt(times3, 4))

  //val multItThreeTimes = multIt(times3 _, _) // we can remove the '_' of times3.. Why is it possible??
  val multItThreeTimes = multIt(times3, _)
  print(multItThreeTimes(4))
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

  } catch { // a pattern matching similar to match/case

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