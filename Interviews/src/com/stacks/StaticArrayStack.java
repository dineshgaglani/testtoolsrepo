package com.stacks;

/**
 * Created by dgaglani on 5/6/14.
 */
public class StaticArrayStack implements Stack<Integer> {

    int[] arrayStack = new int[10];
    private int topIndex = -1;

    @Override
    public void push(Integer value) throws Exception{
        topIndex++;
         if(topIndex == arrayStack.length - 1) {
             // -1 check on top because topIndex starts from 0
             throw new Exception("Stack full");
         }
         arrayStack[topIndex] = value;
    }

    @Override
    public void pop() throws Exception{
        if(topIndex == 0) {
            throw new Exception("StackEmpty");
        }
        topIndex--;
    }

    @Override
    public void displayStackContents() {
        for(int x : arrayStack) {
            System.out.print(x + "\t");
        }
    }

    @Override
    public Integer peek() {
        return arrayStack[topIndex];
    }

    @Override
    public int stackSize() {
        return topIndex+1;
    }

    @Override
    public void emptyStack() {
        topIndex = -1;
        arrayStack = new int[10];
    }
}
