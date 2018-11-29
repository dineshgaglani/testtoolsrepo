package linkedlist;

public class SingleLinkedList {

    Node head;
    int size;

    class Node<T> {
        T content;
        Node next;
        int index;

        Node(T content) {
            this.content = content;
        }
    }

    public Node addToList (int content) {
        size++;
        return addNodeAtIndex(content, size);
    }

    public void removeFromList(int index) {
        removeFromList(index, head);
    }

    private void removeFromList(int index, Node somePrevNode) {
        if (index == 1) {
            Node nodeToRemove;
            if (somePrevNode != null) {
                nodeToRemove = somePrevNode.next;
            } else {
                nodeToRemove = head;
            }
            int deletedNodeIndex = nodeToRemove.index;

            nodeToRemove.next = null;
            if(somePrevNode != null) {
                somePrevNode.next = somePrevNode.next.next;
            } else {
                //Head case
                head = head.next;
            }

            setAllIndexes(deletedNodeIndex, somePrevNode.next);
        } else {
            removeFromList(index--, somePrevNode.next);
        }
    }

    private void setAllIndexes(int startIndex, Node startNode) {
        if(startNode == null) {
            return;
        }
        setAllIndexes(startIndex+1, startNode.next);
        startNode.index = startIndex;
    }

    public Node addNodeAtIndex(int content, int index) {
        Node node = new Node(content);
        return addNodeAtIndex(node, index, head);
    }

    private Node addNodeAtIndex(Node node, int index, Node somePrevNode) {
        if (index == 1) {
            node.next = somePrevNode.next;
            node.index = size;
            return node;
        }
        somePrevNode.next = addNodeAtIndex(node, index--, somePrevNode);
        return somePrevNode;
    }

    public void reverse(Node currNode, Node prevNode) {

        while(currNode != null) {
            Node nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }

    }

    public Node reverseRec(Node node) {
        if(node.next == null) {
            head = node;
            return node;
        }
        Node reversedNext = reverseRec(node.next);
        reversedNext.next = node;
        return node;
    }

}
