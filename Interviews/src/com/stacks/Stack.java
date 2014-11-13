package com.stacks;

/**
 * Created by dgaglani on 5/6/14.
 */
public interface Stack<T> {

    public void push(T value) throws Exception;
    public void pop() throws Exception;
    public void displayStackContents();
    public T peek();
    public int stackSize();
    public void emptyStack();
}
