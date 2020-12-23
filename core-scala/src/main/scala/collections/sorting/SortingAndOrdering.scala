package collections.sorting


object SortingAndOrdering extends App {

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


  println("############ SortedMethod ###############")

  /**
    * define an implicit Ordering[T]
    * implicit val animalOrdering_Inc = Ordering.by[Animal, Int](_.age)
    */
  object SortedMethod {

    val seqOfNums = Seq(3, 6, 1, 88, 3, 6, 4, 9, 6, 7, 200, 15, 133)
    println(seqOfNums.sorted) // will maintain the container type

    val vectorOfNums = Vector(3, 6, 1, 88, 3, 6, 4, 9, 6, 7, 200, 15, 133)
    println(vectorOfNums.sorted) // will maintain the container type

    println("--- Sorting Animals --")
    println("Scala doesn't know how to sort it yet ---> define an implicit Ordering[T] ")

    /**
      * Error:(49, 21) No implicit Ordering defined for collection.sorting.SortingAndOrdering.Animal.
      * println(animals.sorted)
      */
    //    println(animals.sorted)

    // in order for the previous line to work we need to define an ordering for Animal class
    /**
      * see [[SortWithMethod]]
      */


  }

  SortedMethod
  println

  println("############ SortWithMethod ###############")

  /**
    * - works with containers of complex/custom types when Scalal doesn't know how to sort it
    * - override the natural ordering
    *
    * - Notice how it actually calls sorted() and Ordering.fromLessThan()
    *     def sortWith(lt: (A, A) => Boolean): C = sorted(Ordering.fromLessThan(lt))
    */
  object SortWithMethod {

    /**
      * def sortWith(lt: (Animal, Animal) => Boolean): Seq[Animal]
      */

    println("-- Animals in Inc order--")
    animals.sortWith(_.age > _.age).foreach(println)
    println

    println("-- Animals in Dec order--")
    animals.sortWith(_.age < _.age).foreach(println)
    println


  }

  SortWithMethod
  println

  println("############ SortByMethod ###############")

  /**
    * - Notice how it actually calls sorted() and Ordering.fromLessThan()
    *     def sortBy[B](f: A => B)(implicit ord: Ordering[B]): C = sorted(ord on f)
    */
  object SortByMethod {

    /**
      * def sortBy[B](f: Animal => B)(implicit ord: scala.math.Ordering[B]): Seq[Animal]
      *
      * - Notice the implicit Ordering[B] in the 2nd param
      */

    println("-- sortBy --")
    animals.sortBy(_.age).foreach(println)
    println

    println("Defining the implicit Ordering[Animal, Int]:")
    println("-- Increasing Order --")
    implicit val animalOrdering_Inc = Ordering.by[Animal, Int](_.age)
    animals.sorted.foreach(println) // Now we can call sorted()
    println

    println("-- Decreasing Order (revers()) --")

    // we can't have more than an implicit val that takes and emits the same type as the other implicits!!!
    // hence commented and supplied explicitly
    //    implicit val animalOrdering_Dec = Ordering.by[Animal, Int](_.age).reverse
    animals.sorted(Ordering.by[Animal, Int](_.age).reverse).foreach(println) // Now we can call sorted()
    println
  }

  SortByMethod
  println

  println("############ DateTimeOrdering ###############")

  /**
    *
    */
  object DateTimeOrdering {

    import java.time.LocalDateTime

    val firstDate = LocalDateTime.now // now

    val secondDate = LocalDateTime.now.plusDays(5) // after now

    val thirdDate = LocalDateTime.now.minusDays(2) // before now

    val seqOfDates = Seq(firstDate, secondDate, thirdDate)
    println(seqOfDates)

    println("-- DateOrdering (_ isAfter _) --")
    implicit val dateOrdering_Dec = Ordering.fromLessThan[LocalDateTime](_ isAfter _) // equivalent to  "_ > _"
    println(seqOfDates.sorted) // Again an implicit ordering must be defined before hand

    println("-- DateOrdering (_ isBefore _) --")
    // we can't have more than an implicit val that takes and emits the same type as the other implicits!!!
    // hence commented and supplied explicitly
    //    implicit val dateOrdering_Inc = Ordering.fromLessThan[LocalDateTime](_ isBefore _) // equivalent to  "_ > _"
    println(seqOfDates.sorted(Ordering.fromLessThan[LocalDateTime](_ isBefore _))) // Again an implicit ordering must be defined before hand
    println

    println("----- via sortWith ------")
    println(seqOfDates.sortWith(_ isAfter _))
    println(seqOfDates.sortWith(_ isBefore _))

  }

  DateTimeOrdering
  println
}
