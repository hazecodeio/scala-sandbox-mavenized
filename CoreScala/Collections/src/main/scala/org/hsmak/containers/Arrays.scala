package org.hsmak.containers

object Arrays extends App {

  /**
    * Unlike other collections, Arrays are:
    *   - Mutable.... WARNING!
    *   - converted into primitive Arrays of [] by the JVM under the hood
    */
  object MyArray {
    val a: Array[Int] = Array(1, 2, 3)

    println("----------------Arrays operations------------")
    //same list operations can be done on arrays
    println(a.head)
    println(a.tail)
    println(a.init)
    println(a.last)
    println(a.max)
    println(a.min)
    println(a(2))
    println(a.isEmpty)
    println(a.nonEmpty)

    println

    println("----------------- Repeated Params --------------")

    /**
      *
      * @param x
      * @param y
      * @param z <- repeated param, also a WrappedArray
      * @return
      */
    def repeatedParamMethod(x: Int, y: String, z: Any*) = {
      println(z) //z is object of type WrappedArray
      "%d %ss give you  %s".format(x, y, z.mkString(", "))
    }

    //unlimited comma-separated params
    println(repeatedParamMethod(3, "egg", "a delicious sandwich", "protein", " high cholesterol"))
    println

    println("---- passing a list for 'Any*' --")
    val list = List("a delicious sandwich", "protein", " high cholesterol")

    //what if we pass a list
    println(repeatedParamMethod(3, "egg", list))
    println

    // ':_*' in front of any collection will break it apart so it will fit in 'z:Any*'.
    // this is a syntactic sugar for the repeated param
    println(repeatedParamMethod(3, "egg", list: _*))

  }

  MyArray
  println
}
