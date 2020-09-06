package pattern_matchers

object PatternMatchOnTraits extends App {

  /**
    * Having defined Symbol as a sealed trait gives us the guarantee that the possible case of symbols is fixed.
    * The compiler can leverage this knowledge to warn us if we write code that does not handle all the cases.
    */
  sealed trait Symbol

  case class Note(name: String, duration: String, octave: Int) extends Symbol

  case class Rest(duration: String) extends Symbol


  val symbol1: Symbol = Note("C", "Quarter", 3)
  val symbol2: Symbol = Rest("Whole")

  /**
    * notice the trait type and how it's cheked against concreate case objects
    *
    * @param symbol
    * @return
    */
  def symbolDuration(symbol: Symbol): String =
    symbol match {
      case Note(name, duration, octave) => duration
      case Rest(duration) => duration
    }

  println(symbolDuration(symbol1))
  println(symbolDuration(symbol2))

  /////////////////////////////////////////////


  /**
    * Warning:(46, 5) match may not be exhaustive.
    * It would fail on the following input: Note(_, _, _)
    * symbol match {
    *
    * @param symbol
    * @return
    */
  def nonExhaustiveDuration(symbol: Symbol): String =
    symbol match {
      case Rest(duration) => duration
    }


}
