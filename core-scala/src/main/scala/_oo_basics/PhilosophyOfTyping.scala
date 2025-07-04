package _oo_basics

/**
 * - to refer to an object type, call type on that object: "objectName.type"
 * - use it where type is expected
 * - It seems it's only applied on objects!?!?
 */
object PhilosophyOfTyping extends App {

    case object Three {
        def toInt = 3
    }

    def buildThreeExplicit: Three.type = Three

    def buildThree = Three

    println(buildThree.toInt)

    object TypeOnCaseClasses {

        case class Animal()

        val animal1 = Animal

        //The previous line is expanded to the following
        val animal2: Animal.type = Animal // remember this is referencing the CompanionObject so why the "Animal.type"
        // That's why the below methods are recognized
        animal2.apply()
        animal2.unapply(Animal())

        // This is calling the constructor
        val animal3: Animal = Animal()
        //    animal3.apply() // undefined -> compiler error
    }
}
