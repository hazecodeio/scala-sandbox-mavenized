package stringOps

/**
  * Strings in Scala are the same String in Java
  */
object Strings extends App {


  println("----------------------- Instantiating Strings --------------------")
  val a = "Scala"
  val b: String = "Scala"
  val c = "Scala": String

  val str: java.lang.String = "Scala"

  val strObj = new String("hello")
  val unpooledStr = new String("Scala") //will fail the equality test because this is a reference to another object
  println(unpooledStr == strObj)

  val d = "\t\n\r" //tab, newline, carriage return

  val lambda = "\u03BB" //unit code for lambda
  println(lambda)

  println("------------------ Strings and Pool ------------------------")

  val strP1 = "hello" // will be stored in the string Pool
  val strP2 = "hello" // it's already in the pool so no new object will be created

  println("------------------ Equality: Object vs Reference ------------------------")

  // Note: the `==` in Scala is the opposite of that in Java
  println(strObj == strP1) //true
  println(strObj eq strP1) //false -> different reference

  println(strP1 == strP2) //true
  println(strP1 eq strP2) //true -> same reference

}
