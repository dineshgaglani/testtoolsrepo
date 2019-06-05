package dailyCodingProblem;

/*

    Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), which deserializes the string back into the tree.

    For example, given the following Node class

    class Node:
        def __init__(self, val, left=None, right=None):
            self.val = val
            self.left = left
            self.right = right
    The following test should pass:

    node = Node('root', Node('left', Node('left.left')), Node('right'))
    assert deserialize(serialize(node)).left.left.val == 'left.left'

    Approach1: Serialize using pre-order because we see the root of the tree first, then go left and right.

    Mistake committed on attempt 1: When returning from recursion the variable is not equal to what it was changed to
    during recursion, so we may have to return a type that has both the recursion changed value (for index in this case)
    and also the node.

    Mistake committed on the re-attempts: When returning a wrapper, if using that wrapper property, set that property
    on the terminal recursive call.

    Follow up: Attempt in-order
 */

import java.util.ArrayList;
import java.util.List;

public class Problem3BinaryTreeSerialization {

    class Node {
        String data;
        Node left;
        Node right;

        Node (String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data;
        }
    }

    class Res {
        Node node;
        Integer index;
    }

    public void serializa(Node node, List<String> collector) {

        if (node == null) {
            collector.add(null);
            return;
        }

        collector.add(node.toString());


        serializa(node.left, collector);
        serializa(node.right, collector);
    }


    public Node deserialize (List<String> serialized, int index) {
        if (serialized.get(index) == null || index >= serialized.size()) {
            return null;
        }
        Node node = new Node(serialized.get(index));
        index++;
        node.left = deserialize(serialized, index);
        index++;
        node.right = deserialize(serialized, index);

        return node;
    }

    //TODO = structurize this, it's horrible
    public Res deserializeAttempt2 (List<String> serialized, int index) {
        if (index >= serialized.size() || serialized.get(index) == null) {
            Res blank = new Res();
            blank.index = index++;
            return  blank;
        }
        Node node = new Node(serialized.get(index));

        index++;
        Res leftRes = deserializeAttempt2(serialized, index);
        node.left = leftRes.node;
        index = leftRes.index + 1;
        Res rightRes = deserializeAttempt2(serialized, index);
        node.right = rightRes.node;

        Res res = new Res();
        res.node = node;
        res.index = rightRes.index;

        return res;
    }

    /*
        The trick here is that the index should be set inside the serialized[index] is "null" condition
        So, analysis should be on when all do we increment
        Proposed solution: always increment after setting the res value - Did not work - check why

        New proposed solution: always increment index when the function is called, because whenever the function
        is called we anyway have proceeded to the next element on the Serialized String
     */
    public Res deserializeReAttempt(List<String> serialized, int index) {
        Res res = new Res();
        index = index + 1;
        if (index >= serialized.size() || serialized.get(index) == null) {
            res.index = index;
            return res;
        }

        res.node = new Node(serialized.get(index));
        Res leftRes = deserializeReAttempt(serialized, index);
        res.node.left = leftRes.node;
        Res rightRes = deserializeReAttempt(serialized, leftRes.index);
        res.node.right = rightRes.node;

        res.index = rightRes.index;

        return res;
    }

    public static void main (String args[]) {

        /*
                Looks like
                    1
                   / \
                  2   3
                 /     \
                4       5
                       / \
                      6   7
         */

        Problem3BinaryTreeSerialization prob = new Problem3BinaryTreeSerialization();
        Problem3BinaryTreeSerialization.Node root = prob.new Node("1");
        root.left = prob.new Node("2");
        root.right = prob.new Node("3");
        root.left.left = prob.new Node("4");
        root.right.right = prob.new Node("5");
        root.right.right.left = prob.new Node("6");
        root.right.right.right = prob.new Node("7");

        List<String> collector = new ArrayList<>();
        prob.serializa(root, collector);
        System.out.println("Collector: " + collector);
        System.out.println("Should equal [1, 2, 4, null, null, null, 3, null, 5, 6, null, null, 7]");

        Node resNode = prob.deserialize(collector, 0);
        System.out.println("Right: " + resNode.right);
        System.out.println("Should equal: " + root.right);

//        System.out.println("root.right.right.left: " + resNode.right.right.left);
//        System.out.println("Should equal: " + resNode.right.right.left);

        System.out.println("The Above was the first attempt, it failed!");

        Res res = prob.deserializeAttempt2(collector, 0);
        System.out.println("Right: " + res.node.right);
        System.out.println("Should equal: " + root.right);

        System.out.println("root.right.right.left: " + res.node.right.right.left);
        System.out.println("Should equal: " + root.right.right.left);



        System.out.println("The below is last re-attempt, as practice for above, have to add more tests!");

        Res resReattempt = prob.deserializeReAttempt(collector, -1);
        System.out.println("Right: " + resReattempt.node.right);
        System.out.println("Should equal: " + root.right);

        System.out.println("root.right.right.left: " + resReattempt.node.right.right.left);
        System.out.println("Should equal: " + root.right.right.left);
    }
}
