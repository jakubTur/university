import scala.annotation.tailrec

object Main {
  def main(args: Array[String]): Unit = {
    println(applyTwice(func, 1))
    val multp = createMultiplier(2)
    println(multp(2))
    var numbers = List(0, 1, 2, 3,4)
    println(squareList(numbers))
    var multp2 = createMultiplierRec(4)
    println(multp2(2))
    println(compose(f, g))
    splitAtPivot(numbers, pivot)
  }
  def func(x: Int): Int =
  {
    x + x
  }
  def f(x: Int): Int =
  {
    x
  }
  def g(number: Int): Int=
    {
      number*2
    }

  def applyTwice(f: Int => Int, x: Int): Int =
  {
    f(f(x))
  }
  def createMultiplier(factor: Int): Int => Int = (x: Int) =>
  {
    factor * x
  }
  def squareList(numbers: List[Int]): List[Int] =
  {
    numbers.map(x => x * x)
  }
  def createMultiplierRec(n: Int): Int => Int =
  {
    @tailrec
    def mult(result: Int, current: Int): Int =
    {
      if(current==0)
        {
          result
        }
      else
        {
          mult(result+n,current-1)
        }
    }
    (x: Int)=>mult(0, x)
  }
  def compose(f: Int => Int, g: Int=>Int): Int => Int = (x: Int) =>
    {
      g(f(x))
    }
  def splitAtPivot(list: List[Int], p: (Int, Int)=>Boolean): Unit=
    {
      if(list.length%2!=0)
        {
          @tailrec
          def splitinside(newList: List[Int], newList2: List[Int], index: Int): Unit=
          {
            if(p(index, list.length/2))
            {
              (newList.dropRight(1), newList2)
            }
            else
              {
                splitinside(newList.dropRight(1),newList2.tail,index+1)
              }
          }
          splitinside(list, list, 0)
        }
    }
  def pivot(number: Int, pivot: Int): Boolean=
  {
    if(number<pivot) {false}
    else {true}
  }
}