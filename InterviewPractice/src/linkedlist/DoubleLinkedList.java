package linkedlist;

/*
    A tonne of cases to remember:
        1. We can add to front or to back - This isn't tricky
        2. When deleting from front we traverse from front, the cases to remember are
            a. If head is to be deleted, then set nodeToDelete to head, move head to head.next and set nodeToDelete.next = null and set head.prev = null
            b. If the following is to be deleted, traverse to its prev, get a reference to its next by
            prev.next.next
                1a. Prev.next.next will be null when tail is to be deleted, so ignore setting nextNode.prev to null
            c. Set nextNode.prev to prevNode and nodeToDelete.next and prev to null.
        3. We can delete from back, there are 3 cases here as well
            a. Delete the tail - Set nodeToDelete to tail, move tail to tail.prev and set nodeToDelete.prev to null and tail.next to null
            b. Delete the preceding nodes of tail, traverse until finding the next node to be deleted. Get a reference to its prev
            by next.prev.prev
                1a. he next.prev.prev is null if head is to be deleted, so ignore setting prev.next to next
            c. Set prev.next to next and nodeToDelete.next and prev to null.
 */
public class DoubleLinkedList {

    private int size;
    private Node head;
    private Node tail;

    class Node {
        int content;
        Node prev;
        Node next;
    }

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public void addNodeInFront (int content) {
        Node node = new Node();
        node.content = content;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
   }

   public void addNodeAt(int loc, int content) {
       //Have to add check for loc out of bounds
   }

    public void addNodeAtEnd (int content) {
        Node node = new Node();
        node.content = content;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    public Node deleteNode(Integer nodeContent) {
        Node prevNode = head;
        Node nodeToDelete = null;
        if (prevNode.content == nodeContent) {
            //Head to be deleted
            nodeToDelete = head;
            Node nextNode = nodeToDelete.next;
            nodeToDelete.next = null;
            head = nextNode;
            if (nextNode != null) {
                //Only 1 node, only tail node and that is being deleted
                nextNode.prev = null;
            }
            size--; if (size == 0 ) { tail = null; }
            return nodeToDelete;
        }
        while (prevNode.next.content != nodeContent) {
            prevNode = prevNode.next;
        }
        if (prevNode.next != null) {
            nodeToDelete = prevNode.next;
            Node nextNode = prevNode.next.next;
            prevNode.next = nextNode;

            if (nextNode != null) {
                //When this is null, tail is being deleted, so there is no next node
                nextNode.prev = prevNode;
            }

            nodeToDelete.next = null;
            nodeToDelete.prev = null;
        }
        return nodeToDelete;
    }

    public Node deleteNodeFromEnd (int contentToDelete) {
        Node nextNode = tail;
        Node nodeToDelete = null;
        if (nextNode.content == contentToDelete) {
            //tail to be deleted
            nodeToDelete = tail;
            Node prevNode = nodeToDelete.prev;
            if (prevNode != null) {
                //Only one node exists, its the head and that has to be deleted, then we wont come here
                prevNode.next = null;
            }
            nodeToDelete.prev = null;
            tail = prevNode;
            size--;
            if (size == 0) { head = null; }
            return nodeToDelete;
        } else {
            while (nextNode.prev.content != contentToDelete) {
                nextNode = nextNode.prev;
            }
            if (nextNode.prev != null) {
                nodeToDelete = nextNode.prev;
                Node prevNode = nextNode.prev.prev;
                nextNode.prev = prevNode;
                if (prevNode != null) {
                    //When this is null, head is being deleted
                    prevNode.next = nextNode;
                }

                nodeToDelete.next = null;
                nodeToDelete.prev = null;

            }
        }
        size--;
        return nodeToDelete;
    }


}
