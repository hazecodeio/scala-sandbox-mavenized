package collections.performance

/**
 * how about Array()?
 *
 * links:
 *   - https://stackoverflow.com/questions/6928327/when-should-i-choose-vector-in-scala
 *   - https://docs.scala-lang.org/overviews/collections/performance-characteristics.html
 */
object ListVsVector extends App {

    println("###################### Performance: List Vs Vector #########################")

    /**
     * measure elapsed time
     *
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

    println("------------- ListPerformance: Bad ----------------")

    object ListPerformance {

        val l = time("Creating a List") {
            (1 to 10000000).toList
        }

        time("Retrieving Last Element") {
            l.last
        }

        time("Retrieving 500000th Element") {
            l(500000)
        }

        time("Retrieving 500000th Element using lift()") {
            l.lift(500000)
        }

        time("Prepending an Element") {
            0 :: l // Notice it's "+:" in Vector
        }

        time("Appending an Element") {
            l :+ 0
        }
    }


    ListPerformance
    println

    println("-------------- VectorPerformance: Excellent -----------------")

    object VectorPerformance {


        val v = time("Creating a Vector") {
            (1 to 10000000).toVector
        }

        time("Retrieving Last Element") {
            v.last
        }

        time("Retrieving 500000th Element") {
            v(500000)
        }

        time("Retrieving 500000th Element using lift()") {
            v.lift(500000)
        }


        time("Prepending an Element") {
            0 +: v // Notice it's "::" in List
        }

        time("Appending an Element") {
            v :+ 0
        }
    }


    VectorPerformance
    println

}
