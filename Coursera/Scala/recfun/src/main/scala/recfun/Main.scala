package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {

        if(c > r) {
          throw new NoSuchElementException()
        }

        if(r == 0 || c == 0 || r == 1 || c == r) {
          return 1
        }

      pascal(c - 1, r - 1) + pascal(c, r - 1);

    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {

      def recur(index: Int, callCount: Int): Int = {

        if (index == chars.length) {
          return callCount
        }

        if (callCount < 0) {
          //Returning false when ')' without '(' is encountered
          return -1
        }

        if (chars.apply(index) == '(') {
          return recur(index + 1, callCount + 1)
        }

        if (chars.apply(index) == ')') {
          return recur(index + 1, callCount - 1)
        }

        recur(index + 1, callCount)
      }

      return recur(0, 0) == 0
    }

  /**
   * Exercise 3 - countChange(4,List(2,1))
   */
    def countChange(money: Int, coins: List[Int]): Int = {

      val coinsDesc = coins.sorted.reverse

//      def recur(moneyRemaining: Int, remainingCoins: List[Int]): Int = {
//
//        if (remainingCoins.isEmpty) {
//          return 0
//        }
//
//        if (moneyRemaining == 0) {
//          //We have a match, but we need to return the rest of the matches
//          return 1 + recur(remainingCoins.head, remainingCoins.tail)
//        }
//
//        if (moneyRemaining < remainingCoins.head) {
//          //If money remaining less that what is at current index, we have move to the next coin
//          return recur(moneyRemaining, remainingCoins.tail)
//        }
//
//        recur(moneyRemaining%remainingCoins.head, remainingCoins) +
//          recur(moneyRemaining, remainingCoins.tail)
//      }
//
//      return recur(money, coinsDesc)
//    }

      def recur(moneyRemaining: Int, remainingCoins: List[Int]): Int = {

        if (remainingCoins.isEmpty) {
          return 0
        }

        if (moneyRemaining == 0) {
          //We have a match, but we need to return the rest of the matches
          return 1
        }

        if (moneyRemaining < remainingCoins.head) {
          return recur(moneyRemaining, remainingCoins.tail)
        }

        recur(moneyRemaining - remainingCoins.head, remainingCoins) +
          recur(moneyRemaining, remainingCoins.tail)
      }

      return recur(money, coinsDesc)
//      countChange(4,List(1,2)
    }

  def sumInts(a : Int, b: Int, acc: Int): Int = {
    if(a > b) { return acc }
    return sumInts(a + 1, b, acc + a)
  }

  def cube(a: Int) : Int = {
    return a * a * a;
  }

  def sumCubes(a: Int, b: Int, acc: Int): Int = {
    if (a > b) { return acc }
    return (sumCubes( a + 1, b, acc + cube(a)))
  }

  def sumGeneral(f: Int => Int, a: Int, b:Int, acc: Int): Int = {
    if(a > b) { return acc }
    return sumGeneral(f, a + 1, b, acc + f(a))
  }

  sumGeneral(cube, 8, 10, 0)

  def prodGeneral(f: Int => Int, a: Int, b:Int, acc: Int): Int = {
    if(a > b) { return acc }
    return sumGeneral(f, a + 1, b, acc * f(a))
  }

  def intervalOperationGeneral(func: Int => Int, operation: (Int, Int) => Int, a: Int, b:Int, acc:Int): Int = {
    if(a > b) { return acc }
    return intervalOperationGeneral(func, operation, a + 1, b, operation(acc, func(a)))
  }

  intervalOperationGeneral((a) => a * a, (a, b) => a * b, 8, 10, 1);

}
