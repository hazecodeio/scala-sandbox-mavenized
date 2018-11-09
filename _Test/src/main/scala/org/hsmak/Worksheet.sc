case class Person(first: String, last: String, age: Int = 0, ssn: String = "")
val p1 = Person("Fred", "Jones", 23, "111-22-3333")

val parts = Person.unapply(p1).get // this seems weird, but it's critical to other features of Scala

parts._1
