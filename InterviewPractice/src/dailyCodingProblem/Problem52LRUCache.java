package dailyCodingProblem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dinesh on 9/7/2019.
 *
 * Implement an LRU (Least Recently Used) cache. It should be able to be initialized with a cache size n, and contain the following methods:

 set(key, value): sets key to value. If there are already n items in the cache and we are adding a new item, then it should also remove the least recently used item.
 get(key): gets the value at key. If no such key exists, return null.
 Each operation should run in O(1) time.

 Mistakes: Used array as queue, wouldn't work because we need to shift the item to the top when we access it since it has to be removed last now

 Takeaways:
 Use a linked list as queue when elements that exist in between have to be moved
 Use single linked list when adding is done at tail and removing is done at head (when no removing from tail and no adding on head), during this we only need to access next
 Use double linked list when removing from tail and adding in head is needed, at this we need to access both next and prev, since we have access to prev elem of tail during removal
 */
public class Problem52LRUCache<T> {

    HashMap<T, Node> elems  = new HashMap<T, Node>();

    class Node {
        T val;
        Node next;

        Node(T val) {
            this.val = val;
        }
    }

    class Queue {
        Node head;
        Node tail;

        int size;

        int capacity;

        public void enqueue(T val) {
            Node newNode = new Node(val);
            if (size == capacity) {
                dequeue();
            }
            if (head == null) {
                head = newNode;
                tail = head;
            } else {
                tail.next = newNode;
                tail = tail.next;
            }
            size++;
            elems.put(val, newNode);
        }

        public T getVal(T val) {
            //Put the node that is being requested in the tail position
            if (elems.containsKey(val)) {
                Node nodeToMakeTail = elems.get(val);
                tail.next = nodeToMakeTail;
                tail = nodeToMakeTail;
                return val;
            }
            return null;
        }

        public T dequeue() {
            //dequeue at head
            Node nodeToDelete = head;
            head = head.next;
            elems.remove(nodeToDelete.val);
            return nodeToDelete.val;
        }

    }
}
