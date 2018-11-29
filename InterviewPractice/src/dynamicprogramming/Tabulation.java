package dynamicprogramming;

import java.util.Arrays;

public class Tabulation {

    public static void fibanocci(int target) {
        Integer[] res = new Integer[target];
        int ctr = 0;
        res[ctr++] = 1;
        res[ctr++] = 1;
        while (ctr < target) {
            res[ctr] = res[ctr - 1] + res[ctr - 2];
            ctr++;
        }
        System.out.println("Result: " + Arrays.asList(res));
    }

    public static void pascalTriangle(int level) {

    }

    public static void main(String args[]) {
        fibanocci(6);
    }
}
