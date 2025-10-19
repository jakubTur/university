import scala.annotation.tailrec
import scala.math.pow

object books {
  sealed trait Book
  case class Data(title: String, releaseYear: String, author: String, index: Integer) extends Book
  case object Corrupted extends Book
  def createData(s1: String): Book= { Corrupted }
  def createData(index: Integer): Book= { Corrupted }
  def createData(s1: String, index: Integer): Book= { Corrupted }
  def createData(s1: String, s2: String, index: Integer): Book= { Corrupted }
  def createData(title: String, year: String, author: String, index: Integer): Data= { Data(title, year, author, index) }
  def yearDouble(book: Data): Double = {
    val chars = book.releaseYear.toCharArray.toList
    @tailrec
    def inner(arr: List[Char], result: Double, repeat: Double): Double=
    {
      arr match {
        case Nil => result
        case head::tail => inner(tail, result+(head.toInt-48)*pow(10.0,repeat), repeat-1.0)
      }
    }
    inner(chars, 0, (chars.length-1).toDouble)
  }
}
