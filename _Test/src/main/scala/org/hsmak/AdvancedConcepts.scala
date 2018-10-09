package org.hsmak

object AdvancedConcepts extends App {

  println("------------ RunIntsOntoFilters: not testable, readable, and composable ----------------")

  object RunIntsOntoFilters {
    val ints = List(1, 2, 22, 33, 44, 45, 21, 12, 121, 60, 99, 98, 26)

    val filters: List[Int => Boolean] = List(
      _ % 11 == 0,
      _ > 40
    )

    // (seedType)(accumulatorOfSeedType, OperationOn)
    val filteredInts: List[Int] = filters.foldLeft(ints)(
      (filteredIntsAccumulator: List[Int], nextFilter: Int => Boolean) => filteredIntsAccumulator.filter(nextFilter)
    )
    println(filteredInts)

    val filteredIntsWithInference: List[Int] = filters.foldLeft(ints)(
      (filteredIntsAccumulator, nextFilter) => filteredIntsAccumulator.filter(nextFilter)
    )
    println(filteredIntsWithInference)

    filters.foldLeft(ints)((acc, nxt) => acc.filter(nxt))
  }

  RunIntsOntoFilters
  println

  println("--------------- RunIntsOntoFiltersViaMethodReferences: more testable, readable, and composable --------------")

  object RunIntsOntoFiltersViaMethodReferences {
    val ints = List(1, 2, 22, 33, 44, 45, 21, 12, 121, 60, 99, 98, 26)

    def isDivisiblBy11(x: Int) = x % 11 == 0

    def isGreaterThan40(x: Int) = x > 40


    val filters: List[Int => Boolean] = List(
      isDivisiblBy11, //Method Reference
      isGreaterThan40 //Method Reference
    )

    // (seedType)(accumulatorOfSeedType, OperationOn)
    val filteredInts: List[Int] = filters.foldLeft(ints)(
      (filteredIntsAccumulator: List[Int], nextFilter: Int => Boolean) => filteredIntsAccumulator.filter(nextFilter)
    )
    println(filteredInts)

    val filteredIntsWithInference: List[Int] = filters.foldLeft(ints)(
      (filteredIntsAccumulator, nextFilter) => filteredIntsAccumulator.filter(nextFilter)
    )
    println(filteredIntsWithInference)

    filters.foldLeft(ints)((acc, nxt) => acc.filter(nxt))
  }

  RunIntsOntoFiltersViaMethodReferences
  println


  println("--------------- RunIntsOntoFiltersViaPassingFunctions: more testable??, readable, and composable --------------")
  object RunIntsOntoFiltersViaPassingFunctions {
    val ints = List(1, 2, 22, 33, 44, 45, 21, 12, 121, 60, 99, 98, 26)

    val isDivisiblBy11 = (x: Int) => (x % 11 == 0)

    val isGreaterThan40 = (x: Int) => x > 40

    val filters: List[Int => Boolean] = List(
      isDivisiblBy11, //Method Reference
      isGreaterThan40 //Method Reference
    )

    // (seedType)(accumulatorOfSeedType, OperationOn)
    val filteredInts: List[Int] = filters.foldLeft(ints)(
      (filteredIntsAccumulator: List[Int], nextFilter: Int => Boolean) => filteredIntsAccumulator.filter(nextFilter)
    )
    println(filteredInts)

    val filteredIntsWithInference: List[Int] = filters.foldLeft(ints)(
      (filteredIntsAccumulator, nextFilter) => filteredIntsAccumulator.filter(nextFilter)
    )
    println(filteredIntsWithInference)

    filters.foldLeft(ints)((acc, nxt) => acc.filter(nxt))
  }

  RunIntsOntoFiltersViaPassingFunctions
  println

  /**
    * TOB
    */
  object RunFiltersOntoInts {
    /*val ints = List(1, 2, 22, 33, 44, 45, 21, 12, 121, 60, 99, 98, 26)

    val filters: List[Int => Boolean] = List(
      _ % 11 == 0,
      _ > 40
    )*/

    //    ints.fil
    //    ints.foldLeft(filters)((acc:Int, nxt:) => )
  }

  RunFiltersOntoInts
  println

}
