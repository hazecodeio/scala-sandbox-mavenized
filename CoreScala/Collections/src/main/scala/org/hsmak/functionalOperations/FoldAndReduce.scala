package org.hsmak.functionalOperations

object FoldAndReduce extends App {


  //######################################## Fold/Reduce ################################

  object FoldAndReduceRunner {
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
    println("foldLeftResult: " + foldLeftResult)

    //Notice we also replaced the () with {} since it's a curried method and multi-lined lambda implementation
    val foldLeftResultWithPrintln = (1 to 10).foldLeft(0) { (total: Int, next: Int) =>
      println(s"Total: $total, Next: $next ")
      total + next
    }
    println("foldLeftResultWithPrintln: " + foldLeftResultWithPrintln)
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
    println("reduceLeftResult: " + reduceLeftResult)
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
    println("foldRightResult: " + foldRightResult)
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
    println("reduceRightResult: " + reduceRightResult)
    println("-------------")

    println("------------------ operations that use Reduce/Fold Right/Left --------------")

    println("sum: " + (1 to 10).sum)
    println("product: " + (1 to 10).product) // isn't this the factorial?
    println("mkString: " + (1 to 10).mkString(",")) // a reduction operation but on a string

    println("-----------")

    //////////////// using the underscore '_' fo rthe above operations

    println("foldLeft(0)(_ + _): " + (1 to 10).foldLeft(0)(_ + _)) // first '_' for first param "total" and second '_' for second param "next"

  }

  FoldAndReduceRunner
  println

}
