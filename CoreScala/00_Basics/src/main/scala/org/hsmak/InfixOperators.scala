package org.hsmak

object InfixOperators extends App {

  class Foo(x: Int) {
    def bar(y: Int) = x + y
    def baz(y: Int, z: Int) = x + y + z
    def qux(y:Int) = new Foo(x + y)
  }

  println("------------------- InfixWithOneParam ----------------------")
  object InfixWithOneParam {
    Console.println("Using a dot")
    Console println "Infix"

    println(3 + 4)
    println(3.+(4))
  }


  InfixWithOneParam
  println

  println("------------------- AnotherInfixWithOneParam ----------------------")
  object AnotherInfixWithOneParam {

    val foo = new Foo(5)

    println(foo.bar(10))
    println(foo bar 10)

    println(foo bar 10 + 2)
    println(foo.bar(10).+(2))
  }

  AnotherInfixWithOneParam
  println

  println("------------------- InfixWithMultipleParams ----------------------")
  object InfixWithMultipleParams {


    val foo = new Foo(5)

    println(foo.baz(10, 2))
    println(foo baz(10, 2)) //notice the use of params if it's multiple params
  }

  InfixWithMultipleParams
  println

  println("------------------- InfixWithQuxMethod ----------------------")
  object InfixWithQuxMethod {


    val foo = new Foo(5)

    println(foo qux 4 qux 4 qux 4 qux (2 + 3) bar 40 + 300) // 4+5 + 4 + 4 + 5 + 40 +300
  }

  InfixWithQuxMethod
  println

}
