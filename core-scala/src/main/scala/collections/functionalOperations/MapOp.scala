package collections.functionalOperations

object MapOp extends App {

    /**
     * Map Op
     */
    object MyMapOp {

        println("----------- List.map() ----------")
        val l = List(1, 2, 3, 4, 5)
        val f = (x: Int) => x + 1 //this is a function; aka lambda in Java

        println(l.map(f)) // by passing a function reference
        println(l.map((x: Int) => x + 1)) // by passing an inline function
        println(l.map(x => x + 1)) // type is unnecessary for type inference will kick in
        println(l.map(_ + 1)) // '_' can replace the variable name if a variable isn't desired
        println(l.map(1 + _)) // '+' is a commutative operation so flipping should work.
        println

        println("--- using: postfixOps ")

        import scala.language.postfixOps

        println(l.map(1 +)) //Scala will issue a warning around PostFix operator so why the import is used
        println

        println("------------ Set.map() --------------")

        val s = Set("Brown", "Red", "Purple", "Green", "Yellow")
        println(s.map(x => x.size)) // sizes will be collected in a set so any duplicate will be removed
        println(s.map(_.size))
        println(s.map(x => (x, x.size))) // outcome is a set of tuples
        println

        println("--------------- Map.map() ---------------")

        //    Literal symbol ' is deprecated as of 2.13
        //    val m = Map('Germani -> 4, 'Brazil -> 5, 'Italy -> 4, 'Argentina -> 2)

        val m = Map(Symbol("Germani") -> 4, Symbol("Brazil") -> 5, Symbol("Italy") -> 4, Symbol("Argentina") -> 2) // tuples of (Symbols, Int)
        //Just prepend the word "Team" to the each Symbol
        println(m.map(t => (Symbol("Team " + t._1.name), t._2))) //since t._1 is a symbol, we need to extract the String name out of the Symbol
        println

        println("------------ String.map() ------------")

        println("Hello!".map(c => c + 1))
        println("Hello!".map(c => (c + 1).toChar))
        println

        println("------------- Option.map() ------------")

        println(Some(4).map(_ + 1)) // return Some(5)
        //  println(None.map(_ + 1))// Won't work
        println(None.asInstanceOf[Option[Int]].map(_ + 1)) // It will simply return None with no change


    }

    MyMapOp
    println
}
