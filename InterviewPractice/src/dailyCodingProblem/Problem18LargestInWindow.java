package dailyCodingProblem;

/*
Given an array of integers and a number k, where 1 <= k <= length of the array, compute the maximum values of each subarray of length k.

For example, given array = [10, 5, 2, 7, 8, 7] and k = 3, we should get: [10, 7, 8, 8], since:

10 = max(10, 5, 2)
7 = max(5, 2, 7)
8 = max(2, 7, 8)
8 = max(7, 8, 7)
Do this in O(n) time and O(k) space. You can modify the input array in-place and you do not need to store the results. You can simply print them out as you compute them.
 */

import java.rmi.server.ExportException;

public class Problem18LargestInWindow {

     class MaxHeap {

        private Integer[] heap;
        private Integer lastElem = 0;

        public MaxHeap(Integer size) {
            heap = new Integer[size];

            for (int i = 0; i < heap.length; i++) {
                heap[i] = 0;
            }
        }

        private void sink () {
            int elemToSinkIndex = 0;
            int child1Index = 1;
            int child2Index = 2;
            while (heap[elemToSinkIndex] < heap[child1Index] || heap[elemToSinkIndex] < heap[child1Index]) {
                int swapIndex = Math.max(heap[child1Index], heap[child2Index]) == child1Index ? child1Index : child2Index;
                swap(elemToSinkIndex, swapIndex);
                elemToSinkIndex = swapIndex;
                child1Index = elemToSinkIndex * 2;
                child2Index = (elemToSinkIndex * 2) + 1;
            }
        }

        private void swap(int swapIndex1, int swapIndex2) {
            int temp = heap[swapIndex1];
            heap[swapIndex1] = heap[swapIndex2];
            heap[swapIndex2] = temp;
        }

        private void swim () {
            int elemToSwimIndex = lastElem;
            if (lastElem == 0) {
                return;
            }
            int parentIndex = (elemToSwimIndex/2 - 1);
            if (parentIndex < 0) { parentIndex = 0; }
            while (heap[elemToSwimIndex] > heap[parentIndex]) {
                swap(elemToSwimIndex, parentIndex);
                elemToSwimIndex = parentIndex;
                parentIndex = parentIndex/2;
            }
        }

        public void addToHeap(int toAdd) throws Exception {
            if (lastElem != heap.length) {
                heap[lastElem] = toAdd;
                swim();
                lastElem++;
            } else {
               throw new Exception("HEAP FULL!!");
            }

        }

        public Integer peekMax() {
            return heap[0];
        }

        public Integer popMax() {
            swap(0, heap.length - 1);
            int res = heap[heap.length - 1];
            heap[heap.length - 1] = 0;
            sink();
            return res;
        }

    }

    public class LimitedMaxHeap {
        MaxHeap maxHeap;

        public LimitedMaxHeap(Integer size) {
            maxHeap = new MaxHeap(size);
        }

        public Integer addToHeap (int toAdd) throws Exception {
            //Pops the top when heap is full, peeks top otherwise
            int res = -1;
            try  {
                maxHeap.addToHeap(toAdd);
                res = maxHeap.peekMax();
            } catch (Exception e) {
                res = maxHeap.popMax();
                maxHeap.addToHeap(toAdd);
            }

            return res;
        }
    }

    public class MaxQueue {

    }

    public static void findMaxInWindow(Integer[] input, int k) throws Exception {
        Problem18LargestInWindow soln = new Problem18LargestInWindow();
        LimitedMaxHeap solnHeap = soln.new LimitedMaxHeap(k);

        for (int i = 0 ; i < k; i++) {
            solnHeap.addToHeap(input[i]);
        }

        for (int i = k ; i < input.length; i++) {
           int toPrint = solnHeap.addToHeap(input[i]);
            System.out.print(toPrint + "\t");
        }
    }


    public static void main (String args[]) throws Exception {
        Problem18LargestInWindow.findMaxInWindow(new Integer[] {10, 5, 2, 7, 8, 7}, 3);
        System.out.println( " Soln should equal : [10, 7, 8, 8] ");
    }
}
