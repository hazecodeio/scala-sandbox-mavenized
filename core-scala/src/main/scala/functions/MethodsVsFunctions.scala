package functions


object MethodsVsFunctions extends App {

  println("################################### Methods vs Functions ###################################")

  object MethOrFun {
    /**
      * Function -> which is an object itself; that's why we can call apply() on it.
      * Functions are traits that we instantiate anonymously.
      * so 'f' is a reference to the real object. Look up Method reference in Java8
      */
    val f = (x: Int) => x + 1

    /**
      * Method -> it has to be part of an object so it's a member of class/object not an object itself
      */
    def g(x: Int) = x + 1


  }

  println(MethOrFun.f.apply(4)) //a function aka an object too
  println(MethOrFun.f(4)) //a function aka an object too
  println(MethOrFun.g(4)) // a method not an object
  println

}
