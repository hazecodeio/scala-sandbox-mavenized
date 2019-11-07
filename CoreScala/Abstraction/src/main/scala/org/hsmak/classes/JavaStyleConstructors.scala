package org.hsmak.classes

object JavaStyleConstructors extends App {

  import scala.beans.BeanProperty


  /**
    * Notice @BeanProperty: this will generate the Java style getters/setters; beside the scala style
    * run 'javap -p EmployeeJavaStyle'
    *
    * @param firstName
    * @param lastName
    */
  case class EmployeeJavaStyle(@BeanProperty val firstName: String,
                               @BeanProperty var lastName: String)//warning: Scala programmers don't like vars but this is for illustration

  val e = EmployeeJavaStyle("FirstName", "LastName")

  e.setLastName("ModifiedLastName") // JavaStyle Setter
  println(e)
  println(e.firstName)
  println(e.getFirstName)
}
