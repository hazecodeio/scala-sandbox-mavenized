package abstraction.objects

/**
 * For Classes:
 *  - Need to define a template to create multiple instances
 *  - Every instance has a state
 *
 * For Objects:
 *  - You need a singleton
 *  - You need a factory pattern, which defined as: Creating families of related or dependent
 *    objects without specifying or hiding their concrete classes.
 *  - You need to implement pattern matching logic
 *  - You need create a utility that doesn’t require and instance or a state.
 *  - You have some default values or constants
 *
 * In a Nutshell: Objects are typically meant for factories, utilities, defining pattern matching, defining
 * defaults, and main methods
 */
object SingletonObjects extends App {

    object QuickyObject {

        /**
         * You can have one and only one object without a class
         * it's called SingletonObject
         */
        object JustAnObject
        //    case object JustAnObject // notice the case keyword

        println(
            s"""${JustAnObject.toString}
               |${JustAnObject.getClass}""".stripMargin)
        println

        object SingletonObject

        println(SingletonObject)
        println

        val j = JustAnObject
        val a1 = SingletonObject
        val a2 = SingletonObject

        /**
         * '==' is object equality
         * 'eq' is reference equality
         */

        // this will yield this warning: comparing values of types abstraction.objects.SingletonObjects.quickyObject.SingletonObject.type and
        // abstraction.objects.SingletonObjects.quickyObject.JustAnObject.type using `==' will always yield false
        println(a1 == j) //false
        println(a1 eq j) //false

        println(a1 == a2) //true
        println(a1 eq a2) //true

        // Diff between Classes & Objects: https://www.safaribooksonline.com/videos/learning-path-scala/9781491970850/9781491970850-video256895

        object ObjectWithMethods {
            def foo(x: Int, y: Int) = x + y //similar to static method. In scala this is called an object method
        }

        println(ObjectWithMethods.foo(2, 3))
    }

    QuickyObject
    println

    object objectExtendingClass {


        case class Employee(firstName: String, lastName: String, title: String)

        object Husain extends Employee("Hus", "AK", "Developer")

        println(Husain.firstName)
        println(Husain.lastName)
        println(Husain.title)

    }

    objectExtendingClass

}



