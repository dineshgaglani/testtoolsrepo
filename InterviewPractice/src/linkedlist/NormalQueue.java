package linkedlist;

import java.util.LinkedList;

/*
    Implementing max queue:
    Have 2 queues, 1 max queue and 1 notmal queue.
    When adding an element to the queue change all values check if this is bigger than current value in the max queue
    if yes, then change all values in the max queue to this until you find a bigger value

    Why will this work: What we dequeue will be different from what we enqueue, this value will be dequeued after the
    max before this (if greater than this) and until this value is dequeued all values behind this will have this one as
    the corresponding value in max queue

    Complexity is n*n since for every new element added we will possible go over all the values of the max queue
    example: if elements are added in increasing order in the normal queue then for every addition we replace all values
    in the max queue

    Q: {1} MQ: {1}
    Q: {1, 4} MQ: {4, 4}
    Q: {1, 4, 3} MQ: {4, 4, 3}
    Q: {1, 4, 3, 6} MQ: {6, 6, 6, 6}
    Q: {1, 4, 3, 6, 2} MQ: {6, 6, 6, 6, 6}
    Q: {1, 4, 3, 6, 2, 5} MQ: {6, 6, 6, 6, 6, 5}

    Optimized: Instead of replacing if we see that the next element is smaller than the element in the queue then we
    replace that with this element until we find a bigger element, if the next element is bigger then we add it behind that element in the queue

    Q: {1} MQ: {1}
    Q: {1, 4} MQ: {4}
    Q: {1, 4, 3} MQ: {4, 3}
    Q: {1, 4, 3, 6} MQ: {6}
    Q: {1, 4, 3, 6, 2} MQ: {6, 2}
    Q: {1, 4, 3, 6, 2, 5} MQ: {6, 5}

    pseudo code:
    1. enqueue in normal queue
    2. check if element being enqueued is bigger than the back in max queue
    3. if yes, then replace elements in max queue until we find something bigger than this or queue ends (singly linked
        list, so we traverse from front)
    4. if no, then after the previous max is removed this will be the max, so put it behind that in the max queue

    Optimization takeaway: When we are repeatedly replacing elements' values see if they should be included at all
 */
public class NormalQueue {

    SingleLinkedList queueList;
    SingleLinkedList.Node front;
    SingleLinkedList.Node back;

    public NormalQueue() {
        queueList = new SingleLinkedList();
    }

    public void enqueue(Integer value) {
        SingleLinkedList.Node node = queueList.new Node(value);
        if (front == null) {
            front = node;
            //Faaltu singlelinkedlist setting
            queueList.head = front;
        } else {
            back.next = node;
        }
        back = node;
    }

    public SingleLinkedList.Node dequeue() {
        SingleLinkedList.Node node = front;
        if (front != null) {
            front = front.next;
            queueList.head = front;
        } else {
            //if front is null, then back is also null because front CANNOT exceed back
            back = null;
        }
        return node;
    }

    class MaxQueue {
        NormalQueue collectorQueue;
        NormalQueue maxQueue;

        MaxQueue() {
            collectorQueue = new NormalQueue();
            maxQueue = new NormalQueue();
        }

        void enqueue(Integer value) {
            collectorQueue.enqueue(value);
            if (maxQueue.back != null) {

                SingleLinkedList.Node elemInQueue = maxQueue.front;
                int deleteStartIndex = 0;
                while (elemInQueue != null && value < (Integer) elemInQueue.content) {
                    //Move forward until current element is smaller
                    deleteStartIndex++;
                }
                while (elemInQueue != null) {
                    //delete all remaining
                    maxQueue.queueList.removeFromList(deleteStartIndex++);
                }
            }
            //For the first value (intially and after dequeueing all)
            maxQueue.enqueue(value);
        }

        SingleLinkedList.Node dequeue() {
            SingleLinkedList.Node node = collectorQueue.dequeue();
            if (node.content == maxQueue.front.content) {
                maxQueue.dequeue();
            }
            return node;
        }

        Integer getMax() {
            return (Integer) maxQueue.front.content;
        }
    }

    /*
        Q: {1, 4} MQ: {4}
        Q: {1, 4, 3} MQ: {4, 3}
        Q: {1, 4, 3, 6} MQ: {6}
        Q: {1, 4, 3, 6, 2} MQ: {6, 2}
        Q: {1, 4, 3, 6, 2, 5} MQ: {6, 5} - dequeue now
        Q: {2, 5} MQ: {5}
    */
    public static void main(String args[]) {
        NormalQueue.MaxQueue maxQueue = new NormalQueue().new MaxQueue();
        maxQueue.enqueue(1);
        System.out.println(maxQueue.getMax() == 1);

        maxQueue.enqueue(4);
        System.out.println(maxQueue.getMax() == 4);

        maxQueue.enqueue(3);
        System.out.println(maxQueue.getMax() == 4);

        maxQueue.enqueue(6);
        System.out.println(maxQueue.getMax() == 6);

        maxQueue.enqueue(2);
        System.out.println(maxQueue.getMax() == 6);

        maxQueue.enqueue(5);
        System.out.println(maxQueue.getMax() == 6);

        maxQueue.dequeue();
        maxQueue.dequeue();
        maxQueue.dequeue();
        maxQueue.dequeue();
        System.out.println(maxQueue.getMax() == 5);
    }
}
