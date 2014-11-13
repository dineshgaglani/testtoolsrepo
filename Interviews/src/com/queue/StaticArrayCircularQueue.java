package com.queue;

import com.queue.Queue;
import com.singlelist.LinkedList;

/**
 * Created by dgaglani on 5/8/14.
 */
public class StaticArrayCircularQueue implements Queue<Integer> {

    private final int ARRAY_SIZE = 10;
    int[] queueArray = new int[ARRAY_SIZE];
    int frontIndex = 0;
    int rearIndex = 0;

    @Override
    public void enqueue(int value) throws Exception{
        insertIntoArrayCircularly(value);
    }

    @Override
    public Integer dequeue() throws Exception {
        int value = queueArray[frontIndex];
        deleteFromArrayCircularly();
        return value;
    }

    @Override
    public int size() {
        return rearIndex - frontIndex;
    }

    @Override
    public void displayQueue() {
        int startDisplayAt = frontIndex;
        boolean isAllElementsDisplayed = false;
        if(startDisplayAt == -1){
            startDisplayAt = 0;
        }
        while(startDisplayAt != rearIndex && !isAllElementsDisplayed) {
            if(startDisplayAt == ARRAY_SIZE) {
                startDisplayAt = 0;
            }
            System.out.print("\t Index position: " + startDisplayAt + ", value at index: " + queueArray[startDisplayAt]);
            startDisplayAt++;
            if(frontIndex == startDisplayAt) {
                isAllElementsDisplayed = true;
            }
        }
    }

    private void insertIntoArrayCircularly(int value) throws Exception {
        rearIndex++;
        if(rearIndex - 1 == ARRAY_SIZE) {
            //reset rear index to 0
            rearIndex = 1;
        }

        if(rearIndex == frontIndex) {
            throw new Exception("Queue Full");
        }

        queueArray[rearIndex - 1] = value;
    }

    private void deleteFromArrayCircularly() throws Exception {
        if(frontIndex == ARRAY_SIZE) {
            //reset front index to 0
            frontIndex = 0;
        }

        if(frontIndex == rearIndex) {
            throw new Exception("Queue Empty");
        }
        frontIndex++;
    }
}
