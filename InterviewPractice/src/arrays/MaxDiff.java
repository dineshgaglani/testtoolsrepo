package arrays;

/*
    Scenario for problem: stock trading, when will we have made the maximum given a list of stock prices
    Takeaway is that if we think about when should we have bought if we sell now, then we'll know the max if we sell
    at this moment
 */
public class MaxDiff {

    public static int getMaxDiff(Integer a[]) {
        /*
            Principle here is that for every element we will find the lowest before it and get the difference
            Earlier the thought process was that I would buy and then think how I could maximize the profit,
            but now the thought process is that if I sell now, when should I have bought for max profit.

             Straight forward: As we iterate and move across elements, we iterate again until we reach this to find the
             lowest and get difference between current and lowest. Complexity: n*n

             Optimized: We iterate and move across elements while keeping track of the lowest behind this, and also keep
             track of the maximum difference as we move along. Complexity: n

            How to think of the solution: instead of looking ahead, try to analyze if we can look back while we move forward.
            When to do the above: When we are trying to find the maximum while looking ahead we will have to go ahead completely
            since we haven't seen all the elements in the array, but instead, as we go ahead if we see what is minimum
            behind where we are currently since we've already passed that, then we save on time
         */
        int currLow = Integer.MAX_VALUE;
        int currMaxDiff = 0;
        for (int i = 0; i < a.length; i++) {
            currLow = Math.min(currLow, a[i]);
            currMaxDiff = Math.max(currMaxDiff, a[i] - currLow);
        }
        return currMaxDiff;

    }

    public static void main (String args[]) {
        System.out.println(getMaxDiff(new Integer[] {8, 14, 2, 9, 7, 3, 9, 5}));
        System.out.println(Math.abs(-2147483648));
    }

}
