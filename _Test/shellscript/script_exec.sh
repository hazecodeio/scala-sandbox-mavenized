#!/bin/sh
exec scala "$0" "$@"
!#



/*
0  Expands to the name of the shell or shell script.
   This is set at shell initialization. If bash  is
   invoked  with  a  file  of commands, $0 is set to
   the name of that file.  If bash is started with
   the -c option, then $0 is set to the first argument
   after the string to  be  executed, if one is present.
   Otherwise, it is set to the file name used to invoke
   bash, as given by argument zero.

@  Expands  to  the  positional  parameters, starting from
   one. When the expansion occurs within double quotes, each
   parameter expands to a separate word. That is, "$@" is
   equivalent to "$1", "$2" ... If the double-quoted
   expansion occurs within a word, the expansion of the first
   parameter is joined with the beginning part of the original
   word, and the expansion of the last parameter is joined
   with the last part of the original word. When there are no
   positional parameters, "$@" and $@ expand to nothing
   (i.e., they are removed).
*/

//running scala from a shell script

object HelloWorld{
    def main(args: Array[String]): Unit ={
        println("Hellow, World from sh!")
        args.foreach(println(_))
    }
}