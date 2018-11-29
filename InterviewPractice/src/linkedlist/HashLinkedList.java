package linkedlist;

import java.util.HashMap;
import java.util.Map;

/*
    Every element in a hashmap has a reference to a queue element, when the node is accessed again it moves to the front
    of the queue so the next element popped is the most recent one.
    This program implements LRU cache
 */
public class HashLinkedList<T> {

    private int maxSize;
    private Map<T, Node> elems = new HashMap();

    public HashLinkedList(Integer size) {
        this.maxSize = size;
    }

    class Node<T> {
        Node next;
        T value;

        public Node (T value) {
            this.value = value;
        }
    }

    private Node front;
    private Node back;

    public void deleteNode(Node node) {
        node.value = node.next.value;
        node.next = node.next.next;
    }

    public void push (T value) {
        if (elems.size() >= maxSize) {
            pop();
        }

        if (elems.containsKey(value)) {
            Node elemNode = elems.get(value);
            deleteNode(elemNode);
            elems.remove(value);
        }
        Node newNode = new Node(value);

        if (back == null) {
            back = newNode;
            front = back;
        } else {
            back.next = newNode;
            back = newNode;
        }
        elems.put(value, newNode);
    }

    public T peek() {
        //The most recent element is peeked.
        return (T)back.value;
    }

    public Node pop () {
        Node retNode = front;
        if (front != null) {
            elems.remove(front.value);
            front = front.next;
        } else {
            back = null;
        }
        return retNode;
    }

    public static void main (String args[]) {
        HashLinkedList<Integer> sq = new HashLinkedList<>(3);
        sq.push(1);
        sq.push(2);
        sq.push(3);

        HashLinkedList.Node one = sq.pop();
        System.out.println((Integer)one.value == 1);

        sq.push(4);
        HashLinkedList.Node two = sq.pop();
        System.out.println((Integer)two.value == 2);

        HashLinkedList.Node three = sq.pop();
        HashLinkedList.Node four = sq.pop();
        HashLinkedList.Node nullNode = sq.pop();
        System.out.println( (Integer)three.value == 3 );
        System.out.println( (Integer)four.value == 4);
        System.out.println( nullNode == null);

        sq.push(1);
        sq.push(2);
        sq.push(3);
        Integer recent = sq.peek();
        System.out.println(recent == 3);

        sq.push(4);
        sq.push(2);
        recent = sq.peek();
        System.out.println( recent == 2 );

        three = sq.pop();
        System.out.println( (Integer)three.value == 3 );
    }
}
