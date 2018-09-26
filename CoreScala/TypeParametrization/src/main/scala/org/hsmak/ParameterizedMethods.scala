package org.hsmak

object ParameterizedMethods extends App {

  println("---------------------- without parameterization --------------------")
  def decide(b:Boolean, x:Any, y:Any) = if(b) x else y
  println(decide(true, "A", "B"))
  println(decide(false, 3, 10))
  println(decide(true, 'C', 'D'))

  def getNextChar(c:Char) = (c + 1).toChar
//  println(getNextChar(decide(true, 'C', 'D')))// compile error due to type mismatch


  println("---------------------- with parameterization --------------------")
  def decideWithParametirization[T](b:Boolean, x:T, y:T) = if(b) x else y// Notice the type parameterization [T]

  println(decideWithParametirization(true, "A", "B"))
  println(decideWithParametirization(false, 3, 10))
  println(decideWithParametirization(true, 'C', 'D'))

    println(getNextChar(decideWithParametirization(true, 'C', 'D')))// Works fine


}
