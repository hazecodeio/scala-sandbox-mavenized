package io.queerfolxcode.utils

object BooleanMatchers extends App {

    val keywordsIN = Seq("NC", "90DS", "OTC", "QL", "VAC", "LD", "PA", "SF", "MSP", "PDL", "SMKG")
    val keywordsOUT = Seq("BRAND")


    object MatchAnyRunner {

        // Fold() doesn't short-circuit
        /*val matchAny = (str: String,
                        fltr: Seq[String],
                        op: (String, String) => Boolean) => (fltr.foldLeft(false)((acc: Boolean,
                                                                                   nxtFltr: String) => acc || op(str, nxtFltr)))*/

        // find() is simpler and does short-circuiting
        // find() is equivalent to = MatchAny
        /*val matchAny = (str: String,
                        fltr: Seq[String],
                        op: (String, String) => Boolean) => fltr.find(nxt => true && op(str, nxt)).nonEmpty*/
        // exists()
        val matchAny = (str: String,
                        fltr: Seq[String],
                        op: (String, String) => Boolean) => fltr.exists(nxt => true && op(str, nxt))

        val str = "i want to be here 90DS"

//        val matched: Boolean = matchAny(str2, keywordsIN, (str, nxtFltr) => str.contains(nxtFltr))
        val matched: Boolean = matchAny(str, keywordsIN, _.contains(_))
        println(matched)

    }
    MatchAnyRunner

    object MatchAllRunner {

        // Fold() doesn't short-circuit
        /*val matchAll = (str: String,
                        fltr: Seq[String],
                        op: (String, String) => Boolean) => (fltr.foldLeft(true)((acc: Boolean,
                                                                                  nxtFltr: String) => acc && op(str, nxtFltr)))*/
        // forall() is simpler and does short-circuiting
        // forall() is equivalent to = MatchAll
        val matchAll = (str: String,
                        fltr: Seq[String],
                        op: (String, String) => Boolean) => fltr.forall(nxtFltr => op(str, nxtFltr))


        val str = "i want to be here QL NC 90DS OTC QL VAC LD PA SF MSP PDL SMKG"

//        val matched: Boolean = matchAll(str1, keywordsIN, (str, nxtFltr) => str.contains(nxtFltr))
        val matched: Boolean = matchAll(str, keywordsIN, _.contains(_))

        println(matched)
    }

//    MatchAllRunner
}