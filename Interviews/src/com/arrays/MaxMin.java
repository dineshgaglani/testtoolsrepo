package com.arrays;

/**
 * Created by dgaglani on 5/25/14.
 */
public class MaxMin {

    public int findMin(int[] array, int startIndex, int endIndex) {
        int min = Integer.MAX_VALUE;
        if(startIndex == endIndex) {
            min = array[startIndex];
        }
        else if(startIndex == endIndex - 1) {
            if(array[startIndex] > array[endIndex]) {
                min = array[endIndex];
            }
            else {
                min = array[startIndex];
            }
        }
        else{
            int mid = (startIndex + endIndex)/2;
            min = findMin(array, startIndex, mid);
            int min2 = findMin(array, mid+1, endIndex);
            min = min < min2 ? min : min2;
        }
        return min;

    }

    public static void main(String args[]) {
        MaxMin min = new MaxMin();
        int[] array = new int[] {3,5,1,6,8,90, 43, 57, 89};
        min.findMin(array, 0, array.length - 1);
    }
}
