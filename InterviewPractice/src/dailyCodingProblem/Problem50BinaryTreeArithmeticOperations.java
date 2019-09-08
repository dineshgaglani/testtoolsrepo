package dailyCodingProblem;

/**
 * Created by Dinesh on 9/6/2019.
 *
 * Suppose an arithmetic expression is given as a binary tree. Each leaf is an integer and each internal node is one of '+', '−', '∗', or '/'.

 Given the root to such a tree, write a function to evaluate it.

 For example, given the following tree:

 *
 / \
 +    +
 / \  / \
 3  2  4  5
 You should return 45, as it is (3 + 2) * (4 + 5).
 */
public class Problem50BinaryTreeArithmeticOperations {

    class Node {
        Node left;
        Node right;
        String val;

        Node(String val) {
            this.val = val;
        }
    }

    public Integer calculateTree(Node node) {
        if (node == null ) {return null;}
        Integer fromLeft = calculateTree(node.left);
        Integer fromRight = calculateTree(node.right);
        if(fromLeft != null && fromRight != null) { return performOperation(fromLeft, fromRight, node.val); }
        else if (fromLeft != null || fromRight != null) {return fromLeft != null ?  fromLeft : fromRight; }
        else { return Integer.parseInt(node.val); }
    }

    public Integer performOperation(Integer leftOperand, Integer rightOperand, String operation) {

        switch (operation) {
            case "+":
                return leftOperand + rightOperand;
            case "-":
                return leftOperand - rightOperand;
            case "*":
                return leftOperand * rightOperand;
            case "/":
                return leftOperand/rightOperand;
            default:
                return null;
        }

    }
}
