package io.queerfolxcode.utils

import scala.util.Random

object RunnerOfPasswdUtils extends App {

    val isPasswdPassing = (s: String, charsAccepted: String) => {
        s.view
            .map { c => charsAccepted.contains(c) || c.isLetterOrDigit }
            .filter(_ == false)
            .collectFirst { case _ => false }
    }

    val modifyPassword = (s: String, charsAccepted: String) => {
        s.view
            .map { c => (c, charsAccepted.contains(c) || c.isLetterOrDigit) }
            .map {
                case (_, false) => charsAccepted.charAt(Random.between(0, charsAccepted.length))
                case (c, _) => c
            }

    }

    val passwd = """qQpj%x&kQ!RSX4tWJXg3ZcuMcfKXu!p8TeX&xTKF"""
    val charsAccepted = """"!"#$%&'(),.:=?@ """"
    println(isPasswdPassing(passwd, charsAccepted))

    println(passwd)
    println(modifyPassword(passwd, charsAccepted).mkString)
}