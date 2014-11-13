package com.queue;

import com.singlelist.LinkedList.*;

/**
 * Created by dgaglani on 5/8/14.
 */
public interface Queue<T> {

    public void enqueue(int node) throws Exception;
    public <T>  T dequeue() throws Exception;
    public int size();
    public void displayQueue();
}
