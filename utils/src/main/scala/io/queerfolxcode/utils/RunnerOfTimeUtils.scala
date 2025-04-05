package io.queerfolxcode.utils

import java.time.{Instant, LocalDateTime, ZoneOffset}
import scala.io.StdIn
import scala.util.control.Breaks.break
import scala.util.{Failure, Success, Try}

object RunnerOfTimeUtils extends App {

    //    println(parseDateTimeToEpoch("2021-11-04T11:15:00"))
    //    println(parseEpochToDateTime("1635483600000"))


    val parseEpochToDateTime = (s: String) => {
        val i = Instant.ofEpochMilli(s.toLong)
        LocalDateTime.ofInstant(i, ZoneOffset.ofHours(-6))
    }

    val parseDateTimeToEpoch = (s: String) => {
        LocalDateTime.parse(s).toInstant(ZoneOffset.ofHours(-6)).toEpochMilli
    }

    while (true) {
        print(
            """Choose from the menu:
              |1. parseDateTimeToEpoch
              |2. parseEpochToDateTime
              |q. Exit
              |Select Option: """.stripMargin)
        val n = StdIn.readLine()

        val triedInt = Try {
            n.toInt
        }
        val r = triedInt match {
            case Success(n) => n match {
                case 1 => {
                    print(s"Enter in this format (${LocalDateTime.now()}):")
                    parseDateTimeToEpoch(StdIn.readLine())
                }
                case 2 => {
                    print(s"Enter in this format (${Instant.now().toEpochMilli}):")
                    parseEpochToDateTime(StdIn.readLine())
                }
                case _ => -1
            }
            case Failure(_) => break()
        }


        println()
        println(s"Outcome ------------------------> $r")
        println()
    }
}
