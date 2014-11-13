package com.searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dgaglani on 5/16/14.
 */
public class PairSumSearch {

    public static List<String> getPairsThatAddTo(int sum, int[] intArray) {
        List<String> pairsList = new ArrayList<String>();
        int[] sortedArray = sortArray(intArray);
        for(int arrCtr = 0; arrCtr < sortedArray.length; arrCtr++) {
            int difference = sum - sortedArray[arrCtr];
            int pairIndex = binarySearch(sortedArray, arrCtr + 1, sortedArray.length - 1, difference);
            if(pairIndex != -1) {
                pairsList.add(arrCtr + ", " + pairIndex);
                pairsList.add(pairIndex + ", " + arrCtr);
            }
        }
        return pairsList;
    }

    public static int binarySearch(int[] arrayToSearch, int startIndex, int endIndex, int itemToSearch) {
        if(startIndex < endIndex){
            int midIndex = (endIndex + startIndex)/2;
            if(itemToSearch == arrayToSearch[midIndex]) {
                return midIndex;
            } else if(itemToSearch > arrayToSearch[midIndex]) {
                return binarySearch(arrayToSearch, midIndex + 1, endIndex, itemToSearch);
            } else {
                return binarySearch(arrayToSearch, startIndex, midIndex, itemToSearch);
            }
        }
        else return -1;
    }

    public static int[] sortArray(int[] arrayToSort) {
        //4, 5, 7, 0, 10, 67, 1, 35
        int subArraySize = arrayToSort.length/2;
        int[] retArr;
        if(subArraySize >= 1) {
            int[] leftArr = Arrays.copyOfRange(arrayToSort, 0, subArraySize);
            int[] rightArr = Arrays.copyOfRange(arrayToSort, subArraySize, arrayToSort.length);

            leftArr = sortArray(leftArr);
            rightArr = sortArray(rightArr);
            retArr = merge(leftArr, rightArr);
            return retArr;
        } else {
            return arrayToSort;
        }

    }

    public static int[] merge(int array1[], int array2[]) {
        int array1Ctr = 0;
        int array2Ctr = 0;
        int retArrayCtr = 0;
        int[] retArray = new int[array1.length + array2.length];
        while (array1Ctr < array1.length && array2Ctr < array2.length) {
            if(array1[array1Ctr] < array2[array2Ctr]) {
                retArray[retArrayCtr] = array1[array1Ctr];
                array1Ctr++;
            } else {
                retArray[retArrayCtr] = array2[array2Ctr];
                array2Ctr++;
            }
            retArrayCtr++;
        }
        if(array1Ctr < array1.length) {
            while (array1Ctr < array1.length) {
                retArray[retArrayCtr] = array1[array1Ctr];
                retArrayCtr++;
                array1Ctr++;
            }
        }
        else {
            while (array2Ctr < array2.length) {
                retArray[retArrayCtr] = array2[array2Ctr];
                retArrayCtr++;
                array2Ctr++;
            }
        }
        return retArray;
    }

    public static void main(String args[]) {
        List<String> pairsList = getPairsThatAddTo(5, new int[] {4,3,5,2,1,7});
        for(String pair : pairsList) {
            System.out.print(pair + "\t");
        }

    }
}
