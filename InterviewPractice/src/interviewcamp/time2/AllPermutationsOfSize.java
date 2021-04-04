package interviewcamp.time2;

import java.util.Arrays;

public class AllPermutationsOfSize {

    public static void printPermutations(Integer[] arr, Integer size) {
        Integer[] buffer = new Integer[size];
        Boolean[] isFilled = new Boolean[arr.length];
        Arrays.fill(isFilled, false);

        printPermutations(arr, buffer, 0, isFilled);
    }

    public static void printPermutations(Integer[] arr, Integer[] buffer, Integer filled, Boolean[] isFilled) {
        if (filled == buffer.length) {
            System.out.println(Arrays.asList(buffer));
            return;
        }
        for(int i = 0; i < arr.length; i++) {
            if (!isFilled[i]) {
                buffer[filled] = arr[i];
                isFilled[i] = true;
                printPermutations(arr, buffer, filled + 1, isFilled);
                isFilled[i] = false;
            }
        }

    }

    public static void main (String[] args) {
        printPermutations(new Integer[] {1, 2, 3, 4, 5}, 2);
    }
}
