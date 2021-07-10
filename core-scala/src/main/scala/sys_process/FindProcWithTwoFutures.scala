package sys_process

import scala.util.{Failure, Success, Try}

object FindProcWithTwoFutures extends App{

  import scala.concurrent._
  import ExecutionContext.Implicits.global
  import scala.sys.process._


  val findProc = Process("find /home/hsmak -maxdepth 100 -name '*'").run() // Start asynchronously
  val findFut = Future{
    try {
      findProc.exitValue
    } catch{
      case _ => println("fffffffffffffffffffffffffffffffffffff: " + findProc.exitValue)
    }
  } // Wrap in Future

  val cancelFuture = Future{{
    try {
      Thread.sleep(1000)
      println("aaaaaaaaaaaaaaaaaaaaaaaaaaa")
      findProc.destroy()
      println("bbbbbbbbbbbbbbbbbbbbbbb")
      findProc.exitValue()
    } catch {
      case _ => println("eeeeeeeeeeeeeeeeeeeeeeeeeee"); findProc.exitValue()
    }
  }}


//  Try {
    cancelFuture.onComplete{case x => println(s"rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr: $x")}
//  } match {
//    case x => println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa: "+x)
//  }

  findFut.onComplete{
    case x => println(x)
  }
}
