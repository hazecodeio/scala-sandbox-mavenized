package implicits

object Implicitly_Retrieve extends App {

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

  /**
    * CompanionObject holding the implicit Ordering[Animal]
    */
  object Animal {

    /**
      * This is a val
      */
    implicit val animalOrderingByName = new Ordering[Animal] {
      override def compare(x: Animal, y: Animal): Int = implicitly[Ordering[String]].compare(x.name, y.name)
    }


    //alternatively we can use the SingletonObject Ordering.by[in, out]
    //    implicit val animalOrderingByName = Ordering.by[Animal, String](_.name)
  }

  println("------------ Via CompanionObject: Animal.animalOrderingByName -------------")
  animals.sorted.foreach(println)
  println


  println("---------------- RecursiveLookUpForImplicits -----------------")
  object RecursiveLookUpForImplicits {

    /**
      * this is a def
      *
      * @param nameOrdering <- since this is implicit and there is Ordering[String] already defined, it will be passed in
      * @return
      */
    implicit def anotherAnimalOrderingByName(implicit nameOrdering: Ordering[String]) = new Ordering[Animal] {
      override def compare(x: Animal, y: Animal): Int = nameOrdering.compare(x.name, y.name)
    }.reverse

    animals.sorted.foreach(println)

  }

  RecursiveLookUpForImplicits
  println

}
