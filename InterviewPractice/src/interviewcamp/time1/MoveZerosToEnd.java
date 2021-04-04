package interviewcamp.time1;

import java.util.Arrays;

public class MoveZerosToEnd {

    public static Integer[] moveZeros(Integer[] arr) {
        int zCtr = arr.length - 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 0) {
                int temp = arr[i];
                arr[i] = arr[zCtr];
                arr[zCtr] = temp;
                zCtr--;
            }
        }
        return arr;
    }

    public static void main(String args[]) {
        System.out.println(Arrays.asList(moveZeros(new Integer[] {4,2,0,1,0,3,0})) + " should be [4, 2, 1, 3, 0, 0, 0] - " +
                "order doesn't matter, zeros should be at the end");
    }
}
