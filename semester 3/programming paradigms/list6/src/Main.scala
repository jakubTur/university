import scala.annotation.tailrec
import scala.math.BigInt
object Main
{
  def main(args: Array[String]): Unit =
  {
    println(lazyFibonacci.take(2).foreach(println))
  }
  def lazyFibonacci: LazyList[BigInt]=
  {
    def innerFibonacci(sum: BigInt, next: BigInt): LazyList[BigInt] = {
      sum #:: innerFibonacci(next, sum + next)
    }
    innerFibonacci(0,1)
  }

  sealed trait LazyBinaryTree
  case object empty extends LazyBinaryTree
  case class node(value: Int, left:()=>LazyBinaryTree, right:()=>LazyBinaryTree) ///pretty much just like an ADT for a regular binary tree, but the children are call-by-name


  def lazyCartesianProduct(list1: LazyList[Int], list2: LazyList[Int]): LazyList[(Int, Int)]=
  {
    def innerList(shorter1: LazyList[Int], shorter2: LazyList[Int], finalList: LazyList[(Int, Int)], column: Int): LazyList[(Int, Int)]=
    {
      column match
      {
        case list2.length=>
          shorter1 match
          {
            case head#::LazyList()=>finalList
            case head#::tail=>innerList(shorter1.tail, list2, finalList, 0)
          }
        case _=>
        {
          innerList(shorter1, shorter2.tail, (shorter1.head, shorter2.head)#::finalList, column+1)
        }
      }
    }
    innerList(list1, list2, LazyList[Any], 0)
  }
}