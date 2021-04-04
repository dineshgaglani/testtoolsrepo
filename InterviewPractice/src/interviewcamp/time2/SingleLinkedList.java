package interviewcamp.time2;

import java.util.HashMap;
import java.util.Map;

/*
Confusion on size and index relationship. When size is 3, does that mean adding a new element will have to be done at position 3 or 4?
In both my implementations I have done it at 3 (adding at 'size - 1' is adding at the last index). For this counter needs to be set to 0, we need to iterate to less than n - 1 (or up to n - 2)
and the set (n - 2)'s next to this new element (so it becomes the (n - 1)th position indexed at 0)
 */
public class SingleLinkedList<T> {

    Node head;
    Node tail;

    Integer size = 0;

    @Override
    public String toString() {
        return "{size" + size + "} " +
                "head=>" + head +  '}';
    }

    public Integer getSize() {
        return size;
    }

    public class LRUCache {
        Map<T, Node> accessMap = new HashMap<>();
        SingleLinkedList list = new SingleLinkedList();
        Integer MAX_SIZE = 5;

        public LRUCache(Integer MAX_SIZE) {
            this.MAX_SIZE = MAX_SIZE;
        }

        public Integer getCurrSize() {
            return list.size;
        }

        public SingleLinkedList getElements() {
            return list;
        }

        public void insert(T elem) {
            if (list.size == MAX_SIZE) {
                list.deleteAtN(0);
            }
            Node newNode = new Node(elem);
            if (accessMap.containsKey(elem)) {
                Node toDelete = accessMap.get(elem);
                list.deleteNode(toDelete);
            }
            accessMap.put(elem, newNode);
            list.append(newNode);
        }

        @Override
        public String toString() {
            return "LRUCache{" + list + '}';
        }
    }

    public class Node {
        Node next;
        T data;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            String ret = "{" + data + "}";
            if (next != null) {
                ret = ret + " -> " + next;
            } else {
                ret = ret + " <=tail";
            }

            return ret;
        }

        public T getData() {
            return data;
        }
    }

    public void append(Node node) {
        if (head == null) {
            System.out.println("Linked list is empty, adding at head");
            head = node;
        } else {
            System.out.println("Appending to end");
            tail.next = node;
        }
        tail = node;
        size++;
    }

    public void addAtN(Integer n, Node newNode) {
        if (n == size) {
            append(newNode);
            return;
        } else if (n == 0) {
            newNode.next = head;
            head = newNode;
        } else if (n < size) {
            Node nMinus1 = head;
            int ctr = 1;
            while (ctr < n - 1) {
                //Since we need to get the node prior to the position at which we insert the new node
                nMinus1 = nMinus1.next;
                ctr++;
            }
            Node nPlus1 = nMinus1.next;
            newNode.next = nPlus1;
            nMinus1.next = newNode;
        } else {
            System.out.println("Size is " + size + ", to insert node at end call addAtN(size)");
            return;
        }
        size++;
    }

    public void deleteAtN(Integer n) {
        if (n == 0) {
            deleteHead();
        } else if (n <= size) {
            //Go to the previous node of the one to delete
            Node nMinus1 = head;
            int ctr = 1;
            while (ctr < n) {
                nMinus1 = nMinus1.next;
                ctr++;
            }
            Node toDelete = nMinus1.next;
            nMinus1.next = toDelete.next;
            if (toDelete.next == null) {
                //This is the tail
                tail = nMinus1;
            }
        }
        size--;
    }

    private void deleteHead() {
        head = head.next;
    }

    public void deleteLast() {
        deleteAtN(size - 1);
    }

    public void deleteNode(Node node) {
        Node nextNode = node.next;
        if (node.next != null) {
            //MISTAKE - This approach isn't working in LRU Cache because 2 copies of the same node are resulting in repeated nodes
            node.data = nextNode.data;
            node.next = nextNode.next;
            size--;
            //MISTAKE - MISSED THIS PART - SETTING TAIL ON REMOVING HEAD IF THE REMAINING NODE IS THE LAST NODE
            if (size == 1) {
                tail = node;
            }
            //END MISTAKE
        } else {
            deleteLast();
        }
    }

    public void reverse() {
        Node reversedHead = reverse(head);
        tail = reversedHead;
    }

    public Node reverse(Node node) {
        if (node.next == null) {
            head = node;
            return node;
        }
        Node nextNode = reverse(node.next);
        nextNode.next = node;
        node.next = null;
        return node;
    }

    public T getHead() {
        return head.data;
    }

    public T getTail() {
        return tail.data;
    }

    public static void main (String args[]) {
        interviewcamp.time2.SingleLinkedList<Integer> list = new interviewcamp.time2.SingleLinkedList<>();
        list.addAtN(0, list.new Node(1));
        list.addAtN(1, list.new Node(2));
        list.addAtN(2, list.new Node(4));
        list.addAtN(3, list.new Node(3));
        list.addAtN(4, list.new Node(5));
        list.addAtN(5, list.new Node(6));
        System.out.println("List before delete: " + list);

        list.deleteAtN(4);
        System.out.println("List after delete: " + list);

        list.deleteAtN(list.size - 1);
        System.out.println("List after deleting last element: " + list);

        list.reverse();
        System.out.println("reversed: " + list);

        SingleLinkedList.LRUCache cache = list.new LRUCache(5);
        cache.insert(1);
        cache.insert(2);
        cache.insert(3);
        cache.insert(4);
        cache.insert(5);
        System.out.println("cache: " + cache);
        cache.insert(6);
        System.out.println("cache on inserting after full: " + cache + ", should equal [2,3,4,5,6]");
        cache.insert(3);
        System.out.println("cache on inserting existing element after full: " + cache + ", should equal [2,4,5,6,3]");
    }

}
