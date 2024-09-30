package io.queerfolxcode.utils

object RunnerOfPasswdUtils extends App {

    val isPasswdPassing = (s: String, charsAccepted: String) => {
        s.view
            .map{c => charsAccepted.contains(c) || c.isLetterOrDigit}
            .filter(_ == false)
            .collectFirst{case _ => false}
    }

    val s = "asd;klgfjhiuo908753wenbdjhk&65tv 64557"
    val f = """"-().&@?'#,/\"+!""""
    println(isPasswdPassing(s, f))
}