package com.Recursion;

/**
 * Created by dgaglani on 5/23/14.
 */
public class StringReverseRecursion {

    //Caller calls using index - 1
    public void printArrayReversed(int[] arrayToPrint, int index) {
        if(index < 0) return;
        System.out.print(arrayToPrint[index]);
        printArrayReversed(arrayToPrint, index - 1);
    }


}
