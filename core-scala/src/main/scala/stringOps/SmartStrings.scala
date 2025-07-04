package stringOps

/**
 * @author ${user.name}
 */
object SmartStrings extends App {

    println("--------------- InterpolationWithPrintandStyling ------------------")

    object InterpolationWithPrintAndStyling {
        val name = "Husain"
        val age = 32
        val weight = 155.5

        println(s"$name")
        println(f"I am ${age + 1} and weigh $weight%.2f")

        printf("'%5s'\n", 5)
        printf("'%-5d'\n", 5)
        printf("'%05d'\n", 5)

        //right justification
        printf("'%20s'\n", "name Husain")
        printf("'%20s'\n", "age 30")

        // left justification
        printf("'%-20s'\n", "name Husain")
        printf("'%-20s'\n", "age 30")
    }

    InterpolationWithPrintAndStyling
    println

    println("--------------- MultilineString ------------------")

    object MultilineString {
        println(
            """Hey this is a multiline string
              |this is a 2nd line
      """.stripMargin)

        println(
            """This is another multiline string
              @Margin is not | but @
              @And this is a 3rd line
    """.stripMargin('@'))
    }

    MultilineString
    println

}
