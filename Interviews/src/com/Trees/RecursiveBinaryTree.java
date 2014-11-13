package com.Trees;

import com.queue.LinkedListQueue;
import com.queue.Queue;
import com.searching.PairSumSearch;
import com.singlelist.LinkedList;

import java.util.Arrays;

/**
 * Created by dgaglani on 5/11/14.
 */
public class RecursiveBinaryTree implements BinaryTree {

    Queue<TreeNode> levelPrinterQueue = new LinkedListQueue<TreeNode>();

    @Override
    public void postOrderTraversal(BinaryTree.TreeNode node) {
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print("\t" + node.value);
    }

    @Override
    public void inOrderTravesal(BinaryTree.TreeNode node) {
        inOrderTravesal(node.left);
        System.out.print("\t" + node.value);
        inOrderTravesal(node.right);
    }

    @Override
    public void preOrderTraversal(BinaryTree.TreeNode node) {
        System.out.print("\t" + node.value);
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public int findMax(TreeNode root) {
        int max = Integer.MIN_VALUE;
        if(root != null) {
            int rootValue = root.value;
            max = Math.max(rootValue, Math.max(findMax(root.left), findMax(root.right)));
        }
        return max;
    }

    public void printLevels(TreeNode node) throws Exception{
        /*
                                1
                       2                3
                    4     5                6
                            7

                 */

             ((LinkedListQueue)levelPrinterQueue).enqueue(node);
             while(levelPrinterQueue.size() != 0) {
                 node = levelPrinterQueue.dequeue();
                 System.out.print(node.value);
                 ((LinkedListQueue)levelPrinterQueue).enqueue(node.left);
                 ((LinkedListQueue)levelPrinterQueue).enqueue(node.right);
         }
    }

    public TreeNode findNodeInTree(TreeNode node, int value) {
        TreeNode foundNode = null;
        if(node != null) {
            if(node.value == value) {
                return node;
            }
            else {
                foundNode = findNodeInTree(node.left, value);
                if(foundNode == null) {
                    foundNode = findNodeInTree(node.right, value);
                }
            }
        }
        return foundNode;
    }

    public int numOfNodesInTree(TreeNode rootNode) {
         /*
                                1
                       2                3
                    4     5                6
                            7

                 */
        if(rootNode == null) {
            return 0;
        }
        int leftNodes = numOfNodesInTree(rootNode.left);
        int rightNodes = numOfNodesInTree(rootNode.right);
        return leftNodes + rightNodes + 1;
    }

    public void deleteTree(TreeNode node) {
        if(node == null) {
            return;
        }
        deleteTree(node.left);
        deleteTree(node.right);
        System.out.print("Deleting node" + node.value);
    }

    public int findTreeDepth(TreeNode treeNode) {
        if(treeNode == null) {
            return 0;
        }
        return Math.max(findTreeDepth(treeNode.left) , findTreeDepth(treeNode.right)) + 1;
    }

    public int numOfLeafNodes(TreeNode treeNode) {
        int leafNodeCount = 0;
         /*
                                1
                       2                3
                    4     5                6
                            7

                 */
        if(treeNode.left == null && treeNode.right == null) {
            return 1;
        }
        if(treeNode.left != null) {
            leafNodeCount = numOfLeafNodes(treeNode.left);
        }
        if(treeNode.right != null) {
            leafNodeCount = leafNodeCount + numOfLeafNodes(treeNode.right);
        }
        return leafNodeCount;
    }

    public int numOfFullfNodes(TreeNode treeNode) {
        int fullNodeCount = 0;
         /*
                                1
                       2                3
                    4     5                6
                            7

                 */
        if(treeNode == null) {
            return 0;
        }
        if(treeNode.left != null && treeNode.right != null) {
            fullNodeCount++;
        }
        if(treeNode.left == null && treeNode.right == null) {
            return 0;
        }
        fullNodeCount = fullNodeCount + numOfFullfNodes(treeNode.left);
        fullNodeCount = fullNodeCount + numOfFullfNodes(treeNode.right);
        return fullNodeCount;
    }

    public boolean isStructurallyIdentical(TreeNode node1, TreeNode node2) {
         /*
                                1                                       1
                       2                3
                    4     5                6
                            7

                 */
        if(node1 == null && node2 == null) {
            return true;
        }
        if(node1 == null && node2 != null) {
            return false;
        }
        if(node1 != null && node2 == null) {
            return false;
        }
        return isStructurallyIdentical(node1.left, node2.left) && isStructurallyIdentical(node1.right, node2.right);
    }

    public void printPathsFromRootToLeaf(TreeNode root, int[] pathElements, int pathElementsIndex) {
        /*
                                1                                       1
                       2                3
                    4     5                6
                            7

                 */
        if(root == null) {
            return;
        }
        if(root.right == null && root.left == null) {
            //print here
            return;
        }
        pathElements[pathElementsIndex] = root.value;
        pathElementsIndex++;
        printPathsFromRootToLeaf(root.left, pathElements, pathElementsIndex);
        printPathsFromRootToLeaf(root.right, pathElements, pathElementsIndex);
    }

    public boolean checkPathsFromRootToLeafAddUpto(TreeNode root, int[] pathElements, int pathElementsIndex, int elementToBeAdded, int currentSum) {
        /*
                                1                                       1
                       2                3
                    4     5                6
                            7

                 */
        if(root == null) {
            return false;
        }
        if(currentSum + root.value == elementToBeAdded) {
            return true;
        }
        boolean result = checkPathsFromRootToLeafAddUpto(root.left, pathElements, pathElementsIndex, elementToBeAdded, currentSum + root.value);
        if(!result)
        result = checkPathsFromRootToLeafAddUpto(root.right, pathElements, pathElementsIndex, elementToBeAdded, currentSum + root.value);
        return result;
    }

    public void mirrorImageBinaryTree(TreeNode treeNode) {
        /*
                                1                                       1
                       2                3                          3           2
                    4     5                6                                       4
                            7

                 */
        if(treeNode == null) {
            return;
        }
        mirrorImageBinaryTree(treeNode.left);
        mirrorImageBinaryTree(treeNode.right);
        TreeNode temp = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = temp;
    }

    public boolean isMirror(TreeNode node1, TreeNode node2) {
        if(node1 == null && node2 == null) {
             return true;
        }
        if(node1 != null && node2 == null || node1 == null && node2 != null) {
            return false;
        }
        return node1.value == node2.value && isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
    }

    public int treeDiameter(TreeNode treeNode) {
         if(treeNode == null) return 0;
         int lHeight = findTreeDepth(treeNode.left);
         int rHeight = findTreeDepth(treeNode.right);
         int diameter = lHeight + rHeight;
         return Math.max(diameter, Math.max(treeDiameter(treeNode.left), treeDiameter(treeNode.right)));
    }

    public TreeNode closestCommonAncestor(TreeNode treeNode, TreeNode nodeToFindAncestorFor, TreeNode nodeToFindAncestorFor2) {
        if(treeNode == null) {
            return null;
        }

        if(treeNode == nodeToFindAncestorFor || treeNode == nodeToFindAncestorFor2) {
            return treeNode;
        }

        TreeNode left = closestCommonAncestor(treeNode.left, nodeToFindAncestorFor, nodeToFindAncestorFor2);
        TreeNode right = closestCommonAncestor(treeNode.right, nodeToFindAncestorFor, nodeToFindAncestorFor2);

        if(left != null && right != null) {
            return treeNode;
        }

        return left != null ? left:right;
    }

    public TreeNode buildTreeFromInorderAndPreOrderSequences(int[] inOrderSequence, int[] preOrderSequence, int preOrdeSequenceIndex) {
        //Root is the first element in pre-order
        //Look for the root in inorder and send everything pre-root(will be left nodes of root) and post-root(will be right nodes of root) to this method recursively
        if(inOrderSequence.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preOrderSequence[preOrdeSequenceIndex]);
        preOrdeSequenceIndex++;
        int inOrderRootIndex = PairSumSearch.binarySearch(inOrderSequence, 0, inOrderSequence.length - 1, preOrderSequence[preOrdeSequenceIndex]);
        root.left = buildTreeFromInorderAndPreOrderSequences(Arrays.copyOfRange(inOrderSequence, 0, inOrderRootIndex), preOrderSequence, preOrdeSequenceIndex);
        root.right = buildTreeFromInorderAndPreOrderSequences(Arrays.copyOfRange(inOrderSequence, inOrderRootIndex, inOrderSequence.length - 1), preOrderSequence, preOrdeSequenceIndex);
        return root;
    }

    public void printAncestors(TreeNode treeNode, int[] ancestorArray, int ancestorArrayIndex, int nodeToPrintAncestorsFor) {
        if(treeNode == null) {
            return;
        }
        ancestorArray[ancestorArrayIndex] = treeNode.value;
        if(treeNode.value == nodeToPrintAncestorsFor) {
            //Print array here
            return;
        }
        printAncestors(treeNode.left, ancestorArray, ancestorArrayIndex++, nodeToPrintAncestorsFor);
        printAncestors(treeNode.right, ancestorArray, ancestorArrayIndex++, nodeToPrintAncestorsFor);
        //If we have returned, we have not found the ancestor
        ancestorArray[ancestorArrayIndex] = 0;//equalent to remove
    }

    public boolean printAncestors(TreeNode treeNode, int nodeToPrintAncestorsFor) {
        if(treeNode.value == nodeToPrintAncestorsFor || treeNode.left.value == nodeToPrintAncestorsFor || treeNode.right.value == nodeToPrintAncestorsFor || printAncestors(treeNode.left, nodeToPrintAncestorsFor) || printAncestors(treeNode.right, nodeToPrintAncestorsFor)) {
            System.out.print(treeNode.value + "\n");
            return true;
        }
        return false;
    }

    public boolean isBST(TreeNode node) {
        //The minimum for every root should appear on the left, and maximum should appear on the right
        if(node == null) {
            return true;
        }
        boolean result = node.value > findMin(node.left) && findMax(node.right) > node.value;
        return result && isBST(node.left) && isBST(node.right);
    }

    public int findMin(TreeNode node) {
        if(node == null) {
            return Integer.MAX_VALUE;
        }
        return Math.min(node.value, Math.min(findMin(node.left), findMin(node.right)));
    }

    public TreeNode insertIntoBinarySearchTree(TreeNode root, int valueToInsert) {
        if(root == null) {
            root = new TreeNode(valueToInsert);
            root.left = null;
            root.right = null;
            return root;
        }
        if(valueToInsert < root.value) {
            root.left = insertIntoBinarySearchTree(root.left, valueToInsert);
        }
        else if(valueToInsert > root.value) {
            root.right = insertIntoBinarySearchTree(root.left, valueToInsert);
        }
        return root;
    }
}
