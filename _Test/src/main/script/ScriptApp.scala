










//################################### Closure ###################################

object MyClosue {

  // var for closure isn't recommended because there will be different behaviour
  val f = (x: Int) => x + m // this is where the closure effect takes place
  val foo = new Foo(100)
  var m = 200

  class Foo(x: Int) {
    def bar(y: Int => Int) = y(x)
  }

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
  val fo = foo _ // REPL: (Int, Int, Int) => Int <- converting to a regular function with 3 params
  val fo2 = foo(2, _: Int, _: Int) //we have to specify all params if not curried. This is the Partially Applied Function
  //Curried Method: well you reall have to convert it to a function first using '_'
  val curriedFoo = (foo _).curried // 1st convert method to a function then convert it to a curried one. Now this similiar to the following.
  // a curried method (still not a function too!). used by implicit params a lot
  val br = bar _ // REPL: Int => (Int => (Int => Int)) <- fully curried function!
  val br2 = bar(2) _ // partially applied function!
  val bz = baz _ // REPL: (Int, Int) => Int => Int
  val bz2 = baz(2, _: Int) _
  println(br2(3)(10)) //br2.apply(3).apply(10)

  //remember: '_' converts a method into a function
  def foo(x: Int, y: Int, z: Int) = x + y + z // a method not a function

  def bar(x: Int)(y: Int)(z: Int) = x + y + z

  def baz(x: Int, y: Int)(z: Int) = x + y + z

  println(bz2(3)(3))


}

MyCurrying
println


//################################### Functions: By-Name Parameters //###################################

//It's a regular method with two params
object ByNameParameters {
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
  val d = divideSafely {
    val x = 10
    val y = 5
    x / y
  }
  val e = divideSafely {
    val x = 100
    val y = 0
    x / y
  }

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

  println(d)

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

  println(e)

}

ByNameParameters
println

//################################### Collections ###################################

////////////////// List

object MyList {
  val l = List(1, 2, 3, 4)
  // using the CompanionObject
  val l2 = List.apply(1, 2, 3, 4)
  // using the CompanionObject.apply()
  val lColon = 1 :: 2 :: 3 :: 4 :: Nil

  println(l.head)
  println(l.tail)
  println(l.init)
  println(l.last)

  println(l.max)
  println(l.min)
  println(l.isEmpty)
  println(l.nonEmpty)
  println(l(3))
  println(l.apply(3))
  println(l.updated(0, 32))
  println(l.mkString(","))
  println(l.mkString("**"))
  println(l.mkString("[", "**", "]")) // bound the list by []
}

MyList
println

////////////////// Set

object MySet {
  val s = Set(1, 2, 3, 4)
  // using the CompanionObject
  val sA = Set.apply(1, 2, 3, 4)
  // using the CompanionObject.apply()
  val s2 = Set(1, 2, 3, 4, 4, 1, 6, 7)
  val s3 = Set(4, 5, 6, 7)

  println(s diff s2)
  println(s diff s3)
  println(s union s2)
  println(s union s3)
  println(s intersect s2)
  println(s intersect s2)

  /**
    *
    * ++/-- for collection operation
    * +/- one item operation
    *
    */
  println(s ++ s2)
  println(s ++ List(3, 4, 5, 6, 7))
  println(s + 10)

  println(s -- s2)
  println(s -- List(3, 4, 5, 6, 7))
  println(s - 1)

  println(s(3)) //check if the element 3 exists
  println(s.apply(3))
  println(s.contains(3))
}

MySet
println

////////////////// Map

object MyMap {
  val m = Map((1, "One"), (2, "Two")) //key/value tuples

  val t: (Int, String) = (3, "Three")
  val t2: (Int, String) = 3 -> "Three" // the arrow will be converted into a tuple
  println(t2)

  val m2 = Map(1 -> "One", 2 -> "Two") //key/value tuples
  println(m2)
  println(m2.get(1)) //return the Option Some("One")
  //  println(m2.apply(10))//will return it directly but what if it wasn't there -> exception "NoSuchElementException"
  println(m2.get(10)) // return None

  println(m2.toList)
  println(m2.keys) //return a Set
  println(m2.keySet) //return a Set
  println(m2.values) // return MapLike
  println(m2.values.toSet) // return MapLike
  println(m2.values.toList) // return MapLike




  /////////////// Symbols: they have a pool of their own

  val co = Symbol("Co")
  val co2 = 'Co

  println(co == co2) //true
  println(co eq co2) //true

  ////////////////////// Map with Symbols

  val elements: Map[Symbol, String] = Map('Co -> "Cobalt", 'H -> "Helium", 'Pb -> "Lead")
  println(elements.get('H))
  println(elements get 'H)
}

MyMap
println

//######################################## Arrays and Repeated params ################################

/**
  * unlike othjer collections, Arrays are:
  *   Mutable.... WARNING!
  * converted into primitive aarays by the JVM under the hood
  */
object MyArray {
  val a: Array[Int] = Array(1, 2, 3)

  //same list operations can be done on arrays
  println(a.head)
  println(a.tail)
  println(a.init)
  println(a.last)
  println(a.max)
  println(a.min)
  println(a(2))
  println(a.isEmpty)
  println(a.nonEmpty)

  def repeatedParamMethod(x: Int, y: String, z: Any*) = {
    println(z) //z is object of type WrappedArray
    "%d %ss give you  %s".format(x, y, z.mkString(", "))
  }

  println(repeatedParamMethod(3, "egg", "a delicious sandwich", "protein", " high cholesterol"))
  println(repeatedParamMethod(3, "egg", List("a delicious sandwich", "protein", " high cholesterol")))
  println(repeatedParamMethod(3, "egg", List("a delicious sandwich", "protein", " high cholesterol"): _*)) //':_*' in front of any collection will break it apart so it will fit in 'z:Any*'. this is a syntactic sugar fo rthe repeated param

}

MyArray
println

//######################################## Ranges ################################

object MyRange {
  val r3 = 2 to 10 by 2
  val r4 = 10 to 2 by -2

  // Ranges are collections too so same operation of List can be applied on them too
  println(r)
  println(r2)
  println(r.head)
  println(r.tail)
  println(r.last)
  println(r.init)
  println(r.isEmpty)
  println(r.nonEmpty)
  println(r.mkString(", "))
  val r5 = 'a' to 'z'
  println(r3)
  val r55 = ('a' to 'z') ++ ('A' to 'Z') // use ++ to concatenate multiple Ranges
  println(r4)
  val r6 = Range(1, 10) //exclusive ==> until
  println(r5)
  println(r5.toList)
  val r7 = Range(1, 10, 2) //exclusive with stepping
  println(r55)
  val r8 = Range.inclusive(1, 10) //inclusive ==> to
  println(r6)
  var r = 1 to 10 // include the 10
  println(r7)
  var r2 = 1 until 10 // excluded the 10
  println(r8)

  for (i <- 1 to 10) println(i + 1)
  println("----------")
  for (i <- 2 to 10 by 2) println(i + 1)

}

MyRange
println

//######################################## Functional Operations <- Streams in Java ################################

/**
  * Map Op
  */
object MyMapOp {

  ////////////// List
  val a = List(1, 2, 3, 4, 5)
  val f = (x: Int) => x + 1
  println(a.map(f)) // by passing a function reference
  println(a.map((x: Int) => x + 1)) // by passing an inline function
  println(a.map(x => x + 1)) // type is unnecessary for type inference will kick in
  println(a.map(_ + 1)) // '_' can replace the variable name if a variable isn't desired
  println(a.map(1 + _)) // '+' is a commutative operation so flipping should work.

  import scala.language.postfixOps

  println(a.map(1 +)) //Scala will issue a warning around PostFix operator so why the import is used


  //////// Set
  val s = Set("Brown", "Red", "Purple", "Green", "Yellow")
  println(s.map(x => x.size)) // sizes will be collected in a set so any duplicate will be removed
  println(s.map(_.size))
  println(s.map(x => (x, x.size))) // outcome is a set of tuples

  ///////////// Map
  val m = Map('Germani -> 4, 'Brazil -> 5, 'Italy -> 4, 'Argentina -> 2) // tuples of (Symbols, Int)
  //Just prepend the word "Team" to the each Symbol
  println(m.map(t => (Symbol("Team " + t._1.name), t._2))) //since t._1 is a symbole, we need to extract the String name out of the Symbol

  //////////// String

  println("Hello!".map(c => c + 1))
  println("Hello!".map(c => (c + 1).toChar))

  ///////////// Option

  println(Some(4).map(_ + 1)) // return Some(5)
  //  println(None.map(_ + 1))// Won't work
  println(None.asInstanceOf[Option[Int]].map(_ + 1)) // It will simply return None with no change


}

MyMapOp
println

/**
  * Filter Op
  */
object MyFilter {
  val a = 1 to 10
  println(a.filter(x => x % 2 == 0)) //Filter out even numbers
  println(a.filterNot(x => x % 2 == 0)) //the opposite of Filter
  println(a.exists(x => x % 2 == 0)) // 'exists()' takes a function while 'contain()' checks for an element

  // the use of the underscore '_'
  println(a.filter(_ % 2 == 0)) //Filter out even numbers
  println(a.filterNot(_ % 2 == 0)) //the opposite of Filter
  println(a.exists(_ % 2 == 0)) // 'exists()' takes a function while 'contain()' checks for an element
  val s = Set("Brown", "Red", "Purple", "Green", "Yellow")

  println(filterVowels("Orange"))
  val m = Map(1 -> "One", 2 -> "Two", 3 -> "Three", 4 -> "Four")
  //filter out the words that contain at least 1 vowel
  println(s.filter(x => filterVowels(x).size > 1))

  def filterVowels(s: String) = s.toLowerCase.filter(c => Set('a', 'e', 'i', 'o', 'u').contains(c))

  println(m.filterKeys(_ % 2 == 0))

  println(Some(5).filter(_ % 2 == 0)) // return None
  println(Some(4).filter(_ % 2 == 0)) // return Some(4)
}

MyFilter
println

object ForEachOp {
  val a = 1 to 10
  println(a.map(x => println(x))) // if we do this by mistake
  println(a.foreach(x => println(x)))
  a.foreach(println _) // instead do this
  a foreach (println _)
  a foreach println _
  a foreach println
}

ForEachOp
println

object MyFlatMapOp {

  val l = List(1, 2, 3, 4, 5)

  println(l.map(x => List(-x, 0, x))) // produce List of Lists
  println(l.map(x => List(-x, 0, x)).flatten) // flattened list without performing a function on it
  println(l.flatMap(x => List(-x, 0, x))) //flatMap = Flatten + Map <- flatten then perform a function/operation

  //notice the explicit type for illutration
  val b: List[List[List[Int]]] = List(List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9), List(10, 11, 12)))
  println(b.flatMap(c => c)) // one list is reduced
  println(b.flatMap(c => c).flatMap(c => c)) // one more list is reduced so now we only have one list containing all elements
  //  println(b.flatMap(c => c).flatMap(c => c).flatMap(c => c))// if we add one more flatmap call by mistake it won't work because lambda expression in flatmap is expecting 'f: (A) => GenTraversableOnce[B}'
  println(b.flatMap(c => c).flatMap(c => c).flatMap(c => List(-c, c))) //extract, modify, add to list, flatten again to a general list


  //////////// Set
  val s = Set(2, 4, 10, 11)
  println(s.flatMap(x => Set(x, x * 5))) // same thing. Couple the modification using Sets so you will have a Set of Sets then use flatmap to flatten them out

  val m = Map(1 -> "One", 2 -> "Two", 3 -> "Three", 4 -> "Four")
  println(m.flatMap(t => Map(t._1 -> t._2, (t._1 * 100) -> (t._2 + " Hundred"))))

  println(Some(4).map(x => x + 19))
  println(Some(4).map(x => Some(x + 19)))
  println(Some(4).flatMap(x => Some(x + 19)))

  println(List(Some(4), None, None, Some(2), None, Some(55)).flatMap(x => x)) // result: List(4, 2, 55) <- Somes were stripped of and Nones disappeared!
  println(List(Set(1, 2, 3), Set(3, 4, 5)).flatMap(x => x)) // List(1, 2, 3, 3, 4, 5)
  println(List(Set(1, 2, 3), Set(3, 4, 5)).flatten) // same as previous line
}

MyFlatMapOp
println

//######################################## For Comprehensions using yield ################################

/**
  * There was a talk about Monadic and Monads at this section
  * For Compr is immutable
  */
object MyForComprehension {
  val result1 = for (i <- 1 to 10) yield (i + 1) //This For Comprehensions. Yield really is similar to map()
  println(result1) //Vector(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

  val result2 = (1 to 10).map(x => x + 1)
  println(result2) //Vector(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

  val result3 = for (i <- Some(100)) yield (i + 40)
  println(result3)

  val result4 = Some(100).map(x => x + 40)
  println(result4)

  val result5 = for (i <- List(1, 2, 3, 4);
                     j <- List(5, 6, 7, 8)) yield (i, j) //list of tuples <- similar to nested for loops
  println(result5)

  val result6 = List(1, 2, 3, 4).map(i => List(5, 6, 7, 8).map(j => (i, j)))
  println(result6.flatten)

  val resultFaltMap6 = List(1, 2, 3, 4).flatMap(i => List(5, 6, 7, 8).map(j => (i, j)))
  println(resultFaltMap6)

  val result7 = for (i <- List(1, 2, 3, 4) if i % 2 == 0; j <- List(5, 6, 7, 8)) yield (i, j) //couple even numbers from i with every number in j
  println(result7)

  val result8 = for (i <- List(1, 2, 3, 4); j <- List(5, 6, 7, 8) if (j < 7)) yield (i, j)
  println(result8)

  ///////using map & filter // one caveat with filter is the performance <- use withFilter instead because it's lazy call

  val result9 = (1 to 4)
    .toList.filter(_ % 2 == 0)
    .flatMap(i => (5 to 8)
      .toList
      .map(j => (i, j))) //removing toList will return into vector
  println(result9)

  val result10 = (1 to 4)
    .toList
    .flatMap(i => (5 to 8)
      .filter(_ < 7)
      .map(j => (i, j)))
  println(result10)

  ////// using withFilter <- lazy evaluation
  val result11 = (1 to 4)
    .toList
    .withFilter(_ % 2 == 0)
    .flatMap(i => (5 to 8)
      .toList.map(j => (i, j))) //removing toList will return into vector
  println(result11)

  val result12 = (1 to 4)
    .toList
    .flatMap(i => (5 to 8)
      .withFilter(_ < 7)
      .map(j => (i, j)))
  println(result12)
}

MyForComprehension
println


