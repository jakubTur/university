class Nrepository[+T] (val content: List[T]){  //źle

  def add[B](element: B): Unit=
  {
    new Nrepository(content:+element)
  }
  def remove(element: T): Unit=
  {
    content=content.filterNot(_==element)
  }
  def merge(other: Nrepository[T]): Nrepository[T]=
  {
    new Nrepository(content::other.content)
  }
  trait sorting[T]
  {
    this: Nrepository[T] =>
    def sort[B](f: T => B)(implicit order: Ordering[B]): Unit=
    {
      content=content.sortBy(f)
    }
  }
  trait filtering[T]
  {
    this: Nrepository[+T] =>
    def filter(f: T => Boolean): Unit=
    {
      content.filter(f)
    }

  }
}
