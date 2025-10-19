class animal
{
  def pet()=
    {
      println("you pet the animal")
    }

}
class cat extends animal
{
  override def pet(): Unit = println("you pet the cat")
}
class dog extends animal
{
  override def pet(): Unit = println("you pet the dog")
}