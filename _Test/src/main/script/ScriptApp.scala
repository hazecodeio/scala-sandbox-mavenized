


//################################### Collections ###################################

////////////////// List



////////////////// Set



////////////////// Map



//######################################## Arrays and Repeated params ################################



//######################################## Ranges ################################



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


