package interviewcamp.time1;

/*
(Level: Medium) Given a list of stock prices for a company, find the maximum amount of money you could have made with one trade (one buy/sell). For example, if A = [2,3,1,4,5,7,5,4],
the max money with a single trade is 6, if you buy at 1 and sell at 7.
 */
public class MaxDiff {

    public static Integer getMaxDiff(Integer[] stockPrices) {
        Integer minSoFar = Integer.MAX_VALUE;
        Integer maxDiffAti = Integer.MIN_VALUE;

        for(int i = 0; i < stockPrices.length; i++) {
            minSoFar = Math.min(stockPrices[i], minSoFar);
            maxDiffAti = Math.max(maxDiffAti, stockPrices[i] - minSoFar);
        }

        return maxDiffAti;
    }

    public static void main(String args[]) {
        System.out.println(getMaxDiff(new Integer[] {8, 14, 2, 5, 7, 3, 9, 5}) + " should equal: 7");
    }
}
