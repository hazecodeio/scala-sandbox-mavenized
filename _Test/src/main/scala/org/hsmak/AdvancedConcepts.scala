package org.hsmak


//ToDo: namings might be confusing. I need to revise

object AdvancedConcepts extends App {

  println("------------ RunIntsOntoFilters: not testable, readable, and composable ----------------")

  /**
    *
    */
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

    println(ints.foldLeft(List[Int]())((acc, n) => n :: acc))

    def isDivisiblBy11(x: Int) = x % 11 == 0

    def isGreaterThan40(x: Int) = x > 40

    val filters: List[Int => Boolean] = List(
      isDivisiblBy11, //Method Reference
      isGreaterThan40 //Method Reference
    )

    // (seedType)(accumulatorOfSeedType, NextItemOfCollectionType)
    // SeedType will determine the output type
    // output will be accumulated towards into the seed
    // in this case, the first filter is run through the seed which is the List of all Ints
    // then the next filter will be run through the same List of Ints
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

    // (seedType)(accumulatorOfSeedType, NextItemOfCollectionType) <- output of this Op is back to the accumulator
    // SeedType will determine the output type
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


  println("--------------- RunFiltersOntoInts: Inefficient but working --------------")

  object RunFiltersOntoInts {
    val ints = List(1, 2, 22, 33, 44, 45, 21, 12, 121, 60, 99, 98, 26)

    val isDivisiblBy11 = (x: Int) => (x % 11 == 0)

    val isGreaterThan40 = (x: Int) => x > 40

    val filters: List[Int => Boolean] = List(
      isDivisiblBy11, //Method Reference
      isGreaterThan40 //Method Reference
    )

    /**
      * pass all filters on each Int
      */
    println(ints.foldLeft(List[Int]())((acc, n) => {
      //Seed is an empty list to accumulate the Ints that pass the filters

      val booleans: List[Boolean] = filters.map(f => f(n))

      //Apply the list of filters on the current Int
      val bool = booleans.reduceLeft((t, n) => t && n) // Anding '&&' the list of boolean values. if one is false it will short-circuit to false

      if (bool)
        n :: acc
      else
        acc
    }))
  }

  RunFiltersOntoInts
  println

}
