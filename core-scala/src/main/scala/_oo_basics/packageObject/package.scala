package _oo_basics

/**
 * - package object must have the package name it's packaging
 * - Instead do the following
 * - only define type aliases in 'package object'
 *
 * Links:
 *     - https://alvinalexander.com/scala/scala-package-objects-how-to-name-location-cookbook-recipe
 */
package object packageObject {

    /**
     * Don't do this No No No No
     */

    /*trait Foo

    case class FooStrImpl(bar: String) extends Foo

    case class FooIntImpl(bar: Int) extends Foo

    case class FooLongImpl(bar: Long) extends Foo*/

    /**
     * - Instead do the following
     * - only define type aliases in 'package object'
     */

    type StringFoo = Foo[String]
    type LongFoo = Foo[Long]
    type IntFoo = Foo[Int]

}
