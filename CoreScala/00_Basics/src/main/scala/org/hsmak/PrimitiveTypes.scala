package org.hsmak

/**
  * general formula 2^(n-1)   <----> (2^(n-1)) -1
  * where n is the # of bits
  */
object PrimitiveTypes extends App {

  println("-------------- Byte is 8 bits --------------")

  //Byte is 8 bits
  val bMax:Byte = 127 //max value. 128 will give compile error. -128 <-> 127 ----------->-2^7 <-> 2^7 - 1
  val bMin:Byte = -128// compile error. Type mismatch
  println(s"Byte (bMax, bMin): ($bMax, $bMin)")
  println

  println("-------------- Short is 16 bits --------------")

  val sMax:Short = 32767
  val sMin:Short = -32768

  println(s"Short (sMax, sMin): ($sMax, $sMin)")
  println

  println("-------------- Int is 32 bits --------------")

  val iMax:Int = 2147483647
  val iMin:Int = -2147483648

  println(s"Integer (iMax, iMin): ($iMax, $iMin)")
  println

  println("-------------- Long is 64 bits --------------")

  val lNum:Long = 123
  val lNum2 = 123L
  val lNum3 = 123l
  println

  println("-------------- Float is 32 bits --------------")

  val fNum:Float = 123.2F // Notice here you still have to append the 'F' otherwise it will be inferred as a Double and result in compile error "mismatch"
  val fNum2 = 123.2F
  val fNum3 = 123.2f
  println

  println("-------------- Double is 64 bits --------------")

  val dNum:Double = 123.2
  val dNum2 = 123.2 // default is Double
  val dNum3 = 123.2D
  val dNum4 = 123.2d
  println

  println("-------------- Char is 16 bits (unsigned) --------------")

  val cChar:Char = 'A'
  val cChar2 = 'A'
  val cChar3 = '\u03B8'
  println

  println("-------------- Boolean is ?? bits --------------")

  val bBool = true
  val bBool2:Boolean = true
  println
}
