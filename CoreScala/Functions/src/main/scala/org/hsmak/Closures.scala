package org.hsmak

object Closures extends App {

  //################################### Closure ###################################

  object MyClosue {

    // var for closure isn't recommended because there will be different behaviour
    val f = (x: Int) => x + m // this is where the closure effect takes place
    val foo = new Foo(100)
    var m = 200

    class Foo(x: Int) {
      def bar(y: Int => Int) = y(x)
    }

    println(foo.bar(f))
    m = 300 // changing m will result in different behavior when calling the method.
    println(foo.bar(f))
  }

  MyClosue
  println

}
