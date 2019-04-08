package trees;


/*
   A tree is a unival tree when all nodes are equal, if a tree is only leaf, it is also considered unival

   We need 3 pieces of information - whether the children values are equal to the parent value, whether
   the children sub trees are unival and how many of the children subtrees are unival. We need the latter 2 pieces
   of information to decide whether the unival count for the current needs to be increased or not. For all of this
   we could pass our current node's value down or check for child's values here, if we pass the current node down
   we would essentially validate the child is a unival or not, but we will have missed out calculating at the root
       (try  1
           1     1
          0  1  1 1)
            - we pass 1 to left and 1 to right, left is a not unival because its a different leaf, right is a unival because its has same
   leaf, the parent would not count as a unival because the left sub tree isn't. In this case we can pass the root down,
   but there are other cases where this doesn't work, we decide to evaluate children first and then compare then while percolating up we see if the tree at the current
   node is a unival by comparing child's values

 */
public class UnivalTree {

    class Result {
        boolean isUnival;
        int univalsSoFar;
    }

    class Node {
        Node left;
        Node right;
        int value;
    }

    public Result isUnival (Node node) {
        if (node == null) {
            Result r = new Result();
            r.isUnival = true;
            r.univalsSoFar = 0;
        }

        Result leftRes = isUnival(node.left);
        Result rightRes = isUnival(node.right);

        Result r = new Result();
        r.isUnival = false;
        r.univalsSoFar = leftRes.univalsSoFar + rightRes.univalsSoFar;
        if (node.left != null) {
            if (node.value != node.left.value) {
                return r;
            }
        }
        if (node.right != null) {
            if(node.value != node.right.value) {
                return r;
            }
        }
        //If it gets here either both children are null or if present they are equal
        r.isUnival = true;
        r.univalsSoFar = r.univalsSoFar + 1;
        return r;
    }

}
