package _oo_basics

/**
  * Any
  * AnyVal
  * AnyRef
  */
//TODO: Consolidate this file for Any, AnyVal, & AnyRef material
object TypesAndConversions extends App {


  /**
    * default return type is: Any <- the common ancestor between Int and String i.e. AnyVal and AnyRef respectively
    *
    * @param x
    * @param y
    * @return
    */
  println("------------------- Add -------------------")

  //Different Types returned by a method/function
  def add(x: Int, y: Int) = { // Remember that 'Any' is the the parent of AnyRef and AnyVal. Therefore, the return common type for both here is 'Any'
    if (x > 10) (x + y).toString //Return String!!
    else x + y // return Int!!
  }


  println(add(11, 2))
  println(add(5, 2))


  println("------------------- Add2 -------------------")

  /**
    * Return AnyVal because Unit is AnyVal which is the common ancestor between Unit and Int
    *
    * @param x
    * @param y
    * @return
    */
  def add2(x: Int, y: Int) =
    if (x > 10) println(x + y) //return Unit
    else x + y //return Int

  println(add2(11, 2)) //printing "()"


  println("------------------- nonsense -------------------")


  def nonsense(g: Unit): Int = 40

  println(nonsense(())) //passing a unit-typed value
  println(nonsense((): Unit)) // explicitly


  println("------------------- ConvertingBetweenNumericTypes -------------------")

  object ConvertingBetweenNumericTypes {

    private val doubleNum = 1222432.1234
    println(doubleNum.toInt)
    println(doubleNum.toInt)
    println(doubleNum.round) //will give out long
    println(doubleNum.round.toInt)
  }

  ConvertingBetweenNumericTypes
  println


  println("------------------- UnitAndUnitConversion -------------------")

  /**
    * Unit is equivalent to void in Java
    *
    * check predef for the automatically inherited object in each Scala object:
    * https://www.scala-lang.org/api/current/scala/Predef$.html
    *     e.g. print println are inherited the reason you don't need to import them
    */
  object UnitAndUnitConversion {

    val g: Unit = ()

    val a = println("Scala Rocks!")
    println(a) //it will simply print "()"

  }

  UnitAndUnitConversion
  println
}
