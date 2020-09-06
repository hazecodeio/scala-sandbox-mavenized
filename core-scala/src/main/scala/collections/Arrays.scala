package collections

object Arrays extends App {

  /**
    * Unlike other collections, Arrays are:
    *   - Mutable by default.... WARNING! However, they're of static size.
    *   - converted into primitive Arrays of [] by the JVM under the hood
    */
  object MyArray {
    val a: Array[Int] = Array(1, 2, 3)

    println("----------------Arrays operations------------")
    //same list operations can be done on arrays
    println("head       : " + a.head)
    println("tail       : " + a.tail) //will print the reference value
    println("a.init     : " + a.init) //will print the reference value
    println("a.last     : " + a.last)
    println("a.max      : " + a.max)
    println("a.min      : " + a.min)
    println("a(2)       : " + a(2))
    println("a.isEmpty  : " + a.isEmpty)
    println("a.nonEmpty : " + a.nonEmpty)

    // the only Mutating operation? However, we can't add or remove from the Array. Use the mutable ArrayBuffer if this is desired.
    a(2) = 300 // updating
    println("a(2) = 300 : " + a(2)) //retrieving again

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
      println(z) //z is object of type WrappedArray <- WrappedArray is deprecated in 2.13 and replaced by ArraySeq
      "%d %ss give you  %s".format(x, y, z.mkString(", "))
    }

    //unlimited comma-separated params
    println(repeatedParamMethod(3, "egg", "a delicious sandwich", "protein", " high cholesterol"))
    println

    println("---- passing a list for 'Any*' ---")
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
