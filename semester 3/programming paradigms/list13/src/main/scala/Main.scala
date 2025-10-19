import akka.actor.{ActorSystem, Props}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
case class Task(id: Int, var failed:Boolean=false)

case object Done
case object Busy
case object Idle
case object checkStatus

object Main {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem()
    val consumer = system.actorOf(Props(new producerConsumer))
    system.scheduler.scheduleAtFixedRate(0.milliseconds, 500.milliseconds) {
      () =>consumer ! Produce

    }
    Thread.sleep(700)
    system.scheduler.scheduleAtFixedRate(0.milliseconds, 700.milliseconds) {
      () => consumer ! Consume
    }



    Thread.sleep(6000)


    val scheduler = system.actorOf(Props(new taskScheduler))
    for (i <- 1 to 10) {
      scheduler ! Task(i)
      Thread.sleep(500)
    }
    Thread.sleep(8000)
    scheduler ! Task(99)
  }
}