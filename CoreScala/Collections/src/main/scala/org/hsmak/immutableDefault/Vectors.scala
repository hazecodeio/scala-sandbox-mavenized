package org.hsmak.immutableDefault

object Vectors extends App {

  /**
    * measure elapsed time
    *
    * @param f
    * @param A
    * @return
    */
  def time[A](msg: String)(f: => A): A = {

    val s = System.currentTimeMillis()
    val value = f
    val e = System.currentTimeMillis()
    println(s"Elapsed time for $msg: ${e - s}ms")

    value
  }

  object SlicingAndPatching {

    val v = time("Creating a Vector") {
      (1 to 10000000).toVector
    }
    println

    println(time("Slicing 5 Elements from 5000 to 5005") {
      v.slice(5000, 5005)
    })
    println

    println(time("Checking Size") {
      v.size
    })
    println

    val v2 = time("Patch: Insert at index 5000 and replace 5 Elements") {
      v.patch(5000, Seq(1, 2, 3, 4, 5), 5)
    } // patch: insert at an index and replace 5 elemnts
    println

    println(time("Slicing 7 Elements from 4999 to 5006 'immutable'") {
      v.slice(4999, 5006)
    })
    println

    println(time("Slicing 7 Elements from 4999 to 5006 'new vector'") {
      v2.slice(4999, 5006)
    })
    println

    val v3 = time("Patch: Insert at index 5000 and replace 1 Elements") {
      v.patch(5000, Seq(1, 2, 3, 4, 5), 1)
    } // patch: insert at an index and replace 5 elemnts
    println

    println(time("Slicing 7 Elements from 4999 to 5006 'new vector'") {
      v3.slice(4999, 5006)
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
    } // patch: insert at an index and replace 5 elemnts
    println

    println(time("Slicing 7 Elements from 4999 to 5006 'new vector'") {
      v5.slice(4999, 5006)
    })
    println

    val v6 = time("Patch: Insert at index 5000 and delete the remaining 10 elemens") {
      v.patch(5000, Seq(), 10)
    } // patch: insert at an index and replace 5 elemnts
    println

    println(time("Slicing 7 Elements from 4999 to 5006 'new vector'") {
      v6.slice(4999, 5006)
    })
    println

  }

  SlicingAndPatching
  println

}
