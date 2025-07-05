package io.queerfolxcode.utils

object BooleanMatchers extends App {

    val code = Seq("NC", "90DS", "OTC", "QL", "VAC", "LD", "PA", "SF", "MSP", "PDL", "SMKG")
    val matchAll = (str: String,
                    fltr: Seq[String],
                    op: (String, String) => Boolean) => (fltr.foldLeft(true)((acc: Boolean,
                                                                              nxtFltr: String) => acc && op(str, nxtFltr)))

    val str1 = "i want to be here QL 90DS NC 90DS OTC QL VAC LD PA SF MSP PDL SMKG"
    println(matchAll(str1, code, (str, nxtFltr) => str.contains(nxtFltr)))

    val matchAny = (str: String,
                    fltr: Seq[String],
                    op: (String, String) => Boolean) => (fltr.foldLeft(false)((acc: Boolean,
                                                                               nxtFltr: String) => acc || op(str, nxtFltr)))

    val str2 = "i want to be here QL 90DS"
    println(matchAny(str2, code, (str, nxtFltr) => str.contains(nxtFltr)))
}