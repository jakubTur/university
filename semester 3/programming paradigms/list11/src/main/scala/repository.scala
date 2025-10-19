class repository[T] {
  var content: List[T]=Nil
  def add(element: T): Unit=
    {
      content=content:+element //adds elements to the content list
    }
  def remove(element: T): Unit=
    {
      content=content.filterNot(_==element)  //removes elements via filtering
    }
}
trait sorting[T]
{
  this: repository[T] => //function exists just for the repository class
  def sort[B](f: T => B)(implicit order: Ordering[B]): Unit= //takes a function by which to sort the content and does it
  {
    content=content.sortBy(f)
  }
}
trait filtering[T]
{
  this: repository[T] =>
  def filter(f: T => Boolean): Unit= //takes a boolean function by which to filter the list and overwrites it
  {
    content=content.filter(f)
  }
}
