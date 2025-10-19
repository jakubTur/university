import akka.actor.{Actor, Timers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt
import scala.util.Random
case object Fail
case object Back
class worker(val waitingTime: Int) extends Actor with Timers
{
  val random = new Random()
  var occupied=false
  var temp: Task=Task(0)
  def receive: Receive=
  {
    case Back => sender() ! temp  //returns the temporarily saved failed task

    case task: Task=>
      if (!occupied) {
        val og = sender()
        occupied = true
        context.system.scheduler.scheduleOnce(waitingTime.millis) {
          val fail=random.nextBoolean()  //simulates task failure
          if(fail)
          {
            og ! Fail //sends fail to the scheduler (ideally)
            temp=task //and overrwrites failed task
          }
          else
            {
              og ! Done //sends done to scheduler
            }
          occupied = false
        }
      }
      else {
        sender() ! Busy
      }
    case checkStatus => //returns the status based on ccupied variable
      if(occupied)
        {
          sender() ! Busy
        }
      else
        {
          sender() ! Idle
        }
  }
}