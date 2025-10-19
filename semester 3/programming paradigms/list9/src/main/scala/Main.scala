import scala.annotation.tailrec

object Main {
  def main(args: Array[String]): Unit = {
    val circle = new Circle(12) with coloredShape
    val triangle = new Triangle(1,2)
    val rectangle = new Rectangle(3,4)
    println(circle.getColor())
    circle.color("green")
    println(circle.getColor())
    println(circle.description())
    println(circle.coloredDescription)
    val list=List(triangle, rectangle, circle)
    println(TotalArea(list))
    circle.scale(2.0)
    triangle.scale(0.5)
    rectangle.scale(1.2)
    println(circle.description())
    println(TotalArea(list))
    def describeShapes(shapes: List[Shape]): String =
    {
      @tailrec
      def inner(shapes: List[Shape], current: String): String=
      {
        shapes match
        {
          case Nil=>current
          case _ =>
          {
            shapes.head match
            {
              case shape:coloredShape => inner(shapes.tail, current+shape.coloredDescription + " and area "+shape.area+"\n")
              case shape=> inner(shapes.tail, current+shape.description()+" and area "+shape.area+"\n")
            }
          }
        }
      }
      inner(shapes, "")
    }
    def TotalArea(shapes: List[Shape]): Double=
    {
        @tailrec
        def inner(shorter: List[Shape], sum: Double): Double=
          {
            shorter match {
              case Nil=>sum
              case head::tails => inner(tails, sum+head.area())
            }
          }
          inner(shapes, 0)
    }
    println(describeShapes(list))
  }
}
