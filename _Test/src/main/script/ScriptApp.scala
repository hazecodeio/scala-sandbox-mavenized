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

object MethOrFun{
  val f = (x:Int) => x +1 // Function -> which is an object itself; that's why we can call apply() on it. so 'f' is a reference to the real object. Look up Method reference in Java8
  def g(x:Int) = x + 1 // Method -> it has to be part of an object so it's a member of class/object not a n object itself

  println(f.apply(4)) //a function aka an object too
  println(f(4))//a function aka an object too
  println(g(4))// a method not an object
}
MethOrFun
println
