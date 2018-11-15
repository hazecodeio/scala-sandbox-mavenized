package org.hsmak

object ValsAndVars extends App {

  /**
    * var can be reassigned.
    * its use is discouraged
    * instead use val for functional style and immutability since once assigned can't be reassigned
    * var is more of an imperative style
    * var is used with Actors or in a single method where the scope is limited to that method
    */
  object MyVars {

    var myVar = 200
    println(s"this is var: $myVar")

    myVar = 11 // reassigning since this is allowed for var
    println(s"this is a var (reassigned): $myVar")


    println("----------- Inferred Typing! ----------- ")
    val doubleExplicit: Double = 400
    val doubleCoerced = 400: Double // coercing

    println(doubleExplicit.getClass)

    println(doubleCoerced.getClass)
  }

  MyVars
  println


  println("----------- Lazy Evaluation! Applies only to Val! ----------- ")

  object MyVal {

    lazy val n = {
      println("wwwww");
      5
    } //instantiated when it's first called hence lazy val. This is only for val not var
    println(n)

    lazy val a1 = 10 + b1 // without lazy this won't be possible
    lazy val b1 = 5
    println(s"a1 + b1 = ${a1}")

    println("----------- Banding Variables to our Wills using Backticks! ----------- ")
    //bending var/val to a free text
    val `This is a val` = 123 // the backticks also allow to use keywords as var/val such as `import`
    println(`This is a val`)

    val `import` = "this is val whose name is the keyword import"
    println(`import`)

    println("----------- the use of OpChar by appending '_' right before the OPCHAR such as '?' '!' etc ----------- ")
    //
    val hola_? = 5555 // without the '_', it's a compile error
    println(s"var using an OpChar: ${hola_?}")
  }

  MyVal
  println

  println("------------ Val_LazyVal_Def --------------")
  object Val_LazyVal_Def {
    val builder = new StringBuilder

    val x = { builder += 'x'; 1 }
    lazy val y = { builder += 'y'; 2 }
    def z = { builder += 'z'; 3 }

    z + y + x + z + y + x

    println(builder.result())
  }
  Val_LazyVal_Def


}
