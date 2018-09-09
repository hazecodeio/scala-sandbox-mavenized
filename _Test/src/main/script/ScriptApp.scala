import org.hsmak.Complex

/**
  * To run a scala script from Intellij
  *   - Link: https://www.youtube.com/watch?v=phjxbbLk4WM
  */

//################################### Singleton Objects ###################################

// Diff between Classes & Objects: https://www.safaribooksonline.com/videos/learning-path-scala/9781491970850/9781491970850-video256895

object MyObject {
  def foo(x: Int, y: Int) = x + y
}


class Employee(val firstName: String, val lastName: String, val title: String)

object Husain extends Employee("Huss", "AK", "Developer")

println(Husain.firstName)
println(Husain.lastName)
println(Husain.title)


//Testing import statement in a script file
val c = new Complex(1.2, 3.4)
println("imaginary part: " + c.im)
println(c)



//################################### Companion Objects ###################################

//################################### Tuples (not part of collection in Scala) ###################################

val t = (1, "Hus", 400.0)
println(t._1, t._2, t._3) //starting from base one! up to 22!

//types are inferred, so these are the expansion
val t2: (Int, String, Double) = t //notice the parens
val t3: Tuple3[Int, String, Double] = t // just as previously shown

object MyTuple {

  case class Department(name: String)

  val u = ("4", Department("QA")) // check scala api for tuples: https://www.scala-lang.org/api/current/scala/Tuple2.html
  println(u)
  val u2 = u.swap // only Tuple2. it doesn't make sense to have this method for tuple size > 2
  println(u2)
  println(u) // because scala is immutable by nature the original will stay the same
}

MyTuple


//################################### Higher Order Functions ###################################
/**
  * e.g.: f(x) = x + 1
  */

/**
  * Function1 is a trait that has one abstract method apply()
  * remember what is so special about apply() is that there is no need to use its name to call it
  * match with the parametrized type:
  *   - params type and
  *   - return type
  *
  * Similar to Tuples, we can create Functions; lambda expression, with up to 22 params
  *
  * As of scala 2.12 functions will turn into Java8 functions (java.util.function) by the compiler
  * As a matter of coincidence java8 function has apply() so this makes scala/java8 very interchangeable
  *
  * Link:
  *   - https://www.scala-lang.org/api/current/scala/Function1.html
  */
object FunctionExplicit {

  val f1: Function1[Int, Int] = new Function[Int, Int] {
    def apply(x: Int): Int = x + 1
  }

  val f0: Function0[Int] = new Function0[Int] {
    override def apply(): Int = 1
  }

  val f2: Function2[Int, Int, Int] = new Function2[Int, Int, Int] {
    override def apply(x: Int, y: Int): Int = x + y + 1
  }

  val concatF2: Function2[Int, String, String] = new Function2[Int, String, String] {
    override def apply(x: Int, str: String): String = str + x
  }

  println(f1.apply(3))
  println(f1(3)) // the magic about apply()

  println(f0())
  println(f2(3, 5))
  println(concatF2(3, "wow"))

}

FunctionExplicit

println

/**
  * replace the return type with the lambda expression
  */
object FunctionImplicit_01 {


  val f1: Int => Int = new Function[Int, Int] {
    def apply(x: Int): Int = x + 1
  }

  val f0: () => Int = new Function0[Int] {
    override def apply(): Int = 1
  }

  val f2: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(x: Int, y: Int): Int = x + y + 1
  }

  val concatF2: (Int, String) => String = new Function2[Int, String, String] {
    override def apply(x: Int, str: String): String = str + x
  }

  println(f1.apply(3))
  println(f1(3)) // the magic about apply()

  println(f0())
  println(f2(3, 5))
  println(concatF2(3, "wow"))

}

FunctionImplicit_01

println

/**
  * replace the abstract method implementation with the lambda expression too
  * return types are lambda expressions
  */
object FunctionImplicit_02 {


  val f1: Int => Int = (x: Int) => x + 1

  val f0: () => Int = () => 1

  val f2: (Int, Int) => Int = (x: Int, y: Int) => x + y + 1

  val concatF2: (Int, String) => String = (x: Int, str: String) => str + x

  println(f1.apply(3))
  println(f1(3)) // the magic about apply()

  println(f0())
  println(f2(3, 5))
  println(concatF2(3, "wow"))

}

FunctionImplicit_02

println

/**
  * remove the explicit var type because type inference will kick in
  */
object FunctionImplicit_03 {


  val f1 = (x: Int) => x + 1

  val f0 = () => 1

  val f2 = (x: Int, y: Int) => x + y + 1

  val concatF2 = (x: Int, str: String) => str + x

  println(f1.apply(3))
  println(f1(3)) // the magic about apply()

  println(f0())
  println(f2(3, 5))
  println(concatF2(3, "wow"))

}

FunctionImplicit_03

/**
  * Tuples as a return type is the key to make a function return multiple values
  * Anyway, you can also think of it as an object wrapper
  */
object FunctionWithMultipleReturnValues {

  val f: String => (String, Int) = (x: String) => (x, x.size)
  println(f("hh"))

  //we can remove the val type due to type inference
  val f1 = (x: String) => (x, x.size)
  println(f1("hh1"))
}

FunctionWithMultipleReturnValues
println

//################################### Methods vs Functions ###################################

object MethOrFun {
  val f = (x: Int) => x + 1 // Function -> which is an object itself; that's why we can call apply() on it. so 'f' is a reference to the real object. Look up Method reference in Java8
  def g(x: Int) = x + 1 // Method -> it has to be part of an object so it's a member of class/object not a n object itself

  println(f.apply(4)) //a function aka an object too
  println(f(4)) //a function aka an object too
  println(g(4)) // a method not an object
}

MethOrFun
println


//################################### Converting A Method To A Function ###################################

/**
  * also called partially applied function. is this doable in Java8? yes:
  * https://dzone.com/articles/partially-applied-functions-in-java
  * https://www.pgrs.net/2015/04/23/partial-function-application-in-java-8/
  *
  * What are the applications of it?
  */
object ConvMethToFun {

  class Foo(x: Int) {
    def bar(y: Int) = x + y

    def gym(z: Int, a: Int) = x + z + a
  }

  val x = new Foo(10)
  val f = x.bar _ // the underscore '_' is a placeholder. and that's how to convert a method (of an object) into a function
  /**
    * In console:
    * scala> val f = x.bar _
    * f: Int => Int = $$Lambda$1196/132423149@2c6c302f
    */

  println(f.apply(20))
  println(f(20))

  class Baz(z: Int) {
    //a function is a param. aka a lambda param
    def qux(f: Int => Int) = f(z) // call the function on the z value. So it's like we are performing a separate/outside process within the class itself
    def jam(f: (Int, Int) => Int) = f(z, 10)
  }

  val z = new Baz(20)
  // passing the partially applied function
  println(z.qux(f)) // from above: 'val f = x.bar _'
  println(z.qux(x.bar _)) // without calling the val f
  println(z.qux(x.bar)) // no you can't use a method as a function. there is more going on under the hood. Remember this a partially applied function happening midway

  // more than a param
  val f2 = x.gym(40, _: Int) // so this is now a function 'f: Int => Int'
  println(z.qux(f2)) // because f2 is still expecting one param of type Int it matches the lambda param of qux

  //partially applied functions for two params
  val f3 = x.gym _ // Now the underscore '_' is expecting two params: 'f3:(Int, Int) => Int'
  println(z.jam(f3))
  println(z.jam(x.gym _)) //without calling the val f3
  println(z.jam(x.gym)) // no you can't use a method as a function. there is more going on under the hood. Remember this a partially applied function happening midway

  //clear application of the above concept
  def calculateProductPrice(discount: Double, productPrice: Double): Double = (1 - discount / 100) * productPrice

  val discount_30 = calculateProductPrice(30, _: Double)
  println(discount_30(100))

}

ConvMethToFun
println

//################################### Closure ###################################

object MyClosue {

  class Foo(x: Int) {
    def bar(y: Int => Int) = y(x)
  }

  var m = 200
  // var for closure isn't recommended because there will be different behaviour
  val f = (x: Int) => x + m // this is where the closure effect takes place
  val foo = new Foo(100)
  println(foo.bar(f))
  m = 300 // changing m will result in different behavior when calling the method.
  println(foo.bar(f))
}

MyClosue
println

//################################### Functions with Functions, AKA Higher Order Functions ###################################

object FuncWithFunc {

  val f_Explicit: (Int, Int => Int) => Int = (x: Int, y: Int => Int) => y(x) // Notice the colon ':' <- again this is to be explicit with the type
  val f = (x: Int, y: Int => Int) => y(x) // y is of type function that expects an Int and return an Int
  println(f(3, (m: Int) => m + 1)) // the 2nd param is a lamda expression which is an implementation of the function y. Here m is x which is the input and that is 3
  println(f(3, m => m + 1)) // by inference type will be inferred
  println(f(3, _ + 1)) // a placeholder will do just fine as well
  println(f(3, 1 + _)) // by commutative property of teh addition process

  import scala.language.postfixOps
  // turn on the postfixOps flag to turn off the warning
  println(f(3, 1 +)) // if the '_' i sthe last param we can take it away


  // return a function from a function
  val g = (x: Int) => (y: Int) => x + y // this introduces Currying. this is also a closure; a function is being returned but is closing around the x
  println(g(4)(5)) // currying!!
  println(g.apply(4).apply(5)) // this is what it really is

}

FuncWithFunc
println

//################################### Currying ###################################

object MyCurrying {
  /**
    * Currying: named after Haskell Curry. Haskel lang was named after his first name and Currying concept was named after his last name!
    * Currying breaks the function into parts so we can feed it in parts. I.e. a sequence of functions returning functions
    * g -> returns a function/lambda expression
    * currying three param function '(Int, Int, Int)' will convert it to 'Int => (Int => (Int => Int))'
    * again don't confuse methods with functions
    */
  val g = (x: Int) => (y: Int) => x + y
  println(g(2)(5))
  val g3 = (x: Int) => ((y: Int) => (z: Int) => x + y + z) // returns a function that returns another function
  println(g3(2)(3)(5))
  val q = g _

  val f = (x: Int, y: Int) => x + y
  //multiple-param function. signature from the REPL: '(Int, Int) => Int'
  val fc = f.curried
  // convert a regular function into a curried one. f will become: val f = (x:Int) => ((y:Int) => x + y). Signature: 'Int => (Int => Int)'
  val uncurFC = Function.uncurried(fc)
  // uncurrying a curried function


  //remember: '_' converts a method into a function
  def foo(x: Int, y: Int, z: Int) = x + y + z // a method not a function
  val fo = foo _ // REPL: (Int, Int, Int) => Int <- converting to a regular function with 3 params
  val fo2 = foo(2, _: Int, _: Int) //we have to specify all params if not curried. This is the Partially Applied Function

  //Curried Method: well you reall have to convert it to a function first using '_'
  val curriedFoo = (foo _).curried // 1st convert method to a function then convert it to a curried one. Now this similiar to the following.


  def bar(x: Int)(y: Int)(z: Int) = x + y + z

  // a curried method (still not a function too!). used by implicit params a lot
  val br = bar _ // REPL: Int => (Int => (Int => Int)) <- fully curried function!
  val br2 = bar(2) _ // partially applied function!
  println(br2(3)(10)) //br2.apply(3).apply(10)

  def baz(x: Int, y: Int)(z: Int) = x + y + z

  val bz = baz _ // REPL: (Int, Int) => Int => Int
  val bz2 = baz(2, _: Int) _
  println(bz2(3)(3))


}

MyCurrying
println


//################################### Functions: By-Name Parameters //###################################

//It's a regular method with two params
object ByNameParameters {
  def byValue(x: Int)(y: Int) = {
    println("By Value: ")
    x + y
  }

  //The 2nd param is a regular lambda expression
  def byFunction(x: Int)(y: () => Int) = {
    println("By Function: ")
    x + y()
  }

  // ByName param. Notice there is no () in the lambda expression
  def byName(x: Int)(y: => Int) = {
    println("By Name: ")
    x + y
  }

  val a = byValue(3) {
    //Eager evaluation
    println("In call")
    19
  }

  val b = byFunction(3)(() => {
    //Lazy evaluation -> evaluate when it's called <- the body of the called function needs to be instantiated first then th ebelow will be executed
    println("In call")
    19
  })

  /**
    * It's as same as ByFunction
    * It just provides easiness beside the laziness
    *
    * Can be called by black directly since no need to prepend the '() =>'
    * Good to catch exceptions and clean up resource as in try/catch blocks below.
    * Other benefits?
    */
  val c = byName(3) {
    //Lazy evaluation -> evaluate when it's called <- the body of the called function needs to be instantiated first then th ebelow will be executed
    println("In call")
    19
  }

  /**
    * real world example for ByName params
    * Notice there is no 'f:()' instead it is 'f:'
    *
    * @param f
    * @return
    */
  def divideSafely(f: => Int): Option[Int] = {
    try {
      Some(f)
    } catch {
      case ae: ArithmeticException => None
    }
  }

  val d = divideSafely {
    val x = 10
    val y = 5
    x / y
  }
  println(d)

  val e = divideSafely {
    val x = 100
    val y = 0
    x / y
  }
  println(e)

}

ByNameParameters
println