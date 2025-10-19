import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
class producerconsumer extends Thread{
  var queue:List[Int]=Nil
  var next=1
  val producer=Future{
    while (true)
    {
      synchronized
      {
        println(next+" produced")
        queue=queue:+next
        next+=1
      }
      Thread.sleep(500)
    }
  }
  val consumer=Future{
    while (true)
    {
      synchronized
      {
        if(queue.nonEmpty)
          {
            println(queue.head+ " consumed by consumer 1")
            queue=queue.tail
          }
      }
      Thread.sleep(700)
    }
  }

  val secondconsumer=Future{
    while (true)
    {
      synchronized
      {
        if(queue.nonEmpty)
          {
            println(queue.head+" consumed by consumer 2")
            queue=queue.tail
          }
      }
      Thread.sleep(900)
    }
  }
}
