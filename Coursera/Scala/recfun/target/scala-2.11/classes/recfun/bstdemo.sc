abstract class IntSet {
  def incl(intval: Int): IntSet
  def contains(intval: Int): Boolean
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
}

object Empty extends IntSet {
  override def incl(intval: Int): IntSet = {
    return new NonEmpty(intval, Empty, Empty);
  }

  override def contains(intval: Int): Boolean = false

  override def toString(): String = {
    return "."
  }
}

val set1 = new NonEmpty(4, Empty, Empty)
val set2 = set1 incl 3
print(set1)

