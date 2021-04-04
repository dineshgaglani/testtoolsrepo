package interviewcamp.time1;

import interviewcamp.time2.SingleLinkedList;

import java.util.*;

/*
Smallest Subarray Covering All Values: Let's say you are given a large text document Doc. You are also given a set S of words.
You want to find the smallest substring of Doc that contains all the words in S. For example:

S: ["and", "of", "one"]

Doc: "a set of words that is complete in itself, typically containing a subject and predicate, conveying a statement,
question, exclamation, or command, and consisting of a main clause and sometimes one or more subordinate clauses"

The "of a main clause and sometimes one or more subordinate clauses" part above is the solution. Note that the order
in which the words appear doesn't matter. Also, the length of the substring is in terms of number of characters.

Approach: We need a structure where elements are stored in-order of entry and can be looked up in constant time. Also the structure
needs to be of fixed size and we should be able to access last element in constant time. Given such a structure we can
read through the main string and when we see an element from the search string in the main string we can save it into the cache.
As the cache encounters more elements it will have the oldest element as the last node and after it is full we know that we
have encountered all elements of the search string, now we take the smallest difference between the first and last nodes in the cache and
that will be our smallest substring with all elements

MISTAKE - The key for the LRUCache needs to be the term and the value needs to be both term and occurrence
 */
public class SmallestSubarrayWithAllValues {

    class SearchTerm {
        String term;
        Integer lastIndex;

        @Override
        public String toString() {
            return "SearchTerm{" +
                    "term='" + term + '\'' +
                    ", lastIndex=" + lastIndex +
                    '}';
        }
    }

    class LRUCache<T> {
        SingleLinkedList<SearchTerm> list = new SingleLinkedList();
        Map<T, SingleLinkedList.Node> termsMap = new HashMap<>();
        Integer MAX_SIZE = 0;

        public LRUCache(Integer MAX_SIZE) {
            this.MAX_SIZE = MAX_SIZE;
        }

        public void insert(T elem, SingleLinkedList.Node newNode) {
            if (list.getSize() == MAX_SIZE) {
                list.deleteAtN(0);
            }
            if (termsMap.containsKey(elem)) {
                SingleLinkedList.Node toDelete = termsMap.get(elem);
                list.deleteNode(toDelete);
            }
            list.append(newNode);
            termsMap.put(elem, newNode);
        }
    }

    public String getSubstringWithAllValues(String mainString, List<String> searchTerms) {
        SingleLinkedList<SearchTerm> list = new SingleLinkedList<>();
        LRUCache lruCache = new LRUCache(searchTerms.size());

        String[] mainTerms = mainString.split(" ");
        Integer minStart = 0;
        Integer minEnd = 0;
        for (int i = 0; i < mainTerms.length; i++) {
            if (searchTerms.contains(mainTerms[i])) {
                SearchTerm st = new SearchTerm();
                st.term = mainTerms[i];
                st.lastIndex = i;
                SingleLinkedList.Node node = list.new Node(st);
                lruCache.insert(st.term, node);

                if (lruCache.list.getSize() >= searchTerms.size()) {
                    //List is full, subtract the first element's occurrence in the cache with that of the last element's occurrence
                    System.out.println(lruCache.list);
                    if (((SearchTerm)lruCache.list.getTail()).lastIndex - ((SearchTerm)lruCache.list.getHead()).lastIndex < minEnd - minStart) {
                        minEnd = ((SearchTerm)lruCache.list.getTail()).lastIndex;
                        minStart = ((SearchTerm)lruCache.list.getHead()).lastIndex;
                    }
                }
            }
        }
        return mainString.substring(minStart, minEnd);
    }

    public static void main(String[] args) {
        SmallestSubarrayWithAllValues s = new SmallestSubarrayWithAllValues();
        s.getSubstringWithAllValues("a set of words that is complete in itself, typically containing a " +
                "subject and predicate, conveying a statement, question, exclamation, or command, and consisting of a " +
                "main clause and sometimes one or more subordinate clauses", Arrays.asList(new String[] {"and", "of", "one"}));

    }
}
