package org.hsmak

object PartialFunctions extends App {

  case class Animal(name: String, species: String)

  /**
    * PartialFunction[input, output]
    *
    * - How different is this mechanism from unapply()/unapplySeq()?
    *    - unapply()/unapplySeq(): Maybe this can be shipped with CompanionObject, hence part of the library and end use can't modify!
    *    - PartialFunction: I guess provides a way of doing the same thing while it's at end user's hand!
    *
    * @return
    */
  def meow: PartialFunction[Animal, String] = {
    case Animal(_, "Cat") => "meow"
    case Animal(_, "Tiger") => "meow"
    case Animal(_, "Lion") => "roar"
//    case Animal(_, _) => "This is not cat!!"
  }

  val pitbull = Animal("Pitbull", "Dog")
  val russianBlue = Animal("Russian Blue", "Cat")

  println(meow(russianBlue))
//  println(meow(pitbull))

  println(meow.isDefinedAt(russianBlue))
  println(meow.isDefinedAt(pitbull))

  def woof: PartialFunction[Animal, String] = {
    case Animal(_, "Dog") => "woof!"
  }

  println(woof(pitbull))
  println

  println("---------- speak -------------")
  /**
    * combining the two functions!!
    * is this doable with the other functions? like the ones in converting methods into functions?
    */
  val speak = meow orElse woof

  println(speak(russianBlue))
  println(speak(pitbull))

  println("---------- speak.applyOrElse -------------")

  println(speak.applyOrElse(pitbull, (animal: Animal) => "Spooky Fish!"))
  println(speak.applyOrElse(Animal("Grouper", "Fish"), (animal: Animal) => "Spooky Fish!"))

}