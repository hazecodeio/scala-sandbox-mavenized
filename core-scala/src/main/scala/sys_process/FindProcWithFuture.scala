package sys_process

import java.util.concurrent.TimeUnit

object FindProcWithFuture extends App{

  import scala.concurrent._
  import ExecutionContext.Implicits.global
  import scala.sys.process._


  val findProc = Process("find", Seq("/home/hsmak", "-maxdepth", "10", "-name", "*")).run() // Start asynchronously
  val findFut = Future{blocking(findProc.exitValue)} // Wrap in Future since exitValue() locks until this process exits and returns the exit code.

  val findFutureRes = try {
    Await.result(findFut, duration.Duration(3, TimeUnit.SECONDS))
  } catch {
    case _: TimeoutException =>
      println("TIMEOUT!")
      findProc.destroy()
      println("DESTROYED!")
      println("Returning ExitValue")
      findProc.exitValue()
  }

  println(s"result: $findFutureRes")
}
