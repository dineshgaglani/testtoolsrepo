package arrays.doubledimensional;

import java.util.Arrays;

public class Rotate {

    private static int[][] source;

    public static void rotateExtraSpace (int size) {
        Integer[][] source = new Integer[size][size];
        Integer[][] destination = new Integer[size][size];

        int ctr = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                source[i][j] = ctr++;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                destination[j][size - 1 - i] = source[i][j];
            }
        }

        System.out.println("Source: ");
        for (int i = 0; i < source.length; i++) {
            System.out.println(Arrays.asList(source[i]));
        }

        System.out.println("Destination: ");
        for (int i = 0; i < destination.length; i++) {
            System.out.println(Arrays.asList(destination[i]));
        }

    }

    private static Integer[][] populate2DArray (int size) {
        Integer[][] pouplated = new Integer [size][size];
        int ctr = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                pouplated[i][j] = ctr++;
            }
        }
        return  pouplated;
    }

    private static Integer[][] createImageArr() {
        Integer[][] input = { {0, 0, 0, 0, 0}, {0, 0, 8, 0, 0}, {0 , 8, 8, 8, 0}, {8, 8, 8, 8, 8}, {0, 0, 0, 0, 0} };
//        Integer[][] input = { {0, 0, 0}, {1, 1, 1}, {0, 0, 0} };
        return input;
    }

    private static void displayArray (Integer[][] arr) {
        System.out.println("\n");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.asList(arr[i]));
        }
    }


    /*
        Algorithm: We rotate the array in layers going from out to in.

        Outer layer: ( i = 0 ) - i represents layer
        Assuming j = 0 and le = length - 1 j represents element in layer
        left top - [0][j] -> to right top [j][le] ----> first element of first row to last element of first row
        right top [j][le] -> right bottom [le][le - j]  ------> last element of first row to last element of last row
        right bottom [le][le - i] -> left bottom [le - i][0]  ------> last element of last row to first element of last row
        left bottom [le - i][0] -> left top [0][i]  -------> first element of last row to first element of first row
        Then increment j to move to the right on first row, lower on last column, left on last row, up on first column

        Takeaways: If the first bracket is an expression containing "i" then we move slow on row
        If first bracket is an expression containing "j" we move fast on row
        If the second bracket is an expression containing "i" we move slow in the column
        If the second bracket is an expression containing "j" we move fast in the column

        If the first bracket has an expression len - i we move backward in the row slowly
        If the first bracket has an expression len - j we move backward in the row fast
        If the second bracket has an expression len - i we move upward in the column slowly
        If the second bracket has an expression len - j we move upward in the column fast
     */
    public static void rotateNoExtraSpace(int size) {
//        Integer[][] orig = populate2DArray(size);
        Integer[][] orig = createImageArr();
        System.out.println("Source: ");
        displayArray(orig);
        for (int i = 0; i < (orig.length - 1)/2; i++) {
            for (int j = i; j < orig.length - 1 - i; j++) {
                int le = orig.length - 1;

                int temp = orig[j][le - i];
                orig[j][le - i] = orig[i][j];
                int temp2 = orig[le - i][le - j];
                orig[le - i][le - j] = temp;
                int temp3 = orig[le - j][i];
                orig[le - j][i] = temp2;
                orig[i][j] = temp3;

//            System.out.println("\nAfter rotation of outer part: \n");
//            displayArray(orig);
            }
        }

        System.out.println("After rotation of outer: ");
        displayArray(orig);
    }

    /*
        We are moving bottom up on the row (start at length and move (length - j))
        We are moving left to right on column (start at 0 and move j)
        We limit to the length of the size of zig-zag (which increases by 1 and goes upto the length of the array)
        and then we switch the order every zig-zag (which means we go right to left in col or from zig-zag size to 0 at
        the rate of (len - j) and we move from top to bottom in row which means from 0 to zig-zag size at rate (j))
     */
    public static void printZigZag() {
        Integer[][] consecArr = populate2DArray(5);
        displayArray(consecArr);
        for (int i = 0; i < consecArr.length - 1; i++) {
            for (int j = 0; j <= i; j++) {
                int row = j, col = i - j;
                if (i%2 == 1) {
                    row = i - j;
                    col = j;
                }
                System.out.print(consecArr[row][col] + "\t");
            }
            System.out.println();
        }

//        for (int i = consecArr.length - 1; i > 0; i--) {
//            for (int j = i; j > 0; j--) {
//                int row = j, col = i - j;
//                if (i%2 == 1) {
//                    row = i - j;
//                    col = j;
//                }
//                System.out.print(consecArr[row][col] + "\t");
//            }
//            System.out.println();
//        }
    }

    public static void main (String args[]) {

//        rotateExtraSpace(4);
//        rotateNoExtraSpace(4);
        printZigZag();
    }
}
