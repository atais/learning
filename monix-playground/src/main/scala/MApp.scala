import cats.effect.ExitCode
import cats.implicits._
import monix.eval.{Task, TaskApp}

object MApp extends TaskApp {
  override def run(args: List[String]): Task[ExitCode] = {

    val t1: Task[ExitCode] = Task.eval {
      println("Effect"); "Hello!"
    } >> Task(ExitCode.Success)

    val t2: Task[ExitCode] = Task
      .eval {
        println("Effect"); "Hello!"
      }
      .as(ExitCode.Success)

    t1 >> t2
  }

}
