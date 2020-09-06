package abstraction.objects

import java.time.LocalDate

object CompanionWithApply extends App {

  /**
    * notice the position of the access modifier "private"
    * we did this on purpose so only the CompanionObject is used to instantiate instances
    *
    * preferably "protected" should be used so it's accessibly by Test tools
    */
  class Employee private(val firstName: String,
                         val lastName: String,
                         val title: String,
                         val hireDate: LocalDate)

  /**
    * a CompanionObject Employee
    */
  object Employee {
    /**
      * factory method
      */
    def create(firstName: String,
               lastName: String,
               title: String) = new Employee(firstName: String, lastName: String, title: String, LocalDate.now())

    /**
      * another factory method in case a custom hireDate needs to be passed in
      */
    def create(firstName: String,
               lastName: String,
               title: String,
               hirDate:LocalDate) = new Employee(firstName: String, lastName: String, title: String, hirDate)

    /**
      * the amgical method apply() so no need to use the keyword "new" in order to create a new instance
      */
    def apply(firstName: String,
               lastName: String,
               title: String) = new Employee(firstName: String, lastName: String, title: String, LocalDate.now())

    def apply(firstName: String,
               lastName: String,
               title: String,
               hirDate:LocalDate) = new Employee(firstName: String, lastName: String, title: String, hirDate)


  }

  println(Employee.create("hhhh", "AKAKAK", "Dev").hireDate)
  println(Employee.create("hhhh", "AKAKAK", "Dev", LocalDate.now().minusDays(9)).hireDate)

//  println(new Employee("hhhh", "AKAKAK", "Dev", LocalDate.now().minusDays(9)).hireDate)//compile error -> constructor is private

  println("---------------------- Testing Apply() factory method ----------------------")
  println(Employee("hhhh", "AKAKAK", "Dev").hireDate)
  println(Employee("hhhh", "AKAKAK", "Dev", LocalDate.now().minusDays(9)).hireDate)

}
