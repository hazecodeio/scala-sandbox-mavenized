package org.hsmak

/**
  * - the need to match on cases without constructing a complex structure like another case class
  * - use a singleton object
  * - useful to create filters to be used in conjunction with match/case
  *   - and beyond filters like map or a regular match/case vlocks
  *
  * - Two major methods:
  *     - unapply()
  *     - unapplySeq()
  *
  * - can we mix both unapply() & unapplySeq() in one singleton??????
  *
  * - This is covered in the Extractor topic:
  *     - https://www.safaribooksonline.com/library/view/programming-in-scala/9780981531687/extractors.html
  *     - https://www.safaribooksonline.com/videos/scala-intermediate-recipes/9781788397650/9781788397650-video1_7
  */
object CustomPatternMatchers extends App {

  case class Animal(name: String, species: String)

  val myAnimals = Seq(Animal("HolyCow", "Cow"), Animal("NaughtyCat", "Cat"), Animal("CutieDog", "Dog"), Animal("SkinnyCow", "Cow"))

  println("###################### `using unapply()` ######################")

  object using_unapply {
    /**
      * Notice how the Cases in the PatternMatcher inside the "filter()" and "map()" make use of the Singleton FarmAnimal
      * case will call the unapply() defined in the singleton object "FarmAnimal"
      *
      * @param animals
      * @return
      */
    def extractNameOfFarmAnimals(animals: Seq[Animal]): Seq[String] = {
      animals.filter {
        case FarmAnimal(_) => true
        case _ => false
      }.map {
        case FarmAnimal(name) => name // name is extracted value from the returned Some() from unapply() method
        case _ => ""
      }
    }

    /**
      * Exactly same as the previous one
      * Notice the "lambda" expression and the "match" keyword
      *
      * @param animals
      * @return
      */
    def extractNameOfFarmAnimals_Expanded(animals: Seq[Animal]): Seq[String] = {
      animals.filter { x => {
        x match {
          case FarmAnimal(_) => true
          case _ => false
        }
      }
      }.map { x => {
        x match {
          case FarmAnimal(name) => name // name is extracted value from the returned Some() from unapply() method
          case _ => ""
        }
      }
      }
    }

    /**
      * - Singleton that will be used by the PatternMatcher
      * - PatternMatcher will call 'unapply()' method to unfold the match.
      *     - if it returns Some() then it's a match
      *     - else it will check the next case
      *
      *  - unapply(): must return Option[T] because that's how cases in matchers work
      */
    object FarmAnimal {

      /**
        * (one-to-one) -> so this is mapping of 1 animal -> 1 name
        *
        * @param animal
        * @return must return Option[T] because that's how cases in matchers work
        */
      def unapply(animal: Animal): Option[String] =
        if (animal.species == "Cow") Some(animal.name)
        else None
    }


    println("--- myFarmAnimals --")


    val myFarmAnimals = extractNameOfFarmAnimals(myAnimals)
    println(myFarmAnimals)
    val myFarmAnimalsExpanded = extractNameOfFarmAnimals_Expanded(myAnimals)
    println(myFarmAnimalsExpanded)

  }

  using_unapply
  println






  println("###################### `using unapplySeq()` ######################")

  object `using unapplySeq()` {

    /**
      * Notice how the Cases in the PatternMatcher inside the "filter()" and "map()" make use of the Singleton FarmAnimal
      * case will call the unapply() defined in the singleton object "FarmAnimal"
      *
      * @param animals
      * @return
      */
    def extractTasksForAnimals(animals: Seq[Animal]): Seq[String] = {
      animals.filter {
        case FarmAnimalTasks(_*) => true
        case _ => false
      }.map {
        case FarmAnimalTasks(t1, t2, t3) => s"$t1, $t2, $t3"
        case _ => ""
      }
    }

    /**
      * Exactly same as the previous one
      * Notice the "lambda" expression and the "match" keyword
      *
      * @param animals
      * @return
      */
    def extractTasksForAnimals_Expanded(animals: Seq[Animal]): Seq[String] = {
      animals.filter { x => {
        x match {
          case FarmAnimalTasks(_*) => true//
          case _ => false
        }
      }
      }.map { x => {
        x match {
          case FarmAnimalTasks(t1, t2, t3) => s"$t1, $t2, $t3"
          case _ => ""
        }
      }
      }
    }

    /**
      * - Singleton that will be used by the PatternMatcher
      * - PatternMatcher will call 'unapply()' method to unfold the match.
      *     - if it returns Some() then it's a match
      *     - else it will check the next case
      *
      *  - unapply(): must return Option[Seq[T]] because that's how cases in matchers work for Sequence of Options
      */
    object FarmAnimalTasks {

      /**
        * (one-to-many) -> so this is mapping of 1 animal -> many tasks
        *
        * @param animal
        * @return must return Option[Seq[T]] because that's how cases in matchers work for Sequence of Options
        **/
      def unapplySeq(animal: Animal): Option[Seq[String]] =
        if (animal.species == "Cow") Some(Seq("milking", "breeding", "fertilizing"))
        else None
    }


    println("--- myFarmAnimaltasks --")

    val tasksForAnimals = extractTasksForAnimals(myAnimals)
    println(tasksForAnimals)

    val tasksForAnimalsExpanded = extractTasksForAnimals_Expanded(myAnimals)
    println(tasksForAnimalsExpanded)

  }

  `using unapplySeq()`
  println

}
