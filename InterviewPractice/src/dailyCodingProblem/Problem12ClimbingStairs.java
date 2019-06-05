package dailyCodingProblem;

import java.util.Arrays;

/*
There exists a staircase with N steps, and you can climb up either 1 or 2 steps at a time. Given N, write a function that returns the number of unique ways you can climb the staircase. The order of the steps matters.

For example, if N is 4, then there are 5 unique ways:

1, 1, 1, 1
2, 1, 1
1, 2, 1
1, 1, 2
2, 2
What if, instead of being able to climb 1 or 2 steps at a time, you could climb any number from a set of positive integers X? For example, if X = {1, 3, 5}, you could climb 1, 3, or 5 steps at a time.
 */
public class Problem12ClimbingStairs {

    public static void main (String[] args) {
        System.out.println(getCountOfWays(4, new Integer[] {1, 2}) + " should equal 5");
    }

    private static Integer getCountOfWays(int totalSize, Integer[] denominations) {

        Integer[] allStairs = new Integer[totalSize + 1];
        for(int i = 0; i < allStairs.length; i++) {
            allStairs[i] = 0;
        }
        allStairs[0] = 1;
        for (int i = 0; i < allStairs.length; i++) {
            for(Integer denomination : denominations) {
                if (i + denomination < allStairs.length) {
                    allStairs[i + denomination] = allStairs[i + denomination] + allStairs[i];
                }
            }
        }

        return allStairs[totalSize];
    }
}
