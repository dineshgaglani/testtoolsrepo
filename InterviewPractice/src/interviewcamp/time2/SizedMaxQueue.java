package interviewcamp.time2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SizedMaxQueue<T extends Comparable> {

    Deque<T> mainQueue;
    Deque<T> maxQueue;

    Integer MAX_SIZE;

    public SizedMaxQueue(Integer MAX_SIZE) {
        mainQueue = new ArrayDeque<>();
        maxQueue = new ArrayDeque<>();

        this.MAX_SIZE = MAX_SIZE;
    }

    public void add(T elem) {
        if(mainQueue.size() == MAX_SIZE) {
            remove();
        }
        mainQueue.add(elem);
        while (!maxQueue.isEmpty() && maxQueue.getLast().compareTo(elem) < 0) {
            maxQueue.removeLast();
        }
        maxQueue.add(elem);
    }

    public T remove() {
        T removed = mainQueue.remove();
        if (maxQueue.getFirst() == removed) {
            maxQueue.remove();
        }
        return removed;
    }

    public T getMax() {
        return maxQueue.getFirst();
    }

    public static Integer[] getKElemMax(Integer[] ip, Integer k) {
        SizedMaxQueue<Integer> sizedMaxQueue = new SizedMaxQueue<>(k);
        Integer[] op = new Integer[ip.length];
        for (int i = 0; i < ip.length; i++) {
            sizedMaxQueue.add(ip[i]);
            op[i] = sizedMaxQueue.getMax();
        }

        return op;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.asList(getKElemMax(new Integer[] {2, 3, 5, 6, 2, 1}, 3)) + " should equal: [2, 3, 5, 6, 6, 6]");
        System.out.println(Arrays.asList(getKElemMax(new Integer[] {6, 2, 1, 5, 3, 2}, 3)) + " should equal: [6, 6, 6, 5, 5, 5]");
    }
}
