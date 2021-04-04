package interviewcamp.time2;

import java.util.Arrays;

public class AllCombinations {

    public static void printCombinations(Integer[] arr, Integer size) {
        Integer[] buffer = new Integer[size];
        printCombinations(arr, buffer, 0, 0);
    }

    public static void printCombinations(Integer[] arr, Integer[] buffer, Integer filled, Integer start) {
        if (filled == buffer.length) {
            System.out.println(Arrays.asList(buffer));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            buffer[filled] = arr[i];
            printCombinations(arr, buffer, filled+1, i+1);
        }
    }

    public static void main (String args[]) {
        printCombinations(new Integer[] {1, 2, 3, 4, 5, 6}, 3);
    }
}
