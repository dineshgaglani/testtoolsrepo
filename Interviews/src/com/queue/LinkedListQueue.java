package com.queue;

import com.singlelist.LinkedList.*;

/**
 * Created by dgaglani on 5/10/14.
 */
public class LinkedListQueue<T extends Node> implements Queue<T> {

    T frontNode;
    T rearNode;
    int queueSize;


    public void enqueue(int value) throws Exception {
        Node newNode = new Node(value);
        enqueue((T)newNode);
    }

    public void enqueue(T newNode) {
        if(rearNode != null) {
            rearNode.next = newNode;
        }
        rearNode = newNode;
        if(frontNode == null) {
            frontNode = rearNode;
        }
        queueSize++;
    }

    @Override
    public T dequeue() throws Exception {
        if(frontNode == null) {
            throw new Exception("Queue Empty!!");
        }
        Node removedNode = frontNode;
        frontNode = (T)frontNode.next;
        removedNode.next = null;
        queueSize--;
        return (T)removedNode;
    }

    @Override
    public int size() {
        return queueSize;
    }

    public void displayQueue(Node node) {
        if(node == null) {
            return;
        }
        System.out.print("\t" + node.value);
        displayQueue(node.next);
    }

    @Override
    public void displayQueue() {
        displayQueue(frontNode);
    }

    public void reverseQueue() throws Exception {
        reverseQueue(frontNode, rearNode);
    }

    private void reverseQueue(Node currNode, Node initialRearNode) throws Exception {
        if(currNode == initialRearNode) {
            return;
        } else {
            //Get reference to this node
            currNode = dequeue();
            reverseQueue(frontNode, initialRearNode);
        }
        //insert behind current rear
        enqueue((T)currNode);
    }
}
