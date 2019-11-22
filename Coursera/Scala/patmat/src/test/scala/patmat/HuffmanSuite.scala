package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

//import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
//	trait TestTrees {
//		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
//		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
//	}
//
//
//  test("weight of a larger tree") {
//    new TestTrees {
//      assert(weight(t1) === 5)
//    }
//  }
//
//
//  test("chars of a larger tree") {
//    new TestTrees {
//      assert(chars(t2) === List('a','b','d'))
//    }
//  }
//
//  test("times test") {
//    assert(times(List('a','b','c','b','c')) == List(('c',2), ('b',2), ('a',1)))
//  }
//
//
//  test("string2chars(\"hello, world\")") {
//    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
//  }
//
//
//  test("makeOrderedLeafList for some frequency table") {
//    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
//  }
//
//
//  test("combine of some leaf list") {
//    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
////    val leaflist = List(Leaf('e', 2), Leaf('t', 3), Leaf('x', 4))  ==> List(Leaf('x',4), Fork(Leaf('e',2),Leaf('t',3),List('e', 't'),5)))
//    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
//  }
//
//  test("validate until function") {
//    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
//    assert(until(Huffman.singleton, Huffman.combine)(leaflist) === Fork(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4), List('e', 't', 'x'), 7))
//  }
//
//  test("decode") {
////    print(decode(Fork(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4), List('e', 't', 'x'), 7), List(0, 1)))
//    print(decodedSecret)
//  }
//
//
//  test("decode and encode a very short text should be identity 1") {
//    new TestTrees {
//      print(encode(t1)("ab".toList))
//      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
//      assert((encode(frenchCode)("huf".toList)) === List(0,0,1,1,1,0,1,0,1,1,1,0, 0, 1, 1, 0, 1))
//    }
//  }

  test("n queens") {
    val setOfLists = nqueens.queens(4)
    println("\n set of lists: " + setOfLists)

    val anagrams = nqueens.anagrams(0, List("1", "2", "3"))
  }

  test("map demo") {
    def res = nqueens.mapDemo(Map(1 -> 2, 3 -> 4, 5 -> 6), Map(1 -> 3, 6 -> 7))
    println(res)
  }

}
