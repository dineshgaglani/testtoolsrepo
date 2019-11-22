package LastMinutePrep.interviewcamp.week1;

import java.util.Arrays;

/**
 * Created by Dinesh on 11/13/2019.
 */
public class Squares {

    static void squares(Integer[] input) {
        int pos = input.length - 1;
        int front = 0, back = input.length - 1;
        Integer[] output = new Integer[input.length];

        while(front < back) {

            if(Math.abs(input[front]) > Math.abs(input[back])) {
                output[pos] = input[front] * input[front];
                front++;
            } else {
                output[pos] = input[back] * input[back];
                back--;
            }

            pos--;
        }

        System.out.print(Arrays.asList(output));
    }

    public static void main(String args[]) {
        squares(new Integer[] {-4, -2, -1, 0, 3, 5});
        System.out.println(" should equal [0,1,4,9,16,25]");
    }
}
