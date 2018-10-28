package org.hsmak

object Implicitly_Scoped extends App {

  implicit val op = (x: String) => x.contains("H")

  def operateOnString(str: String)(implicit op: String => Boolean) = {
    if (op(str))
      "It satisfies the passed operation!"
    else
      "It doesn't satisfy the passed operation!"
  }

  println(operateOnString("Hello"))
  println


  println("Retrieve implicit lambda via 'implicitly[T]': ")
  println(implicitly[Function1[String, Boolean]])

  println("String the retrieved lambda via implicitly[T] in a val:")
  val w = implicitly[Function1[String, Boolean]];
  println(w)


  //re-pass the retrieved implicit
  println(operateOnString("Hello")(w))
  println

  println("------------- ScopedImplicit01 ---------------")

  object ScopedImplicit01 {

    implicit val ctx_01: List[String => String] = List(
      _.trim,
      _.toLowerCase)

    println(implicitly[List[Function1[String, String]]])

  }

  ScopedImplicit01
  println

  println("--------------- ScopedImplicit02 -------------------")

  object ScopedImplicit02 {

    //Same as previous one. just being explicit instead of inference
    implicit val ctx_02: List[String => String] = List(
      x => x.trim,
      x => x.toLowerCase)

    println(implicitly[List[Function1[String, String]]])

  }

  ScopedImplicit02
  println

  println("---------------- ScopedImplicit03 ----------------")

  object ScopedImplicit03 {

    //More explicit than ctx_02
    implicit val ctx_03: List[String => String] = List(
      (x: String) => x.trim,
      (x: String) => x.toLowerCase)

    println(implicitly[List[Function1[String, String]]])
  }

  ScopedImplicit03
  println


  println

  import ScopedImplicit01._

  println(implicitly[List[Function1[String, String]]])

  // This won't work because the previous line already imported similar cxt from a different object
  //  import ScopedImplicit03._
  //  println(implicitly[List[Function1[String, String]]])

}
