package linkedlist;

public class MaxQueue {

    DoubleEndedQueue normalQueue;
    DoubleEndedQueue maxQueue;

    int maxSize;

    public MaxQueue (int maxSize) {
        normalQueue = new DoubleEndedQueue();
        maxQueue = new DoubleEndedQueue();
        this.maxSize = maxSize;
    }

    public void enque(Integer content) {
        if (normalQueue.getDd().getSize() == maxSize) {
            dequeue();
        }
        normalQueue.enqueue(content);
        while (maxQueue.getDd().getSize() > 0 && maxQueue.getDd().getTail().content < content) {
            maxQueue.getDd().deleteNodeFromEnd(maxQueue.getDd().getTail().content);
        }
        maxQueue.enqueue(content);
    }

    public Integer dequeue () {
        Integer dequeuedContent = normalQueue.dequeue();
        if (maxQueue.getDd().getHead().content == dequeuedContent) {
            maxQueue.dequeue();
        }
        return dequeuedContent;
    }

    public static void main (String args[]) {
        Integer[] ints = new Integer[] { 9, 5, 4, 6, 8, 0, 1, 7};
        MaxQueue mq = new MaxQueue(3);
        for (int i = 0; i < ints.length; i++) {
            mq.enque(ints[i]);
            System.out.print(mq.maxQueue.getDd().getHead().content + "  ");
        }
    }
}
