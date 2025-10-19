trait Colorable {
  var color = "colorless"
  def color(text: String): Unit = color=text
  def getColor(): String=color
}
