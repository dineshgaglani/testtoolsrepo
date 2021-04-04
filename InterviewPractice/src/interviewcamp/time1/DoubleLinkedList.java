package interviewcamp.time1;

/*
Corner cases: Last node deletion : Last node deletion will not have nextNode (nPlus2)
Last node adding: Will throw NPE when setting nextNode.prev since next node is null

//MISTAKE - Missed setting newNode's prev
//MISTAKE - Checked for n less than size instead of greater than. Only if n is greater than size do we add at end
 */
public class DoubleLinkedList<T> {

    Node head;
    Node tail;
    private Integer size = 0;

    @Override
    public String toString() {
        return "size{" + size + "}" +
                "head=" + head;
    }

    class Node {
        private T data;
        Node prev;
        Node next;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            String ret = "{" + data + "}";
            if (prev != null) {
                ret = "<-" + ret;
            }
            if (next != null) {
                ret = ret + "->" + next;
            }
            return ret;
        }
    }

    public Integer getSize() {
        return size;
    }

    public void addAtN(Integer n, T data) {
        Node newNode = new Node(data);
        if (head == null) {
            System.out.println("Empty linked list");
            head = newNode;
            tail = head;
        }
        //MISTAKE - Checked for n less than size instead of greater than. Only if n is greater than size do we add at end
        else if (n > size) {
        //END MISTAKE
            System.out.println("Linked list size greater than " + n + " adding node at end ");
            tail.next = newNode;
            tail = newNode;
        } else {
            System.out.println("Adding node at position: " + n);
            Node nMinus1 = head;
            int ctr = 1; //Since we are already at head
            while (ctr < n) {
                nMinus1 = nMinus1.next;
                ctr++;
            }
            Node nPlus1 = nMinus1.next;
            nMinus1.next = newNode;
            newNode.next = nPlus1;

            //MISTAKE - Missed setting newNode's prev
            newNode.prev = nMinus1;
            //END MISTAKE
            if (nPlus1 != null) {
                nPlus1.prev = newNode;
            }
        }

        size++;
    }

    public void removeAtN(Integer n) {
        if(head == null) {
            System.out.println("Linked list empty");
        } else if (size < n) {
            System.out.println("Size of the list " + size + "is lower than " + n);
        } else {
            Node nMinus1 = head;
            int ctr = 1;
            while(ctr < n) {
                nMinus1 = nMinus1.next;
                ctr++;
            }
            Node toDelete = nMinus1.next;
            Node nPlus1 = toDelete.next;
            if (nPlus1 != null) {
                nMinus1.next = nPlus1;
                nPlus1.prev = nMinus1;
            } else {
                nMinus1.next = null;
            }

        }

        size--;
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
        //MISTAKE - missed if condition , only assign prev to null if nextNode is head, otherwise all nodes are getting set to null prev
        if(nextNode == head) {
            nextNode.prev = null;
        }
        //END MISTAKE
        node.prev = nextNode;
        node.next = null;

        return node;
    }


    public static void main(String args[]) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.addAtN(0, 1);
        list.addAtN(1, 2);
        list.addAtN(2, 4);
        list.addAtN(2, 3);
        list.addAtN(4, 5);
        list.addAtN(5, 6);
        System.out.println("List before delete: " + list);

        list.removeAtN(4);
        System.out.println("List after delete: " + list);

        list.reverse();
        System.out.println("Reversed: " + list);
    }
}
