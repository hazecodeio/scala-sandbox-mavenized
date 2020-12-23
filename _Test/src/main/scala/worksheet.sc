import scala.util.Random

/*-------------------------------------- Classes and CompanionObjects -------------------*/
object Wrap {

  import scala.beans.BeanProperty

  class Employee(firstname: String, middleName: Option[String], lastName: String) {
    def this(firstname: String, lastName: String) = this(firstname, None, lastName)

    def this(firstname: String, middleName: String, lastName: String) = this(firstname, Option(middleName), lastName)

    def this() = this("unknown", "unknown")
  }

  object Employee {
    def apply(firstname: String, middleName: String, lastName: String) = new Employee(firstname, middleName, lastName)

    def apply(firstname: String, lastName: String) = new Employee(firstname, lastName)

    def apply() = new Employee();
  }

  List(Option(1), None, Option(2)).map(o => o.fold(-1)(_ * 3))


  case class Person(@BeanProperty var firstName: String)

  val p = Person("ss")
  //p.firstName = "dd"
  p.setFirstName("sssfgvd")
  println(p)

  type ENV = String => Int

  val e: String => Int = {
    case "x" => 5
  }

  /*----------------------------------- Functor -------------------------------------------------*/

  trait Functor[F[_]] {
    def map[A, B](as: F[A])(f: A => B): F[B]

    def mapC[A, B](f: A => B): F[A] => F[B] = as => map(as)(f)
  }

  implicit val optionFunctor = new Functor[Option] {
    override def map[A, B](as: Option[A])(f: A => B) = as.map(f)
  }

  implicit def eitherFunctor[L] = new Functor[({type T[R] = Either[L, R]})#T] {
    override def map[A, B](as: Either[L, A])(f: A => B) = as.map(f)
  }

  /*-------------------------------------- FlatMap -------------------------------------------------*/


  (1 to 5).flatMap(i => List(-i, 0, i)).foreach(println)

  val m = Map(1 -> "One", 2 -> "Two", 3 -> "Three", 4 -> "Four")
  m.flatMap(t => Map(t._1 -> t._2,
    t._1 * 100 -> (t._2 + "Hundred")))

  /*----------------------------------- Fold/Reduce -----------------------------*/

  (1 to 10).reduce(_ + _)

  /*----------------------------------- Grouping/Collecting -----------------------------*/

  val (lt, gt) = (1 to 10).partition(_ <= 5)

  val value: Map[Int, IndexedSeq[Int]] = (1 to 10).groupBy(_ % 2)


  /*----------------------------------- Sorting & Ordering[A] -----------------------------*/


  /*----------------------------------- Generating Alphabets with Random -----------------------------*/
  val alphabetsLowercase = ('a' to 'z').toVector
  val alphabetsUppercase = alphabetsLowercase map (_.toUpper)

  def randLowercase = Random.between(alphabetsLowercase.head, alphabetsLowercase.last)

  def randUppercase = Random.between(alphabetsUppercase.head, alphabetsUppercase.last)

  //ToDO

}

/*----------------------------------- Bounded Ranges Via Stream/lazyLis -----------------------------*/

// creating stream ranges
def streamRange(lo: Int, hi: Int): LazyList[Int] =
  if (lo >= hi) LazyList.empty
  else LazyList.cons(lo, streamRange(lo + 1, hi))// Recursive call. Note this is not a tail recursion

streamRange(4, 10).mkString("[", " ** " , "]").foreach(print)
