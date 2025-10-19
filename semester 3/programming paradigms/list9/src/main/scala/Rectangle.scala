import scala.math._
class Rectangle(side1: Double, side2: Double) extends Shape with Scalable {
  private var width: Double=side1
  private var length: Double=side2
  protected def perimeter(): Double = 2*width + 2*length
  def area(): Double = width*length
  def description(): String = "this is a rectangle with width "+width+" length "+length
  def scale(factor: Double):Unit =
    {
      width=factor*width
      length=factor*length
    }

}
