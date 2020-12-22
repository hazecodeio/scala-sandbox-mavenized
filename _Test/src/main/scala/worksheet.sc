import scala.beans.BeanProperty

class Employee(firstname: String, middleName: Option[String], lastName: String) {
  def this(firstname: String, lastName: String) = this(firstname, None, lastName)
  def this(firstname: String, middleName: String, lastName: String) = this(firstname, Option(middleName), lastName)
  def this() = this("unknown", "unknown")
}

object Employee{
  def apply(firstname: String, middleName: String, lastName: String) = new Employee(firstname, middleName, lastName)
  def apply(firstname: String, lastName: String) = new Employee(firstname, lastName)
  def apply() = new Employee();
}

List(Option(1), None, Option(2)).map(o => o.fold(-1)(_*3))


case class Person(@BeanProperty var firstName:String)
val p = Person("ss")
//p.firstName = "dd"
p.setFirstName("sssfgvd")
println(p)

type ENV = String => Int

val e:String => Int =  {
  case "x" => 5
}