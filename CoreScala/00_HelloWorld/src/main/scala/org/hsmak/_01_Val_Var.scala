package org.hsmak

object _01_Val_Var extends App{
  println("Hello World!")
  val doubleExplicit: Double = 400
  println(doubleExplicit.getClass)
  val doubleCoerced = 400: Double // coercing
  println(doubleCoerced.getClass)


  lazy val n = {
    println("wwwww");
    5
  } //instantiated when it's first called hence lazy val. This is only for val not var
  println(n)

  lazy val a1 = 10 + b1 // without lazy this won't be possible
  lazy val b1 = 5
  println(s"a1 + b1 = ${a1}")

  //bending var/val to a free text
  val `This is a val` = 123 // the backticks also allow to use keywords as var/val such as `import`
  println(`This is a val`)

  //the use of OPCHAR by appending '_' right before the OPCHAR such as '?' '!' etc
  val hola_? = 5555 // without the '_', it's a compile error
}
