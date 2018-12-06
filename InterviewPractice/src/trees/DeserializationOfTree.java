package trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DeserializationOfTree {

    class Node {
        int content;
        Node left;
        Node right;

        Node (int content) {
            this.content = content;
        }
    }

    /*
        Limitation of deserialize is that it only works for this serialize

        Approach - Straight forward:
        We will perform the same operations that happens in Heaps.

        So if we have a tree with 1 left and many rights with no left, our structure is like this
          1
        2    3
                4
                   5

         Our list is this 1 2 3 null null null 4 null null null 5

         While deserializing it becomes easy since we only need to look at 2n and 2n + 1 when an element is not null,
         the disadvantage here is that on a very unbalanced tree there will be many nulls and space waste (requires exponential space)
         for every level added.

         Approach 2, in-order with nulls:
         When we find a null, add it to a list and return and then add its root as the next element. So non-leaf roots are at
         in our example null, 2, null, 1, null, 3, null, 4, 5, null, null

         Won't exactly work because we don't where root starts

         Approach 3 Preorder traversal

         1, 2, null, null, 3, null, 4, null, 5

         We first create a node with 1 and call its next element assigning the current's left to its return value
         So, the function is called with 2 and again with null as its next left value, our base case is return on null
         so left of 2 is nothing, we then call the same function with next index (value: null) for the right of 2,
         we return again, so we have 2 returned to 1 as its left subtree. We then do the same for the next index (3)
         For 3 the next subtree is null and we return and assign to left of 3 and we go right, after which we assign
         left of 4 as null and 5 as right of 4 and return 4 to be assigned as right of 3

         Mistake was I wasn't returning on null and wasn't recursing for left and right, also on 1 we must be returned
         the index so it must be passed by reference
     */
    public void serialize(Node node, List<Integer> serializedCollector) {
        if (node == null) {
            serializedCollector.add(null);
            return;
        }
        serializedCollector.add(node.content);
        serialize(node.left, serializedCollector);
        serialize(node.right, serializedCollector);
    }

    public Node deserialize(List<Integer> serialized) {
        if (serialized.size() == 0 || serialized.get(0) == null) {
            if (serialized.size() > 0) {
                serialized.remove(0);
            }
            return null;
        }
        Node node = new Node(serialized.get(0));
        serialized.remove(0);
        node.left = deserialize(serialized);
        node.right = deserialize(serialized);

        return node;
    }

   public static void main (String args[]) {
       DeserializationOfTree dt = new DeserializationOfTree();
       Integer[] ints = new Integer[] {1, 2, null, null, 3, null, 4, null, 5};
       ArrayList<Integer> serialized = new ArrayList<>();
       Arrays.stream(ints).forEach(it -> serialized.add(it));
       Node root = dt.deserialize(serialized);
       System.out.println(root);

       ArrayList<Integer> expectedSerialized = new ArrayList<>();
       dt.serialize(root, expectedSerialized);

       System.out.println(expectedSerialized);
   }
}
