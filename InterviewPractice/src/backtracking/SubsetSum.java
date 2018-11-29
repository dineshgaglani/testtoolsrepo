package backtracking;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {

    public static void printSubsetSum (int[] arr, int targetSumRemaining, int currIndex, List<Integer> collector) {
        if (targetSumRemaining == 0) {
            System.out.println(collector);
        }
        if (currIndex >= arr.length) {
            return;
        }
        else if (targetSumRemaining > 0) {
            //Not including this element
            printSubsetSum(arr, targetSumRemaining, currIndex + 1, collector);

            //Including this element
            collector.add(arr[currIndex]);
            printSubsetSum(arr, targetSumRemaining - arr[currIndex], currIndex + 1, collector);
            collector.remove(collector.size() - 1);
        }

    }

//    public static void printSubsetSum (int[] arr, int targetSumRemaining, int currIndex, List<Integer> collector) {
//        if (targetSumRemaining == 0) {
//            System.out.println(collector);
//            return;
//        }
//        if (currIndex >= arr.length) {
//            return;
//        }
//        if (targetSumRemaining > 0) {
//            //Including this element
//            collector.add(arr[currIndex]);
//            printSubsetSum(arr, targetSumRemaining - arr[currIndex], currIndex + 1, collector);
//
//            //Excluding this element
//            collector.remove(collector.size() - 1);
//            printSubsetSum(arr, targetSumRemaining, currIndex + 1, collector);
//        }
//
//    }

    public static void main (String args[]) {
        printSubsetSum(new int[] {1, 2, 3, 4, 5}, 5, 0, new ArrayList<Integer>());
    }

}
