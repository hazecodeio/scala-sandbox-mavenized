package org.hsmak

/**
  * Created by hsmak on 3/14/17.
  */
object ControlAbstraction {

  def main(args: Array[String]): Unit = {

    filesEnding(".*").map(_.getName).foreach(println)
  }

  private def filesHere = (new java.io.File(".")).listFiles


  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName))
      yield file

  // you can think of the below as anonymous classes or abstract methods implementations

  def filesEnding(query: String) =
    filesMatching(_.endsWith(query)) // this is a closure becaus eof the free variable "query"

  // similarly
  /*def filesEnding(query: String) =
    filesMatching(s => s.endsWith(query))*/

  def filesContaining(query: String) =
    filesMatching(_.contains(query)) // this is a closure because eof the free variable "query"

  def filesRegex(query: String) =
    filesMatching(_.matches(query)) // this is a closure because eof the free variable "query"


}
