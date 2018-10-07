package org.hsmak.containers

object Sets extends App {

  object MySet {

    // using the CompanionObject
    val s = Set(1, 2, 3, 4)// recall the magic mathod 'apply()'
    println(s)

    // using the CompanionObject.apply()
    val sA = Set.apply(1, 2, 3, 4)
    println(sA)

    println

    //Scala maintain the order of teh Set if # elements <= 4
    //Scala starts mixing up elements if # elements > 4
    val s2 = Set(1, 2, 3, 4, 4, 1, 6, 7)
    println(s2)

    val s3 = Set(4, 5, 6, 7)
    println(s3)

    println

    println("------ Set Algebraic Operations: diff, union, intersect ------")
    println("diff: Return a Set whose elements are missing from the 'right' Set but exists in the left set. Perform a (-) algebraic operation on each element")
    println(s diff s2)
    println(s2 diff s)
    println(s diff s3)
    println

    println("union: return a set whose elements are the combination of the two sets")
    println(s union s2)
    println(s union s3)
    println

    println("intersect: return a set whose elements are common between the two sets")
    println(s intersect s2)
    println(s intersect s2)
    println

    println

    println("-------Set Operations of: (++/-- +/-) --------")
    /**
      *
      * ++/-- for collection operation
      * +/- one item operation
      *
      */
    println(s ++ s2)
    println(s ++ List(3, 4, 5, 6, 7))
    println(s + 10)//add an element

    println(s -- s2)
    println(s -- List(3, 4, 5, 6, 7))
    println(s - 1)//remove an element

    println

    println("--------Contains an element?-----")
    println(s(3)) //check if the element 3 exists
    println(s.apply(3))
    println(s.contains(3))
  }

  MySet
  println
}
