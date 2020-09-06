package _oo_basics

/**
  * general formula 2^(n-1)   <----> (2^(n-1)) -1
  * where n is the # of bits
  */
//TODO: Consolidate this file for Any, AnyVal, & AnyRef material
object TypesOfPrimitives extends App {

  println("-------------- Byte is 8 bits --------------")

  object Byte {
    //Byte is 8 bits
    val bMax: Byte = 127 //max value. 128 will give compile error. -128 <-> 127 -----------> -2^7 <-> 2^7 - 1
    val bMin: Byte = -128 // compile error. Type mismatch
    println(s"Byte (bMax, bMin): ($bMax, $bMin)")
  }

  Byte
  println

  println("-------------- Short is 16 bits --------------")

  object Short {
    //Link: https://stackoverflow.com/questions/32277626/operators-on-scala-short-return-int
    val sMax: Short = (-(Math.pow(2,15).toShort + 1)).asInstanceOf[Short]
    val sMin: Short =  Math.pow(2,15).toShort

    println(s"Short (sMax, sMin): ($sMax, $sMin)")
  }

  Short
  println

  println("-------------- Int is 32 bits --------------")

  object Int {
    val iMin: Int = -2147483648 //-(Math.pow(2,31).toInt + 1)
    val iMax: Int = 2147483647 // Math.pow(2,31).toInt

    println(s"Integer (iMax, iMin): ($iMax, $iMin)")

  }

  Int
  println

  println("-------------- Long is 64 bits --------------")

  object Long {
    val lNum: Long = 123
    val lNum2 = 123L
    val lNum3 = 123l

    val lMax:Long = Math.pow(2, 63).toLong
    val lMin:Long = -(Math.pow(2,63).toLong + 1)
    println(s"Long (lMax, lMin): ($lMax, $lMin)")

  }

  Long
  println

  println("-------------- Float is 32 bits --------------")

  object Float {
    val fNum: Float = 123.2F // Notice here you still have to append the 'F' otherwise it will be inferred as a Double and result in compile error "mismatch"
    val fNum2 = 123.2F
    val fNum3 = 123.2f
    val fExpNum = 12e-5 // Floats can also be expressed with exponents with a small e or a capital E.

  }

  Float
  println

  println("-------------- Double is 64 bits --------------")

  object Double {
    val dNum: Double = 123.2
    val dNum2 = 123.2 // default is Double
    val dNum3 = 123.2D
    val dNum4 = 123.2d

  }

  Double
  println

  println("-------------- Char is 16 bits (unsigned) --------------")

  object Char {
    val cChar: Char = 'A'
    val cChar2 = 'A'
    val cChar3 = '\u03B8'

  }

  Char
  println

  println("-------------- Boolean is ?? bits --------------")

  object Boolean {
    val bBool = true
    val bBool2: Boolean = true

    /**
      * you can compare booleans!?!?
      *
      * @see [[java.lang.Boolean.compare]] which return 0, 1, -1
      * @see [[java.lang.Boolean.TRUE.compareTo] which return 0, 1, -1
      *
      */

    println("(true > false): " + (true > false))
    println("(true < false): " + (true < false))
    println("(false > true): " + (false > true))
    println("(false < true): " + (false < true))
    println

    println("(true >= false): " + (true >= false))
    println("(true <= false): " + (true <= false))
    println("(false >= true): " + (false >= true))
    println("(false <= true): " + (false <= true))
    println

    println("(true == true): " + (true == true))
    println("(false == false): " + (false == false))

  }

  Boolean
  println
}
