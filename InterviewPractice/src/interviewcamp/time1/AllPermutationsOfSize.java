package interviewcamp.time1;

import java.util.Arrays;

public class AllPermutationsOfSize {

    public static void printPermutations(Integer arr[], Integer size) {
        Integer[] buffer = new Integer[size];
        Boolean[] isPresent = new Boolean[arr.length];
        Arrays.fill(isPresent, false);

        printPermutations(arr, buffer, 0, isPresent);
    }

    public static void printPermutations(Integer[] arr, Integer[] buffer, Integer filled, Boolean[] isPresent) {
        if (filled == buffer.length) {
            System.out.println(Arrays.asList(buffer));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!isPresent[i]) {
                buffer[filled] = arr[i];
                isPresent[i] = true;
                printPermutations(arr, buffer, filled + 1, isPresent);
                isPresent[i] = false;
            }
            //<Mistake> - This should be inside if. With outside, we have repeating results //TODO - Analyze mistake
            //<Explanation> - When outside, we don't populate the position and move on resulting in null on that position.
            //printPermutations(arr, buffer, filled + 1, isPresent);

            //<Mistake> - Missed this line - if this line wasn't there then after i-th elem is used for a slot, it will never be used again
            //isPresent[i] = false;
        }
    }

    public static void main (String[] args) {
        printPermutations(new Integer[] {1, 2, 3, 4, 5}, 2);
    }
}
