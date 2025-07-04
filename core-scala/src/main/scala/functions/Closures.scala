package functions

object Closures extends App {

    //################################### Closure ###################################

    object MyClosure {

        // var for closure isn't recommended because there will be different behaviour
        var m = 200

        val f = (x: Int) => x + m // this is where the closure effect takes place
        val foo = new Foo(100)

        class Foo(x: Int) {
            def bar(y: Int => Int) = y(x)
        }

        println(foo.bar(f))
        m = 300 // changing m will result in different behavior when calling the method.
        println(foo.bar(f))
    }

    MyClosure
    println

}
