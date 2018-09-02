package org.hsmak

/**
  * Created by hsmak on 2/16/17.
  */

object HelloWorld01 {
  def main(args: Array[String]): Unit = {
    println("Hello, World 01!!")
    args.foreach(println(_))
  }
}

object HelloWorld02 extends App{
    println("Hello, World 02!!")
}