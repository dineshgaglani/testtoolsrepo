package com.arrays;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String args[] ) throws Exception {
       /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        //scanner.nextLine();
        //String[] inputArr = scanner.nextLine().split(" ");
        //a1 + (n – 1)d
       // int[] intArr = convertStringArrToIntArr(inputArr);
       // System.out.print(missingTermFromProgression(intArr, 0, intArr.length, intArr[0]));
        System.out.print(missingTermFromProgression(new int[] {1, 3, 5, 9, 11}, 0, 4, 1));
    }

    public static int[] convertStringArrToIntArr(String[] stringArray) {
        int[] intArray=new int[stringArray.length];
        int i=0;
        for(String str:stringArray){
            intArray[i]=Integer.parseInt(str);//Exception in this line
            i++;
        }
        return intArray;
    }

    public static int missingTermFromProgression(int[] progression, int start, int end, int firstElementValue) {
        int diff = progression[start + 1] - progression[start];
        int diff2and3 = progression[start + 2] - progression[start + 1];
        if(start > end) {
            return 0;
        }
        if(diff == diff2and3) {
            //find the missing term using binary search
            int mid = (start + end)/2;
            int expectedMid = (firstElementValue + ((mid) * diff));
            if(progression[mid] == expectedMid) {
                //missing element in second half
                return missingTermFromProgression(progression, mid, end, firstElementValue);
            } else {
                //missing element in first half
                return missingTermFromProgression(progression, start, mid, firstElementValue);
            }
        }
        else {
            //the missing term is either the first one or the second one
            int diff3and4 = 0;
            if(progression.length < start + 3) {
                diff3and4 = progression[start + 3] - progression[start + 2];
            }
            diff3and4 = progression[start + 3] - progression[start + 2];
            if(diff == diff3and4) {
                //third term is missing
                return (firstElementValue + ((start + 3) * diff));
            } else {
                return (firstElementValue + ((start + 2) * diff2and3));
            }
        }
    }
}
