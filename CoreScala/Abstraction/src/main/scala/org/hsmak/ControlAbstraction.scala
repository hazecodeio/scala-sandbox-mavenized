package org.hsmak

/**
  * In Scala, it's called ControlAbstraction:
  *     - passing a function as a param; aka method reference
  *       - it has to do with Higher order functions: functions that take other functions as param and return a function
  *     - Strategy design pattern
  *     - In Java, this is called BehaviourParameterization
  *
  * I would call it: OperationAbstraction compared to DataAbstraction(e.g. inheritance)
  */
object ControlAbstraction {

  def main(args: Array[String]): Unit = {
    println("---------------- printing all files ----------------")
    filesHere.foreach(println)
    println

    println("---------------- Filtering ----------------")
    filesEnding(".md").map(_.getName).foreach(println)
  }

  private def filesHere = (new java.io.File("./")).listFiles

  /**
    * ControlAbstraction:
    *     - passing a function
    *     - Strategy design pattern
    *     - In Java, this is called BehaviourParameterization
    *
    * @param matcher: This is the ControlAbstraction.
    * @return
    */
  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName))
      yield file


  // you can think of the below as anonymous classes or abstract methods implementations

  /**
    * 'filesEnding' is one behavioral concrete implementation for the ControlAbstraction in 'filesMatching'
    *
    * @param query
    * @return
    */
  def filesEnding(query: String) =
    filesMatching(_.endsWith(query)) // this is a closure becaus eof the free variable "query"

  /**
    * 'filesContaining' is one behavioral concrete implementation for the ControlAbstraction in 'filesMatching'
    *
    * @param query
    * @return
    */
  def filesContaining(query: String) =
    filesMatching(_.contains(query)) // this is a closure because eof the free variable "query"


  /**
    * 'filesRegex' is one behavioral concrete implementation for the ControlAbstraction in 'filesMatching'
    *
    * @param query
    * @return
    */
  def filesRegex(query: String) =
    filesMatching(_.matches(query)) // this is a closure because eof the free variable "query"

  // similarly
  /*def filesEnding(query: String) =
    filesMatching(s => s.endsWith(query))*/




}
