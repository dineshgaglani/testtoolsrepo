package arrays;

/*
Initial thoughts:
       The thought process is that we keep adding the numbers on every index, but when there is a negative number
       that will reduce the sum we already have, but if there is a bigger number than the dip caused by the negative
       number then the negative impact has been more than offsetted by this new positive integer. But including
       just this positive also will not suffice because we may be losing out on all the value provided by the
       numbers encountered prior to the negative.

    Insight is that every number we encounter we WILL have to consider it to be a part of the sum we have so far.
    If we don't consider it then we will have to start the sumSoFar from back to 0 and when we do consider it we have
    to check if including this makes our currentSum more than the maxSum. It all sounds very confusing but the point to
    remember is that THIS CURRENT VALUE HAS TO BE MADE A PART OF THE CURRENT SUM, OR THE CURRENT SUM HAS TO BE RESET.

    code: sumSoFar = Max(sumSoFar + a[i], 0) - its either 0 or it has the value we are currently at.

    Another insight after finishing the code is that we set sumSoFar to zero only when the dip has caused the value we have
    so far to have gone below 0. This makes sense because as long as we have positive value sumSoFar the elements in sumSoFar
    should be included in the maxSum because they will only increase when we add positive to positive
 */
public class MaximumSubarraySum {

    public static int getMaxSubsetSum (Integer[] nums) {
        int sumSoFar = 0, maxSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sumSoFar + nums[i] > maxSum) {
                maxSum = sumSoFar + nums[i];
            }
            sumSoFar = Math.max(sumSoFar + nums[i], 0);
        }
        return maxSum;
    }

    public static void main (String args[]) {
        System.out.println(getMaxSubsetSum(new Integer[] {-2, 4, -5, 2, 1, 3, -1, 7, -8}));
        System.out.println(getMaxSubsetSum(new Integer[] {2, 4, -1, -1, -2, -1, 1, 5, -1, 7, 8} ));
    }
}
