package org.hsmak.examples

/*
 * This Complex class takes two arguments, which are the real and imaginary part of the complex.
 * These arguments must be passed when creating an instance of class Complex, as follows: new Complex(1.5, 2.3).
 * The class contains two methods, called re and im, which give access to these two parts.
 */
class Complex(real: Double, imaginary: Double) {

  override def toString() = "" + re + (if (im < 0) "" else " + ") + im + "i"

  def re = real //sama as -> def re() = real

  def im = imaginary //sama as -> def im() = imaginary
}

object ComplexNumbers {
  def main(args: Array[String]) {
    val c = new Complex(1.2, 3.4)
    println("imaginary part: " + c.im)
    println(c)
  }
}