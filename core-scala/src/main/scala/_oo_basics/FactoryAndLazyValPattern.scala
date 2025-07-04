package _oo_basics

/**
 * I'm not sure if there is any special thing of using 'Option[() => String]' <- Option of a Lambda
 * Option[String] worked just fine! <- Option of a String
 * Maybe it has to do with deferred calls?
 *
 * - last I checked yes this has to do with "deferred call/evaluation"
 *
 * Lazy Val can be used in configuring apps
 *
 */
object FactoryAndLazyValPattern {

    /**
     * () at the end of the line is to invoke the function
     */
    lazy val jdbcURL = jdbcURLFactory.getOrElse(() => "jdbc:mysql://localhost:3306")()
    lazy val username = usernameFactory.getOrElse(() => "root")()
    lazy val password = passwordFactory.getOrElse(() => "password")()

    var jdbcURLFactory: Option[() => String] = None
    var usernameFactory: Option[() => String] = None
    var passwordFactory: Option[() => String] = None

    def main(args: Array[String]): Unit = {

        passwordFactory = Some(() => "OverriddenSecret") //change the factory to a different Option of another password

        println(s"$jdbcURL")
        println(s"$username")
        println(s"$password") //password is a lazy val and hasn't been instantiated. Once instantiated it can't be changed.
        println

        /**
         * so changing this factor won't change the password
         * because password has already been instantiated
         */
        passwordFactory = Some(() => "OverriddenSecret_02")
        println(s"$password")
    }
}
