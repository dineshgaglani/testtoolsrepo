package com.arrays;

import com.sorting.QuickSort;

/**
 * Created by dgaglani on 5/27/14.
 */
public class AllCombinations {


    public void printAllCombinations(int[] intArr) {
       //u either select an item or you dont for k numbers
       //equivalent of selection here is printing
        boolean[] booleanArr = new boolean[intArr.length];
        printCombinations(intArr, booleanArr, 0);
    }

    public void printCombinations(int[] intArr, boolean[] booleanArr, int index) {
        if(index == intArr.length) {
            for(int i = 0; i < intArr.length; i++) {
                if(booleanArr[i] == true) {
                    System.out.print(intArr[i] + "\t");
                }
            }
            System.out.println();
            return;
        }
        booleanArr[index] = false;
        printCombinations(intArr, booleanArr, index+1);
        booleanArr[index] = true;
        printCombinations(intArr, booleanArr, index + 1);
    }

    public static void main(String args[]) {
        AllCombinations ac = new AllCombinations();
        ac.printAllCombinations(new int[] {4,3,6,7,9,2});
    }
}
