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

