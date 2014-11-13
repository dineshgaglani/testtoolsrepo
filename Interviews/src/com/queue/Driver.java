package com.queue;

/**
 * Created by dgaglani on 5/9/14.
 */
public class Driver {

    public static void main(String[] args) throws Exception{
        Queue queue = new LinkedListQueue();
        queue.enqueue(1);
        queue.dequeue();
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        System.out.print("Queue Contents \t");
        queue.displayQueue();
        System.out.println();
        System.out.print("DeQueue-ing \t");
        queue.dequeue();
        System.out.print("Queue Contents \t");
        queue.displayQueue();
        System.out.println();
        System.out.print("Enqueue-ing \t");
        queue.enqueue(11);
        System.out.print("Queue Contents \t");
        queue.displayQueue();
        System.out.print("Dequeue-ing 4 times and then enqueue-ing \t");
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(12);
        queue.enqueue(13);
        queue.enqueue(14);
        queue.enqueue(15);
        System.out.print("Queue Contents \t");
        queue.displayQueue();
        System.out.println();
        System.out.print("Reversing Contents \t");
        ((LinkedListQueue)queue).reverseQueue();
        queue.displayQueue();
        System.out.println();
    }
}
