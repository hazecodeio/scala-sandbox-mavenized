package org.hsmak

;

object ParametrizedClasses extends App {


  println("################################### Parametrized Classes ###################################")

  case class Box[T](t: T) {
    //    def coupleWith[U](u:U):Box[Couple[T, U]] = new Box(new Couple(t, u)) //we can remove the 'new' keyword since Box is a case clase
    def coupleWith[U](u: U): Box[Couple[T, U]] = Box(Couple(t, u))
  }

  val intBox1 = new Box(1)

  // in Java -> public class Box<T>{public Box(T t)}
  // 'new' keyword is unnecessary
  val intBox2 = Box[Int](1)
  val intBox3: Box[Int] = Box(1)
  val intBox4 = Box(1): Box[Int] // coercion
  val doubleBoxBox = Box(Box(4.0))
  val couple = new Couple(10, "Scala")
  println(s"doubleBoxBox: ${doubleBoxBox.t.t}")


  println(Couple(10, "Scala"))
  println(new Couple(10, "Scala"))
  println(new Couple(10, "Scala").toString)
  println(Couple[Int, String](10, "Scala"))

  case class Couple[A, B](first: A, second: B) {
    //    def swap(): Couple[B, A] = new Couple[B, A](second, first)
    def swap() = new Couple[B, A](second, first) // return type will be inferred
  }

  println(couple)
  Couple("Hello", Couple(3, 22.2))

}
