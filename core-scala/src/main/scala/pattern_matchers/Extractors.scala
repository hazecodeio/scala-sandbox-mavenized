package pattern_matchers

object Extractors extends App {

  case class Animal(name: String, age: Int, classification: Classification)

  case class Classification(genus: String, species: String)

  val cat = Animal("Santiago", 8, Classification("felix", "felicis"))

  val Animal(name, age, classification) = cat
  println(name, age, classification)
}
