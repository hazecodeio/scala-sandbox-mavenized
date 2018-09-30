package org.hsmak

/**
  * Created by hsmak on 3/5/17.
  */
object ClassesConcrete {

  def main(args: Array[String]) {

    var p1 = new Person()
    var p2 = new Person("Husain", 32)
    var s1 = new Student("mo", 15, "UVIC")
    Array(p1, p2, s1).foreach(println)
  }


  /**
    * this the main constructor as well
    * parameters must be of type var otherwise defaulted to val??
    *
    * @param name
    * @param age
    */
  class Person(var name: String, var age: Int) {

    // define a unique identifier for this object
    val id = Person.newIdNum
    /**
      * instance fields. These will be created by the compiler!
      */
    /*var name: String = name
    var age: Int = age*/

    /**
      * overloading the main constructor
      */
    def this() {
      // use Option instead of null
      this("none", 0)
    }

    def this(name: String) {
      this(name, 0)
    }

    //End of constructor overloading

    /*
     * Setters/Getters
     */
    def getName(): String = name

    def setName(name: String): Unit = {
      this.name = name
    }

    def getAge(): Int = age

    def setAge(age: Int): Unit = {
      this.age = age
    }

    override def toString = s"Person($id, $name, $age)"


  }

  class Student(name: String, age: Int, school: String) extends Person {
    def this(name: String, age: Int) {
      this(name, age, "no school")
    }

    def this(name: String) {
      this(name, 0, "no school")
    }

    def this() {
      this("no name", 0, "no school")
    }

    override def toString: String = s"Student($id, $name, $age, $school)"
  }

  /*
   * create an object that holds IDs
   * this is similar to Static fields
   * In Scala, all objects are singleton
   */
  object Person {
    private var idNumber: Int = 0

    private def newIdNum(): Int = {
      idNumber += 1
      idNumber
    }
  }

}


/**
  * Abstract Class
  */
object ClassesAbstract {

  def main(args: Array[String]) {
    var w = new Wolf("Fang")
    println(w.move)
  }

  abstract class Mammal(val name: String) {
    //an abstract field that is not initialized to any value
    var moveSpeed: Double

    //abstract method that has no body
    def move: String
  }

  class Wolf(name: String) extends Mammal(name) {
    override var moveSpeed: Double = 35.0

    override def move = s"This walf '$name' runs at speed '$moveSpeed'"
  }

}


/**
  * Traits
  */
object ClassesWithTraits {

  def main(args: Array[String]) {
    var superman = new SuperHero("Superman")
    println(superman.fly)
    println(superman.hitByBulletProof)
    println(superman.richochet(2500))
  }


  trait Flyable {
    def fly: String
  }

  trait BulletProof {
    //abstract method
    def hitByBulletProof: String

    //traits can have concrete method implementations
    def richochet(startSpeed: Double): String = {
      "the bullet richochets at a speed of %.1f ft/sec".format(startSpeed * 0.75)
    }
  }

  class SuperHero(name: String) extends Flyable with BulletProof {
    override def fly = "%s fly through the air".format(this.name)

    override def hitByBulletProof = "The bullet bounces off of %s".format(this.name)

  }

}

