trait coloredShape extends Shape with Colorable {
  def coloredDescription: String = {
    getColor() match {
      case "colorless" => description()
      case _ => description() + " and color " + color
    }
  }
}