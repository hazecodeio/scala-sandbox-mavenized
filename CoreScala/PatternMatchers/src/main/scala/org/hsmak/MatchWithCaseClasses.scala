package org.hsmak

object MatchWithCaseClasses extends App {

  object NestedCases {

    case class Animal(name: String, age: Int, classification: Classification)

    case class Classification(genus: String, species: String)

    val cat = Animal("Santiago", 8, Classification("felix", "felicis"))

    val m = cat match {
      case Animal(name, age, classification) => s" This animal is a ($name) of age ($age) and of classification ($classification)"
    }

    println(m)
    // extracting from nested cases
    // notice the Classification
    val nestedM = cat match {
      case Animal(name, age, Classification(genus, species)) => s" This animal is a ($name) of age ($age) and of genus($genus) and species ($species)"
    }

    println(nestedM)

    val nestedMWithoperator_@ = cat match {
      case Animal(name, age, classification @ Classification(genus, species)) => s" This animal is a ($name) of age ($age) and of genus($genus) and species ($species) with extracted object classification ($classification)"
    }

    println(nestedMWithoperator_@)

  }
  NestedCases
  println

}
