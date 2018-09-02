#!/usr/bin/env scala

//running scala from a shell script

object HelloWorld extends App{
        println("Hellow, World from sh!")
        args.foreach(println(_))
}
