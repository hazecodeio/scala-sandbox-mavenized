def repeatedParameterMethod(x: Int, y: String, z: Any*) = {
  "%d %ss can give you %s".format(x, y, z.mkString(", "))
}

repeatedParameterMethod(3, "egg", List("a delicious sandwich", "protein", "high cholesterol"))

object PigLatinizer {
  def apply(x: ⇒ String) = x.tail + x.head + "ay"
}

val result = PigLatinizer {
  val x = "pret"
  val z = "zel"
  x ++ z //concatenate the strings
}

