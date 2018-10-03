package org.hsmak.objects

class SecretAgent(val name: String) {

  //  def shoot(n: Int) = SecretAgent.decrementBullets(n) // or you can use import to avoid repetitive use of Object name like the following

  import SecretAgent._

  def shoot(n: Int) = decrementBullets(n)

}

/**
  * Companion Object
  *     - shared amongst all instances -> analogous to static
  *     - can't have a main method/runner
  *     - can access private members of the class
  *     - used as Factory Method to create instances and avoid code smells in the constructor e.g. creating an automatic HireDate in the constructor which is not Unit Testable
  * Sol: Have the companionObject create that for you and keep the constructor clean
  */
object SecretAgent {
  private var b: Int = 3000

  def bullets = b

  private def decrementBullets(count: Int): Unit = {
    if (b - count <= 0) b = 0
    else b = b - count
  }
}




/**
  * main runner
  */
object Runner extends App {

  println("------------------- SecretAgent ------------------------")
  val bond = new SecretAgent("James Bond")
  val felix = new SecretAgent("Felix Leitner")

  bond.shoot(800)
  felix.shoot(300)
  println(SecretAgent.bullets)
  //  println(new p().t)


  println("------------------- HireDate for Person ------------------------")
  Person
  println(Person.create.hireDate)

}

import java.time._

/**
  * you can make constructors private so they're only accessible to CompanionObjects:
  * 'class person private(...)' <- notice the position of the keyword 'private'
  *
  * @param hireDate
  */
class Person(val hireDate: LocalDateTime) {
  def this() = this(null)

  private def privateInfo = 1
}

/**
  * CompanionObject accessing private member of the class
  */
object Person {
  println(new Person().privateInfo) //accessing private member

  def create = new Person(LocalDateTime.now())
}