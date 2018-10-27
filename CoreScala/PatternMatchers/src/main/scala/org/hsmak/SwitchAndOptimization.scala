package org.hsmak

/**
  * maybe only optimized with primitive Int!?!?
  * if optimization is achieved -> compiler will jump to matched address directly with checking the previous cases
  */
object SwitchAndOptimization extends App {

  var result = 5 match {
    case 5 => "hello"
    case 3 => "good bye"
    case _ => "done"
  }

  println(result)

  /**
    * "@annotation.switch": force the compiler (if possible) to convert it to Java switch; optimized PatternMatcher
    *     - otherwise it will only issue a WARNING
    */
  result = (5: @annotation.switch) match {
    case 5 => "hello"
    case 3 => "good bye"
    case _ => "done"
  }

  println(result)

  case class Test(in: String)

  /**
    * Warning: could not emit switch for @switch annotated match
    * var t = (Test("hai"): @annotation.switch) match {
    */
  var t = (Test("hai"): @annotation.switch) match {
    case Test("bai") => "bai"
    case Test("hai") => "shai"
    case _ => "done"
  }
  println(t)

  /**
    * If there are only two cases, it will always be optimized under the JVM hood
    * - No WARNING will be issued
    */
  t = (Test("hai"): @annotation.switch) match {
    case Test("bai") => "bai"
    case _ => "done"
  }
  println(t)

  /////////////////////////////////

  println("Primitives are always optimized!?!?")
  val myNum = 10
  (myNum: @annotation.switch) match {
    case 5 => "hello"
    case 3 => "good bye"
    case _ => "done"
  }

  (myNum: @annotation.switch) match {
    case 5 => "hello"
    case 3 => "good bye"
    case big if big > 500 => "huge"
    case _ => "done"
  }
}
