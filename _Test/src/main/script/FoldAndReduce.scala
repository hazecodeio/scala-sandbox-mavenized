
//######################################## Fold/Reduce ################################

object FoldAndReduce {
  /**
    * signature:
    *
    * def foldLeft[B](z: B)(op: (B, A) => B): B = {...}
    * [B] is parameterized type for result and the type will be used for the seed for the initial value and the accumulation
    * [A] is the type being passed from the collection
    *
    * foldLeft(_seed_)(_operation_on_seed_and_next_item_)
    * first element, leftmost element, will be accumulated towards the last, hence left to right
    */
  val foldLeftResult = (1 to 10).foldLeft(0)((total: Int, next: Int) => total + next)
  println(foldLeftResult)

  //Notice we also replaced the () with {} since it's a curried method and multi-lined lambda implementation
  val foldLeftResultWithPrintln = (1 to 10).foldLeft(0) { (total: Int, next: Int) =>
    println(s"Total: $total, Next: $next ")
    total + next
  }
  println(foldLeftResultWithPrintln)
  println("-------")

  //////////// reduceLeft

  /**
    * signature
    * def reduceLeft[B >: A](op: (B, A) => B): B = {...) <- no seed, only operation
    *
    * it picks the first element, leftmost element, as a seed for next!
    */
  val reduceLeftResult = (1 to 10).reduceLeft { (total: Int, next: Int) =>
    println(s"Total: $total, Next: $next ")
    total + next
  }
  println(reduceLeftResult)
  println("------")

  ////////////////// foldRight

  /**
    * signature
    * def foldRight[B](z: B)(op: (A, B) => B): B = {...}
    *
    * exactly same as foldLeft except the operation has the parametrized types swapped
    * so below we will swap total and next
    *
    * last element, rightmost element, will be accumulated towards the first, hence right to left
    */
  val foldRightResult = (1 to 10).foldRight(0) { (next: Int, total: Int) =>
    println(s"Total: $total, Next: $next ")
    total + next
  }
  println(foldRightResult)
  println("-------------")

  ////////// reduceRight

  /**
    * signature (swapping [A] & [B] in the operation if compared to reduceLeft)
    * def reduceRight[B >: A](op: (A, B) => B): B = {...) <- no seed, only operation
    *
    * it picks the last element, rightmost element, as a seed for the next!
    */
  val reduceRightResult = (1 to 10).reduceRight { (next: Int, total: Int) =>
    println(s"Total: $total, Next: $next ")
    total + next
  }
  println(reduceRightResult)
  println("-------------")

  ////////////// operations that use Reduce/Fold Right/Left

  println((1 to 10).sum)
  println((1 to 10).product) // isn't this the factorial?
  println((1 to 10).mkString(",")) // a reduction operation but on a string

  println("-----------")

  //////////////// using the underscore '_' fo rthe above operations

  println((1 to 10).foldLeft(0)(_ + _)) // first '_' for first param "total" and second '_' for second param "next"

}

FoldAndReduce
println


//######################################## Zip ################################

/**
  * Zip[: intertwine elements of two collections, and produces a collection of tuples of the same type
  */
object Zipping {
  val a = (1 to 4).toList
  val b = (5 to 8).toList
  println(a zip b)

  println((1 to 5) zip (6 to 9)) // constrained by the collection of the least elements
  println((1 to 3) zip (4 to 9)) // constrained by the collection of the least elements
}

Zipping
println

//######################################## RandomAPIMethods ################################

object RandomAPIMethods {

  println((1 to 12).partition(x => x % 2 == 0))
  println(List("Read", "Blue", "Brown", "Pink", "Purple", "Green", "Grey", "Orange").groupBy(x => x.head))
  println((1 to 100).take(10))
  println((1 to 100).takeRight(10))
  println((1 to 100).takeWhile(x => x % 50 != 0)) // take the first elements that satisfy the predicate then stop. Use filter if to return all elemnts that satisfy the predicate
  println((1 to 100).drop(80)) //drop the first 80 items
  println((1 to 100).dropWhile(x => x % 80 != 0)) // drop as long it satisfies the predicate. Once true stop dropping and take the rest of the collection

  println(List.fill(10) {
    // we can use a block since the 2nd param is a param by name 'def fill[A](n: Int)(elem: => A)'
    val x = 10
    val y = 20
    x + y + 19
  })

  val groceries = List("Apple", "Milk", "Naan", "Eggs", "Oranges")
  println(groceries zip (1 to 100)) //assign indeces to each item
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
  //use view for lazyiness -> all operations taking place after 'view.' will not be called till the terminal op is called i.e the reduce op 'mkString'
  println(groceries.view.zipWithIndex.map(t => (t._1, t._2 + 1)).map(_.swap).map(t => s"${t._1}. ${t._2}").mkString("\n"))
  println("-------------")
  //sort
  println(groceries.view.sorted.zipWithIndex.map(t => (t._1, t._2 + 1)).map(_.swap).map(t => s"${t._1}. ${t._2}").mkString("\n"))
}

RandomAPIMethods
println