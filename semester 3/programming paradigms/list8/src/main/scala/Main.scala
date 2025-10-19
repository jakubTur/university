import shapes.{ShapeOperations => area}
import MathOperations._
import books._
object Main {
  def main(args: Array[String]): Unit = {
    val a = area.calculate(1,2)
    val b = area.calculate(1.0, 3.0)
    val c = calculate(1,3)
    println(yearDouble(books.createData("interesting book","1234","john",1234)))
    val list = List(books.createData("interesting book","1234","john",1234), books.createData("idk"))
    println(a, b, c)
  }
}