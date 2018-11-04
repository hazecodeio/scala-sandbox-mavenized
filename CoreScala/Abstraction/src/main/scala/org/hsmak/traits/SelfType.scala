package org.hsmak.traits

//TODO: have an example of self type
/**
  * to me it is:
  *   - analogous to abstract protected method
  *   - the extending class will have to provide the concrete implementation
  *   - the abstract class doesn't need to extend any interface in order to make use of the abstract method
  *   - however, the method is a member of that interface
  *   - so provide a "use-a" relationship
  *   - in the case of the trait,
  */
object SelfType extends App {


  /**
    * Here is my initial though and comparing it to Java
    */

  trait MyAbstractV1 {
    def moveV1: Unit = {}
  }

  /**
    * notice the mandatory use of 'abstract'
    */
  abstract case class ProtectedClassV1() {

    self: MyAbstractV1 =>
    // this: MyAbstractV1 => // using 'this' seems to be equivalent to 'self'

    def methodDependentOnAbstractMethodV1(): Unit = {
      moveV1 //
    }

  }


  //////////////////// the Java way?? ///////////////////////////////

  trait MyAbstractV2 {
    def moveV2: Unit
  }

  /**
    * notice the mandatory use of 'abstract'
    */
  abstract case class ProtectedClassV2() {

    def a: MyAbstractV2

    def methodDependentOnAbstractMethodV2(): Unit = {
      a.moveV2 //
    }

  }


  //  val a = ProtectedClass()
  //  a.fun()
}



