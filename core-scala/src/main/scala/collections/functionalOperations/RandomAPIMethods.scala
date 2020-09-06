package collections.functionalOperations

object RandomAPIMethods extends App {

  println("-------------- Functional Programming (Take & Drop) ------------")

  object TakeAndDrop {

    println("partition: " + (1 to 12).partition(x => x % 2 == 0))

    println("groupBy: " + List("Read", "Blue", "Brown", "Pink", "Purple", "Green", "Grey", "Orange").groupBy(x => x.head))

    println

    println((1 to 100).take(10))
    println((1 to 100).takeRight(10))
    println((1 to 100).takeWhile(x => x % 50 != 0)) // take the first elements that satisfy the predicate then stop. Use filter if to return all elemnts that satisfy the predicate
    println((1 to 100).drop(80)) //drop the first 80 items
    println((1 to 100).dropRight(80)) //drop the last 80 items
    println((1 to 100).dropWhile(x => x % 80 != 0)) // drop as long it satisfies the predicate. Once true stop dropping and take the rest of the collection

    println(List.fill(10) {
      // we can use a block since the 2nd param is a param by name 'def fill[A](n: Int)(elem: => A)'
      val x = 10
      val y = 20
      x + y + 19
    })
  }

  TakeAndDrop
  println

  println("-------------- Functional Programming (Zipping & Mapping) ------------")

  /**
    * Zipping: combining towo data types in a Tuple
    * Unzipping: is the reverse of zipping; unpacking each type from a list of tuples into a separate list of that type
    */
  object ZippingAndMapping {

    import scala.language.postfixOps

    val groceries = List("Apple", "Milk", "Naan", "Eggs", "Oranges")
    println(groceries zip (1 to 100)) //assign indices to each item
    println(groceries zipWithIndex) // however it starts with zero

    //let's add one to each index and swap
    println(groceries.zipWithIndex.map(t => (t._1, t._2 + 1)).map(t => t.swap)) //swap method is only part of Tuple2

    //let's convert each tuple to a String
    println(groceries.zipWithIndex.map(t => (t._1, t._2 + 1)).map(t => t.swap).map(t => s"${t._1}. ${t._2}"))

    //let's convert the list to a consolidated string with each item on its own line i.e. new line
    println(groceries.zipWithIndex.map(t => (t._1, t._2 + 1)).map(t => t.swap).map(t => s"${t._1}. ${t._2}").mkString("\n"))

    //use '_' for swap
    println(groceries.zipWithIndex.map(t => (t._1, t._2 + 1)).map(_.swap).map(t => s"${t._1}. ${t._2}").mkString("\n"))
    println("-------------")

    //use view for laziness -> all operations taking place after 'view.' will not be called till the terminal op is called i.e the reduce op 'mkString'
    println(groceries.view.zipWithIndex.map(t => (t._1, t._2 + 1)).map(_.swap).map(t => s"${t._1}. ${t._2}").mkString("\n"))
    println("-------------")

    //sort
    println(groceries.view.sorted.zipWithIndex.map(t => (t._1, t._2 + 1)).map(_.swap).map(t => s"${t._1}. ${t._2}").mkString("\n"))
  }

  ZippingAndMapping
  println

  object UnZippingAndMapping {

    val groceries = List("Apple", "Milk", "Naan", "Eggs", "Oranges")
    val zippedGroceries = groceries.zipWithIndex //assign indices to each item
    println(zippedGroceries)

    val (restoredGro, indices) = zippedGroceries.unzip
    println(restoredGro)

  }

  UnZippingAndMapping
  println
}
