package interviewcamp.time1;

import java.util.ArrayList;
import java.util.List;

/*
Given an array of integers, find the continuous subarray, which when sorted, results in the entire array being sorted. For example: A = [0,2,3,1,8,6,9], result is the subarray [2,3,1,8,6]
 */

/*
Mistake : Had to take min and max from dip index to bump index and then find spots where min and max had to go
 */
public class SubarraySort {

    public static List<Integer> sortSubarray(Integer[] arr) {
        Integer dipIndex = -1;
        Integer bumpIndex = -1;

        Integer dipSpotIndex = -1;
        Integer bumpSpotIndex = -1;

        List<Integer> ret = new ArrayList();

        Integer prev = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < prev) {
                dipIndex = i;
                break;
            }
            prev = arr[i];
        }
        if (dipIndex == -1) { return null; }

        Integer next = arr[arr.length - 1];
        for (int i = arr.length - 2; i >=0; i--) {
            if (arr[i] > next) {
                bumpIndex = i;
                break;
            }
            next = arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[dipIndex] < arr[i]) {
                dipSpotIndex = i;
                break;
            }
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[bumpIndex] > arr[i]) {
                bumpSpotIndex = i;
                break;
            }
        }

        for (int i = dipSpotIndex ; i < bumpSpotIndex; i++) {
            ret.add(arr[i]);
        }

        return ret;
    }

    public static void main(String[] args){
        System.out.println(sortSubarray(new Integer[] {0,2,3,1,8,6,9}) + " should equal [2,3,1,8,6]");
    }
}
