package LastMinutePrep.interviewcamp.week1;

import java.util.Arrays;

/**
 * Created by Dinesh on 11/13/2019.
 */
public class DutchNationalFlag {

    static void partition(Integer[] input) {
        int front = 0;
        int middle = 0;
        int back = input.length - 1;

        while (input[back] == 2) { back--; }
        while (input[front] == 0) { front++; middle++; }
        while (input[middle] == 1) { middle++; }
       //0, 0, 0, 1, 1, 2, 0, 2   ---> 0, 0, 0, 1, 1, 0, 2, 2
       // Question : How to keep track of swapped integers
       // Answer: Only when swapping with the back and i, the swapped from back is not processed, so only when swapping with the back, we should not increment i, ok vaa
        int i = front;
        while (i <= back){
            if (input[i] == 0) {
                swap(input, front, i);
                front++; i++;
            } else if (input[i] == 2) {
                swap(input, i, back);
                back--;
            } else {
                i++;
            }
        }

        System.out.print(Arrays.asList(input));
    }

    private static void swap(Integer[] input, int i, int pos) {
        int temp = input[i];
        input[i] = input[pos];
        input[pos] = temp;
    }

    public static void main(String[] args) {
        partition(new Integer[] { 1, 0, 2, 0, 1, 2, 1, 2, 1, 2 });
        System.out.println(" should equal: [0, 0, 1, 1, 1, 1, 2, 2, 2, 2]");
        partition(new Integer[] { 1, 2, 0, 0, 1, 1, 2, 1, 2 });
        System.out.println(" should equal: [0, 0, 1, 1, 1, 1, 2, 2, 2]");
        partition(new Integer[] {0, 0, 0, 1, 1, 2, 0, 2});
        System.out.println(" should equal : [0, 0, 0, 0, 1, 1, 2, 2]");
    }

}
