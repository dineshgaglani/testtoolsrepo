package com.stacks;

import sun.jvm.hotspot.oops.ExceptionTableElement;

/**
 * Created by dgaglani on 5/6/14.
 */
public class DynamicArrayStack implements Stack<Integer> {

    int[] dynamicArray = new int[1];
    int topIndex = -1;

    @Override
    public void push(Integer value) throws Exception {
        topIndex++;
        if(topIndex == dynamicArray.length - 1) {
            //Copy rest of the values
            int[] tempArr = new int[dynamicArray.length * 2];
            int arrCtr = 0;
            for(int x : dynamicArray) {
                tempArr[arrCtr] = x;
                arrCtr++;
            }
            dynamicArray = tempArr;
        }
        //Push value into new array
        dynamicArray[topIndex] = value;
    }

    @Override
    public void pop() throws Exception {
        if(topIndex == dynamicArray.length/2 - 1) {
            // -1 because top index begins at 0 and we have to check whether we have reached exactly half the size
            //Reduce size, copy contents
            int[] tempArr = new int[dynamicArray.length/2];
            int arrCtr = 0;
            while(arrCtr < dynamicArray.length/2 - 1) {
                tempArr[arrCtr] = dynamicArray[arrCtr];
                arrCtr++;
            }
            dynamicArray = tempArr;
        }
        if(topIndex == 0) {
            throw new Exception("Stack empty");
        }
        topIndex--;
    }

    @Override
    public void displayStackContents() {
        for(int x : dynamicArray) {
            System.out.print(x + "\t");
        }
    }

    @Override
    public Integer peek() {
        return dynamicArray[topIndex];
    }

    @Override
    public int stackSize() {
        return topIndex + 1;
    }

    public int dynamicArraySize() {
        return dynamicArray.length;
    }

    @Override
    public void emptyStack() {
        topIndex = -1;
        dynamicArray = new int[1];
    }
}
