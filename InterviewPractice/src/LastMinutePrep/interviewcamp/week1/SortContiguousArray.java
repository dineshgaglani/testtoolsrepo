package LastMinutePrep.interviewcamp.week1;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dinesh on 11/13/2019.
 */
public class SortContiguousArray {

    static List<Integer> findMaxLengthToSort(Integer[] input) {
        int frontPivot = 0;
        int backPivot = input.length - 1;

        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] > input[i + 1]) {
                frontPivot = i + 1;
                break;
            }
        }

        for (int i = input.length - 2; i >= 0; i--) {
            if(input[i] > input[i+1]) {
                backPivot = i;
                break;
            }
        }

        int start = 0;
        int end = input.length - 1;
        //Find the point where the front pivot needs to be inserted
        while(input[start] < input[frontPivot]) {
            start++;
        }

        while (input[end] > input[backPivot]) {
            end--;
        }

        return Arrays.asList(input).subList(start, end);
    }

    public static void main(String args[]) {
        System.out.println(findMaxLengthToSort(new Integer[] {0,2,3,1,8,6,9}));
    }
}
