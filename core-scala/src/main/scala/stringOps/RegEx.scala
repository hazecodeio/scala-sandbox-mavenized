package stringOps

import scala.util.matching.Regex

/**
 * @author ${user.name}
 */
object RegEx extends App {

    println("################################### RegEx ###################################")

    object RegEx {
        //
        val message = "we are meetin on June 13th of this year, and having lunch at 12:30PM"
        val regex = """(\s|[0-9])?[0-9]:[0-5][0-9]\s*(AM|PM)""".r
        private val matches: List[Regex.Match] = regex.findAllMatchIn(message).toList
        println("Found RegEx: " + matches)

    }

    RegEx
    println

}
