package linkedlist;

/*
    Mistakes I made
    Was not setting back to null after last pop (only set front to front.next which would be null)
    Have to set back also to null after lost pop because the queue has 0 elements left.
 */
public class SizedQueue<T> {

    private int maxSize;

    public SizedQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    class Node<T> {
        T value;
        Node<T> next;

        Node(T t) {
            value = t;
        }
    }

    private Node front;
    private Node back;
    private int size;

    public void push (T value) {
        if (size == maxSize) {
            pop();
        }

        Node<T> newNode = new Node(value);
        if (back == null) {
            back = newNode;
            front = back;
        } else {
            back.next = newNode;
            back = newNode;
        }
        size++;
    }

    public Node pop() {
        Node retNode = front;
        if (front != null) {
            front = front.next;
            size--;
        } else {
            //Front becomes null only after crossing back, so if it has crossed back then even back is null
            back = null;
        }
        return retNode;
    }


    public static void main(String args[]) {
        SizedQueue<Integer> sq = new SizedQueue<>(3);
        sq.push(1);
        sq.push(2);
        sq.push(3);

        SizedQueue.Node one = sq.pop();
        System.out.println((Integer)one.value == 1);

        sq.push(4);
        SizedQueue.Node two = sq.pop();
        System.out.println((Integer)two.value == 2);

        SizedQueue.Node three = sq.pop();
        SizedQueue.Node four = sq.pop();
        SizedQueue.Node nullNode = sq.pop();
        System.out.println( (Integer)three.value == 3 );
        System.out.println( (Integer)four.value == 4);
        System.out.println( nullNode == null);

        sq.push(1);
        sq.push(2);
        sq.push(3);
        sq.push(4);

        two = sq.pop();
        System.out.println( (Integer)two.value == 2 );
    }


}
