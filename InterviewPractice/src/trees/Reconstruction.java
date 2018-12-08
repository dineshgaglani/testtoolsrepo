package trees;

/*
                4
          2             5
       1     3      6       7

                    4
             2             5              ---> Inorder : 1 8 9 10 2 3 4 5 6 7
       1        3      6       7          ---> Preorder: 4 2 1 9 8 10 3 5 6 7
          9
         8 10

       We need both in-oder and pre-order to achieve same rendering of a tree, this is because from pre-order we
       get the root and from the in-order we get which side the next nodes are on from the root.

       I have memorized this procedure due to lack of time. Please do explore other procedures later on.
       Don't proceed ahead until we analyze the procedure involving picking the first node to be left.

       Algorithm: Pick the first node from the pre-order to be the root, find the location of the pre-order in the
       in-order, call a recursive function (with this node's left assigned to the result) that takes in the elements
       on the left of the index in the in-order and repeat until we don't have anymore elements in the left.
       So the terminal condition is when there are no more lefts from the index we are at in the in-order list (or
       first element in the list).

        Then as we return do the same for the right

        We need to keep in mind that the inorder array has to be trimmed to include only the elements left of the node
        that was just created so that we don't have to keep track of when to return back from the recursion.
        And include only right of target in inorder should be included when recursing for the right,
        also when we go right pre-order needs to stripped by 2 places since 1 is already made part of the left node.
 */
public class Reconstruction {

    class Node  {
        Node left;
        Node right;
        int value;

        Node (int value) {
            this.value = value;
        }
    }

    public Node reconstruct (Integer[] inorder, Integer[] preorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        int targetVal = preorder[0];
        Node node = new Node(targetVal);
        node.left = reconstruct(newArrFromTo(inorder, 0, elemIndex(inorder, targetVal)), newArrFromTo(preorder, 1, preorder.length));;
        node.right = reconstruct(newArrFromTo(inorder, elemIndex(inorder, targetVal) + 1, inorder.length), newArrFromTo(preorder, 2, preorder.length));
        return node;
    }

    public int elemIndex(Integer[] inorder, int target) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public Integer[] newArrFromTo(Integer[] orig, int start, int end) {
        Integer[] newArr = new Integer[end - start];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = orig[start++];
        }
        return newArr;
    }

    public static void main (String args[]) {
        Integer[] inOrder = new Integer[] {1, 2, 3, 4, 5, 6, 7};
        Integer[] preOrder = new Integer[] { 4, 2, 1, 3, 5, 6, 7};
        Reconstruction r = new Reconstruction();
        r.reconstruct(inOrder, preOrder);
    }

}
