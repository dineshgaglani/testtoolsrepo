package interviewcamp.time1;

import java.util.ArrayList;
import java.util.List;

/*
Coin Change Problem: Given a set of coin denominations, print out the different ways you can make a target amount. You can use as many coins of each denomination as you like.

For example: If coins are [1,2,5] and the target is 5, output will be:

    [1,1,1,1,1]

    [1,1,1,2]

    [1,2,2]

    [5]

    Won't it keep printing the same combination? If yes, then why?
    It doesn't because when combination is printed eg: [1, 1, 1, 2] (here the caller function had combination [1,1,1]), we then return and we add another 1 to it which exceeds the target.
    After which we return to [1, 1, 1, 2] and then add another 2 making it [1, 1, 1, 2, 2], which again exceeds the target.
    After which we again return to add 5 making it [1, 1, 1, 2, 5], which again exceeds
    After we return the loop is done and combination starting with [1, 1, 1] has completed, which returns to the caller which then adds [1, 1, 2..] and calls this again

    Why aren't we passing currCoin - We are - but that is so that we don't print the same combination in different order again
 */
public class CoinChange {

    public static void printCoins(Integer[] coinDenoms, Integer target) {
        List<Integer> buffer = new ArrayList<>();
        Integer currSum = 0;

        printCoins(coinDenoms, target, buffer, currSum);
    }

    public static void printCoins(Integer[] coinDenoms, Integer target, List<Integer> buffer, Integer currSum) {
        if (currSum == target) {
            System.out.println(buffer);
            return;
        }
        if (currSum > target) {
            return;
        }
        for (int i = 0; i < coinDenoms.length; i++) {
            buffer.add(coinDenoms[i]);
            printCoins(coinDenoms, target, buffer, currSum + coinDenoms[i]);
            buffer.remove(buffer.size() - 1);
        }
    }

    public static void main(String[] args) {
        printCoins(new Integer[] {1, 2, 5}, 5);
    }
}
