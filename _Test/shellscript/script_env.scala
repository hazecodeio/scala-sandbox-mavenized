#!/usr/bin/env scala3

//running scala from a shell script

object HelloWorld extends App{
        println("Hello, World from sh!")
        args.foreach(println(_))
}
