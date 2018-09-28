package org.hsmak

object JavaStyleConstructs extends App {

  import scala.beans.BeanProperty

  /**
    * Notice @BeanProperty: this will generate the Java style getters/setters; beside the scala style
    * run 'javap -p EmployeeJavaStyle'
    *
    * @param firstName
    * @param lastName
    */
  case class EmployeeJavaStyle(@BeanProperty val firstName: String,
                               @BeanProperty var lastName: String)

  val e = EmployeeJavaStyle("FirstName", "Lastname")
  e.setLastName("ModifiedLastName") // JavaStyle Setter
  println(e)
}
