package com.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dgaglani on 5/26/14.
 */
public class SumOfSubset {

    static List<Integer> selectedList = new ArrayList<Integer>();

    public static void sumOfSubset(int[] arr, int sumToFind, int currIndex, int currSum) {
        if(currSum == sumToFind) {
            Utils.printArray(selectedList);
            return;
        }
        if(currIndex == arr.length) {
            return;
        }
        if(selectedList.contains((Object)arr[currIndex])) {
            selectedList.remove((Object)arr[currIndex]);
        }
        sumOfSubset(arr, sumToFind, currIndex + 1, currSum);
        selectedList.add(arr[currIndex]);
        sumOfSubset(arr, sumToFind, currIndex + 1, currSum + arr[currIndex]);
        if(selectedList.contains((Object)arr[currIndex])) {
            selectedList.remove((Object)arr[currIndex]);
        }
    }

    public static void main(String args[]) {
        int[] arr = new int[] {1, 2, 5, 6, 3, 4, -1};
        sumOfSubset(arr, 7, 0, 0);
    }


}
