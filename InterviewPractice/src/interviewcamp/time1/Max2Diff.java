package interviewcamp.time1;

/*
Given a list of stock prices for a company, find the maximum amount of money you can makewith two trades.
 A trade is a buy and sell.The two trades cannot overlap.
 */
public class Max2Diff {

    public static Integer getMax2Diff(Integer[] stockPrices) {
        Integer[] maxUptoI = new Integer[stockPrices.length];
        Integer[] maxAfterI = new Integer[stockPrices.length];

        Integer minUptoI = Integer.MAX_VALUE;
        for (int i = 0; i < stockPrices.length; i++) {
            minUptoI = Math.min(stockPrices[i], minUptoI);
            maxUptoI[i] = stockPrices[i] - minUptoI;
        }

        Integer maxGainFromI = Integer.MIN_VALUE;
        for (int i = stockPrices.length - 1; i >= 0; i--) {
            //what I'll make if I sold something at max which I bought now.
            maxGainFromI = Math.max(stockPrices[i], maxGainFromI);
            maxAfterI[i] = maxGainFromI - stockPrices[i];
        }

        //MISTAKE - Should be value at maxAfter[i] + max(maxFromI[i + 1] to array..length)
        Integer maxTrade = Integer.MIN_VALUE;
        for (int i = 0; i < stockPrices.length; i++) {
            maxTrade = Math.max(maxTrade, maxUptoI[i] + maxAfterI[i]);
        }
        //END MISTAKE

        return maxTrade;
    }

    public static void main (String args[]) {
        System.out.println(getMax2Diff(new Integer[] {8, 14, 2, 5, 7, 3, 9, 5}) + " should equal: 7");
    }

}
