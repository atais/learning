import monix.eval.Task
import monix.execution.Ack
import monix.execution.Scheduler.Implicits.global
import monix.reactive._

import scala.concurrent.Future
import scala.concurrent.duration._

object ObservableExample extends App {

  method1()
  method2()

  private def method1(): Unit = {
    val o = Observable.fromTask {
      Task.eval {
        println("Effect");
        "Hello!"
      }
    }

    val t: Task[String] = o.consumeWith(Consumer.foldLeft("")(_ + _))
    val ef = t.runSyncUnsafe()
    println(ef)
  }

  private def method2(): Unit = {
    val source = Observable
      .interval(1.second)
      .flatMap(x => Observable(x, x))
      .take(10)

    source.subscribe(_ => Future(Ack.Continue))
    source.subscribe(_ => Future(Ack.Continue))
    source.subscribe(_ => Future(Ack.Continue))
    source.subscribe(_ => Future(Ack.Continue))

    val t = source.consumeWith(
      Consumer.fromObserver(
        s =>
          new Observer[Long] {
            override def onNext(elem: Long): Future[Ack] = Future {
              println(elem)
              Ack.Continue
            }

            override def onError(ex: Throwable): Unit = println("error")

            override def onComplete(): Unit = println("done")
        }
      )
    )

    t.runSyncUnsafe()
  }
}
