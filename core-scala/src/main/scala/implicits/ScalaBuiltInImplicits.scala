package implicits

object ScalaBuiltInImplicits extends App {

    object InOrdering {

        /**
         * def sorted[B >: Int](implicit ord: scala.math.Ordering[B]): List[Int]
         */
        List(3, 2, 1).sorted

        //in order for the previous line to work there is actually an Ordering[Int] already implemented in scala library
        val retIntOrdering: Ordering[Int] = implicitly[Ordering[Int]]
        println(retIntOrdering) // print: scala.math.Ordering$Int$@43d7741f

        //and here we're passing the same but retrieved Ordering[Int] explicitly
        List(3, 2, 1).sorted(retIntOrdering)

        implicit val intOrdering = new Ordering[Int] {
            override def compare(x: Int, y: Int): Int = x - y
        }

        //the previous implicit will take precedence over the other one since this one is more local in scope
        println(List(3, 2, 1).sorted)

        // ToDo: How about using the singleton object "Ordering.by[in, out]"???
        //    Ordering.by[Animal, Int](_.age)
    }

    InOrdering
    println

    object DoubleOrdering {
        /**
         * def sorted[B >: Double](implicit ord: scala.math.Ordering[B]): List[Double]
         */
        List(3.2, 2.001, 0.2331).sorted
        val retDoubleOrdering = implicitly[Ordering[Double]]
        println(retDoubleOrdering) // print: scala.math.Ordering$Double$@3439f68d

        List(3.2, 2.001, 0.2331).sorted(retDoubleOrdering)

        /**
         * Comparing by converting Double into Int is inaccurate but just to show how the following implicit takes precedence
         */
        implicit val doubleOrdering = new Ordering[Double] {
            override def compare(x: Double, y: Double): Int = (x - y).toInt
        }

        //the previous implicit will take precedence over the other one since this one is more local in scope
        println(List(3.225, 3.229, 3.221).sorted)
    }

    DoubleOrdering
    println

    object OtherImplicits {
        println("Find them!!")
    }

    OtherImplicits
    println
}
