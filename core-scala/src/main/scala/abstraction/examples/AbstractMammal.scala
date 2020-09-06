package abstraction.examples

/**
  * Abstract Class
  */
object AbstractMammal extends App {

  var w = new Wolf("Fang")
  println(w.move)

}

abstract class Mammal(val name: String) {
  //an abstract field that is not initialized to any value
  var moveSpeed: Double

  //abstract method that has no body
  def move: String
}

class Wolf(name: String) extends Mammal(name) {
  override var moveSpeed: Double = 35.0

  override def move = s"This wolf '$name' runs at speed '$moveSpeed'"
}