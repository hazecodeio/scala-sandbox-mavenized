package io.queerfolxcode.utils.scalajHttp

import java.net.URL

//import org.springframework.core.io.ClassPathResource
import scalaj.http._

import scala.xml.{PrettyPrinter, XML}

/**
  * Created by hsmak on 3/28/17.
  */
object HttpClientAsXML extends App {

  val hostname = "localhost"
  val port = 8983
  val path = "/solr/admin/collections"

  val url = new URL("http", hostname, port, path)

  val request = Http(url.toString)
  val responseOne = request.params("action" -> "LIST", "wt" -> "xml").asString
  //  println(responseOne.body)

  val xml = XML.loadString(responseOne.body)

  val formatted = new PrettyPrinter(80, 2).format(xml)
  println(formatted)

  println((xml \\ "response" \ "arr" \ "str").text)
}

object HttpClientAsJSON extends App {

  val hostname = "localhost"
  val port = 8983
  val path = "/solr/admin/collections"

  val url = new URL("http", hostname, port, path)

  val request = Http(url.toString)
  val responseOne = request.params("action" -> "LIST", "wt" -> "json").asString

//  val json = Json.parse(responseOne.body)
//  val formatted = Json.prettyPrint(json)

//  println(formatted)

//  println((json \ "collections").get)

}

object CSVUpload extends App {

  /*val cpr = new ClassPathResource("./test.csv");
  val inputStream = cpr.getInputStream();

  val ar = Stream.continually(inputStream.read).takeWhile(_ != -1).map(_.toByte).toArray
  println(ar.length)
  val mp = MultiPart("test.csv", "test.csv", "application/csv", ar)


  val hostname = "localhost"
  val port = 8983
  val collections_path = "/solr/gettingstarted/update"
  val url = new URL("http", hostname, port, collections_path)


  val collections_url = new URL("http", hostname, port, collections_path)

  //  val request = APIs.getUpdateURL
  val request = Http(url.toString)
  val responseOne = request.params("commit" -> "true", "wt" -> "json", "literal.type" -> "FAQ").postMulti(mp).asString

  val json = Json.parse(responseOne.body)
  val formatted = Json.prettyPrint(json)

  println(formatted)*/

}

object CollectionReload extends App {

  val hostname = "localhost"
  val port = 8983
  val collections_path = "/solr/admin/collections"

  val collections_url = new URL("http", hostname, port, collections_path)

  val request = Http(collections_url.toString)
  val responseOne = request.params("action" -> "RELOAD", "name" -> "gettingstarted", "wt" -> "json").asString

//  val json = Json.parse(responseOne.body)
//  val formatted = Json.prettyPrint(json)
//
//  println(formatted)

}

object Delete extends App {

  val hostname = "localhost"
  val port = 8983
  val path = "/solr/gettingstarted/update"

  val url = new URL("http", hostname, port, path)

  val request = Http(url.toString)
  val responseOne = request.header("Content-type", "text/xml").params("wt" -> "json").postData("<delete><query>*:*</query></delete>").asString

//  val json = Json.parse(responseOne.body)
//  val formatted = Json.prettyPrint(json)
//
//  println(formatted)

}