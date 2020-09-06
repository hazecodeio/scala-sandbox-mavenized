package implicits

/**
  * In combination with implicit
  * Links:
  *     - https://blog.scalac.io/2017/04/19/typeclasses-in-scala.html
  *     - https://www.theguardian.com/info/developer-blog/2016/dec/22/parental-advisory-implicit-content
  *     - https://www.cakesolutions.net/teamblogs/demystifying-implicits-and-typeclasses-in-scala
  */
object TypeClasses_And_Implicits extends App {

  println("################ WithoutUtilizingImplicitAndTypeClasses #####################")
  object WithoutUtilizingImplicitAndTypeClasses {

    /**
      * Typeclass represented by a trait
      *
      * @param A
      */
    trait Show[A] {
      def show(a: A): String
    }

    /**
      * CompanionObject
      */
    object Show {

      val intShow: Show[Int] =
        new Show[Int] {
          def show(int: Int): String = s"int $int"
        }

    }


    /**
      * This is where it's knock-out
      * calling:
      *     - intShow for Int
      *     - doubleShow for Double
      *     - etc
      * We can't simply provide all implementation for all types out there for they might be over hundreds
      */
    println(Show.intShow.show(3))

  }
  WithoutUtilizingImplicitAndTypeClasses
  println





  println("################ UtilizingImplicitAndTypeClasses #####################")

  object UtilizingImplicitAndTypeClasses{

    /**
      * Typeclass represented by a trait
      *
      * @tparam A
      */
    trait Show[A] {
      def show(a: A): String
    }

    /**
      * CompanionObject
      *
      * The show() function takes some parameter of type A and an implementation of the Show trait for that type A.
      * Marking the intCanShow value as implicit makes the compiler able to find this implementation of Show[A] when there is a call to e.g. show(20).
      *
      * That is basically a type class.
      */
    object Show {

      /**
        * Redefine the same method from the trait [[Show.show()]] above
        * by introducing an implicit param
        *
        * @param a
        * @param sh
        * @tparam A
        * @return
        */
      def show[A](a: A)(implicit sh: Show[A]) = sh.show(a)

      /**
        * Some implicit implementation
        */
      implicit val intShow: Show[Int] =
        new Show[Int] {
          def show(int: Int): String = s"Int: $int"
        }


    }

    /**
      * We're not calling intShow
      * instead we're calling show which has an implicit val
      *
      * if a user is in need of a new TypeClass then defining a new implicit comes to the rescue :)
      */
    println(Show.show(2))

    implicit val doubleShow = new Show[Double]{
      override def show(a: Double) = s"Double: $a"
    }

    println(Show.show(2.5))// after introducing the new doubleShow
    println(Show.show(2))// of course the IntShow is still there!


  }
  UtilizingImplicitAndTypeClasses
  println

}
