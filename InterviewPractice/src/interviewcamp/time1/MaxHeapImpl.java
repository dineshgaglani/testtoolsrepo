package interviewcamp.time1;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxHeapImpl {

    Integer[] heap;

    public MaxHeapImpl(Integer size) {
        heap = new Integer[size];
    }

    //This is where the last element will be inserted - this is the size of the heap
    Integer lastElemIndex = -1;

    public void add(Integer elem) {
        lastElemIndex++;
        if (lastElemIndex >= heap.length) {
            removeMax();
        }
        heap[lastElemIndex] = elem;
        swim();
    }

    private void swim() {
        //MISTAKE - WAS CHANGING LAST ELEM INDEX, ASSIGN A NEW VARIABLE SINCE LASTELEMINDEX IS USED ALL OVER IN THE HEAP AND SHOULD NOT CHANGE
        Integer parentIndex = (lastElemIndex - 1)/2;
        Integer indexToSwim = lastElemIndex;
        if (parentIndex < 0) { return; }
        Util<Integer> util = new Util<>();
        while (heap[indexToSwim] > heap[parentIndex]) {
            util.swap(heap, indexToSwim, parentIndex);
            indexToSwim = parentIndex;
            parentIndex = (lastElemIndex - 1)/2;
        }
    }

    public Integer removeMax() {
        int max = heap[0];
        int toMove = heap[lastElemIndex];
        heap[lastElemIndex] = 0;
        lastElemIndex--;
        heap[0] = toMove;
        sink();
        return max;
    }

    private void sink() {
        int toSinkIndex = 0;
        Util<Integer> util = new Util<>();
        if(lastElemIndex > 0) {
            int maxChildIndex = heap[(toSinkIndex * 2) + 1] > heap[(toSinkIndex * 2) + 2] ? (toSinkIndex * 2) + 1 : (toSinkIndex * 2) + 2;
            while(heap[toSinkIndex] < heap[maxChildIndex]) {
                util.swap(heap, toSinkIndex, maxChildIndex);
                toSinkIndex = maxChildIndex;
                maxChildIndex = heap[(toSinkIndex * 2) + 1] > heap[(toSinkIndex * 2) + 2] ? (toSinkIndex * 2) + 1 : (toSinkIndex * 2) + 2;
            }
        }
    }

    public Double[] getMedian(Integer[] arr) {
        Double[] medians = new Double[arr.length];
        PriorityQueue<Integer> lowers = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -1 * o1.compareTo(o2);
            }
        });
        PriorityQueue highers = new PriorityQueue();
        for (int i = 0; i < medians.length; i++) {
            addToMedian(arr[i], lowers, highers);
            rebalance(lowers, highers);
            medians[i] = getMedianFromHeaps(lowers, highers);
        }
        return medians;
    }

    private Double getMedianFromHeaps(PriorityQueue<Integer> lowers, PriorityQueue highers) {
        PriorityQueue smaller = lowers.size() >= highers.size() ? highers : lowers;
        PriorityQueue larger = lowers.size() > highers.size() ? lowers : highers;
        if (smaller.size() == larger.size()) {
            return ((Double)smaller.peek() + (Double) larger.peek()/2);
        } else {
            return (Double)larger.peek();
        }
    }

    private void rebalance(PriorityQueue<Integer> lowers, PriorityQueue highers) {
        PriorityQueue smaller = lowers.size() >= highers.size() ? highers : lowers;
        PriorityQueue larger = lowers.size() > highers.size() ? lowers : highers;
        if (larger.size() - smaller.size() > 1) {
            smaller.add(larger.poll());
        }
    }

    private void addToMedian(Integer toAdd, PriorityQueue<Integer> lowers, PriorityQueue highers) {
        if (lowers.size() == 0 || lowers.peek() > toAdd) {
            lowers.add(toAdd);
        } else {
            highers.add(toAdd);
        }
    }
}
