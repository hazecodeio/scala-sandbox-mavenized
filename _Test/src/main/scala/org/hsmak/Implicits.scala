package org.hsmak

object Implicits extends App {


  println("------------ NoImplicit -------------")

  object NoImplicit {

    def operateOnString(str: String, op: String => Boolean) = {
      if (op(str))
        "It satisfies the passed operation!"
      else
        "It doesn't satisfy the passed operation!"
    }

    println(operateOnString("Hello", x => x.contains("H")))
    println(operateOnString("Hello", x => x.contains("q")))
  }

  NoImplicit
  println

  println("------------- WithImplicit ---------------")

  /**
    * Currying must be used if implicits are to be used
    */
  object WithImplicit {

    //implicit is to supply default value??
    implicit val op = (x: String) => x.contains("H")

    /**
      *
      * @param str
      * @param op : now this is a curried param
      * @return
      */
    def operateOnString(str: String)(implicit op: String => Boolean) = {
      if (op(str))
        "It satisfies the passed operation!"
      else
        "It doesn't satisfy the passed operation!"
    }

    println(operateOnString("Hello")) // implicit val is kicking in; which is: x.contains("H")
    println(operateOnString("Hello")(x => x.contains("H")))
    println(operateOnString("Hello")(x => x.contains("q")))

  }

  WithImplicit
  println

}
