package type_parameterization

object ParameterizedMethods extends App {

  println("----------------------- Intro to Parameterization ---------------------")
  //parametrized types on methods. Similar to Java's generics

  def parameterizedFunction[T](param: T) = param //param is of the type being passed during invocation

  println(parameterizedFunction[Boolean](true)) //brackets might be unnecessary if compiler can infer types
  println(parameterizedFunction(true))
  println

  println("---------------------- without parameterization --------------------")

  def decide(b: Boolean, x: Any, y: Any) = if (b) x else y

  println(decide(true, "A", "B"))
  println(decide(false, 3, 10))
  println(decide(true, 'C', 'D'))

  def getNextChar(c: Char) = (c + 1).toChar

  //  println(getNextChar(decide(true, 'C', 'D')))// compile error due to type mismatch

  println

  println("---------------------- with parameterization --------------------")

  def decideWithParameterization[T](b: Boolean, x: T, y: T) = if (b) x else y // Notice the type parameterization [T]

  println(decideWithParameterization(true, "A", "B"))
  println(decideWithParameterization(false, 3, 10))
  println(decideWithParameterization(true, 'C', 'D'))

  println(getNextChar(decideWithParameterization(true, 'F', 'D'))) // Works fine
  println

}
