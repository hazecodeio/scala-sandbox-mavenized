package org.hsmak.controller

import org.hsmak.model.TempThreshEnum
import org.hsmak.util.Utils

import scala.language.postfixOps
import scala.sys.process._

/**
  * Created by hsmak on 2/25/17.
  */

object FanCtl_i8kfan extends App {

  println("FanCtrol has started monitoring your CPU temperature...")

  val debug = args.filter(_.startsWith("-d="))
    .headOption.map(_.substring(3))
    .filter(_.toLowerCase.startsWith("t"))
    .map(_ => true)
    .getOrElse(false)

  val MAX = 39
  val threshholdGap = 2

  val util = new Utils
  var prevThreshold = TempThreshEnum.HIGH
  var thresholdChange: Boolean = true

  var currentThreshold = TempThreshEnum.HIGH // move this line outside the loop

  while (true) {
    val coresTemp = Process("sensors") #| Process("grep Core") // call system commands
    val streamOfLines =
      coresTemp.lineStream
        .collect(
          {
            case line: String => line
          }
        )

    streamOfLines
      .map(line => line.split(" "))
      .flatMap(_.toStream)
      .filter(_.startsWith("+"))
      .filter(_.endsWith("°C"))
      .map(util.stripOffTheCrap(_))
    //      .foreach(println(_))

    val cpuTemp = streamOfLines
      .map(line => line.split(" ")) //split each line on the space, so it will return another StreamOfString[]; precisely Stream<String[]>
      .flatMap(_.toStream) // flatten Stream<String[]> into Stream<Stream<String>>, and then into a combined Stream<String>
      .filter(_.startsWith("+")) // filters on each line and each word per line
      .filter(_.endsWith("°C"))
      .map(util.stripOffTheCrap(_)) // could the use of head/headoption work before calling map?
      .reduce(_ max _)

    if (debug) {
      println
      println("cpuTemp " + cpuTemp)
    }

    // move this logic to a method
    if (cpuTemp >= MAX)
      currentThreshold = TempThreshEnum.HIGH
    else if (cpuTemp < MAX)
      currentThreshold = TempThreshEnum.LOW


    if (currentThreshold == prevThreshold)
      thresholdChange = false
    else
      thresholdChange = true

    if (debug) {
      println(currentThreshold)
      println(thresholdChange)
    }
    prevThreshold = currentThreshold;

    //change this if/else into a strategy design pattern
    if (cpuTemp >= (MAX) && thresholdChange) {
      val lineStream = Process("i8kfan 2 2").lineStream
      if (debug)
        lineStream.foreach(println(_))
    }
    else if (cpuTemp < (MAX) && thresholdChange) {
      val lineStream = Process("i8kfan 1 1").lineStream
      if (debug)
        lineStream.foreach(println(_))
    }

    Thread.sleep(1000)
  }
}