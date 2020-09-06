package collections.immutableDefault

object Ranges extends App {

  object MyRange {


    println("---------Range Operations----------")
    // Ranges are collections too so same operation of List can be applied on them too
    var r = 1 to 10 // include the 10
    println(r)
    println(r.head)
    println(r.tail)
    println(r.last)
    println(r.init)
    println(r.isEmpty)
    println(r.nonEmpty)
    println(r.mkString(", "))
    println


    println("-------- Until-----------")
    var r2 = 1 until 10 // excluded the 10
    println(r2)
    println
    println

    println("------ Incremental (+) Range using 'To' and stepping using 'By' -----------")
    val r3 = 2 to 10 by 2
    println(r3)
    println

    println("------ Decremental (-) Range using 'To' and stepping using 'By' -----------")
    val r4 = 10 to 2 by -2
    println(r4)
    println

    println("------ Range of Alphabetics --------")
    val r5 = 'a' to 'z'
    println(r5)
    println(r5.toList)
    println

    println("------ Combining Ranges using (++)  --------")
    val concatenatedRanges = ('a' to 'z') ++ ('A' to 'Z') ++ (1 to 10)// use ++ to concatenate multiple Ranges
    println(concatenatedRanges)
    println

    println("------ Range using Factory Method e.g. Rang(1, 10) --------")
    val r6 = Range(1, 10) //exclusive ==> until
    println(r6)
    println

    val r7 = Range(1, 10, 2) //exclusive with stepping
    println(r7)
    println

    println("------ Inclusive --------")
    val r8 = Range.inclusive(1, 10) //inclusive ==> to
    println(r8)
    println

    println("------ Range use in 'For' loops --------")
    for (i <- 1 to 10) println(i + 1)

    println("----------")

    for (i <- 2 to 10 by 2) println(i + 1)

  }

  MyRange
  println
}
