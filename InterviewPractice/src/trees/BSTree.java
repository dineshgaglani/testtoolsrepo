package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class BSTree {

    private class Node {
        Node left;
        Node right;
        int key;
        int xCord;
        int yCord;

        Node(int key) {
            this.key = key;
        }
    }

    public Node insert(int key, Node root) {
        //Returns root
        if (root == null) {
            root = new Node(key);
            return root;
        }
        Node nodeToTraverse = null;
        if (key < root.key) {
            //Traverse to left and after inserting assign to left
            nodeToTraverse = root.left;
            root.left = insert(key, nodeToTraverse);
        } else if (key >= root.key) {
            //Traverse to left and after inserting assign to right
            nodeToTraverse = root.right;
            root.right = insert(key, nodeToTraverse);
        }
        return nodeToTraverse;
    }

    public Node delete(int key, Node root) {
        Node nodeToTraverse = null;
        if (root == null) {
            return null;
        } else if (root.key < key) {
            //Key supplied is bigger, so the key supplied is on the right
            nodeToTraverse = root.right;
            root.right = delete(key, nodeToTraverse);
        } else if (root.key > key) {
            nodeToTraverse = root.left;
            root.left = delete(key, nodeToTraverse);
        } else {
            //Node equal, has to be deleted
            Node nodeToReturn = null;
            if (root.right == null) {
                nodeToReturn = root.left;
            } else if (root.left == null) {
                nodeToReturn = root.right;
            } else {
                //Find the minNode from the right and replace this node with that, the minNode won't have a right, so call this delete itself passing the minNode key and right node as the root
                Node minNode = getMinNode(root.right);
                int minNodeKey = minNode.key;
                root.key = minNodeKey;
                root.right = delete(minNodeKey, root.right);
                nodeToReturn = root;
            }
            return nodeToReturn;
        }
        return null;
    }

    public Node getMinNode(Node root) {
        //Get the left most node
        if (root.left == null) {
            return root;
        }
        return getMinNode(root.left);
    }

    public void display() {
        //Using x cord and y cord
    }

    public Node floor(int target, Node node) {
        // Every node that is smaller than the target is a potential floor,
        // so until the node is bigger traverse left,
        // when a node is smaller go to its right until a node that is between the root and target is encountered,
        // if nothing is found return null
        if (node == null) {
            return null;
        }
        else if (node.key == target) {
            return node;
        }
        else if (node.key > target) {
            return floor(target, node.left);
        }
        else if (node.key < target) {
            Node floorNode = floor(target, node.right);
            if (floorNode != null) {
                return floorNode;
            } else {
                return node;
            }
        }
        return null;
    }

    public Node ciel(int target, Node node) {
        //Anything bigger than the target is a potential ciel anything smaller can be ignored,
        // go right until nodes are smaller, when a bigger is found,
        // go to its left until something between the target and the node is found
        if (node == null) {
            return null;
        }
        else if (target > node.key) {
            return ciel(target, node.right);
        } else if (target == node.key) {
            return node;
        } else if (target < node.key) {
            Node cielNode = ciel(target, node.left);
            if (cielNode != null) {
                return cielNode;
            }
                return node;
        }
        return null;
    }

    public Node constructFromLinkedList(List<Integer> keys) {
        return null;
    }

    public Node constructFromSortedArray(SortedSet<Integer> sortedSet) {
        return null;
    }

    public int maxHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(maxHeight(node.left), maxHeight(node.right));
    }

    public boolean isEqualToTree (Node tree1Node, Node tree2Node) {
        return false;
    }

    public boolean isMirrorOfTree (Node tree1Node, Node tree2Node) {
        return false;
    }

    public void rangeQuery(int from, int to, List<Integer> collector, Node node) {

    }

    public int transformToGreaterSum (Node node) {
        // We need to provide the root with all of right sub-tree's sum and not just the right of right.
        // But the key will only have the right subtrees sum
        // TODO - and the left nodes will have the root and the root's right nodes' sum
        // The caller node (if he called right side) will need to get both sums however,
        // if he called left he will not set the key but return the sum from the right AND left
        if (node == null) {
            return 0;
        }
        node.key = node.key + transformToGreaterSum(node.right);
        return node.key + transformToGreaterSum(node.left);
    }

    public int setXCordForTree(Node node, int lastX) {
        if (node == null) {
            return lastX;
        }
        node.xCord = 1 + setXCordForTree(node.left, lastX);
        return setXCordForTree(node.right, node.xCord);
    }

    public int getTreeSum(Node node) {
      return 0;
    }

    public void setYCordForTree (Node node) {
        Node delimNode = new Node(Integer.MIN_VALUE);
        List<Node> nodeHolder = new ArrayList<>();
        nodeHolder.add(node);
        nodeHolder.add(delimNode);
        int level = 1;
        List<Node> nodeCollector = new ArrayList<>();
        while (!nodeHolder.isEmpty()) {
            Node dequeued = nodeHolder.get(0);
            nodeCollector.add(dequeued);
            nodeHolder.remove(0);
            if (dequeued.key != Integer.MIN_VALUE) {
                dequeued.yCord = level;
                if (dequeued.left != null) {
                    nodeHolder.add(dequeued.left);
                }
                if (dequeued.right != null) {
                    nodeHolder.add(dequeued.right);
                }
            } else {
                level++;
                nodeHolder.add(delimNode);
            }
        }
    }

}
