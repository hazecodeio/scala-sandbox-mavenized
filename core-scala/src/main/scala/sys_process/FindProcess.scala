package sys_process

import scala.sys.process.Process

object FindProcess extends App {

  val findProc = Process("find /home/hsmak -maxdepth 100 -name '*'").run() // Start asynchronously
  Thread.sleep(1000)
  findProc.destroy()
  println
  println(s"blah blah: ${findProc.exitValue()}")


}
