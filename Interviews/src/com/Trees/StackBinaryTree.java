package com.Trees;

import com.queue.LinkedListQueue;
import com.queue.Queue;
import com.singlelist.LinkedList;
import com.stacks.LinkedListStack;
import com.stacks.Stack;

/**
 * Created by dgaglani on 5/11/14.
 */
public class StackBinaryTree implements BinaryTree {

    @Override
    public void postOrderTraversal(TreeNode node) {
        try{
            LinkedListStack<TreeNode> stack = new LinkedListStack<TreeNode>();
            while(true) {
                //move to left of node and push
                /*
                                1
                       2                3
                    4     5                6
                            7

                 */
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                }
                else {
                    if(stack.stackSize() == 0) {
                        break;
                    }

                    else if(stack.peek().right == null) {
                        node = stack.peek();
                        stack.pop();
                        System.out.print("\t" + node.value);

                        while(stack.stackSize()!= 0 && node == stack.peek().right) {
                            System.out.print("\t" + stack.peek().value);
                            node = stack.peek();
                            stack.pop();
                        }
                    } if(stack.stackSize() != 0) {
                        node = stack.peek().right;
                    } else {
                        node = null;
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void inOrderTravesal(TreeNode node) {
        try{
            LinkedListStack<TreeNode> stack = new LinkedListStack<TreeNode>();
            while(true) {
                //move to left of node and push
                while(node != null) {
                    stack.push(node);
                    node = node.left;
                }
                node = stack.peek();
                if(stack.stackSize() == 0) {
                    break;
                }
                stack.pop();
                System.out.print("\t" + node.value);

                node = node.right;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void preOrderTraversal(TreeNode node) {
     try {
        LinkedListStack<TreeNode> stack = new LinkedListStack<TreeNode>();
        while(true) {
            //move to left of node and push
            while(node != null) {
                System.out.print("\t" + node.value);
                stack.push(node);
                node = node.left;
            }
            node = stack.peek();
            if(stack.stackSize() == 0) {
                break;
            }
            stack.pop();
            node = node.right;
        }
    } catch(Exception e) {
        e.printStackTrace();
    }
    }

    public TreeNode findNode(TreeNode root, int valueToFind) throws Exception{
        Queue<TreeNode> queue = new LinkedListQueue<TreeNode>();
        ((LinkedListQueue)queue).enqueue(root);
        while (queue.size() != 0) {
            TreeNode node = queue.dequeue();
            if(node.value == valueToFind) {
                return node;
            }
            else {
                if(node.left != null) {
                    ((LinkedListQueue)queue).enqueue(node.left);
                }
                if(node.right != null) {
                    ((LinkedListQueue)queue).enqueue(node.right);
                }
            }
        }
        return null;
    }

    public void insertNodeIntoTree(TreeNode root, TreeNode newNode) throws Exception {
        Queue<TreeNode> queue = new LinkedListQueue<TreeNode>();
        ((LinkedListQueue)queue).enqueue(root);
        while (queue.size() != 0) {
            TreeNode node = queue.dequeue();
            if(node.left == null) {
                node.left = newNode;
            }
            else if(node.right == null) {
                node.right = newNode;
            } else {
                ((LinkedListQueue)queue).enqueue(node.left);
                ((LinkedListQueue)queue).enqueue(node.right);
            }
        }
    }

    public void printLevelNodesFromLowestLevel(TreeNode rootNode) throws Exception{
          /*
                                1
                       2                3
                    4     5                6
                            7
                 */
        Queue<TreeNode> queue = new LinkedListQueue<TreeNode>();
        Stack<TreeNode> stack = new LinkedListStack<TreeNode>();
        ((LinkedListQueue) queue).enqueue(rootNode);
        while(queue.size() != 0) {
            TreeNode node = queue.dequeue();
            if(node.right != null) {
                ((LinkedListQueue)queue).enqueue(node.right);
            } if(node.left != null) {
                ((LinkedListQueue)queue).enqueue(node.left);
            }
            stack.push(node);
        }
        while (stack.stackSize() != 0) {
            TreeNode stackNode = stack.peek();
            System.out.print(stackNode.value);
            stack.pop();
        }
    }

    public int findTreeHeight(TreeNode treeNode) throws Exception {
        /*
                                1
                       2                3
                    4     5                6
                            7
                 */
        Queue<TreeNode> queue = new LinkedListQueue<TreeNode>();
        int level = 1;
        ((LinkedListQueue) queue).enqueue(treeNode);
        ((LinkedListQueue) queue).enqueue(null);
        while (queue.size() != 0) {
            TreeNode currNode = queue.dequeue();
            if(currNode == null) {
                level ++;
                ((LinkedListQueue) queue).enqueue(null);
            }
            if(treeNode.left != null) {
                ((LinkedListQueue) queue).enqueue(treeNode.left);
            }
            if(treeNode.right != null) {
                ((LinkedListQueue) queue).enqueue(treeNode.right);
            }
        }
        return level;
    }

    public TreeNode findDeepestNode(TreeNode node) throws Exception{
        Queue<TreeNode> queue = new LinkedListQueue<TreeNode>();
        ((LinkedListQueue) queue).enqueue(node);
        TreeNode currNode = null;
        while (queue.size() != 0) {
            currNode = queue.dequeue();
            if(node.left != null) {
                ((LinkedListQueue) queue).enqueue(node.left);
            }
            if(node.right != null) {
                ((LinkedListQueue) queue).enqueue(node.right);
            }
        }
        return currNode;
    }

    public TreeNode findLeafNodes(TreeNode node) throws Exception{
        /*
                                1
                       2                3
                    4     5                6
                            7
                 */
        Queue<TreeNode> queue = new LinkedListQueue<TreeNode>();
        ((LinkedListQueue) queue).enqueue(node);
        TreeNode currNode = null;
        int leafNodeCount = 0;
        while (queue.size() != 0) {
            currNode = queue.dequeue();
            if(node.left == null && node.right == null) {
                leafNodeCount++;
            }
            if(node.left != null) {
                ((LinkedListQueue) queue).enqueue(node.left);
            }
            if(node.right != null) {
                ((LinkedListQueue) queue).enqueue(node.right);
            }
        }
        return currNode;
    }

    public int findFullNodes(TreeNode node) throws Exception{
        /*
                                1
                       2                3
                    4     5                6
                            7
                 */
        Queue<TreeNode> queue = new LinkedListQueue<TreeNode>();
        ((LinkedListQueue) queue).enqueue(node);
        TreeNode currNode = null;
        int fullNodeCount = 0;
        while (queue.size() != 0) {
            currNode = queue.dequeue();
            if(node.left != null && node.right != null) {
                fullNodeCount++;
            }
            if(node.left != null) {
                ((LinkedListQueue) queue).enqueue(node.left);
            }
            if(node.right != null) {
                ((LinkedListQueue) queue).enqueue(node.right);
            }
        }
        return fullNodeCount;
    }

    public int maximumLevelSum(TreeNode node) throws Exception{
        /*
                                1
                       2                3
                    4     5                6
                            7

                    2 --> 3 --> null --> 4 --> 5 --> 6
                 */
        Queue<TreeNode> queue = new LinkedListQueue<TreeNode>();
        ((LinkedListQueue) queue).enqueue(node);
        TreeNode currNode = null;
        ((LinkedListQueue) queue).enqueue(null);
        int levelSum = 0;
        int maxSum = 0;
        int fullNodeCount = 0;
        while (queue.size() != 0) {
            currNode = queue.dequeue();
            if(currNode == null) {
                //End of level
                maxSum = Math.max(levelSum, maxSum);
                ((LinkedListQueue) queue).enqueue(null);
                levelSum = 0;
            }
            else {
                levelSum = levelSum + currNode.value;
                if(currNode.left != null) {
                    ((LinkedListQueue) queue).enqueue(currNode.left);
                }
                if(currNode.right != null) {
                    ((LinkedListQueue) queue).enqueue(currNode.right);
                }
            }
        }
        return fullNodeCount;
    }

    public void printNodesZigZag(TreeNode node) throws Exception{
        /*
                                1
                       2                3
                    4     5                6
                            7
                      2
                      3
                 */
        Stack<TreeNode> stack = new LinkedListStack<TreeNode>();
        Stack<TreeNode> secondStack = new LinkedListStack<TreeNode>();
        stack.push(node);
        boolean isRightToLeft = true;
        while (stack.stackSize() > 0) {
            TreeNode currNode = stack.peek();
            stack.pop();
            System.out.print(currNode.value + "\t");
            if(isRightToLeft) {
                //To print right to left, the right node should be added second since it will be popped first
                if(node.left != null) {
                    stack.push(node.left);
                }
                if(node.right != null) {
                    stack.push(node.right);
                }
            } else {
                if(node.right != null) {
                    stack.push(node.right);
                }
                if(node.left != null) {
                    stack.push(node.left);
                }
            }
            if(currNode.value == Integer.MIN_VALUE) { //is level breaker node
                //end of level, switch from left to right
                isRightToLeft = !isRightToLeft;
                stack.pop();
                stack.push(levelBreakerNode());
            }
        }
    }

    private TreeNode levelBreakerNode() {
        TreeNode treeNode = new TreeNode(Integer.MIN_VALUE);
        return treeNode;
    }


   /*public TreeNode kthLargest(TreeNode node) {

    }*/
}
