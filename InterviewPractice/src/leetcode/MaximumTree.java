package leetcode;

/**
 * Created by Dinesh on 9/17/2019.
 *
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

 The root is the maximum number in the array.
 The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 Construct the maximum tree by the given array and output the root node of this tree.

 Example 1:
 Input: [3,2,1,6,0,5]
 Output: return the tree root node representing the following tree:

 6
 /   \
 3     5
 \    /
 2  0
 \
 1
 */
public class MaximumTree {

    public class TreeNode {
      int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            return  val +
                    ", \n//" + "\t" + "\\" +
                    ", \n" + left + "\t" + right;
        }
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructUsingMaxIndex(0, nums.length - 1, nums);
    }

    public TreeNode constructUsingMaxIndex(int startIndex, int endIndex, int[] nums) {
        if (startIndex > endIndex) {
            return null;
        }
        Integer maxIndex = findMax(nums, startIndex, endIndex);
        TreeNode node = new TreeNode(nums[maxIndex]);
        node.left = constructUsingMaxIndex(startIndex, maxIndex - 1, nums);
        node.right = constructUsingMaxIndex(maxIndex+1, endIndex, nums);

        return node;
    }

    static Integer findMax(int[] nums, int startIndex, int endIndex) {
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = startIndex; i <= endIndex; i++) {
            if (nums[i] > max) {
                maxIndex = i;
                max = nums[i];
            }
        }
        return maxIndex;
    }

    public static void main (String[] args) {
        MaximumTree mt = new MaximumTree();
        TreeNode res = mt.constructMaximumBinaryTree(new int[] {3,2,1,6,0,5});
        System.out.println(res);
    }
}
