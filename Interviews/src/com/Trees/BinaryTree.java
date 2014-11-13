package com.Trees;

import com.singlelist.LinkedList;

/**
 * Created by dgaglani on 5/11/14.
 */
public interface BinaryTree {

    class TreeNode extends LinkedList.Node{
        int value;
        public TreeNode right;
        public TreeNode left;
        public TreeNode(int value) {
            this.value = value;
        }
    }

    public void postOrderTraversal(TreeNode node);
    public void inOrderTravesal(TreeNode node);
    public void preOrderTraversal(TreeNode node);

}
