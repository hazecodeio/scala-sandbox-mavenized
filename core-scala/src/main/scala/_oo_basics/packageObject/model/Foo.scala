package _oo_basics.packageObject

//import oo_basics.packageObject._

trait Foo[A] {
    def bar: A
}

/*case class FooStrImpl(bar: String) extends Foo[String]

case class FooIntImpl(bar: Int) extends Foo[Int]

case class FooLongImpl(bar: Long) extends Foo[Long]*/

/**
 * use the type aliases
 */
case class FooStrImpl(bar: String) extends StringFoo

case class FooIntImpl(bar: Int) extends IntFoo

case class FooLongImpl(bar: Long) extends LongFoo


