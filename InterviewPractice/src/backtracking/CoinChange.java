package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinChange {

    public static void printDenominations(int targetDenominationRemaining, Integer[] denominations, List<Integer> buffer, int currDenomination) {
        if (targetDenominationRemaining == 0) {
            System.out.println(buffer);
            return;
        }

            while(targetDenominationRemaining > 0 && currDenomination < denominations.length) {
                //Choose current denomination and explore further
                buffer.add(denominations[currDenomination]);
                printDenominations(targetDenominationRemaining - denominations[currDenomination], denominations, buffer, currDenomination);
                buffer.remove(buffer.size() - 1);
                currDenomination++;
            }


    }

    public static void main(String args[]) {
        printDenominations(4, new Integer[] {1, 2, 3, 4}, new ArrayList<>(), 0);
    }

}
