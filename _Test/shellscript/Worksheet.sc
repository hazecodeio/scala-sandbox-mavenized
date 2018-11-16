/**
  * A type parameter A of a generic class can be made covariant by using the annotation +A.
  *
  * For some class List[+A],
  * making A covariant implies that for two types A and B where A is a subtype of B,
  * then List[A] is a subtype of List[B].
  *
  * This allows us to make very useful and intuitive subtyping relationships using generics.
  */

/**
  * This has been mind-boggling for quite some time. I will assume that I haven't got it yet. So, please bear with me.
  * As far as the Covariance & Contravariance go, I've always looked at them as UpperBound & LowerBound, respectively.
  *
  * For the first case, List[+A] was used, Replacing the type with a concrete example:
  *  `List[Animal]// A = Animal`
  *
  *
  */

abstract class Animal {
  def name: String
}
case class Cat(name: String) extends Animal
case class Dog(name: String) extends Animal


object CovarianceTest extends App {
  def printAnimalNames(animals: List[Animal]): Unit = {
    animals.foreach { animal =>
      println(animal.name)
    }
  }

  val cats: List[Cat] = List(Cat("Whiskers"), Cat("Tom"))
  val dogs: List[Dog] = List(Dog("Fido"), Dog("Rex"))

  printAnimalNames(cats)
  // Whiskers
  // Tom

  printAnimalNames(dogs)
  // Fido
  // Rex
}

val animals:List[Animal] = List[Cat]()








abstract class Printer[-A] {
  def print(value: A): Unit
}

class AnimalPrinter extends Printer[Animal] {
  def print(animal: Animal): Unit =
    println("The animal's name is: " + animal.name)
}

class CatPrinter extends Printer[Cat] {
  def print(cat: Cat): Unit =
    println("The cat's name is: " + cat.name)
}





val myCat: Cat = Cat("Boots")

def printMyCat(printer: Printer[Cat]): Unit = {
  printer.print(myCat)
}

val catPrinter: Printer[Cat] = new AnimalPrinter
val animalPrinter: Printer[Animal] = new AnimalPrinter

printMyCat(catPrinter)
printMyCat(animalPrinter)
