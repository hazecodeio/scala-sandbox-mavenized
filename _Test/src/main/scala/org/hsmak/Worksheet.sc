val doubleEvens: PartialFunction[Int, Int] = {
  case x if (x % 2) == 0 ⇒ x * 2
}
val tripleOdds: PartialFunction[Int, Int] = {
  case x if (x % 2) != 0 ⇒ x * 3
}

val addFive = (x: Int) ⇒ (x + 5)
val whatToDo = doubleEvens orElse tripleOdds andThen addFive
whatToDo(3)