package _oo_basics

object InfixOperators extends App {

    class Foo(x: Int) {
        def bar(y: Int) = x + y

        def baz(y: Int, z: Int) = x + y + z

        def qux(y: Int) = new Foo(x + y)
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


    /**
     * An infix type T1 op T2 consists of an infix operator op which gets applied to two type operands T1 and T2.
     * The type is equivalent to the type application op[T1,T2].
     */
    object AnotherInfixBizarre {

        case class Person(name: String)

        class Loves[A, B](val a: A, val b: B)

        /**
         * Bizarre
         *
         * @param couple : Loves[Person, Person] <=> Person Loves Person
         * @return
         */
        //ToDo - Revisit to understand infix Types
        def announceCouple(couple: Person Loves Person) = //Notice our type: Person Loves Person!
            couple.a.name + " is in love with " + couple.b.name

        val romeo = new Person("Romeo")
        val juliet = new Person("Juliet")

        println(announceCouple(new Loves(romeo, juliet)))
    }

    AnotherInfixBizarre
    println

    /**
     * An infix type T1 op T2 consists of an infix operator op which gets applied to two type operands T1 and T2.
     * The type is equivalent to the type application op[T1,T2].
     *
     * more elegant
     */
    object AnotherInfixBizarre_02 {

        case class Person(name: String) {
            def loves(person: Person) = new Loves(this, person)
        }

        class Loves[A, B](val a: A, val b: B)

        /**
         * Bizarre
         *
         * @param couple : Loves[Person, Person] <=> Person Loves Person
         * @return
         */
        //ToDo - Revisit to understand infix Types
        def announceCouple(couple: Person Loves Person) = //Notice our type: Person Loves Person!
            couple.a.name + " is in love with " + couple.b.name

        val romeo = new Person("Romeo")
        val juliet = new Person("Juliet")

        println(announceCouple(romeo loves juliet)) // notice how it's more elegant
    }

    AnotherInfixBizarre_02
    println
}
