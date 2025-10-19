import scala.math._
class Triangle(side1: Double, side2: Double) extends Shape with Scalable {
  var base=side1
  var height=side2
  def area(): Double = (base*height)/2
  def description(): String = "this is a triangle with base "+base+" height "+height
  def scale(factor: Double):Unit =
    {
      base=factor*base
      height=factor*height
    }
}
