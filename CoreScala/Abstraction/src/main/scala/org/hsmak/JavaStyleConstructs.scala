package org.hsmak

object JavaStyleConstructs extends App {

  import scala.beans.BeanProperty

  val e = EmployeeJavaStyle("FirstName", "Lastname")

  /**
    * Notice @BeanProperty: this will generate the Java style getters/setters; beside the scala style
    * run 'javap -p EmployeeJavaStyle'
    *
    * @param firstName
    * @param lastName
    */
  case class EmployeeJavaStyle(@BeanProperty val firstName: String,
                               @BeanProperty var lastName: String)//warning: Scala programmers don't like vars but this is for illustration

  e.setLastName("ModifiedLastName") // JavaStyle Setter
  println(e)
}
