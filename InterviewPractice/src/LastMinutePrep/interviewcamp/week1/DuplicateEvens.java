package LastMinutePrep.interviewcamp.week1;

import java.util.Arrays;

/**
 * Created by Dinesh on 11/13/2019.
 */
public class DuplicateEvens {

    static void duplicateEvens(Integer[] input, int endIndex) {
        int pos = input.length - 1;
        for (int i = endIndex - 1; i >= 0; i--) {
            if(input[i]%2 == 0) { input[pos] = input[i]; input[pos-1] = input[i]; pos--;}
            else { input[pos] = input[i]; }
            pos--;
        }
        System.out.print(Arrays.asList(input));
    }

    public static void main(String args[]) {
        duplicateEvens(new Integer[] {1, 2, 3, 4, 5, -1, -1}, 5);
        System.out.println(" should equal [1, 2, 2, 3, 4, 4, 5]");

        duplicateEvens(new Integer[] {1, 2, 3, 4, 5, 6, -1, -1, -1}, 6);
        System.out.println(" should equal [1, 2, 2, 3, 4, 4, 5, 6, 6]");

        duplicateEvens(new Integer[] {2, -1}, 1);
        System.out.println(" should equal [2, 2]");
    }
}
