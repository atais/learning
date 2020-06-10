import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

object TaskExample extends App {

  val task = Task.eval { println("Effect"); "Hello!" }

  task.runToFuture.foreach(println)
  task.runToFuture.foreach(println)

}
