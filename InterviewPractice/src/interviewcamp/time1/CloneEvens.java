package interviewcamp.time1;

import java.util.Arrays;

public class CloneEvens {

    public static Integer[] cloneEvens(Integer[] ar) {
        int rCtr = ar.length - 1;
        for (int i = ar.length - 1; i > 0; i--) {
            if (ar[i] != -1) {
                break;
            } else {
                rCtr--;
            }
        }

        int wCtr = ar.length - 1;
        while (rCtr >= 0) {
            if (ar[rCtr] % 2 == 0) {
                ar[wCtr] = ar[rCtr];
                wCtr--;
            }
            ar[wCtr] = ar[rCtr];
            wCtr--;
            rCtr--;
        }

        return ar;
    }

    public static void main (String args[]) {
        Arrays.stream(cloneEvens(new Integer[]{2, 3, 4, 5, -1, -1})).forEach(e -> System.out.print(e + ", ")); System.out.println(" should equal [2, 2, 3, 4, 4, 5] ");
        Arrays.stream(cloneEvens(new Integer[] {1, 3, 5})).forEach(e -> System.out.print(e + ", ")); System.out.println(" should equal [1, 3, 5] ");
        Arrays.stream(cloneEvens(new Integer[] {2,-1} )).forEach(e -> System.out.print(e + ", ")); System.out.println(" should equal [2, 2] ");
    }
}
