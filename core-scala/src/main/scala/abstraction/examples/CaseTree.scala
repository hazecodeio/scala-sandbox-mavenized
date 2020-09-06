package abstraction.examples

/*
 * The fact that classes Sum, Var and Const are declared as case classes means that they differ from standard classes in several respects:
 *    - the new keyword is not mandatory to create instances of these classes (i.e., one can write Const(5) instead of new Const(5)),
 *    - getter functions are automatically defined for the constructor parameters
 *        (i.e., it is possible to get the value of the v constructor parameter of some instance c of class Const just by writing c.v),
 *    - default definitions for methods equals and hashCode are provided, which work on the structure of the instances and not on their identity,
 *    - a default definition for method toString is provided, and prints the value in a “source form” (e.g., the tree for expression x+1 prints as Sum(Var(x),Const(1))),
 *    - instances of these classes can be decomposed through pattern matching as we will see below.
 */


abstract class Expression

case class Term(l: Expression, r: Expression) extends Expression // An Algebraic Term

case class Var(n: String) extends Expression // An Algebraic Variable

case class Const(v: Int) extends Expression // An Algebraic Constant


class TestTree {

  // ToDo - custom "type" is really a lambda function that takes a param and emit the expected type.
  //  So, the magic apply() method is already there.
  //the type Environment can be used as an alias of the type of functions from String to Int
  type Environment = String => Int

  def evalSum(t: Expression, env: Environment): Int = t match {
    case Term(l, r) => evalSum(l, env) + evalSum(r, env) // Recursive calls
    case Var(n) => env(n) // calling apply() of Environment
    case Const(v) => v
  }

  /*
   * To explore pattern matching further, let us define another operation on arithmetic expressions: symbolic derivation.
   * The reader might remember the following rules regarding this operation:
   *    - the derivative of a sum is the sum of the derivatives,
   *    - the derivative of some variable v is one if v is the variable relative to which the derivation takes place, and zero otherwise,
   *    - the derivative of a constant is zero.
   */
  def derive(t: Expression, v: String): Expression = t match {
    case Term(l, r) => Term(derive(l, v), derive(r, v))
    case Var(n) if (v == n) => Const(1)
    case _ => Const(0)
  }

  def testApp {
    val exp: Expression = Term(Term(Var("x"), Var("x")), Term(Const(7), Var("y")))
    val env: Environment = {
      case "x" => 5
      case "y" => 7
    }
    println("Expression: " + exp)
    println("Evaluation with x=5, y=7: " + evalSum(exp, env))
    println("Derivative relative to x:\n " + derive(exp, "x"))
    println("Derivative relative to y:\n " + derive(exp, "y"))
  }
}


object test extends App {
  val t = new TestTree
  t.testApp
}