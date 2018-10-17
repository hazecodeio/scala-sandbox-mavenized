package org.hsmak.traits

object CoreTraits extends App {

  println("------------------ RegularTraits ---------------------")

  object RegularTraits {

    trait Nameable {
      def name: String
    }

    trait Moveable {
      def moveToPlace(place: String): Unit

      def place: String
    }

    //This will fail for there is no implementation of the methods coming dwon from the trats
    //  class Person extends Nameable with Moveable

    class Person(val name: String, //val will provide default implementation for 'def name: String' as an accessor
                 var place: String) //var will provide default implementation for 'def place: String' as both accessor/mutator
    // extending from two traits
      extends Nameable with Moveable {

      def moveToPlace(place: String): Unit = {
        this.place = place
      }

    }


    val antonio = new Person("Antonio", "Canada")

    println(antonio.name)
    println(antonio.place)
    antonio.moveToPlace("USA")
    println(antonio.place)

    println(antonio.isInstanceOf[Moveable])
    println(antonio.isInstanceOf[Nameable])
  }

  RegularTraits
  println


  println("----------------- ExtendedTraits --------------------")

  object ExtendedTraits {

    trait Nameable {
      def name: String
    }

    trait Placeable {
      def place: String
    }

    trait Moveable extends Placeable {
      def moveToPlace(place: String): Unit

    }


    class Person(val name: String, //val will provide default implementation for 'def name: String' as an accessor
                 var place: String) //var will provide default implementation for 'def place: String' as both accessor/mutator
    // extending from two traits
    // Moveable is bringing down the Placebale methods too
      extends Nameable with Moveable {


      def moveToPlace(place: String): Unit = {
        this.place = place
      }

    }

    val antonio = new Person("Antonio2", "Canada2")

    println(antonio.name)
    println(antonio.place)
    antonio.moveToPlace("USA2")
    println(antonio.place)
    println(antonio.isInstanceOf[Moveable])
    println(antonio.isInstanceOf[Placeable])
    println(antonio.isInstanceOf[Nameable])
  }

  ExtendedTraits
  println

  println("----------------- TraitsWithDefaultMethods ---------------------")

  object TraitsWithDefaultMethods {

    trait Moveable {

      var placeCount = 0

      def incPlaceCount: Unit = placeCount += 1

      var place: String

      def moveTo(place: String) = this.place = place
    }

    class Person(val name: String, var place: String) extends Moveable {

      /**
        * Notice here we have to use the keyword 'override' becasue there is already a default impl from the trait
        *
        * @param place
        */
      override def moveTo(place: String): Unit = {
        this.place = place
      }

    }

    val antonio = new Person("Antonio3", "Canada3")

    println(antonio.name)
    println(antonio.place)
    antonio.moveTo("USA3")
    println(antonio.place)
    println(antonio.isInstanceOf[Moveable])

  }

  TraitsWithDefaultMethods
  println

  println("----------------- MultipleTraitsWithSameMethodsAndOrder ---------------------")

  object MultipleTraitsWithSameMethodsAndOrder {

    trait MoveableBy01 {

      //      var placeCount = 0
      var placeCount: Int

      def incPlaceCount: Unit = placeCount += 1

      var place: String

      /*def moveTo(place: String) = {
        this.place = place
        incPlaceCount
      }*/
    }

    trait MoveableBy02 {

      var placeCount = 0

      def incPlaceCount: Unit = placeCount += 2

      var place: String

      def moveTo(place: String) = {
        this.place = place
        incPlaceCount
      }

      def latestPlace: String = s"$place $placeCount" // this methid only in this trait
    }


    /**
      * notice the order: MoveableBy02 with MoveableBy01
      * @param name
      * @param place
      */
    class Person(val name: String, var place: String) extends MoveableBy02 with MoveableBy01 {

      /**
        * Notice here we have to use the keyword 'override' because there is already a default impl from the trait
        */
      override def incPlaceCount: Unit = super.incPlaceCount // this will call MoveableBy01.incPlaceCount

    }

    val antonio = new Person("Antonio3", "Canada3")

    println(antonio.latestPlace)
    antonio.moveTo("USA3")
    println(antonio.latestPlace)

    println("---- Switching orders 'MoveableBy01 with MoveableBy02' ---")

    /**
      * notice the order: MoveableBy01 with MoveableBy02
      * @param name
      * @param place
      */
    class Person2(val name: String, var place: String) extends MoveableBy01 with MoveableBy02 {

      /**
        * Notice here we have to use the keyword 'override' because there is already a default impl from the trait
        */
      override def incPlaceCount: Unit = super.incPlaceCount // this will call MoveableBy02.incPlaceCount

    }

    val antonio2 = new Person2("Antonio3", "Canada3")

    println(antonio2.latestPlace)
    antonio2.moveTo("USA3")
    println(antonio2.latestPlace)
    println(antonio2.isInstanceOf[MoveableBy01])
  }

  MultipleTraitsWithSameMethodsAndOrder
  println

}
