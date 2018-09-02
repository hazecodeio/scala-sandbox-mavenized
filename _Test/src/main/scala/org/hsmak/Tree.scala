package org.hsmak


/*
 * The fact that classes Sum, Var and Const are declared as case classes means that they differ from standard classes in several respects:

    the new keyword is not mandatory to create instances of these classes (i.e., one can write Const(5) instead of new Const(5)),
    getter functions are automatically defined for the constructor parameters (i.e., it is possible to get the value of the v constructor parameter of some instance c of class Const just by writing c.v),
    default definitions for methods equals and hashCode are provided, which work on the structure of the instances and not on their identity,
    a default definition for method toString is provided, and prints the value in a “source form” (e.g., the tree for expression x+1 prints as Sum(Var(x),Const(1))),
    instances of these classes can be decomposed through pattern matching as we will see below.

 */



abstract class Tree

case class Sum(l: Tree, r: Tree) extends Tree

case class Var(n: String) extends Tree

case class Const(v: Int) extends Tree



class TestTree{

  //the type Environment can be used as an alias of the type of functions from String to Int
  type Environment = String => Int

  def eval(t: Tree, env: Environment): Int = t match {
    case Sum(l, r) => eval(l, env) + eval(r, env)
    case Var(n) => env(n)
    case Const(v) => v
  }

  /*
    To explore pattern matching further, let us define another operation on arithmetic expressions: symbolic derivation. The reader might remember the following rules regarding this operation:

    the derivative of a sum is the sum of the derivatives,
    the derivative of some variable v is one if v is the variable relative to which the derivation takes place, and zero otherwise,
    the derivative of a constant is zero.

 */
  def derive(t: Tree, v: String): Tree = t match {
    case Sum(l, r) => Sum(derive(l, v), derive(r, v))
    case Var(n) if (v == n) => Const(1)
    case _ => Const(0)
  }

  def testApp {
    val exp: Tree = Sum(Sum(Var("x"), Var("x")), Sum(Const(7), Var("y")))
    val env: Environment = {
      case "x" => 5
      case "y" => 7
    }
    println("Expression: " + exp)
    println("Evaluation with x=5, y=7: " + eval(exp, env))
    println("Derivative relative to x:\n " + derive(exp, "x"))
    println("Derivative relative to y:\n " + derive(exp, "y"))
  }
}



object test extends App{
  val t = new TestTree
  t.testApp
}