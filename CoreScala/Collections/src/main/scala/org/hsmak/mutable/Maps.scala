package org.hsmak.mutable

import scala.collection._

object Maps extends App {

  val m = mutable.Map(1 -> "Foo");

  //using put()
  m.put(2, "Bar") //you can't do this with the immutable Map

  //using teh magical update()
  m(3) = "Baz"

  println(m)


}
