object Main {
  def main(args: Array[String]): Unit =
  {
    println(trafficAction(Red))
    println(summation(node(2, node(1, node(1, leaf(2), leaf(3)), leaf(2)), leaf(1))))
    val numbers=List(1,2,3)
    println(length(create(numbers)))
    println(map(create(numbers)))
    println(reverse(create(numbers)))
  }
  sealed trait TrafficLight
  case object Red extends TrafficLight
  case object Yellow extends TrafficLight
  case object Green extends TrafficLight
  def trafficAction(light: TrafficLight): String =
  {
    light match
    {
      case Red=> "Stop"
      case Yellow=> "Slow down"
      case Green=> "Go"
    }
  }
  sealed trait binaryTree
  case class leaf(value: Int) extends binaryTree
  case class node(value: Int, left: binaryTree, right: binaryTree) extends binaryTree
  def summation(tree: binaryTree): Int=
    {
      tree match
      {
        case leaf(value)=>value
        case node(value, left, right)=>value+summation(left)+summation(right)
      }
    }
  sealed trait linkedList
  case object NIL extends linkedList
  case class nodel(value: Int, next: linkedList) extends linkedList
  def create(list: List[Int]): linkedList=
  {
    list match
    {
      case Nil=>NIL
      case(head::tail)=>nodel(head, create(tail))
    }
  }
    def length(list: linkedList): Int =
    {
      def summation(result: Int, list: linkedList): Int=
        {
          list match
          {
            case NIL=>result
            case nodel(value, next)=>summation(result+value, next)
          }
        }
      list match
      {
        case NIL=>0
        case nodel(value, next)=>summation(value, next)
      }
    }
    def f(n: Int): Int=
      {
        n+n
      }
    def map(list: linkedList, f: Int => Int): linkedList=
      {
        def apply(newList: linkedList): linkedList=
        {
          list match
          {
            case NIL=>NIL
            case nodel(value, next)=>nodel(f(value), apply(next))
          }
        }
        list match
        {
          case NIL=>NIL
          case nodel(value, next)=>apply(list)
        }
      }
    def reverse(list: linkedList): Unit = {
      def check(list: linkedList, finalList: List[Int]): linkedList=
        {
          list match
          {
            case NIL=>create(finalList)
            case nodel(value, next)=>check(next, value::finalList)
          }
        }
      list match
      {
        case NIL=>
        case nodel(value, next)=>check(list, List())
      }
  }
}