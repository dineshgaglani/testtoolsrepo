package LastMinutePrep.interviewcamp.week1;

import java.util.Arrays;

/**
 * Created by Dinesh on 11/13/2019.
 */
public class MoveZeros {

    static void moveZerosToEnd(Integer[] input) {
        int pos = input.length - 1;
        for (int i = input.length - 1; i >= 0; i--) {
            if(input[i] == 0) { swap(input, i, pos); pos--; }
        }

        System.out.print(Arrays.asList(input));
    }

    private static void swap(Integer[] input, int i, int pos) {
        int temp = input[i];
        input[i] = input[pos];
        input[pos] = temp;
    }

    public static void main(String args[]) {
        moveZerosToEnd(new Integer[] {3,5,0,0,7});
        System.out.println(" should equal: [3, 5, 7, 0, 0]");

        moveZerosToEnd(new Integer[] {0, 0, 0, 0});
        System.out.println(" should equal: [0, 0, 0, 0]");

        moveZerosToEnd(new Integer[] {1, 0});
        System.out.println(" should equal: [1, 0]");

        moveZerosToEnd(new Integer[] {0, 1});
        System.out.println(" should equal: [1, 0]");
    }
}
