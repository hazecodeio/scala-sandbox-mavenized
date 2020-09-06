package abstraction.classes


object CaseClassDepartment extends App {

  /**
    * case classes will automatically add: toString(), equals(), hashCode(), copy(), apply(), unapply() so no need to use 'new' keyword, CompanionObject?
    * 'val' is the default to constructor params
    * if you don't like what case provides you can always override
    * used in pattern matching
    * it makes the following possible (Pattern Extractors): val Department(n) = dept// param already assigned in dept will be extracted and assigned to 'n'
    * and so on <- the use of 'unapply()'
    *
    * However, case-to-case class inheritance isn't possible!
    *   - Why? maybe because the copy() will always return an instance of the superclass; which violate Liskov Substitution principle??
    *
    * @param name
    */
  case class Department(val name: String)

  println("################################### Case Class Department ###################################")


  val d1 = Department("D1")
  val d2 = Department("D2")


  val d1_another = Department("D1")

  println(d1 == d2)
  println(d1 == d1_another)

  val name = d1 match {
    case Department("D2") => "This is D2"
    case Department(n) => n
    case _ => "Unknown"
  }

  println(name)

  println("################### Extractors ###############")

  val Department(dd1) = d1
  println(dd1)

}
