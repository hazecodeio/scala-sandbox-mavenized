import scala.annotation.tailrec

def fibonacci(n: Long): Long = {

  import scala.annotation.tailrec
  @tailrec
  def fib(n: Long, count: Long, prev: Long, curr: Long): Long = {
    n match {
      case 0 => 0
      case 1 => 1
      case w if w == count => curr
      case _ => fib(n, count + 1, curr, prev + curr)
    }
  }

  fib(n, 1, 0, 1)
}

println("Fibonacci of 10th: " + fibonacci(10))


def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = {
  as match{
    case Nil => z
    case x :: xs => f(x, foldRight(xs, z)(f))//non-tail recursive
  }
}

foldRight(1::2::3::Nil, 0)(_ + _)

@tailrec
def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = {
  as match {
    case Nil => z
    case x :: xs => foldLeft(xs, f(z, x))(f)//tail recursive
  }
}

foldLeft(1::2::3::Nil, 0)(_ + _)
