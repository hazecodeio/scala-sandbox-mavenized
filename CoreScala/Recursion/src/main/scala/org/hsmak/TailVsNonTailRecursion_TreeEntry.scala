package org.hsmak

/**
  * Recursion:
  *     - Intermediate/Recursive Case
  *     - Terminal Case
  *
  * Determine the:
  *     - param type
  *     - return type <- this will determine how the function recurse
  *
  */
object TailVsNonTailRecursion_TreeEntry extends App {

  sealed trait TreeEntry

  case class Branch(children: List[TreeEntry]) extends TreeEntry

  case class Leaf(content: String) extends TreeEntry


  println("---------------------- NonTailRecursiveCalls -------------------")

  object NonTailRecursiveCalls {

    /**
      * extract content from all leaves
      *
      * @param root
      * @return
      */
    def uniqueContent_NonTailRecursive(root: TreeEntry): List[String] = {

      root match {

        case Branch(children) => children.foldLeft(List[String]()) { (acc, nxt) => acc ++ uniqueContent_NonTailRecursive(nxt) } //Recursive Case "non-tail"

        /**
          * this is exactly like the previous line. it just makes use of the underscore "_"
          *
          * remember that:
          *   - the 1st '_' refers to 'acc'
          *   - the 2nd '_' refers to 'nxt'
          */
        //      case Branch(children) => children.foldLeft(List[String]())(_ ++ uniqueContent(_))

        case Leaf(content) => List(content) //Terminal Case
      }

    }


    val b = Branch(List(Leaf("hello"), Branch(List(Leaf("hi"), Leaf("bye")))))

    println(b)

    println(uniqueContent_NonTailRecursive(b))

    println("--")


    var deepNest = Branch(List())

    for (i <- 1 to 4000) {
      deepNest = Branch(List(deepNest))

      //the above case would call the below copy method from the CompanionObject
      //      deepNest = deepNest.copy(List(deepNest))
    }
    println("error: StackOverflow")
    //    println(uniqueContent(deepNest)) //error: StackOverflow
  }

  NonTailRecursiveCalls
  println


  println("---------------------- TailRecursiveCalls -------------------")

  object TailRecursiveCalls {

    /**
      * extract content from all leaves
      *
      * @param currentLevel
      * @param seenSoFar <- this is the key of having a tail Recursion, a param the keeps track what has been visited in the terminal case
      * @return
      */
    @annotation.tailrec
    def uniqueContent_TailRecursive_ViaSeq(currentLevel: Seq[TreeEntry], seenSoFar: Seq[String] = List()): Seq[String] = {

      currentLevel match {

        // the use of the operator '@' in the extractor to assign the retrieve values to var rest
        // link: https://www.safaribooksonline.com/videos/scala-intermediate-recipes/9781788397650/9781788397650-video1_2
        case Seq(Branch(children), rest@_*) => uniqueContent_TailRecursive_ViaSeq(children ++ rest, seenSoFar)

        case Seq(Leaf(content), rest@_*) => uniqueContent_TailRecursive_ViaSeq(rest, seenSoFar :+ content)

        case _ => seenSoFar
      }

    }


    /**
      * Compare this method to the previous one
      * List is used instead of Seq
      * List notations '+:' are used instead of the '@' operator
      * Link to more operations: https://stackoverflow.com/questions/6807540/scala-pattern-matching-on-sequences-other-than-lists
      *
      * Any performance/memory concern? not sure yet! <- time lapse below shows it has a bad performance
      * So stick to the 'Seq' and the '@_*'
      *
      * @param currentLevel
      * @param seenSoFar
      * @return
      */
    @annotation.tailrec
    def uniqueContent_TailRecursive_ViaList(currentLevel: List[TreeEntry], seenSoFar: List[String] = List()): List[String] = {

      currentLevel match {

        case (Branch(children) +: rest) => uniqueContent_TailRecursive_ViaList(children ++ rest, seenSoFar)

        case (Leaf(content) +: rest) => uniqueContent_TailRecursive_ViaList(rest, seenSoFar :+ content)

        case _ => seenSoFar
      }

    }

    var deepNest = Branch(List())

    for (i <- 1 to 490000) {
      deepNest = Branch(List(deepNest))

      //the above case would call the below copy method from the CompanionObject
      //      deepNest = deepNest.copy(List(deepNest))
    }

    val b = Branch(List(Leaf("hello"), Branch(List(Leaf("hi"), Leaf("bye")))))

    println("-- uniqueContent_TailRecursive_ViaSeq --")
    println(uniqueContent_TailRecursive_ViaSeq(List(b)))

    var s = System.currentTimeMillis();
    println(uniqueContent_TailRecursive_ViaSeq(List(deepNest))) //no StackOverflow after performing tail recursion
    var e = System.currentTimeMillis();
    println(e - s)

    println("-- uniqueContent_TailRecursive_ViaList --")
    println(uniqueContent_TailRecursive_ViaList(List(b)))

    s = System.currentTimeMillis();
    println(uniqueContent_TailRecursive_ViaList(List(deepNest))) //no StackOverflow after performing tail recursion
    e = System.currentTimeMillis();
    println(e - s)


    /**
      * Equivalent performance to [[uniqueContent_TailRecursive_ViaSeq]]
      *
      * @param currentLevel
      * @param seenSoFar
      * @return
      */
    @annotation.tailrec
    def uniqueContent_TailRecursive_ViaListAndCasting(currentLevel: List[TreeEntry], seenSoFar: List[String] = List()): List[String] = {

      currentLevel match {

        case List(Branch(children), rest@_*) => uniqueContent_TailRecursive_ViaListAndCasting(children ++ rest.asInstanceOf[List[TreeEntry]], seenSoFar)

        // It would be cool if we can specify the type of the Seq extractor like this 'rest:List @_*'
        case List(Leaf(content), rest@_*) => uniqueContent_TailRecursive_ViaListAndCasting(rest.asInstanceOf[List[TreeEntry]], seenSoFar :+ content)

        case _ => seenSoFar
      }

    }

    println("-- uniqueContent_TailRecursive_ViaListAndCasting --")
    println(uniqueContent_TailRecursive_ViaListAndCasting(List(b)))

    s = System.currentTimeMillis();
    println(uniqueContent_TailRecursive_ViaListAndCasting(List(deepNest))) //no StackOverflow after performing tail recursion
    e = System.currentTimeMillis();
    println(e - s)
  }

  TailRecursiveCalls
  println
}
