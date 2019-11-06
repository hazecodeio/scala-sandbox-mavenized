package org.hsmak.immutableDefault

/*
 * Vectors seem to beat other containers in terms of performance when patching
 * ToDo - Compare Vectors and other data structures
 */
object Vectors extends App {

  /**
    * measure elapsed time
    *
    * @param msg
    * @param f
    * @tparam A
    * @return
    */
  def time[A](msg: String)(f: => A): A = {

    val s = System.currentTimeMillis()
    val value = f
    val e = System.currentTimeMillis()
    println(s"Elapsed time for $msg: ${e - s}ms")

    value
  }

  /**
    * Observation:
    *   - A `val` can't define a generics/variances.
    *   - It has to be a method via 'def'
    *   - It must be done through Partial Function application
    * Link: https://stackoverflow.com/questions/17372916/how-do-i-make-lambda-functions-generic-in-scala
    *
    * @tparam A
    * @return
    */
  def `instantiating a construct`[A] = time("Instantiating a Construct...")(_: A)

  object SlicingAndPatching {

    val v = time("Creating a Vector") {
      (1 to 10000000).toVector
    }
    println

    println(time("Slicing 5 Elements from 5000 to 5005") {
      v.slice(5000, 5005) // Remember that Vectors are immutable. So this will return the sliced elements in a new vector
    })
    println

    println(time("Checking Size") {
      v.size
    })
    println

    val vectorPatch5 = time("Patch: Insert at index 5000 and replace 5 Elements") {
      v.patch(5000, Seq(1, 2, 3, 4, 5), 5)
    } // patch: insert at an index and replace 5 elements
    println

    println(time("Slicing 7 Elements from 4999 to 5006 'immutable'") {
      v.slice(4999, 5006) // this is to show the original vector is immutable
    })
    println

    println(time("Slicing 7 Elements from 4999 to 5006 'new vector'") {
      vectorPatch5.slice(4999, 5006) // Inclusive@4999 <-> Exclusive@50006
    })
    println

    val vectorPatch1 = time("Patch: Insert at index 5000 and replace 1 Elements") {
      v.patch(5000, Seq(1, 2, 3, 4, 5), 1)
    } // patch: insert at an index and replace 5 elemnts
    println

    println(time("Slicing 7 Elements from 4999 to 5006 'new vector'") {
      vectorPatch1.slice(4999, 5006)
    })
    println

    val v4 = time("Patch: Insert at index 5000 don't replace any element") {
      v.patch(5000, Seq(1, 2, 3, 4, 5), 0)
    } // patch: insert at an index and replace 5 elemnts
    println

    println(time("Slicing 7 Elements from 4999 to 5006 'new vector'") {
      v4.slice(4999, 5006)
    })
    println

    val v5 = time("Patch: Insert at index 5000 replace 5 elements and delete the remaining 5 ones") {
      v.patch(5000, Seq(1, 2, 3, 4, 5), 10)
    } // patch: insert at an index and replace 5 elements
    println

    println(time("Slicing 7 Elements from 4999 to 5006 'new vector'") {
      v5.slice(4999, 5006)
    })
    println

    val v6 = time("Patch: Insert at index 5000 and delete the remaining 10 elemens") {
      v.patch(5000, Seq(), 10)
    } // patch: insert at an index and replace 5 elements
    println

    println(time("Slicing 7 Elements from 4999 to 5006 'new vector'") {
      v6.slice(4999, 5006)
    })
    println

  }

  SlicingAndPatching
  println

}
