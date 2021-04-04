package interviewcamp.time1;

import java.util.Arrays;
import java.util.Comparator;

/*
Longest Increasing Subsequence: Given an array of integers, find the length of the longest increasing subsequence.
For e.g, in [1, 3, 2, 5, 3, 5, 6], the longest increasing subsequence is [1, 2, 3, 5, 6] of length 5

Approach : for every index in the input I am finding which position it will be in if we go by increasing order from the start
example: for the above input the values at indexes i=1 and i=2 are 3 and 2, if we go in increasing order from i=0, both
of these can be in position 1 (1, 3 or 1, 2 - both are increasing), like this we do for all indexes.
Essentially, For every index we are finding the maximum position for it if we go by increasing order
 */
public class LongestIncreasingSubSequence {

    public static Integer findLongestIncreasingSubsequenceSize(Integer[] input) {
        Integer[] positionInLongestIncreasing = new Integer[input.length];
        Arrays.fill(positionInLongestIncreasing, 0);
        for (int i = 0; i < input.length; i++) {
            if (positionInLongestIncreasing[i] == 0) { positionInLongestIncreasing[i] = 1; }
            for(int j = i + 1; j < input.length; j++) {
                if (input[i] < input[j]) {
                    if (positionInLongestIncreasing[j] < positionInLongestIncreasing[i] + 1) {
                        positionInLongestIncreasing[j] = positionInLongestIncreasing[i] + 1;
                    }
                }
            }
        }

        return Arrays.stream(positionInLongestIncreasing).max(Comparator.comparingInt(Integer::intValue)).get();
    }

    public static void main (String args[]) {
        System.out.println(findLongestIncreasingSubsequenceSize(new Integer[] {1, 3, 2, 5, 3, 5, 6, 4}) + " should equal 5 " +
                "because [1, 2, 3, 5, 6] is the longest increasing subsequence");
        System.out.println(findLongestIncreasingSubsequenceSize(new Integer[] {1, 0, 2, 5, 3, 5, 6, 4}) + " should equal 5 " +
                "because [1, 2, 3, 5, 6] is the longest increasing subsequence");
    }
}
