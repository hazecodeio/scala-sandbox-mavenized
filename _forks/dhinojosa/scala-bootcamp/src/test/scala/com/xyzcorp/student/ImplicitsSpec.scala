package com.xyzcorp.student


import org.scalatest.{FunSpec, Matchers}


class ImplicitsSpec extends FunSpec with Matchers {

  describe(
    """Implicits is like a Map[Class[A], A] where A is any object
      | and it is tied into the scope,
      | and it is there when you need it, hence it is implicit.
      | This provide a lot of great techniques that we
      | can use in Scala.""".stripMargin) {

    it(
      """Case 1: is done per scope so in the following example, we will begin with an implicit value
        |  and call it from inside a method which uses a multiple parameter list where one
        |  one group would """.stripMargin) {
      implicit val rate: Int = 100

      def calculatePayment(hours: Int)(implicit currentRate: Int) = hours *
        currentRate

      calculatePayment(50) should be(5000)
    }

    it(
      """Case 2: will allow you to place something manually, if you want to override the implicit value"""
        .stripMargin) {
      implicit val rate: Int = 100
      //implicit val age:Int = 20

      def calculatePayment(hours: Int)(implicit currentRate: Int) = hours * rate

      calculatePayment(50) should be(5000)
    }

    it(
      """Case 3: will gripe at compile time if there are two
        |  implicit bindings of the same type.  It's
        |  worth noting that what Scala doing are compile time tricks for implicit.
        |  One strategy is to wrap a value in a type to avoid conflict"""
        .stripMargin) {

      case class Rate(value: Int)
      case class Age(value: Int)

      implicit val rate: Rate = Rate(100)
      implicit val age: Age = Age(40)

      def calculatePayment(hours: Int)(implicit currentRate: Rate) = {
        def methodA(x: Int) = x + (hours * currentRate.value)

        methodA(10)
      }
    }

    it(
      """Case 4: is really used to bind services that require something and
        |  you don't particularly need to inject everywhere explicitly, in this
        |  case let's discuss Future[+T]""".stripMargin) {

      pending
    }

    it(
      """Case 5: can bring up any implicit directly by
        |  merely calling up implicitly""".stripMargin) {

      case class IceCream(name: String)
      case class Scoops(n: Int, flavor: IceCream)
      implicit val flavorOfTheDay: IceCream = IceCream("Spring Green")

      def orderIceCream(num: Int)(implicit flavorOfTheDay: IceCream) = {
        Scoops(num, flavorOfTheDay)
      }

      orderIceCream(3)(IceCream("Rocky Road"))

      def orderIceCream2(num: Int) = {
        Scoops(num, implicitly[IceCream])
      }

      def orderIceCream3(n: Int, flavor: IceCream = implicitly[IceCream]) = {
        Scoops(n, flavor)
      }
    }

    it(
      """Case 6: the implicit group parameter list, can contain more than one parameter, but
        |  needs to be in the same implicit parameter group""".stripMargin) {
      implicit val bonus: Int = 5000
      implicit val currency: String = "Euro"

      def calcYearRate(amount: Int)
                      (implicit bonusAmt: Int, preferredCurrency: String) = {
        amount + bonusAmt + " " + preferredCurrency
      }

      calcYearRate(60000) should be("65000 Euro")
    }

    it(
      """Case 7: can also be replaced with default parameters, choose accordingly""")
    {
      def calcYearRate(amount: Int, bonusAmt: Int = 5000,
                       currency: String = "Euro") = {
        amount + bonusAmt + " " + currency
      }

      calcYearRate(60000) should be("65000 Euro")
    }


    it(
      """Case 8: If you have a List[String] implicitly will it try
        |  to inject into a List[Double]?""".stripMargin) {

      implicit val listOfString: List[String] = List("Foo", "Bar", "Baz")
      implicit val listOfDouble: List[Double] = List(1.0, 2.0, 3.0)

      val result = implicitly[List[Double]]

      result should be(List(1.0, 2.0, 3.0))
    }


    it(
      """Case 9: can be used for something like what Ruby has called
        |  monkey patching or Groovy calls mopping where we can add functionality to
        |  a class that we don't have access to, like isOdd/isEven
        |  in the Int class.  This is what we call implicit wrappers.
        |  First we will use a conversion method.""".stripMargin) {
      class IntWrapper(x: Int) {
        def isOdd: Boolean = x % 2 != 0
        def isEven: Boolean = !isOdd
      }

      import scala.language.implicitConversions

      implicit def intToWrapper(x: Int): IntWrapper = new IntWrapper(x)

      10.isOdd should be (false)
      10.isEven should be (true)
    }


    it(
      """Case 10: Implicit wrappers can be created using
        | a function and is often easier to mental map.""".stripMargin) {
      class IntWrapper(x: Int) {
        def isOdd: Boolean = x % 2 != 0
        def isEven: Boolean = !isOdd
      }

      import scala.language.implicitConversions

      implicit val int2IntWrapper: Int => IntWrapper = (x: Int) => new IntWrapper(x)

      10.isOdd should be (false)
      10.isEven should be (true)
    }

    it(
      """Case 11: can be use a short hand version of this called implicit classes, before using them
        |  there are some rules:
        |  1. They can only be used inside of an object/trait/class
        |  2. They can only take one parameter in the constructor
        |  3. There can not be any colliding method name as that
        |     with the implicit outer scope""".stripMargin) {

      import scala.language.implicitConversions
      implicit class IntWrapper(x: Int) {
        def isOdd: Boolean = x % 2 != 0
        def isEven: Boolean = !isOdd
      }

      10.isOdd should be (false)
      10.isEven should be (true)
    }

    it(
      """Lab: Create an implicit wrapper that has a method called exclaim.
        |  When exclaim is called on any object. It will return the
        |  toString implementation but with an exclamation mark at the end.
        |
        |  For example:
        |  10.exclaim => 10!
        |  "Hello".exclaim => Hello!
        |  List(1,2,3).exclaim => List(1,2,3)!
        |
        |  Note: You can include everything you need inside of this test."""
        .stripMargin) {
      pending
    }

    it(
      """Case 12: can also convert things to make it fit into a particular API,
        | this is called implicit conversion,
        | in this scenario we will use a method""".stripMargin) {

      import scala.language.implicitConversions

      case class Dollar(value: Int)

      implicit def int2Dollar(i: Int): Dollar = Dollar(i)

      def addAmount(x: Dollar, y: Dollar): Dollar = Dollar(x.value + y.value)

      addAmount(Dollar(40), Dollar(100)) should be(Dollar(140))

      addAmount(50, 150) should be(Dollar(200))
    }

    it(
      """Case 13: can also convert things to make it fit into a particular API,
        | this is called implicit conversion,
        | in this scenario we will use a function""".stripMargin) {
      import scala.language.implicitConversions

      case class Dollar(value: Int)

      def combine(x: Dollar, y: Dollar): Dollar = Dollar(x.value + y.value)

      implicit val int2Dollar: Int => Dollar = (i: Int) => Dollar(i)

      combine(Dollar(100), Dollar(200)) should be(Dollar(300))
      combine(100, 200) should be(Dollar(300))

    }

    it(
      """Case 14: is done automatically in Scala because what is inside of
        |  scala.Predef, for example,
        |  it explains how be can set a scala.Float , and there is java.lang.Float,
        |  java primitive float.
        |  We can investigate this by looking at
        |  the documentation.""".stripMargin) {

      val f1: scala.Float = 4001.00f
      val f2: scala.Float = 5030.40f

      val result = java.lang.Math.min(f1, f2)

      result should be(4001.00f)
    }
  }

  describe("Locating implicits recipes") {
    it(
      """Case 15: has a common way, to store that particular implicit
        |  recipe in an object that makes should make
        |  sense and then import that object""".stripMargin) {

      object MyPredef {

        import scala.language.implicitConversions

        implicit class IntWrapper(x: Int) {
          def isOdd: Boolean = x % 2 != 0

          def isEven: Boolean = !isOdd
        }

      }

      import MyPredef.IntWrapper
      29.isEven should be(false)
    }

    it(
      """Case 16: can also use a companion object to store any implicit recipes"""
        .stripMargin) {
      class Artist(val firstName: String, val lastName: String)
      object Artist {

        import scala.language.implicitConversions

        implicit def tupleToArtist(t: (String, String)): Artist = new Artist(
          t._1, t._2)
      }

      def playPerformer(a: Artist) = s"Playing now: ${a.firstName} ${a.lastName}"

      playPerformer("Elvis" -> "Presley") should
        be("Playing now: Elvis Presley")
    }

    it(
      """Case 17: can also use a package object
        |  to store some of these implicits""".stripMargin) {
      def concatAll(xs: List[String]) = xs.reduce(_ + _)

      concatAll((3, "Hello")) should be("HelloHelloHello")
      concatAll(3 -> "Hello") should be("HelloHelloHello")
    }

    it(
      """Lab: Use scala.collection.JavaConverters to convert the Java call,
        |  java.time.ZoneId.getAvailableZoneIds, from a Java collection to
        |  a Scala collection. The do something fun with it like find all the
        |  time zones in Asia and sort them.  Everything can be
        |  done inside of this test. Consider REPL as a handy tool."""
        .stripMargin) {

      pending
    }
  }

  describe(
    "View Bounds are used to ensure that there is a particular recipe for a certain type")
  {
    it(
      """Case 19: Uses <% inside of a parameterized type declaration to determine if there is a conversion available
        | then within you can treat an object as an object of that type. It is unorthodox, and has since been
        | deprecated.""".stripMargin) {

      pending

      import scala.language.implicitConversions

      class Employee(val firstName: String, val lastName: String)

      implicit def str2Employee(s: String): Employee = ???

      def hireEmployee[A <% Employee](a: A) = {
        s"Hired an employee ${a.firstName} ${a.lastName}"
      }

      hireEmployee("Joe Employee") should be("Hired an employee Joe Employee")
    }
  }



  describe(
    """Context Bounds works so that there is a type A, and it requires a B[A] somewhere
      |  within the the implicit scope, for example like Ordered[T], or TypeTag[T], or Numeric[T],
      |  this provides a way to check that something is something can be implicitly defined but
      |  gives the end user no opportunity to the ability to inject a different implementation"""
      .stripMargin) {

    it(
      """Case 20: uses the signature [T:WrappedType], which is
        | equivalent to (t:T)(implicit w:WrappedType[T])
        | let's try it with """.stripMargin) {

      trait Loggable[T] {
        def log(t: T): String
      }

      pending
    }
  }

  describe(
    """Type Constraints are used to ensure that a particular method can run
      | if a particular generic is of a certain type, this is typically used for
      | one method""".stripMargin) {

    it(
      """Case 21: uses one operator, =:= which is actually the full type =:=[A,B] that
        |  will to see if something is of the same type""".stripMargin) {

      class MyPair[A, B](val a: A, val b: B) {
        def first: A = a

        def second: B = b

        def toList: List[A] = ???
      }

      pending
    }

    it(
      """Case 22: Take a look at the API, and see if it uses the operator, <:<
        |which will test if A is a subtype of B""".stripMargin) {
      List(1 -> "One", 2 -> "Two").toMap
    }
  }

  describe("Getting around Erasure Using TypeTags") {
    it(
      "Case 23: used to use Manifest but now uses a type tag to retrieve what is erased")
    {

      import scala.reflect.runtime.universe._

      def matchList[A](list: List[A])(implicit tt: TypeTag[A]): String = {
        tt.tpe match {
          case x if x =:= typeOf[String] => "List of String"
          case y if y =:= typeOf[Int] => "List of Int"
          case _ => "List of Something Else"
        }
      }

      matchList(List(1, 2, 3)) should be("List of Int")
    }
  }

  describe(
    """Typeclasses are a way of generating or extending behavior
      |  using Java-like interfaces,
      |  but operate as outside.  There is another term for this,
      |  and it's called ad-hoc polymorphism""".stripMargin) {

    it(
      """Case 24: can be used to determine equality, so whether than make equals inside of an class,
        | it is now an outside concern""".stripMargin) {
      trait Eq[T] {
      }

      pending
    }

    it(
      """Lab: Can be used for ordering, in this case
        |   how do we sort an Employee""".stripMargin) {
      case class Employee(firstName: String, lastName: String)
      pending
    }
  }
}