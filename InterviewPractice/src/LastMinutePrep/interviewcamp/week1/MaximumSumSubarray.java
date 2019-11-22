package LastMinutePrep.interviewcamp.week1;

/**
 * Created by Dinesh on 11/13/2019.
 */
public class MaximumSumSubarray {

    static int getMaxSubsetSum(Integer[] input) {
        //As long as sumSoFar > 0, we accumulate to it
        int maxSum = 0, sumSoFar = 0;
        for (int i = 0; i < input.length; i++) {
            if (sumSoFar + input[i] > 0) {
                sumSoFar = sumSoFar + input[i];
            } else {
                sumSoFar = 0;
            }
            maxSum = Math.max(sumSoFar, maxSum);
        }
        return maxSum;
    }

    public static void main(String arg[]) {
        System.out.println(getMaxSubsetSum(new Integer[] {1, 2, 3, -4, 1} ) + " should equal: 6" );
        System.out.println(getMaxSubsetSum(new Integer[] {1, 2, 3, -1, 4} ) + " should equal: 9" );
        System.out.println(getMaxSubsetSum(new Integer[] {-2, -3, 4, -1, -2, 1, 5, -1} ) + " should equal: 7" );
    }

}
