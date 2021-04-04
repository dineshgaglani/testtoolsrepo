package interviewcamp.time1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/*
MISTAKE - On the getKElemMax function I am checking the size of the maxQueue before inserting into the result buffer.
This is a mistake because the result buffer may/may not have 3 elements during the third iteration of the input, this is
because we only retain the largest elements in the max queue and remove the rest (so it may will not have 3 elements if
elements in the input are in increasing order (it will have only the 3rd element and will have removed the other 2 since they are
smaller) )
 */
public class SizedMaxQueue<T extends Comparable> {

    Deque<T> mainQ;
    Deque<T> maxQ;

    Integer MAX_SIZE;

    public SizedMaxQueue(Integer MAX_SIZE) {
        mainQ = new ArrayDeque<>();
        maxQ = new ArrayDeque<>();
        this.MAX_SIZE = MAX_SIZE;
    }

    public void add(T elem) {
        if (mainQ.size() == MAX_SIZE) {
            remove();
        }
        mainQ.add(elem);
        while (!maxQ.isEmpty() && (maxQ.getLast().compareTo(elem) < 0)) {
            maxQ.removeLast();
        }
        maxQ.addLast(elem);
    }

    public T remove() {
        T  removed = mainQ.remove();
        if (removed == maxQ.getFirst()) {
            maxQ.remove();
        }
        return removed;
    }

    public T getMax() {
        return maxQ.getFirst();
    }

    public static Integer[] getKELemMax(Integer[] input, Integer k) {
        SizedMaxQueue<Integer> ksizedMaxQueue = new SizedMaxQueue<>(k);

        //MISTAKE 1
        //Integer[] op = new Integer[input.length - k];
        //END MISTAKE 1
        Integer[] op = new Integer[input.length];
        int opCtr = 0;
        for (int i = 0; i < input.length; i++) {
            ksizedMaxQueue.add(input[i]);
            //MISTAKE - NO NEED FOR THIS CONDITION
            //if (ksizedMaxQueue.maxQ.size() == k) {
            op[opCtr] = ksizedMaxQueue.getMax();
            opCtr++;
            //}
        }

        return op;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.asList(getKELemMax(new Integer[] {2, 3, 5, 6, 2, 1}, 3)) + " should equal: [2, 3, 5, 6, 6, 6]");
        System.out.println(Arrays.asList(getKELemMax(new Integer[] {6, 2, 1, 5, 3, 2}, 3)) + " should equal: [6, 6, 6, 5, 5, 5]");
    }
}
