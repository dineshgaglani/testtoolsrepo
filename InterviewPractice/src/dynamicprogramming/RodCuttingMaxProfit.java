package dynamicprogramming;

/*
    We have a rod of size 8 which we have to sell
    The price of the rods we will sell varies by size and the difference is not linear
    A rod of size 8 could be divided into multiple pieces of size 1 to 8 and the prices of each size are: 1 5 8 9 10 17 17 20
    Our task is to find the max profit.

    Approach:
    Imagining our first cut will be at 0, we the profit for each cut will be only the profit for that cut, so at index 0
    we will launch into every position and update the profit as the corresponding value
    Now we imagine our first cut to be 1 and we launch into every other cut, compare the profit we already have on that cut
    and update it if the profit with 1 piece of size 1 and the other piece at that size is bigger than it was when the first
    cut was at 0

    Example: On our cut at 0 our table of profits will be 1 5 8 9 10 17 17 20 (following lines are indexed at 1)
    On our cut at 1 we will have 1 for position 1, 2 for position 2 since the rods would be cut as 2 1s of size 1 and since
    this is lesser we dont update the max profits array, 6 for position 3 since we will have 1 rod of size 1 and the other of size 2, 9 for
    position 4, 10 for position 5, 11 for position 6, 18 for position 7 and since this is bigger, we update the array and 18
    for position 8. So the updated array is 1 2 6 9 10 11 18 18 and since only position 7 is bigger we update that and so our
    profit array is 1 5 8 9 10 17 18 20. We then continue on for 2 where we get 0 5 6 10 13 14 15 22. from here we update
    the profit array to 1 5 8         10                               13                                  17 18 20
                             (max profit is 2 rods of size 2)  (max is 1 rod of size 2 and the other of 3)

     End Game: Our end game is that for every position we are storing the max profit we can gain from a rod (cut up or otherwise)
     at that position.

     Algorithm:
     Create a new array profits with 0 at pos 0 and values of all profits in subsequent spots
     Outer loop goes on from 1 to size of price values (i)
        inner loop from (j = i) to length of profits
            when i = 1, if (a[j] < a[i] + a[j - i]) a[j] = a[i] + a[j - i];
     Return the last value in the array

     We return the last value in the array because this HAS TO be the max, since if the max weren't in the last spot and if it
     were in one of the other spots, then it would be added with the value of one of the other spots making it higher and pushing
     it to last (not exactly the most articulate wording, but you can convince yourself of this with examples)

 */
public class RodCuttingMaxProfit {

    static int getMaxProfit(int maxSize, Integer[] profitValues) {
        //Assuming profit values has 0 for 0 length rods
        for (int i = 0; i < profitValues.length; i++) {
            for (int j = i + 1; j < profitValues.length; j++) {
                if (profitValues[j] < profitValues[i] + profitValues[j - i]) {
                    profitValues[j] = profitValues[i] + profitValues[j - i];
                }
            }
        }

        return profitValues[profitValues.length - 1];
    }

    public static void main (String args[]) {
        System.out.println(getMaxProfit(8, new Integer[] {0, 1, 5, 8, 9, 10, 17, 17, 20} ));
        System.out.println(getMaxProfit(7, new Integer[] {0, 2, 7, 8, 25, 17, 28, 30} ));
    }

}
