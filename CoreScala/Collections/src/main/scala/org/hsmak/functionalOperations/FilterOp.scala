package org.hsmak.functionalOperations

object FilterOp extends App {

  /**
    * Filter Op
    */
  object MyFilter {


    println("---------- filter_filterNot_exists on Range --------------")

    val range = 1 to 10

    println(range.filter(x => x % 2 == 0)) //Filter out even numbers
    println(range.filterNot(x => x % 2 == 0)) //the opposite of Filter
    println(range.exists(x => x % 2 == 0)) // 'exists()' takes a function while 'contain()' checks for an element

    println("------------- using underscore '_' -------------")

    println(range.filter(_ % 2 == 0)) //Filter out even numbers
    println(range.filterNot(_ % 2 == 0)) //the opposite of Filter
    println(range.exists(_ % 2 == 0)) // 'exists()' takes a function while 'contain()' checks for an element
    println


    println("---------- filter_filterNot_exists on Set --------------")

    val s = Set("Brown", "Red", "Purple", "Green", "Yellow")

    def filterVowels(s: String) = s.toLowerCase.filter(c => Set('a', 'e', 'i', 'o', 'u').contains(c))

    print("filter out the words that contain at least 1 vowel: ")
    println(s.filter(x => filterVowels(x).size > 1))

    println(s"""filterVowels("Orange"): ${filterVowels("Orange")}""")
    println


    println("---------- filter_filterNot_exists on Map --------------")

    val m = Map(1 -> "One", 2 -> "Two", 3 -> "Three", 4 -> "Four")

    println(s"m.filterKeys: ${m.filterKeys(_ % 2 == 0)}")
    println


    println("---------- filter_filterNot_exists on Option --------------")

    println(Some(5).filter(_ % 2 == 0)) // return None
    println(Some(4).filter(_ % 2 == 0)) // return Some(4)
  }

  MyFilter
  println

}
