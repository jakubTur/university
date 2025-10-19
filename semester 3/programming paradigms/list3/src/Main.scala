import scala.annotation.tailrec

object Main {
  def main(args: Array[String]): Unit =
  {
    var numbers=List(1,3,0)
    var numbers2=List(2,4,6)
    println(sumList(numbers));
    println(sortCheck(numbers));
    println(revertRecursion(numbers))
    println(tailRecursion(numbers))
    println(merge(numbers, numbers2))
    println(sortCheckrev(numbers))
    println(mergetwo(numbers, numbers2))
    println(factorial(3))
  }
  def sumList(list: List[Int]): Int =
  {
    @tailrec
    def summation(listShorter: List[Int], sum: Int): Int=
      {
        listShorter match
        {
          case Nil => sum;
          case head::tail=> summation(tail, sum+head)
        }
      }
    summation(list, 0)
  }
  def revertRecursion(list: List[Int]): List[Int] =
  {
    list match
    {
      case Nil => Nil
      case head::tail => list.last::revertRecursion(list.take(list.length-1))
    }
  }
  def tailRecursion(list: List[Int]): List[Int] =
  {
    @tailrec
    def matchRecursion(list: List[Int], newList: List[Int]): List[Int] =
    {
      list match
      {
        case Nil => newList
        case List(_) => list.head::newList
        case head::tail => matchRecursion(list.tail, list.head::newList)
      }
    }
    var newList=List()
    matchRecursion(list, newList)
  }
  @tailrec
  def sortCheck(list: List[Int]): Boolean =
    {
      list match
      {
        case Nil=> true
        case List(_)=> true
        case head::tail =>
          if (head > tail.head) false
          else sortCheck(tail)
      }
    }
  def merge(list1: List[Int], list2: List[Int]): List[Int]=
  {
    @tailrec
    def mergeMatch(list1: List[Int], list2: List[Int], finalList: List[Int]): List[Int] = {
      (list1, list2) match
      {
        case (Nil, Nil)=> finalList
        case (Nil, list2)=> list2.reverse:::finalList
        case (list1, Nil)=> list1.reverse:::finalList
        case (list1, list2)=> mergeMatch(list1.tail, list2.tail, list2.head::list1.head::finalList)
      }
    }
    mergeMatch(list1, list2, Nil).reverse
  }
  @tailrec
  def sortCheckrev(list: List[Int]): Boolean =
  {
    list match
    {
      case Nil=> {true}
      case List(_)=> true
      case head::tail =>
        if (head < tail.head) { false }
        else { sortCheckrev(tail) }
    }
  }
  def mergetwo(list1: List[Int], list2: List[Int]): List[Int]=
  {
    @tailrec
    def mergeMatch(list1: List[Int], list2: List[Int], finalList: List[Int]): List[Int] = {
      (list1, list2) match
      {
        case (Nil, Nil)=> finalList
        case (Nil, list2)=> list2.reverse:::finalList
        case (list1, Nil)=> list1.reverse:::finalList
        case (head::Nil, list2)=>list2.reverse:::list1:::finalList
        case (list1, head::Nil)=>list1.tail.reverse:::list2:::(list1.head::finalList)
        case (head::second::tail, head2::second2::tail2)=> mergeMatch(tail, tail2, second2::second::head2::head::finalList)
      }
    }
    mergeMatch(list1, list2, Nil).reverse
  }
  def factorial(number: Int): Int=
    {
      @tailrec
      def inFactorial(number: Int,result: Int): Int=
      {
        number match
        {
          case 0=>1
          case 1=>result
          case _=>inFactorial(number-1,number*result)
        }
      }
      if(number>=0) { inFactorial(number, 1) }
      else { -1 }
    }
}