package dailyCodingProblem;

/**
 * Created by Dinesh on 9/6/2019.
 *
 * This problem was asked by Amazon.

 Given an array of numbers, find the maximum sum of any contiguous subarray of the array.

 For example, given the array [34, -50, 42, 14, -5, 86], the maximum sum would be 137, since we would take elements 42, 14, -5, and 86.

 Given the array [-5, -1, -8, -9], the maximum sum would be 0, since we would not take any elements.

 [34, -30, 42, 14, -5, 86] --> Add all of them
 */
public class Problem49MaxSubArray {

    public static int getMaxSubArray(Integer[] vals) {
        int sumSoFar = 0;
        int maxSum = 0;
        for (int v : vals) {
            sumSoFar = Math.max(0, sumSoFar + v); //Only if sumSoFar dips below zero do we lose sumSoFar
            // Why not Math.max(sumSoFar, sumSoFar + v);  -----> Because at position 2, we have -50, and after this sumSoFar will have 34, but we can't use 34 in the next step if we aren't using -50
            //Why not 'if (sumSoFar < sumSoFar + v) {sumSoFar = v}'  ----> Because we want to retain prior values, they may be useful ahead. If we do this, we lose everything in sumSoFar before this
            maxSum = Math.max(sumSoFar, maxSum);
        }
        return maxSum;
    }

    public static void main (String[] args){
        Integer max = getMaxSubArray(new Integer[] {255, -235, 34, -50, 42, 14, -5, 86} );
        System.out.println(max);
    }

}
