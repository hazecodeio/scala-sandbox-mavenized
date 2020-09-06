package _oo_basics

/**
  * deprecated in 2.13
  */
object Symbols extends App {

  println("-------------------------------- Symbols: they have a pool of their own --------------------------------")

  val co = Symbol("Co")
  val co2 = 'Co

  println(co == co2) //true
  println(co eq co2) //true
}
