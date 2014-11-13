package com.sorting;

import java.util.Arrays;

/**
 * Created by dgaglani on 5/14/14.
 */
public class MergeSort implements ISort{

    @Override
    public int[] sortArray(int[] arrayToSort) {
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

    public int[] merge(int array1[], int array2[]) {
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
}
