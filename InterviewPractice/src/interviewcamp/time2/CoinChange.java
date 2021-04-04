package interviewcamp.time2;

import java.util.ArrayList;
import java.util.List;

public class CoinChange {

    public static void printCoins(Integer[] denominations, Integer target) {
        List<Integer> buffer = new ArrayList<>();
        Integer currSum = 0;

        printCoins(denominations, target, buffer, currSum, 0);
    }

    public static void printCoins(Integer[] denominations, Integer target, List<Integer> buffer, Integer currSum, Integer currCoinIndex) {
        if (currSum > target) {
            return;
        }
        if (currSum == target) {
            System.out.println(buffer);
            return;
        }
        for (int i = currCoinIndex; i < denominations.length; i++) {
            buffer.add(denominations[i]);
            printCoins(denominations, target, buffer, currSum + denominations[i], i);
            buffer.remove(buffer.size() - 1);
        }
    }

    public static void main(String[] args) {
        printCoins(new Integer[] {1, 2, 5}, 5);
    }
}
