package com.singlelist; /**
 * Created by dgaglani on 5/5/14.
 */

import com.singlelist.LinkedList.Node;

public class OperationsOnLists {

    LinkedList sortedList = new LinkedList();

    public void sortLists(Node list1Node, Node list2Node) {
        if(list1Node == null && list2Node == null) {
            return;
        }
        else {
            if(list1Node == null && list2Node != null) {
                sortedList.insertNodeAtEnd(list2Node.value);
                sortLists(list1Node, list2Node.next);
                return;
            }
            if(list1Node != null && list2Node == null) {
                sortedList.insertNodeAtEnd(list1Node.value);
                sortLists(list1Node.next, list2Node);
                return;
            }
            if(list1Node.value < list2Node.value) {
                sortedList.insertNodeAtEnd(list1Node.value);
                sortLists(list1Node.next, list2Node);
            }
            else {
                sortedList.insertNodeAtEnd(list2Node.value);
                sortLists(list1Node, list2Node.next);
            }
        }
    }

    public static void main(String args[]) {
        System.out.print("\n List sorting \t");
        LinkedList linkedList1 = new LinkedList();
        linkedList1.insertNodeAtEnd(1);
        linkedList1.insertNodeAtEnd(3);
        linkedList1.insertNodeAtEnd(5);
        linkedList1.insertNodeAtEnd(7);
        linkedList1.insertNodeAtEnd(9);
        LinkedList linkedList2 = new LinkedList();
        linkedList2.insertNodeAtEnd(2);
        linkedList2.insertNodeAtEnd(4);
        linkedList2.insertNodeAtEnd(6);
        linkedList2.insertNodeAtEnd(8);
        linkedList2.insertNodeAtEnd(10);
        linkedList1.insertNodeAtEnd(11);
        linkedList1.insertNodeAtEnd(12);
        linkedList2.insertNodeAtEnd(13);
        OperationsOnLists listSort = new OperationsOnLists();
        listSort.sortLists(linkedList1.head, linkedList2.head);
        listSort.sortedList.displayList(listSort.sortedList.head);
        linkedList1.interchangeNodes();
    }

    public boolean match(String stringToMatch, String pattern) {

        char[] patternChars = pattern.toCharArray();
        int patternStringCtr = 0;
        int nextToWildCharIndex = 0;
        String wildChar = "*";
        for(int stringToMatchCtr = 0; stringToMatchCtr < stringToMatch.length(); stringToMatchCtr++) {
            if(patternChars[patternStringCtr] == wildChar.toCharArray()[0]) {
                nextToWildCharIndex = patternStringCtr + 1;
                while(stringToMatch.charAt(stringToMatchCtr) != patternChars[nextToWildCharIndex] && stringToMatchCtr < stringToMatch.length()) {
                    stringToMatchCtr++;
                }
                patternStringCtr++;
            }

            if(stringToMatchCtr == stringToMatch.length()) {
                break;
            }

            if(patternChars[patternStringCtr] == stringToMatch.charAt(stringToMatchCtr)) {
                patternStringCtr++;
                stringToMatchCtr++;
            }
            else {
                if(nextToWildCharIndex == 0) {
                    return false;
                }
                patternStringCtr = nextToWildCharIndex - 1;
            }
        }
        return patternStringCtr == patternChars.length - 1;
    }
}
