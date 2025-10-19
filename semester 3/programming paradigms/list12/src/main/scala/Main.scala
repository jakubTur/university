object Main {
  def main(args: Array[String]): Unit = {
    val t1=new newthread()
    val t2=new newthread()
    val t3=new newthread()
    val t4=new newthread()
    val t5=new newthread()
    t1.start()
    t2.start()
    t3.start()
    t4.start()
    t5.start()

    Thread.sleep(2000)


    val pc = new producerconsumer
    Thread.sleep(10000)


  }
}