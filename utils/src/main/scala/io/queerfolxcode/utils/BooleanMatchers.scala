package io.queerfolxcode.utils

object BooleanMatchers extends App {

    val keywordsIN = Seq("NC", "90DS", "OTC", "QL", "VAC", "LD", "PA", "SF", "MSP", "PDL", "SMKG")
    val keywordsOUT = Seq("BRAND")


    object MatchAnyRunner {
        val matchAny = (str: String,
                        fltr: Seq[String],
                        op: (String, String) => Boolean) => (fltr.foldLeft(false)((acc: Boolean,
                                                                                   nxtFltr: String) => acc || op(str, nxtFltr)))

        val str2 = "i want to be here QL 90DS"

//        val matched: Boolean = matchAny(str2, keywordsIN, (str, nxtFltr) => str.contains(nxtFltr))
        val matched: Boolean = matchAny(str2, keywordsIN, _.contains(_))
        println(matched)

    }
    MatchAnyRunner

    object MatchAllRunner {
        val matchAll = (str: String,
                        fltr: Seq[String],
                        op: (String, String) => Boolean) => (fltr.foldLeft(true)((acc: Boolean,
                                                                                  nxtFltr: String) => acc && op(str, nxtFltr)))

        val str1 = "i want to be here QL 90DS NC 90DS OTC QL VAC LD PA SF MSP PDL SMKG"

//        val matched: Boolean = matchAll(str1, keywordsIN, (str, nxtFltr) => str.contains(nxtFltr))
        val matched: Boolean = matchAll(str1, keywordsIN, _.contains(_))

        println(matched)
    }

    MatchAllRunner
}