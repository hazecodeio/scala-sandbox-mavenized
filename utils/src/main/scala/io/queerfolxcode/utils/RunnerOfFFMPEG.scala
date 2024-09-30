package io.queerfolxcode.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.sys.process._

object RunnerOfFFMPEG extends App {

    case class MediaURLs(prefix: String, urlToV: String, urlToA: String)

    def getCmd(mediaURLs: MediaURLs) = {

        val date = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)

        val filenameToV = new StringBuilder(mediaURLs.prefix)
            .append('-')
            .append(date)
            .append("-v.mp4")
            .toString()
        val wgetProcV = Process("wget", Seq("-O", filenameToV, mediaURLs.urlToV))


        val filenameToA = new StringBuilder(mediaURLs.prefix)
            .append('-')
            .append(date)
            .append("-a.mp4")
            .toString()
        val wgetProcA = Process("wget", Seq("-O", filenameToA, mediaURLs.urlToA))


        val filenameToOut = new StringBuilder(mediaURLs.prefix)
            .append('-')
            .append(date)
            .append("-out.mp4")
            .toString()

        val ffmpegProc = Process("ffmpeg", Seq("-i", filenameToA, "-i", filenameToV, "-c:v", "libx264", "-c:a", "copy", "-map_metadata", "-1", filenameToOut))

        wgetProcA.#&&(wgetProcV).#&&(ffmpegProc).run()
    }

    val prefix = "prefix"

    val urlToV = ""

    val urlToA = ""


    getCmd(MediaURLs(prefix, urlToV, urlToA))

}