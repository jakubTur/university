import akka.actor.{Actor, Timers}

case object Produce
case object Consume
class producerConsumer extends Actor with Timers {
  var products: List[Int]=Nil //empty list of products
  var next = 1
  val max=20
  def receive: Receive = {
    case Produce =>
      println(next+" produced")
      products=products:+next
      next+=1  //if they get a message to produce it happens (pretty much the same as last list)
      if(next>=16)
      {
        println("we produced enough, shop's closed")
        context.stop(self)
      }
    case Consume=> //if to consume they consume
      if (products.nonEmpty) {
        println(products.head + " consumed by consumer")
        products = products.tail
      }
      else
        {
          context.stop(self)
        }
  }
}