import scala.math._
class Circle(rad: Double) extends Shape with Scalable {
  private var radius = rad
  protected def diameter():Double = 2*radius
  def area(): Double = Pi*pow(radius, 2)
  def description(): String = "this is a circle with radius "+radius
  def scale(factor: Double):Unit = radius=factor*radius

}
