abstract class IntSet {
  def incl(intval: Int): IntSet
  def contains(intval: Int): Boolean
  def union(that: IntSet): IntSet
}

class NonEmpty(thisval: Int, intSetLeft: IntSet, intSetRight: IntSet) extends IntSet {
  override def incl(intval: Int): IntSet = {
    if (thisval < intval) {
      new NonEmpty(thisval, intSetLeft, intSetRight incl intval)
    } else {
      new NonEmpty(thisval, intSetLeft incl intval, intSetRight)
    }

  }

  override def contains(intval: Int): Boolean = {
    if (intval < thisval) {
      intSetLeft contains intval
    } else if (intval > thisval) {
      intSetRight contains thisval
    }
    else true
  }

  override def toString() =
    ("{ [" + intSetLeft + "]" +  thisval + "[" + intSetRight + "] }")

  override def union(that: IntSet): IntSet = {
    intSetLeft union intSetRight union that incl thisval
  }
}

object Empty extends IntSet {
  override def incl(intval: Int): IntSet = {
    return new NonEmpty(intval, Empty, Empty);
  }

  override def contains(intval: Int): Boolean = false

  override def toString(): String = {
    return "."
  }

  override def union(that: IntSet): IntSet = return that
}

val set1 = new NonEmpty(4, Empty, Empty)
val set2 = set1 incl 3
print(set2)

val set3 = new NonEmpty(6, Empty, Empty)
val set4 = set3 incl 7

set2 union set4

class List[T](head: T, tail: List[T]){
  def nth(index: Int) : T = {
    def iter(currIndex: Int, currList: List[T]) : T = {
      if(currIndex == index) {
        return head
      } else {
        iter(currIndex + 1, currList.tail) }
    }
    iter(0, tail)
  }

}