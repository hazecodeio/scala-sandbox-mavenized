package org.hsmak

object TypeSystem extends App {

  ///////////////////////////////////////////
  sealed trait Contents

  case class Water(purity: Int) extends Contents

  case class Whiskey(label: String) extends Contents

  sealed trait Container[C] {
    def contents: C
  }

  case class Glass[C](contents: C) extends Container[C]

  case class Jar[C](contents: C) extends Container[C]

  sealed trait Filler[C <: Contents, CC <: Container[C]] {
    def fill(c: C): CC
  }

  type WaterFiller[CC <: Container[Water]] = Filler[Water, CC]

  def fillWithWater1[CC <: Container[Water]](container: CC)(filler: WaterFiller[CC]) = ???

  //def fillWithWater2[CC <: Container[Water], F: WaterFiller[CC]](container: CC)(filler: F) = ???

  def fillWithWater3[CC <: Container[Water]](container: CC)(filler: ({ type T[C] = Filler[Water, C] })#T[CC]) = ???


  ////////////////////////////////////////

  case class Lock() {
    final case class Key()
    def open(key: Key): Lock = this
    def close(key: Key): Lock = this
    def openWithMaster(key: Lock#Key): Lock = this
    def makeKey: Key = new Key
    def makeMasterKey: Lock#Key = new Key
  }

  object PhantomTypes {

    sealed trait LockState

    sealed trait Open extends LockState

    sealed trait Closed extends LockState

    sealed trait Broken extends LockState

    case class Lock[State <: LockState]() {
      def open[_ >: State <: Closed]: Lock[Open] = Lock()

      def close[_ >: State <: Open]: Lock[Closed] = Lock()

      def break: Lock[Broken] = Lock()
    }

    val openLock = Lock[Open]

    val closedLock = openLock.close
    val broken = closedLock.break

    // closedLock.close() // compile error
    // openLock.open() // compile error
    // broken.open() // compile error
  }

  PhantomTypes

  object RecursiveTypes {

    sealed trait Secret[E]

    sealed trait Lock[E <: Lock[E]] { self: E  =>
      def open(key: Secret[E]): E = self
    }

    // case class IntLock() extends Lock[Int] // compile error

    case class PadLock() extends Lock[PadLock]
    // case class CombinationLock() extends Lock[PadLock] // compile error

    case class CombinationLock() extends Lock[CombinationLock]


    val unlocked: PadLock = PadLock().open(new Secret[PadLock]{})
    CombinationLock().open(new Secret[CombinationLock]{})

  }
  RecursiveTypes


  object SelfTyping {

    val realBeyoncé = new VerifiedTweeter("Beyoncé")

    trait User {
      def username: String
    }

    trait Tweeter {
      this: User => // reassign this
      def tweet(tweetText: String) = println(s"$username: $tweetText")
    }

    class VerifiedTweeter(val username_ : String) extends Tweeter with User {  // We mixin User because Tweeter required it
      def username = s"real $username_"
    }

    realBeyoncé.tweet("Just spilled my glass of lemonade")

  }

  SelfTyping
}




trait ParentA { def name: String }
trait ParentB
class ChildA(val name: String) extends ParentA with ParentB

object ParentB {
  implicit def a2Char(a: ParentA): Char = a.name.head

}
object ParentA {
  implicit def a2Int(a: ParentA): Int = a.hashCode()
  implicit val ordering = new Ordering[ChildA] {
    override def compare(a: ChildA, b: ChildA): Int =
      implicitly[Ordering[String]].compare(a.name, b.name)
  }
}
object ChildA {
  implicit def a2String(a: ParentA): String = a.name
}

trait Test {
  def test(a: ChildA) = {
//    val _: Int = a // companion object of ParentA
    // val _: String = a // companion object of ChildA
    // val _: Char = a // companion object of ParentB
  }
  def constructor[T: Ordering](in: T*): List[T] = in.toList.sorted // companion object of type constructor
  constructor(new ChildA("A"), new ChildA("B")).sorted // companion object of type parameters
}