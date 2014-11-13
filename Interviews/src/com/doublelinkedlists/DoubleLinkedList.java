package com.doublelinkedlists;

/**
 * Created by dgaglani on 5/4/14.
 */
public class DoubleLinkedList {

    Node head;
    Node tail;
    int size;

    public class Node {
        Node next;
        Node previous;
        int value;

        Node(int value) {
            this.value = value;
        }
    }

    public void insertNodeAtEnd(int newValue) {
        if(head == null) {
            this.head = new Node(newValue);
            this.tail = head;
            head.next = null;
            head.previous = null;
            size++;
            return;
        }
        Node tempTail = tail;
        tail = new Node(newValue);
        tempTail.next = tail;
        tail.previous = tempTail;
        size++;
    }

    public void insertNodeAtHead(int value) {
        Node tempHead = head;
        Node newHead = new Node(value);
        newHead.next = tempHead;
        tempHead.previous = newHead;
        head = newHead;
        tempHead = null;
        size++;
    }


    public void insertNodeAtPosition(int value, int position) {
        Node nodeToInsertAfter = navigateToNode(position - 1);
        Node nodeToInsert = new Node(value);
        nodeToInsert.previous = nodeToInsertAfter;
        nodeToInsert.next = nodeToInsertAfter.next;
        nodeToInsertAfter.next = nodeToInsert;
        nodeToInsert.next.previous = nodeToInsert;
    }

    public void deleteHeadNode() {
        head = head.next;
        head.previous = null;
        size--;
    }

    public void insertNewTail(int value){
        Node newTail = new Node(value);
        tail.next = newTail;
        newTail.previous = tail;
        tail = newTail;
        size++;
    }

    public void deleteNodeAtPosition(int position) {
        Node nodeToDeleteAfter = navigateToNode(position - 1);
        Node nodeToDelete = nodeToDeleteAfter.next;
        nodeToDeleteAfter.next = nodeToDelete.next;
        nodeToDelete.next.previous = nodeToDeleteAfter;
    }

    public void deleteNextNodeFor(Node node) {

    }

    public void deleteLastNode() {
        tail.previous.next = null;
        tail = tail.previous;
        size--;
    }

    public Node navigateToNode(int position) {
        Node tempNode = head;
        int nodeCtr = 1;
        while(nodeCtr < position) {
            tempNode = tempNode.next;
            nodeCtr++;
        }
        return tempNode;
    }

    public Node revList(Node node) {
        return null;
    }

    public void displayListRecurse(Node node) {
        if(node == null) {
            System.out.println();
            return;
        }
        System.out.print(node.value + "\t");
        displayListRecurse(node.next);
    }

    public void displayListFromBackRecurse(Node node) {
        if(node == null) {
            System.out.println();
            return;
        }
        System.out.print(node.value + "\t");
        displayListFromBackRecurse(node.previous);
    }

    public static void main(String args[]) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.insertNodeAtEnd(1);
        doubleLinkedList.insertNodeAtEnd(2);
        doubleLinkedList.insertNodeAtEnd(3);
        doubleLinkedList.insertNodeAtEnd(4);
        doubleLinkedList.insertNodeAtEnd(5);
        doubleLinkedList.displayListRecurse(doubleLinkedList.head);
        doubleLinkedList.displayListFromBackRecurse(doubleLinkedList.tail);
        System.out.print("Inserting new head: " + "\t");
        doubleLinkedList.insertNodeAtHead(0);
        doubleLinkedList.displayListRecurse(doubleLinkedList.head);
        doubleLinkedList.displayListFromBackRecurse(doubleLinkedList.tail);
        System.out.print("Deleting newly created head: " + "\t");
        doubleLinkedList.deleteHeadNode();
        doubleLinkedList.displayListRecurse(doubleLinkedList.head);
        doubleLinkedList.displayListFromBackRecurse(doubleLinkedList.tail);
        System.out.print("Deleting node using position: " + "\t");
        doubleLinkedList.deleteNodeAtPosition(3);
        doubleLinkedList.displayListRecurse(doubleLinkedList.head);
        doubleLinkedList.displayListFromBackRecurse(doubleLinkedList.tail);
        System.out.print("Inserting node using position: " + "\t");
        doubleLinkedList.insertNodeAtPosition(3, 3);
        doubleLinkedList.displayListRecurse(doubleLinkedList.head);
        doubleLinkedList.displayListFromBackRecurse(doubleLinkedList.tail);
        System.out.print("deleting last node: " + "\t");
        doubleLinkedList.deleteLastNode();
        doubleLinkedList.displayListRecurse(doubleLinkedList.head);
        doubleLinkedList.displayListFromBackRecurse(doubleLinkedList.tail);
        System.out.print("Inserting new tail: " + "\t");
        doubleLinkedList.insertNewTail(5);
        doubleLinkedList.displayListRecurse(doubleLinkedList.head);
        doubleLinkedList.displayListFromBackRecurse(doubleLinkedList.tail);
        System.out.print("List Size: " + "\t" + doubleLinkedList.size);
    }
}
