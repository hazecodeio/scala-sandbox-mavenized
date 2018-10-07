package org.hsmak

object Maps extends App {

  /**
    * @see [[org.hsmak.Tuples]]
    */
  object MyMap {

    println("------------Tuples Illustration------------")

    //key/value tuples
    val m = Map((1, "One"), (2, "Two"))

    //Tuples
    val t: (Int, String) = (3, "Three")

    println("Tuples using '->'")

    val t2: (Int, String) = 3 -> "Three" // the arrow will be converted into a tuple
    println(t2)
    println

    println("---------------Map Operations--------------")

    val m2 = Map(1 -> "One", 2 -> "Two") //key/value tuples
    println(m2)
    println(m2.get(1)) //return the Option Some("One")

    //will return it directly but what if it wasn't there -> exception "NoSuchElementException"
    //  println(m2.apply(10))

    println(m2.get(10)) // return None

    println(s"List of tuples using toList: ${m2.toList}")
    println(m2.keys) //return a Set
    println(m2.keySet) //return a Set
    println(m2.values) // return MapLike
    println(m2.values.toSet) // return MapLike
    println(m2.values.toList) // return MapLike
    println


    println("-------------Symbols in Maps------------")

    /////////////// Symbols: they have a pool of their own

    val co = Symbol("Co")
    val co2 = 'Co

    println(co == co2) //true
    println(co eq co2) //true

    ////////////////////// Map with Symbols

    val elementsExplicit: Map[Symbol, String] = Map('Co -> "Cobalt", 'H -> "Helium", 'Pb -> "Lead")
    val elements = Map('Co -> "Cobalt", 'H -> "Helium", 'Pb -> "Lead")
    println(elements.get('H))
    println(elements get 'H)

  }

  MyMap
  println
}
