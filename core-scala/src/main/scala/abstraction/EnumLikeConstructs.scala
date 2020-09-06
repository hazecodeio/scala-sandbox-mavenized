package abstraction

object EnumLikeConstructs extends App {

  sealed trait NoteName

  case object A extends NoteName

  case object B extends NoteName

  case object C extends NoteName

  case object D extends NoteName

  case object E extends NoteName

  case object F extends NoteName

  case object G extends NoteName

  sealed trait Symbol

  case class Note(name: NoteName, duration: String, octave: Int) extends Symbol


  ////////////////something that works like a Java enum//////////////////

  object Margin extends Enumeration {
    type Margin = Value
    val TOP, BOTTOM, LEFT, RIGHT = Value
  }


  import Margin._

  // use an enumeration value in a test
  var currentMargin = TOP

  // later in the code ...
  if (currentMargin == TOP) println("working on Top")

  // print all the enumeration values
  Margin.values foreach println

}
