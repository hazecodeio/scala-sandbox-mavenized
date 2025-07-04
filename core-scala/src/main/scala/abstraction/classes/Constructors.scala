package abstraction.classes

object Constructors extends App {


    println("################################### Overloading Constructor using 'keyword this' ###################################")

    object ConstructorOverloadingUsingKeywordThis {

        /**
         * overloading constructor using 'this' in a block
         * -
         *     - link: https://www.safaribooksonline.com/videos/learning-path-scala/9781491970850/9781491970850-video256883
         *
         * @param firstName
         * @param lastName
         * @param age
         */
        case class Employee(firstName: String,
                            lastName: String,
                            age: Int) {

            //loaded constructor comes here

            /**
             * Overloaded constructor
             *
             * @param firstName
             * @param lastName
             * @return
             */
            def this(firstName: String, lastName: String) = this(firstName, lastName, -1)

        }

        /*
         * ToDo - So we there aren't apply() methods in the CompanionObject for overloaded constructor??
         *    - override the CompanionObject and overload the apply() method in it
         */
        println(new Employee("hhhh", "lllllll"))

        /**
         * Overriding the CompanionObject Employee
         */
        object Employee {
            /**
             * Overloading the apply() method
             *
             * @param firstName
             * @param lastName
             * @return
             */

            def apply(firstName: String, lastName: String): Employee = new Employee(firstName, lastName)
        }

        println(Employee("hhhh", "lllllll"))

    }

    ConstructorOverloadingUsingKeywordThis
    println

    println("########################## ConstructorWithNamedArgument #################################")

    object ConstructorWithNamedArgument {

        case class Employee(val firstName: String,
                            var lastName: String)

        println(Employee(lastName = "llll", firstName = "ffff"))
    }

    ConstructorWithNamedArgument
    println


    println("########################## ConstructorWithDefaultArgument #################################")

    object ConstructorWithDefaultArgument {

        case class Employee(val firstName: String, var lastName: String, age: Int = 30)

        //supplying default value

        println(Employee(lastName = "llll", firstName = "ffff")) // this actually calls the constructor with the age argument
    }

    ConstructorWithDefaultArgument
    println

    println("########################## ConstructorWithMethods #################################")

    object ConstructorWithMethods {

        case class Employee(val firstName: String, var lastName: String, age: Int = 30) {

            def fullName = s"$firstName $lastName"

            /**
             * create a new object by cloning this current object using the supplied default values or any explicitly passed is param
             *
             * @param firstName
             * @param lastName
             * @param age
             * @return
             */
            def copy(firstName: String = this.firstName,
                     lastName: String = this.lastName,
                     age: Int = this.age) = new Employee(firstName, lastName, age) // you can omit the 'new' keyword

            def changeLastName(ln: String) = Employee(firstName, ln)
        }


        val employee = Employee(lastName = "llll", firstName = "ffff") // this actually calls the constructor with the age argument
        val employee2 = employee.copy()
        println(employee2)
        println(employee2.fullName)
        println(employee2.changeLastName("AnotherLastName"))
        println(employee2.copy(lastName = "AnotherLastName2")) //using named arg
    }

    ConstructorWithMethods
    println

}
