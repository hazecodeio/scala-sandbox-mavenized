package org.hsmak

/**
  * I'm not sure if there is any special thing of using 'Option[() => String]'
  * Option[String] worked just fine!
  * Maybe it has to do with deferred calls?
  *
  * Lazy Val can be used in configuring apps
  *
  */
object FactoryAndLazyValPattern {

  var jdbcURLFactor: Option[() => String] = None
  var usernameFactor: Option[() => String] = None
  var passwordFactory: Option[() => String] = None

  /**
    * () at the end of the line is to invoke the function
    */
  lazy val jdbcURL = jdbcURLFactor.getOrElse(() => "jdbc:mysql://localhost:3306")()
  lazy val username = usernameFactor.getOrElse(() => "root")()
  lazy val password = passwordFactory.getOrElse(() => "password")()

  def main(args: Array[String]): Unit = {

    passwordFactory = Some(() => "OverridenSecret")//change the factory to a different Option of another password

    println(s"$jdbcURL")
    println(s"$username")
    println(s"$password")//password is a lazy val and hasn't been instantiated. Once instantiated it can't be changed.
    println

    /**
      * so changing this factor won't change the password
      * because password has already been instantiated
      */
    passwordFactory = Some(() => "OverridenSecret_02")
    println(s"$password")


  }

}
