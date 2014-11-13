package com.singlelist;

/**
 * Created by dgaglani on 4/10/14.
 */
public class LinkedList {

    public Node head;
    public int size;

    public static class Node {

        public Node(int value) {
            this.value = value;
        }

        public Node() {

        }

        public int value;
        public Node next;
    }

    public void insertNodeAtEnd(int newValue) {
        Node nodeToInsert = new Node(newValue);
        if(head == null) {
            this.head = new Node(newValue);
            size++;
            return;
        }
        Node insertAfterNode = head;
        //else, get to where list ends and insert there
        while(insertAfterNode.next != null) {
            insertAfterNode = insertAfterNode.next;
        }
        insertAfterNode.next = nodeToInsert;
        size++;
    }

    public void insertNodeAtHead(int value) {
        Node newHead = new Node(value);
        Node temp = head;
        newHead.next = temp;
        head = newHead;
        size++;
    }


    public void insertNodeAtPosition(int value, int position) {
        Node nodeToInsertAfter = navigateToNode(position - 1);
        Node newNode = new Node(value);
        Node temp = nodeToInsertAfter.next;
        nodeToInsertAfter.next = newNode;
        newNode.next = temp;
        size++;
    }

    public void deleteHeadNode() {
        //Just to dispose this later
        Node temp = head;
        head = head.next;
        size--;
    }

    public void deleteNodeAtPosition(int position) {
        //Get to the position of the node to remove
        Node nodeAfterWhichToDelete = navigateToNode(position - 1);
        deleteNextNodeFor(nodeAfterWhichToDelete);
    }

    public void deleteNextNodeFor(Node node) {
        Node temp = node.next;
        node.next = node.next.next;
        size--;
        //Then dispose off temp
    }

    public void interchangeNodes() {
        Node currNode = head;
        Node previouslyChangedNode = null;
        while(currNode.next != null) {
            Node currNext = currNode.next;
            if(currNode == head) {
                head = currNext;
            }
            Node temp = currNext.next;
            currNext.next = currNode;
            currNode.next = temp;
            if(previouslyChangedNode != null) {
                previouslyChangedNode.next = currNext;
            }
            previouslyChangedNode = currNode;
            currNode = temp;
        }
    }

    public void deleteLastNode() {
        //get to the second last node
        Node nodeAfterWhichToDelete = navigateToNode(size - 1);
        nodeAfterWhichToDelete.next = null;
        size--;
    }

    public Node navigateToNode(int position) {
        Node temp = head;
        int nodeCtr = 1;
        while(nodeCtr < position) {
            temp = temp.next;
            nodeCtr++;
        }
        return temp;
    }

    public Node revList(Node node) {
        if(node.next == null) {
            this.head = node;
            return node;
        }
        Node lastNode = revList(node.next);
        lastNode.next = node;
        node.next = null;
        return node;
    }

    public void displayList(Node node) {
        Node thisNode = node;
        while(thisNode != null){
            System.out.print(thisNode.value + "\t");
            thisNode = thisNode.next;
        }
    }

    public void swapKNodes(Node node, int k) {
        /* swap K nodes
           example: 1 2 3 4 5 6 7 8 9, k = 2, result: 2 1 4 3 6 5 8 7 9
           1 2 3 4 5 6 7 8 9, k = 3, 3 2 1 6 5 4 9 8 7
           1 2 3 4 5 6 7 8 9, k = 4, 4 3 2 1 8 7 6 5 9
         */
        //save the k + 1th node
        Node temp = navigateToNode(k+1);

    }


    public static void main(String args[]){
        Node node = new Node(1);
        LinkedList linkedList1 = new LinkedList();
        linkedList1.insertNodeAtEnd(1);
        linkedList1.insertNodeAtEnd(3);
        linkedList1.insertNodeAtEnd(5);
        linkedList1.insertNodeAtEnd(7);
        linkedList1.insertNodeAtEnd(9);
        LinkedList linkedList2 = new LinkedList();
        linkedList2.insertNodeAtEnd(2);
        linkedList2.insertNodeAtEnd(4);
        linkedList2.insertNodeAtEnd(6);
        linkedList2.insertNodeAtEnd(8);
        linkedList2.insertNodeAtEnd(10);
        linkedList1.displayList(linkedList1.head);
        linkedList1.revList(linkedList1.head);
        System.out.print("\n Reversed: ");
        linkedList1.displayList(linkedList1.head);
        linkedList1.revList(linkedList1.head);
        System.out.print("\n Reversed: ");
        linkedList1.displayList(linkedList1.head);
        linkedList1.deleteHeadNode();
        System.out.print("\n Deleted head: ");
        linkedList1.displayList(linkedList1.head);
        System.out.print("\n Created new head: ");
        linkedList1.insertNodeAtHead(1);
        linkedList1.displayList(linkedList1.head);
        System.out.print("\n Deleted 3rd using position: ");
        linkedList1.deleteNodeAtPosition(3);
        linkedList1.displayList(linkedList1.head);
        System.out.print("\n inserted 3rd using position: ");
        linkedList1.insertNodeAtPosition(3, 3);
        linkedList1.displayList(linkedList1.head);
        System.out.print("\n Deleted last : ");
        linkedList1.deleteLastNode();
        linkedList1.displayList(linkedList1.head);
        System.out.print("\n inserted last using position: ");
        linkedList1.insertNodeAtPosition(5, 5);
        linkedList1.displayList(linkedList1.head);
        System.out.print("\n Size of list : " + linkedList1.size);
        System.out.print("\n ===================== ENNNDDDDD =============================");
    }
}
