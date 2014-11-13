package com.stacks;

import com.singlelist.LinkedList;
import com.singlelist.LinkedList.*;

/**
 * Created by dgaglani on 5/6/14.
 */
public class LinkedListStack<T extends Node> implements Stack<T>{

    T topNode;
    int stackSize = 0;

    public void push(Integer value) throws Exception {
        Node newNode = new Node(value);
        push((T)newNode);
    }

    @Override
    public void push(T newNode) throws Exception {
        if(topNode == null) {
            topNode = newNode;
        }
        else {
            newNode.next = topNode;
            topNode = newNode;
        }
        stackSize++;
    }

    @Override
    public void pop() throws Exception {
        if(topNode == null) {
            throw new Exception("Stack Empty");
        }
        stackSize--;
        topNode = (T)topNode.next;
    }

    @Override
    public void displayStackContents() {
        Node currNode = topNode;
        while(currNode != null) {
            System.out.print(currNode.value + "\t");
            currNode = currNode.next;
        }
    }


    public T peek() {
        if(topNode == null) {
            return null;
        }
        return topNode;
    }

    @Override
    public int stackSize() {
        return stackSize;
    }

    @Override
    public void emptyStack() {
        topNode = null;
        stackSize = 0;
    }

    public void reverseStack() throws Exception{
        //Get to the last node in the stack and insert into end
        if(this.stackSize == 0) {
            return;
        }
        int value = this.peek().value;
        this.pop();
        reverseStack();
        insertAtEnd(value);
    }

    private void insertAtEnd(int value) throws Exception{
        if(this.stackSize == 0) {
            this.push(value);
            return;
        }
        int poppedValue = this.peek().value;
        this.pop();
        insertAtEnd(value);
        this.push(poppedValue);
    }
}
