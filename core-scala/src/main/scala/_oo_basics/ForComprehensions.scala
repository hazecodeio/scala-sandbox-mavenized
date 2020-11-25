package _oo_basics

object ForComprehensions extends App {

  /**
    * There was a talk about Monadic and Monads at this section
    *     - Monad is really a flatMap with mathematical laws
    *     - Option is an example of a Monad
    *     - links:
    *         - https://dzone.com/articles/simplifying-monads-in-scala
    *         - https://medium.com/@sinisalouc/demystifying-the-monad-in-scala-cc716bb6f534
    *         - http://appliedscala.com/blog/2016/understanding-monads/
    *
    * ForComprehensions is immutable
    */
  object BasicForComprehension {

    println("------------------ yield <=> map() using Ranges ---------------")

    val result1 = for (i <- 1 to 10) yield (i + 1) //This For Comprehensions. Yield really is similar to map()
    println("yield: " + result1) //Vector(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

    val result2 = (1 to 10).map(x => x + 1)
    println("map(): " + result2) //Vector(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)

    println

    println("------------------ yield <=> map() using Option ---------------")

    val result3 = for (i <- Some(100)) yield (i + 40)
    println("yield: " + result3)

    val result4 = Some(100).map(x => x + 40)
    println("map(): " + result4)

    println

    println("------------------ yield <=> map() using two Lists ---------------")

    val result5 = for (i <- List(1, 2, 3, 4);
                       j <- List(5, 6, 7, 8)) yield (i, j) //list of tuples <- similar to nested for loops
    println("yield: " + result5)

    val result6 = List(1, 2, 3, 4).map(i => List(5, 6, 7, 8).map(j => (i, j)))
    println("map().flatten: " + result6.flatten)

    val resultFlatMap6 = List(1, 2, 3, 4).flatMap(i => List(5, 6, 7, 8).map(j => (i, j)))
    println("flatMap(): " + resultFlatMap6)

    val result7 = for (i <- List(1, 2, 3, 4)
                       if i % 2 == 0; // if is part of the previous line so why the semicolon ';' is here
                       j <- List(5, 6, 7, 8)) yield (i, j) //couple even numbers from i with every number in j
    println("Another yield 1: " + result7)

    val result8 = for (i <- List(1, 2, 3, 4); //this is the end of this line so why the semicolon ';' is here
                       j <- List(5, 6, 7, 8)
                       if (j < 7)) yield (i, j)
    println("Another yield 2: " + result8)

    println


  }

  BasicForComprehension
  println

  object ForComprehensionWithBlock {

    sealed trait Species

    /**
      * Notice the case objects, not case classes!!
      */

    case object Cow extends Species

    case object Horse extends Species

    case object Chicken extends Species

    case object Dog extends Species

    case object Cat extends Species

    case object Wolf extends Species


    case class Animal(name: String, age: Int, species: Species)

    val animals = Seq(
      Animal("Babs", 12, Chicken),
      Animal("Lady", 4, Chicken),
      Animal("Babsie", 9, Cow),
      Animal("Bessy", 12, Cow),
      Animal("Lettie", 6, Cow),
      Animal("Douglas", 12, Horse),
      Animal("Cleo", 12, Dog),
      Animal("Bonnie", 9, Dog),
      Animal("Santiago", 12, Cat),
      Animal("Athena", 3, Cat)
    )

    println("------------------ For Comprehension with {} Instead of () -------------------")
    var yieldedNames = for {
      animal <- animals
      if animal.age >= 10
    } yield (animal.name) // yield a Seq[String]

    yieldedNames.foreach(println)
    println

    //Alternatively:

    //animals.filter(_.age >= 10).map(_.name)

    //animals.filter(_.age >= 10).collect{case s => s.name}

    //animals.filter(_.age >= 10).collect{a => a match{
    //     | case Animal(name, age, species) => name
    //     | }}

    //animals.filter(_.age >= 10).collect{_ match{ // notice the underscore '_'
    //     | case Animal(name, age, species) => name
    //     | }}


    println("""------------------ For Comprehension {} with " _ = println" -------------------"""")

    yieldedNames = for {
      animal <- animals

      // Notice the "_"
      _ = println("printing age between the lines: " + animal.age)

      if animal.age >= 10

    } yield (animal.name) // yield a Seq[String]

    yieldedNames.foreach(println)

    println("""------------------ For Comprehension {} with PatternMatchers -------------------"""")

    yieldedNames = for {
      Animal(name, age, species) <- animals

      // Notice the "_"
      _ = println("printing age between the lines: " + age)

      if age >= 10

    } yield (name) // yield a Seq[String]

    yieldedNames.foreach(println)
    println

    println("""------------------ For Comprehension {} with PatternMatchers and Fixed Values -------------------"""")

    println("Extracting only Cows (names):")
    yieldedNames = for {
      //Extract only Cows
      Animal(name, age, Cow) <- animals

      if age >= 10

    } yield (name) // yield a Seq[String]

    yieldedNames.foreach(println)
    println


    println("Extracting only Cows (Animal object):")
    var yieldedAnimals = for {
      //Extract only Cows
      animal@Animal(_, age, Cow) <- animals

      if age >= 10

    } yield (animal) // yield a Seq[String]

    yieldedAnimals.foreach(println)
    println

  }


  ForComprehensionWithBlock
  println()


}
