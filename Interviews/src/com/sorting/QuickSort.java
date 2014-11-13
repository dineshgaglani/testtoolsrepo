package com.sorting;

/**
 * Created by dgaglani on 5/15/14.
 */
public class QuickSort implements ISort {

    @Override
    public int[] sortArray(int[] arrayToSort) {
        //4, 5, 0, 7, 10, 67, 1, 35, 8
        //https://www.youtube.com/watch?v=COk73cpQbFQ&index=8&list=PL2_aWCzGMAwKedT2KfDMB9YA5DgASZb3U
        //Also used to find kth largest - https://www.youtube.com/watch?v=kcVk30zzAmU&list=PLD629C50E1A85BF84&index=29
        //Also used to find all elements less than a particular number
        quickSort(arrayToSort, 0, arrayToSort.length - 1);
        System.out.print("finished");
        return arrayToSort;
    }

    public void quickSort(int[] arrayToSort, int startIndex, int endIndex) {
        if(startIndex >= endIndex) {
            return;
        }
        int pivotIndex = partition(arrayToSort, startIndex, endIndex);
        quickSort(arrayToSort, startIndex, pivotIndex - 1);
        quickSort(arrayToSort, pivotIndex, endIndex);
    }

    public int partition(int[] arrayToSort, int startIndex, int endIndex) {
        int pivot = arrayToSort[endIndex];
        int pivotIndex = startIndex;
        for(int i = startIndex; i < endIndex; i++) {
            if(arrayToSort[i] < pivot) {
                swap(arrayToSort, i, pivotIndex);
                pivotIndex++;
            }
        }
        swap(arrayToSort, pivotIndex, endIndex);
        return pivotIndex;
    }

    public void swap(int[] arrayToSwapIn, int indexA, int indexB) {
        if(arrayToSwapIn[indexA] != arrayToSwapIn[indexB]) {
            Integer temp = arrayToSwapIn[indexA];
            arrayToSwapIn[indexA] = arrayToSwapIn[indexB];
            arrayToSwapIn[indexB] = temp;
        }

    }
}
