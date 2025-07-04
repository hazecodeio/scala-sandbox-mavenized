package type_parameterization.advanced.monoids

abstract class SemiGroup[A] {
    def add(x: A, y: A): A
}

/**
 * Think of it as the properties of an operation as in math, e.g.:
 *   - Multiplication
 *       - identity a*1 = a
 *   - Addition
 *       - identity a+0 = a
 *
 * So that's what a unit is about!!
 * Are Monoids more than a unit???
 *
 * @tparam A
 */
abstract class Monoid[A] extends SemiGroup[A] {
    def unit: A
}

/**
 * Monoids and implicits
 */
object ImplicitTest extends App {

    implicit object StringMonoid extends Monoid[String] {
        def add(x: String, y: String): String = x concat y

        def unit: String = ""
    }

    implicit object IntMonoid extends Monoid[Int] {
        def add(x: Int, y: Int): Int = x + y

        def unit: Int = 0
    }

    def sum[A](xs: List[A])(implicit m: Monoid[A]): A =
        if (xs.isEmpty) m.unit
        else m.add(xs.head, sum(xs.tail))

    println(sum(List(1, 2, 3)))
    println(sum(List("a", "b", "c")))
}