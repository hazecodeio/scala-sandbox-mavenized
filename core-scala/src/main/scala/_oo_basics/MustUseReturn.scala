package _oo_basics

object MustUseReturn extends App {

    def isPrime(n: Int): Boolean = {

        if (n == 1)
            false

        else if (n == 2)
            true

        else

            // for doesn't return the boolean. it always return Unit!!!
            //for() is the only exception where we have to use "return"
            for (x <- 2 until n) //use 'until' to avoid dividing the number by itself and always return true
                if (n % x == 0)
                    return false

        true //last return statement

    }


    println(isPrime(1))
    println(isPrime(2))
    println(isPrime(3))
    println(isPrime(11))
    println(isPrime(12))
}
