package com.Trees;

/**
 * Created by dgaglani on 5/11/14.
 */
public class Driver {

    public static void main(String args[]) throws Exception{
        BinaryTree binaryTree = new StackBinaryTree();
        BinaryTree.TreeNode root = new BinaryTree.TreeNode(1);
        BinaryTree.TreeNode rootLeft = new BinaryTree.TreeNode(2);
        root.left = rootLeft;
        BinaryTree.TreeNode rootRight = new BinaryTree.TreeNode(3);
        root.right = rootRight;
        BinaryTree.TreeNode rootLeftLeft = new BinaryTree.TreeNode(4);
        rootLeft.left = rootLeftLeft;
        BinaryTree.TreeNode rootLeftRight = new BinaryTree.TreeNode(5);
        rootLeft.right = rootLeftRight;
        BinaryTree.TreeNode rootLeftRightRight = new BinaryTree.TreeNode(7);
        rootLeftRight.right = rootLeftRightRight;
        BinaryTree.TreeNode rootRightRight = new BinaryTree.TreeNode(6);
        rootRight.right = rootRightRight;
        //binaryTree.postOrderTraversal(root);
        //System.out.print(((RecursiveBinaryTree)binaryTree).findMax(root));
        ((StackBinaryTree)binaryTree).printNodesZigZag(root);
    }


}
