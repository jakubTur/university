class newthread extends Thread
{
  override def run(): Unit = {
    for(x<-1 to 10)
    {
      println(Thread.currentThread().getName()+" "+Thread.currentThread().threadId())
      Thread.sleep(100)
    }
  }
}