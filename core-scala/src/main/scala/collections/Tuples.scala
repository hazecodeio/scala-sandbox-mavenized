package collections

/**
 * Note: Tuples are not part of collection API!!!!! They're just containers that hold different types of data.
 * Tuples used in Maps
 */
object Tuples extends App {

    println("################################### Tuples (not part of collection in Scala) ###################################")

    //Testing import statement in a script file

    val t = (1, "Hus", 400.0)
    //types are inferred, so these are the expansion
    val t2: (Int, String, Double) = t //notice the parens

    val t3: Tuple3[Int, String, Double] = t // just as previously shown


    println(t._1, t._2, t._3) //starting from base one! up to 22! Unlimited TupleN in Scala 2.13


    println("------------------------- MyTuple -----------------------")

    object MyTuple {

        case class Department(name: String)

        val u = ("4", Department("QA")) // check scala api for tuples: https://www.scala-lang.org/api/current/scala/Tuple2.html
        println("-------- swapping in Tuple2 ------------")
        println(u)


        val u2 = u.swap // only Tuple2. it doesn't make sense to have this method for tuple size > 2
        println(u2)
        println(u) // because scala is immutable by nature the original will stay the same
    }

    MyTuple
    println

    println("------------ TheArrow_-> -----------")

    /**
     * The arrow '->' is part of any object
     * it will be converted into Tuple2
     */
    object TheArrow_-> {

        val t = 1 -> "one"
        println(t)

        val anotherT = 1 -> "one" -> 200 -> "interesting" // (((1,one),200),interesting)
        println(anotherT)

    }

    TheArrow_->
}
