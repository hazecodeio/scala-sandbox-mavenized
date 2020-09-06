package abstraction.objects

/**
  * Created by hsmak on 3/11/17.
  */
object FunctionalObjects extends App {

  //    Console println new Rational(1, 0)
  val oneHalf = new Rational(1, 2)
  val twoThirds = new Rational(2, 3)
  println(oneHalf.add(twoThirds))
  println(oneHalf add twoThirds)

  println(oneHalf.numer)

  println(oneHalf lessThan twoThirds)

  println(oneHalf max twoThirds)

  println(new Rational(5))
  println(new Rational(66, 42))
  println(oneHalf + twoThirds)
  println(oneHalf * twoThirds)
  println("twoThirds + oneHalf * twoThirds " + (twoThirds + oneHalf * twoThirds)) // precedence is behaving as expected, that is, * is performed 1st then add => twoThirds + (oneHalf * twoThirds)
  println("twoThirds + oneHalf multiply twoThirds " + (twoThirds + oneHalf multiply twoThirds)) // Wrong!! Because this from left to right with no precedence in effect!
  println("twoThirds + oneHalf.multiply(twoThirds) " + (twoThirds + oneHalf.multiply(twoThirds))) // Correct! Because the Dot notation already specified which operation belongs to which object!
  println(twoThirds * 2)

  implicit def intToRational(x: Int) = new Rational(x) // This defines a conversion method from Int to Rational. This needs to be in scope of the object or the interpreter
  println(2 * twoThirds) //the implicit function made it possible to do * operation on Int type


}

class Rational(n: Int, d: Int) {
  //precondition. this will throw IllegalArgumentException if not met
  require(d != 0)

  // find the gcd to simplify the rational expression. Expression will be simplified once object is created
  private val g = gcd(n.abs, d.abs)
  /*
   * if class parameters are val, you won't be able to use them in methods as in "add()" below;
   * i.e. that.n and that.d won't work
   * However, if n & d were "var" it would work!
   */
  val numer = n / g
  // divide by gcd to simplify
  val denom = d / g // divide by gcd to simplify

  def this(n: Int) = this(n, 1) // auxiliary constructor for cases such as 5/1

  def /(that: Rational): Rational =
    new Rational(numer * that.denom, denom * that.numer)

  def /(i: Int): Rational =
    new Rational(numer, denom * i)

  def +(that: Rational) = add(that)

  def add(that: Rational): Rational =
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom * that.denom)

  def *(that: Rational) = multiply(that)

  def multiply(that: Rational) =
    new Rational(this.numer * that.numer, this.denom * that.denom)

  def *(i: Int) = multiply(i)

  def multiply(i: Int) = new Rational(this.numer * i, this.denom)

  def max(that: Rational) =
    if (this.lessThan(that)) that else this

  def lessThan(that: Rational) = this.numer * that.denom < that.numer * this.denom

  override def toString = s"Rational = $numer/$denom"

  /**
    * GCD Greatest Common Factor
    *
    * @param a
    * @param b
    * @return
    */
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else gcd(b, a % b)
}