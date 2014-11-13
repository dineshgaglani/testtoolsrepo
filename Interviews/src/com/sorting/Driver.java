package com.sorting;

/**
 * Created by dgaglani on 5/14/14.
 */
public class Driver {

    public static void main(String args[]) {
        ISort sorter = new QuickSort();
        sorter.sortArray(new int[] {4, 5, 0, 7, 10, 67, 1, 35, 8});
    }
}
