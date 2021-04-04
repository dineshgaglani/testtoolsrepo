package interviewcamp.time1;

import java.util.Arrays;

/*
Dutch National Flag Problem: Given an array of integers A and a pivot, rearrange A in the following order:
[Elements less than pivot, elements equal to pivot, elements greater than pivot]

For example, if A = [5,2,4,1,4,6,4,4,3] and pivot = 4 -> result = [3,2,1,4,4,4,4,6,5]

Note: the order within each section doesn't matter.

Mistake made: was counting on lCtr instead of pCtr, since pCtr increments in all cases, that should be used to count.
If lCtr is used and pivot is found, lCtr doesn't increment and we check the same element repeatedly, this is fixed by using pCtr
 */
public class DutchNationalFlag {

    public static Integer[] arrange(Integer[] arr, int pivot) {
        Util<Integer> util = new Util<>();
        int pCtr = 0;
        int lCtr = 0;
        int gCtr = arr.length - 1;

        while (gCtr > pCtr) {
            if (arr[pCtr] > pivot) {
                util.swap(arr, pCtr, gCtr);
                gCtr--;
            } else if (arr[pCtr] < pivot) {
                util.swap(arr, pCtr, lCtr);
                lCtr++;
                pCtr++;
            } else if (arr[pCtr] == pivot) {
                pCtr++;
            }
        }

        return arr;
    }

    public static void main(String arg[]) {
        System.out.println(Arrays.asList(arrange(new Integer[] {5,2,4,1,4,6,4,4,3}, 4)) + " should equal  [3,2,1,4,4,4,4,6,5]");
    }
}
