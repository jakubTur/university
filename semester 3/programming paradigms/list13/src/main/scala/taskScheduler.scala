import akka.actor.{Actor, ActorRef, Props, Timers}
import akka.util.Timeout
import akka.pattern.ask
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

import scala.collection.immutable.Queue
import scala.util.Success

class taskScheduler extends Actor with Timers
{
  implicit val timeout: Timeout = Timeout(99999.seconds)
  var taskQueue: Queue[Task] = Queue.empty
  val workers: List[ActorRef] = List(
    context.actorOf(Props(new worker(10000)), "worker1"),  //list of 3 workers
    context.actorOf(Props(new worker(15000)), "worker2"),
    context.actorOf(Props(new worker(20000)), "worker3")
  )
  def giveTasks(): Unit=
  {
    if (taskQueue.nonEmpty)
    {
      workers.foreach { worker =>
        (worker ? checkStatus).map {       //checking the status of workers
          case Idle=>  //if they're idle they are assigned a task
            val (task, newQueue) = taskQueue.dequeue
            taskQueue = newQueue //task queue gets updated as well
            println(task.id+" in progress")  //tasks starts being processed
            (worker ? task).onComplete {
              case Success(Done) =>
                self ! Done  //if the worker will go through the task without fail it gets done
                println(task.id+" is done")
              case Success(Fail) =>
                println(task.id+" failed, requeing")
                self ! (worker ! Back)  //if it fails the scheduer requests it back
            }
        }
      }
    }

  }
  def receive: Receive =
  {
    case task: Task =>
      taskQueue = taskQueue.enqueue(task)
      println(task.id+" is waiting")
      giveTasks()
    case Done if taskQueue.nonEmpty =>
      giveTasks()
    case Done if taskQueue.isEmpty =>
      context.stop(self)
  }
}