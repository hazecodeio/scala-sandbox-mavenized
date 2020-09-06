package methods

object OperatorOverloading extends App {

  def +(x: Int, y: Int) = x + y

  println(this.+(3, 4)) // I had to use "this"

  def ~~+:> = true

  println(this.~~+:>)

}
