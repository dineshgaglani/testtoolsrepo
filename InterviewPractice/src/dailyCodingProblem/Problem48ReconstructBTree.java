package dailyCodingProblem;

import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;

/**
 * Created by Dinesh on 9/6/2019.
 *
 * Given pre-order and in-order traversals of a binary tree, write a function to reconstruct the tree.

 For example, given the following preorder traversal:

 [a, b, d, e, c, f, g]

 And the following inorder traversal:

 [d, b, e, a, f, c, g]

 You should return the following tree:

          a
         / \
        b   c
       / \ / \
      d  e f  g

 THIS QUESTION IS CRAZY HARD MONSIEUR!!!

 PROBABLY MISTAKE HERE IS THAT I NEED TO GO BOTH LEFT AND RIGHT
 */
public class Problem48ReconstructBTree {

    static class Node {
        String val;
        Node left;
        Node right;

        public Node(String val) {
            this.val = val;
        }
    }

    public static Node getTree(String[] preorders, String[] inorders, int preOrderElemIndex) {
        if (preOrderElemIndex >= preorders.length) { return null; }
        String currElem = preorders[preOrderElemIndex];
        Node node = new Node(currElem);
        if (Arrays.asList(inorders).contains(currElem)) {
            node = new Node(currElem);
            int indexToSplitOn = Arrays.asList(inorders).indexOf(currElem);
            String nextElem = preorders[preOrderElemIndex + 1];

            if (!Arrays.asList(inorders).contains(nextElem)) return node;

            int nextElemIndex = Arrays.asList(inorders).indexOf(nextElem);
            String[] inordersNewLefts = (String[]) Arrays.asList(inorders).subList(0, indexToSplitOn).toArray();
            String[] inordersNewRights = (String[]) Arrays.asList(inorders).subList(indexToSplitOn, inorders.length).toArray();
            if (nextElemIndex < indexToSplitOn) {
                node.left = getTree(preorders, inordersNewLefts, preOrderElemIndex + 1);
            } else {
                node.right = getTree(preorders, inordersNewRights, preOrderElemIndex + 1);
            }
        }
        return node;
    }

    public static void main (String args[]) {

    }

}
