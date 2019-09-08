package dailyCodingProblem;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Dinesh on 9/6/2019.
 *
 * Given a array of numbers representing the stock prices of a company in chronological order, write a function that calculates the maximum profit you could have made from buying and selling that stock once. You must buy before you can sell it.

    For example, given [9, 11, 8, 5, 7, 10], you should return 5, since you could buy the stock at 5 dollars and sell it at 10 dollars.
 */
public class Problem47MaxProfit {

    public static Integer getMaxProfit(Integer[] pricePerDay) {
        int minSoFar = Integer.MAX_VALUE;
        int[] profits = new int[pricePerDay.length];
        int ctr = 0;
        for (int i : pricePerDay) {
            if (i < minSoFar) {
                minSoFar = i;
            }

            profits[ctr++] = i - minSoFar;
        }

        return Arrays.stream(profits).max().getAsInt();
    }

    public static void main(String args[]) {

        Integer maxProfit = getMaxProfit(new Integer[] {9, 11, 8, 5, 7, 10} );
        System.out.println( "maxProfit is: " + maxProfit );
        System.out.println(  "test status is:" + maxProfit == 5 + "");
    }

}
