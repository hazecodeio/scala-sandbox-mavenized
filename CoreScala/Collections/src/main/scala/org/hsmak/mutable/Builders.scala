package org.hsmak.mutable

/**
  * Builders are a way to provide mutable collections
  * WARNING: be aware about thread safety when used with threads
  * once done processing or modifying call "result()" to emit the Immutable List/Vector
  */
object Builders extends App {

  println("#################### ListBuilder #######################")

  object MyListBuilder {

    // val listBuilder:ListBuffer[Int] = List.newBuilder[Int] // returns ListBuffer[Int] -> that's of type "scala.collection.mutable.Builder[Int,List[Int]]"

    /* From the REPL:

    scala> l
    res3: scala.collection.mutable.Builder[Int,List[Int]] = ListBuffer()

    scala> l += 1
    res4: l.type = ListBuffer(1)

    scala> l
    res5: scala.collection.mutable.Builder[Int,List[Int]] = ListBuffer(1
     */

    val listBuilder = List.newBuilder[Int] // returns ListBuffer[Int] -> of type "scala.collection.mutable.Builder[Int,List[Int]]"

    println(listBuilder)

    listBuilder += 5
    println(listBuilder)

    listBuilder ++= Seq(11, 22, 33)
    println(listBuilder)

    println("--- once done from modifying -> return the Immutable List via list() ---")
    val result: List[Int] = listBuilder.result // return List[Int]
    println(result)


  }

  MyListBuilder

  println("#################### VectorBuilder #######################")

  object MyVectorBuilder {

    val vectorBuilder = Vector.newBuilder[Int] // returns VectorBuilder -> of type scala.collection.mutable.ReusableBuilder[Int,scala.collection.immutable.Vector[Int]]
    println(vectorBuilder)

    vectorBuilder += 5
    println(vectorBuilder.result) // must call result to extract the vector and print otherwise it will print the address

    vectorBuilder ++= Seq(11, 22, 33)
    println(vectorBuilder.result) // must call result to extract the vector and print otherwise it will print the address
  }

  MyVectorBuilder


}
