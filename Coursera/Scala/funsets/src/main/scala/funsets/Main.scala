package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))

  def twoSet = union(singletonSet(1), singletonSet(2))
  println(contains(twoSet, 1) + " expect true") //true
  println(contains(twoSet, 2) + " expect true")  //true
  println(contains(twoSet, 3) + " expect false") //false

  def threeSet = union(twoSet, singletonSet(3))
  println(contains(threeSet, 1) + " expect true") //true
  println(contains(threeSet, 2) + " expect true")  //true
  println(contains(threeSet, 3) + " expect true") //true
  println(contains(threeSet, 4) + " expect false") // false

  printSet(threeSet)
  def mappedSet = map(threeSet, i => i * 2)
  printSet(mappedSet)
}
