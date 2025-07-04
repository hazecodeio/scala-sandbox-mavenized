package collections.functionalOperations

object ForEachOp extends App {


    object ForEachOp {

        val a = 1 to 5

        println("-----Println returns unit-----")
        println(a.map(x => println(x))) // if we do this by mistake.. Println() returns Unit
        println

        println("----------ForEach on Range----------")

        println(a.foreach(x => println(x)))
        a.foreach(println _) // instead do this
        a foreach (println _)
        a foreach println _
        a foreach println
    }

    ForEachOp
    println

}
