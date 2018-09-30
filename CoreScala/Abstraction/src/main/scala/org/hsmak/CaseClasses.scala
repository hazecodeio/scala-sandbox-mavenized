package org.hsmak

object CaseClasses extends App {


  abstract class Person {
    //abstract methods.. compared to Java we only add 'abstract' to the class. abstract methods don't make use of this keyword
    def firstName: String

    def lastName: String

    //some random concrete methods
    def concreteMethod(): Unit = {

    }
  }


  println("################################### Case Class Department ###################################")

  /**
    * overloading constructor using 'this' in a block
    * link: https://www.safaribooksonline.com/videos/learning-path-scala/9781491970850/9781491970850-video256883
    *
    * @param firstName
    * @param lastName
    * @param age
    */
  class Employee(val firstName: String,
                 var lastName: String,
                 val age: Int) {

    def this(firstname: String, lastName: String) = this(firstname, lastName, -1)

    require(firstName.nonEmpty, "First name can't be empty!") // this will throw an exception if first name is empty

    if (age <= 0)
      throw new IllegalArgumentException("Age can't be negative!")


    /**
      * create a new object by cloning this current object using the supplied default values or any explicitly passed is param
      *
      * @param firstName
      * @param lastName
      * @param age
      * @return
      */
    def copy(firstName: String = this.firstName,
             lastName: String = this.lastName,
             age: Int = this.age) = new Employee(firstName, lastName, age)
  }


  // remember: inheritance inherits both State & Behaviour from the parent (to doscuss further later on private access)
  // val is simply to add an accessor
  // same Java's polymorphism rules apply such object type, reference type, variable type, Is-A relationship etc


  println("################################### Abstract Classes ###################################")

  /**
    * case classes will automatically add: toString(), equals(), hashCode(), copy(), apply() so no need to use 'new' keyword, CompanionObject?
    * 'val' is the default to constructor params
    * if you don't like what case provides you can always override
    * used in pattern matching
    * it makes the following possiple: val Department(n) = dept// param already assigned in dept will be extracted and assigned to 'n'
    * and so on
    *
    * however, case classes can't be extended/subclassed, maybe because the copy() will always return an instance of the superclass; which violate Liskov Substitution principle
    *
    * @param name
    */
  case class Department(val name: String)

  case class ConcretePerson(firstName: String, lastName: String) extends Person

  //this would compile because case classes already add accessor for the params. I.e. the params are 'val'
  class AnotherConcretePerson(val firstName: String, val lastName: String) extends Person

  // Notice the 'val', again this is to accessor methods, hence compiler will automatically add the implementation. if param names were different this wouldn't compile!


}
