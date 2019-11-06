package org.hsmak.functionalOperations

/**
  * general formula for non-list input:
  *     - lambdas.foldLeft(seed)((acc, func) => func(acc))// apply func on acc
  *
  * general formula for list input:
  *     - lambdas.foldLeft(seeds)((accs, func) => accs.Process(func))// re-stream accs and apply func on each
  *
  * Observation:
  *     - 'Seed' doesn't have to be an empty List/String/etc.
  *     - In this case. the Seed was the List of Ints needed transformations
  *
  */
object AdvancedConceptsViaFoldAndReduce extends App {

  println("------------ StreamLambdasOntoInts: not testable, readable, and composable ----------------")

  /**
    * Streaming Lambdas/functions onto a list of input data
    */
  object StreamLambdasOntoInts {
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

  StreamLambdasOntoInts
  println

  println("--------------- StreamLambdasOntoInts_ViaMethodReferences: more testable, readable, and composable --------------")

  /**
    * Streaming Lambdas/functions onto a list of input data
    */
  object StreamLambdasOntoInts_ViaMethodReferences {
    val ints = List(1, 2, 22, 33, 44, 45, 21, 12, 121, 60, 99, 98, 26)

    println(ints.foldLeft(List[Int]())((acc, n) => n :: acc))

    // breaking the filters out into their own method would make them testable

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

    filters.foldLeft(ints)(_.filter(_))
  }

  StreamLambdasOntoInts_ViaMethodReferences
  println


  println("--------------- StreamLambdasOntoInts_ViaPassingFunctions: more testable??, readable, and composable --------------")

  /**
    * Streaming Lambdas/functions onto a list of input data
    */
  object StreamLambdasOntoInts_ViaPassingFunctions {
    val ints = List(1, 2, 22, 33, 44, 45, 21, 12, 121, 60, 99, 98, 26)

    // the followings are functions/lambdas not methods

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

  StreamLambdasOntoInts_ViaPassingFunctions
  println


  println("--------------- StreamIntsOntoLambdas: Inefficient but working --------------")

  /**
    * Streaming Lambdas/functions onto a list of input data
    */
  object StreamIntsOntoLambdas {
    val ints = List(1, 2, 22, 33, 44, 45, 21, 12, 121, 60, 99, 98, 26)

    val isDivisibleBy11 = (x: Int) => (x % 11 == 0)

    val isGreaterThan40 = (x: Int) => x > 40

    val filters: List[Int => Boolean] = List(
      isDivisibleBy11, //Method Reference
      isGreaterThan40 //Method Reference
    )

    /**
      * Streaming a list of input data onto lambdas/functions
      */
    println(ints.foldLeft(List[Int]())((accumulator, nextInt) => {
      //Seed is an empty list to accumulate the Ints that pass the filters

      val booleans: List[Boolean] = filters.map(f => f(nextInt))

      //Apply the list of filters on the current Int
      val bool = booleans.reduceLeft((t, n) => t && n) // Anding '&&' the list of boolean values. if one is false it will short-circuit to false

      if (bool)
        nextInt :: accumulator
      else
        accumulator
    }))

  }

  StreamIntsOntoLambdas
  println

}
