package abstraction.traits

//TODO: have an example of self type
/**
  * to me it is:
  *   - analogous to abstract protected method
  *   - the extending class will have to provide the concrete implementation
  *   - the abstract class doesn't need to extend any interface in order to make use of the abstract method
  *   - however, the method is a member of that interface
  *   - so provide a "use-a" relationship
  *   - in the case of the trait,
  *
  * Found this somewhere:
  *   - Traits also can use self-types. A self-type lists the required dependencies for mixing in the trait.
  *   - When mixing in the main trait, all self-type dependencies of that trait must also be mixed in, otherwise a compile-time error is thrown.
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
    // this: MyAbstractV1 => // using 'this' seems to be equivalent to 'self'.. not sure yet!

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

  println("------------- SelfTypeAndMixinTraits ----------------------")
  object SelfTypeAndMixinTraits {

    trait B {
      def bId = 2
    }

    trait A {
      self: B =>

      def aId = 1
    }

    //val a = new A  //***does not compile!!!***
    val obj = new A with B
    println((obj.aId + obj.bId))
    println

    println("----------------- MixIn SelfTypes -----------------")

    trait C {
      def cId = 2
    }

    trait AA {
      self: B with C => // Notice how self is of type B mixed-in with C

      def aId = 1
    }

    //val a = new A  //***does not compile!!!***
    val obj2 = new AA with B with C
    println((obj2.aId + obj2.bId + obj2.cId))
  }
  SelfTypeAndMixinTraits
  println

}



