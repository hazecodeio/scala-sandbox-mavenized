package sys_process

import scala.concurrent.{CancellationException, ExecutionContext, Future, Promise}

object WithCancellableFuture extends App {

    import ExecutionContext.Implicits.global

    type Cancellable[T] = (Promise[Unit], Future[T])

    def cancellable[T](b: Future[Unit] => T): Cancellable[T] = {
        val cancel = Promise[Unit]
        val f = Future {
            val r = b(cancel.future)
            if (!cancel.tryFailure(new Exception))
                throw new CancellationException
            r
        }
        (cancel, f)
    }

    val (cancel, value) = cancellable { cancel =>
        var i = 0
        while (i < 5) { // analogous to a busy loop?
            if (cancel.isCompleted) throw new CancellationException

            /**
             * ToDo - enhance cancellation with wait() so
             *  cancellation is immediate and
             *  not blocked by Thread.sleep()*/
            Thread.sleep(1000)
            println(s"$i: working") // ToDo - replace with lamda function that can be passed by a method param
            i += 1
        }
        "resulting value"
    }

    Thread.sleep(1500)
    cancel trySuccess()
    println("computation cancelled!")
    Thread.sleep(2000)
}
