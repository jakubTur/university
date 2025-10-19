object Main {
  def main(args: Array[String]): Unit = {
    val numbers = List(1,2,3,4,5)
    println(sumList(numbers))
    println(sumList2(numbers))
    val words = List("kot", "pies", "jeżyk")
    println(connectStrings(words, " "))
    println(Fibonacci(5))
    println(mList(numbers))
    println(mList2(numbers))
    println(func(4))
  }
  def sumList(list: List[Int]):Int =
  {
    var result = 0
    for(i<- list if i%2!=0)
    {
      result = result+i
    }
    result
  }
  def sumList2(list: List[Int]):Int =
  {
    var result=0
    if(list.nonEmpty)
    {
      if (list.head % 2 != 0)
      {
        result = list.head
      }
      var list_shorter = list.patch(0, List.empty, 1)
      if (list_shorter.nonEmpty)
      {
        result=result + sumList2(list_shorter)
      }
    }
    result
  }
  def connectStrings(listOfString: List[String], separator: String): String=
  {
    var word = ""
    if(listOfString.nonEmpty)
      {
        word=listOfString.head
        var list_shorter = listOfString.patch(0, List.empty, 1)
        if (list_shorter.nonEmpty)
        {
          word=word.concat(separator)
          word=word.concat(connectStrings(list_shorter, separator))
        }
      }
    word
  }
  def Fibonacci(number: Int): Int=
    {
      if(number>0)
        {
          if(number==1)
            {
              return 0
            }
          if(number==2)
            {
              return 1
            }
          addition(0,1,number,3)
        }
      else
        {
          -1
        }
    }
    def addition(number1: Int, number2: Int, threshold: Int, current: Int): Int=
      {
        if(current!=threshold)
          {
            addition(number2, number2+number1,threshold,current+1)
          }
        else
          {
            number2+number1
          }
      }
  def mList(list: List[Int]): Int=
    {
      var result = 1
      for(i<- list if i%2!=0)
      {
        result = result*i
      }
      result
    }
  def mList2(list: List[Int]): Int=
  {
    var result=1
    if(list.nonEmpty)
    {
      if (list.head % 2 != 0)
      {
        result = list.head
      }
      var list_shorter = list.patch(0, List.empty, 1)
      if (list_shorter.nonEmpty)
      {
        result=result * mList2(list_shorter)
      }
    }
    result
  }
  def func(n: Int): Int=
  {
    if(n<=1)
      {
        -2
      }
    else
      {
        (n-1)*func(n-1)+3
      }
  }
}