package linkedlist;

public class DoubleEndedQueue {

    private DoubleLinkedList dd;

    public DoubleLinkedList getDd() {
        return dd;
    }

    public DoubleEndedQueue () {
        dd = new DoubleLinkedList();
    }

    public void enqueue (Integer content) {
        dd.addNodeAtEnd(content);
    }

    public Integer dequeue() {
        return dd.deleteNode(dd.getHead().content).content;
    }
}
