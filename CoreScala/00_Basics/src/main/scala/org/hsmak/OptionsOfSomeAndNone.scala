package org.hsmak

//################################### Scala Option ###################################

/**
  * Finding:
  *     - Option behave like a collection. therefore Stream operation can be applied on it
  *     - so you can treat it like a collection but it's not
  *
  * Option[A]
  * <- Some[A]
  * <- None == Option[Nothing]
  *
  * Legend: '<-' extends from
  *
  * Link:
  *   -  scalas-hierarchy: https://www.safaribooksonline.com/library/view/programming-in-scala/9780981531687/scalas-hierarchy.html
  */
object OptionsOfSomeAndNone extends App {

  println("----------------- Option - Some ----------------")
  val middleName = Some("Antony") // Some is an object that has the method apply() -> Some.apply(..)
  val middleName2: Option[String] = middleName
  val middleName3: Some[String] = middleName

  println(s"$middleName $middleName2 $middleName3")
  println

  println(middleName.getOrElse("Unknown"))
  println

  println("----------------- Option - None ----------------")

  val noMiddleName = None // None is a type of Option[Nothing]. It's a singleton object
  val noMiddleName2: Option[String] = noMiddleName
  val noMiddleName3: Option[Nothing] = noMiddleName // Nothing is the subtype of every class
  // Nothing is the subtype of every class
  val noMiddleName4: None.type = noMiddleName

  println(s"$noMiddleName $noMiddleName2 $noMiddleName3 $noMiddleName4")
  println


  println("--------------------- Employee - with Option ----------------------")

  object EmployeeWithOption {

    //making constructor private
    //class Employee private (val firstName: String, val middleName: Option[String], val lastName: String) {
    class Employee(val firstName: String, val middleName: Option[String], val lastName: String) {

      /**
        * Use Ancillary constructors to hide the Option param from end user.
        * If you wish you may make the main constructor private so users isn't aware of Option at all
        *
        * @param firstName
        * @param middleName
        * @param lastName
        */
      def this(firstName: String, middleName: String, lastName: String) = this(firstName, Option(middleName), lastName)

      def this(firstName: String, lastName: String) = this(firstName, None, lastName)

      def this() = this("Unknown", "Unknown")
    }

    /**
      * CompanionObject
      */
    object Employee {
      def apply(firstName: String, middleName: String, lastName: String) = new Employee(firstName: String, middleName: String, lastName: String)

      def apply(firstName: String, lastName: String) = new Employee(firstName: String, lastName: String)

      def apply() = new Employee()

      //      def apply = new Employee// the missing () hid the access to the getters???
    }

    //This is how it will look like if constructor was public and there was no CompanionObject
    val husAk = new Employee("Hus", Some("W"), "AK")
    val antony = new Employee("Antony", None, "Last")

    //Using the CompanionObject
    val husAk2 = Employee("Hus", "W", "AK")
    val antony2 = Employee("Antony", "Last")
    val unknown = Employee()

    // retrieve values from Option using get/getOrElse

    println(noMiddleName.getOrElse("Unknown"))
    println(husAk2.middleName.getOrElse("Unknown2"))
    println(unknown.middleName.getOrElse("Unknown2"))

    def peelMiddleName(x: Option[String]): String = {
      x match {
        case Some(name) => name
        case None => "Unknown"

      }
    }

    println("--------------------- Invoking Peel method -----------------------")
    println(peelMiddleName(husAk2.middleName))
    println(peelMiddleName(unknown.middleName))

  }

  EmployeeWithOption
  println

  println("---------------------------------- OrElse_vs_GetOrElse -------------------------------")

  /**
    * orElse(): Functional and Intermediate Operation
    * get()/getOrElse(): Terminal operation
    */
  object OrElse_GetOrElse {

    val myAlly = Some("Ally")
    val noAlly = None

    val myFriend = Some("Friend")
    val noFriend = None

    val myPet = Some("Pet")
    val noPet = None

    val someRelationship: Option[String] = noAlly orElse myFriend orElse myPet
    println(someRelationship)

    val relationship: String = noAlly orElse myFriend orElse myPet getOrElse "myself"
    println(relationship)

    val nobody: String = noAlly orElse noFriend orElse noPet getOrElse "myself"
    println(nobody)
  }

  OrElse_GetOrElse
  println()

  println("---------------------- OptionOfMultipleElements ------------------------")

  /**
    * It's still  one element but it will unfold into a Tuple of these elements
    * Remember as of Scala 2.12 you can go up to 'Tuple22'
    */
  object OptionOfMultipleElements {
    val o = Some(1, 2, 3)

    // the above would be unfolded into the following; Tuple
    val o2: Option[Tuple3[Int, Int, Int]] = Some(1, 2, 3)

    println(o2)
    println(o2.map(x => x._1))


  }

  OptionOfMultipleElements
  println

  object FoldingWithOption {
    val number: Option[Int] = Some(3)
    val noNumber: Option[Int] = None
    val result1 = number.fold(1)(_ * 3)
    val result2 = noNumber.fold(1)(_ * 3)

    println(result1)
    println(result2)
  }

  FoldingWithOption
  println
}
