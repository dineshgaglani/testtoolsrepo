package interviewcamp.time1;

import java.util.HashMap;
import java.util.Map;

/*
Corner cases: Only 1 node deletion
Head node deletion
adding at position 0 (head)

 //MISTAKE - During reversal set the current node's next to null after setting the nextNode's next to current (Because of toString this was turning cyclic)
 //MISTAKE - provide a function to get a node by index
 //MISTAKE - Using set instead of map in LRU cache - Problem: We need lookup to node in O(1) time, saving nodes in a set will not work since we look up the node's element
 and a set will return false if look up by a node's element, so we need to have the node's element as key and node itself as value
 //MISTAKE - Using single linked list to save for the LRU cache - We need to delete the element from the list (in case the element exists in the list and it needs to be moved to the front)
 but with a single linked list we cannot delete the element in the list without the previous node , so since a single linked list doesn't have a
 prev node, we'll use a double linked list - NOT A MISTAKE, WE CAN MOVE THE NEXT CONTENT AND DELETE THE NEXT
 MISTAKE - Made the least recently used node in the LRU Cache the tail and not head - In LRU cache make the least recently used the head since removing head is constant time and for tail we have to iterate to the
 end of the list to delete the node

 Question: Does the set in LRU cache need node<T> or just element of type T?
 Answer: It'll need node<T> because we need to access node<T> in constant time to be able to delete it. Having element of type T will mean we will have to search
 through the linked-list for the node with the element, and then delete it which will cost O(n).
 But for this we'll have to make another function in the linked list that will accept a Node rather than an element so that we insert the same node in both the set and the list

 Question: The set insert function takes in an element, now how do we call the insert function if the set contains nodes but accepts elements of type T?
 Answer: The set should be a MAP, whose key is the content and value is the node, this way the MAP can take an element and still be able to get the element associated with the element

 Question: The LRU cache gets an element of T, but we need to remove a node from the list and for removal we need to set the previous pointer's next variable, but we only will have a reference to
 the node (through the map)
 Answer: We'll need a double linked list rather than a single linked list (NOT A MISTAKE SINCE WE CAN MOVE NEXT'S DATA AND POINTER TO THIS ONE)

 */
public class SingleLinkedList<T> {

    private Integer size = 0;
    private Node head;

    @Override
    public String toString() {
        return "SingleLinkedList{" +
                "size=" + size + "}" +
                ", head=" + head;
    }

    class Node {
        Node (T data) {
            this.data = data;
        }

        T data;
        Node next;

        @Override
        public String toString() {
            String ret = "{" + data + "}";
            if (next != null) {
                ret = ret + " -> " + next;
            }
            return ret;
        }
    }

    class LRUCache {
        int MAX_SIZE = 5;
        //MISTAKE - Made Set instead of MAP.
        //Set<Node> set = new HashSet<>();
        //END Mistake

        //CORRECTION
        Map<T, Node> map = new HashMap<>();
        //END CORRECTION
        SingleLinkedList<T> list = new SingleLinkedList<>();

        public void insert(T elem) {
            Node newNode = list.new Node(elem);
            if (map.containsKey(elem)) {
                Node toRemove = map.get(elem);
                list.removeNode(toRemove);
                map.put(elem, newNode);
            } else if (list.size == MAX_SIZE) {
                // the element isn't in the list and the list is full
                Node lastNode = list.getAtN(list.size - 1);
                list.removeAtN(list.size - 1);
                map.remove(elem);
            } else {
                map.put(elem, newNode);
            }
            list.addAtN(0, newNode);
        }

        @Override
        public String toString() {
            return "LRUCache{" +
                    "list=" + list +
                    '}';
        }
    }

    public Integer getSize() {
        return size;
    }

    public Node getAtN(Integer n) {
        if (n >= size) {
            System.out.println("List only has " + size + " nodes indexed at 0, the last element can be got by getAtN(list.size - 1)");
        } else {
            Node node = head;
            int ctr = 1;
            while (ctr < n) {
                node = node.next;
                ctr++;
            }
            return node;
        }
        return null;
    }

   public Node addAtN(Integer n, Node newNode) {
        if (head == null) {
            System.out.println("Linked list has 0 nodes!");
            head = newNode;
        }
        //Corner case - adding at head
        else if (n == 0) {
            newNode.next = head;
            head = newNode;
       } else if (size < n) {
            System.out.println("Linked list has lesser nodes than " + n + ", adding node to end");
            Node node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        } else {
            System.out.println("Adding node at position " + n);
            Node nMinus1 = head;
            //Mistake, was setting counter to 0
            int ctr = 1;
            //End mistake
            while (ctr < n) {
                nMinus1 = nMinus1.next;
                ctr++;
            }
            newNode.next = nMinus1.next;
            nMinus1.next = newNode;
        }
        size++;
        System.out.println("Size is now: " + size);
        return head;
   }

   public Node removeAtN(Integer n) {
        if (head == null) {
            System.out.println("LinkedList is empty");
            return null;
        }
        //Optional condition for size 1 list
        /*else if (size == 1) {
            head = null;
        } */
        else if (size <= n) {
            System.out.println("Linked list only has " + size + " nodes and is indexed at 0, to remove last element call removeAtN(size - 1)");
        } else {
            Node nMinus1 = head;
            //Mistake - was setting counter to 0
            int ctr = 1;
            //End mistake
            while(ctr < n) {
                nMinus1 = nMinus1.next;
                ctr++;
            }
            //Corner case - last node to be removed
            if (nMinus1.next != null) {
                nMinus1.next = nMinus1.next.next;
            }
        }
        size--;
        return head;
   }

   public void removeNode(Node node) {
        if (node.next != null) {
            Node nextNode = node.next;
            node.data = node.next.data;
            node.next = nextNode.next;
            nextNode.next = null;
        } else {
            //Last node
            removeAtN(size - 1);
        }
   }

   public static void splitList(SingleLinkedList list) {
        SingleLinkedList oddList = new SingleLinkedList();
        SingleLinkedList evenList = new SingleLinkedList();

        int ctr = 0;
        SingleLinkedList.Node node = list.head;
        while (node != null) {
            if (ctr % 2 == 0) {
                evenList.addAtN(evenList.size, list.new Node(node.data));
            } else {
                oddList.addAtN(oddList.size, list.new Node(node.data));
            }
            node = node.next;
            ctr++;
        }

       System.out.println("Even list: " + evenList);
       System.out.println("Odd list: " + oddList);
   }

   public void reverse() {
        reverse(head);
   }

    private Node reverse(Node node) {
        if (node.next == null) {
            head = node;
            return node;
        }
        Node nextNode = reverse(node.next);
        nextNode.next = node;
        //MISTAKE - Because of toString not having node.next was turning cyclic
        node.next = null;
        //END MISTAKE
        return node;
    }

   public static void main(String args[]) {
       SingleLinkedList<Integer> list = new SingleLinkedList<>();
       list.addAtN(0, list.new Node(1));
       list.addAtN(1, list.new Node(2));
       list.addAtN(2, list.new Node(4));
       list.addAtN(2, list.new Node(3));
       list.addAtN(4, list.new Node(5));
       list.addAtN(5, list.new Node(6));
       System.out.println("List before delete: " + list);

       list.removeAtN(4);
       System.out.println("List after delete: " + list);

       list.removeAtN(list.size - 1);
       System.out.println("List after deleting last element: " + list);

       splitList(list);

       list.reverse();
       System.out.println("reversed: " + list);

       SingleLinkedList.LRUCache cache = list.new LRUCache();
       cache.insert(1);
       cache.insert(2);
       cache.insert(3);
       cache.insert(4);
       cache.insert(5);
       System.out.println("cache: " + cache);
       cache.insert(6);
       System.out.println("cache on inserting after full: " + cache + ", should equal [6, 5, 4, 3, 2]");
       cache.insert(3);
       System.out.println("cache on inserting existing element after full: " + cache + ", should equal [3, 6, 5, 4, 2]");
   }
}
