package interviewcamp.time1;

public class Rotate2DArray {
    /*
     To move the first row to last column, we are moving fast on columns on left side and moving fast on rows on right side
     so j is second parameter (for accessing array element) on right side and first parameter on left side.
     (0, 0) moves to (0, le - 1)
     (0, 1) moves to (1, le - 1)
     (0, 2) moves to (2, le - 1)  ----> so far we have moved [0][j] to [j][le - 1]

     and after this we move second row starting at (1, 1) to second last column ending at (le - 2, le - 2) so the first
     parameter on the left moves slowly in increasing order and second parameter on right moves slowly in reverse order
     (since we are going inwards column wise starting at last column)
     so we have this --> so far we have moved [i][j] to [j][le - 1 - i] and j equals i since we start at 1,1
     ..

     To move last column to last row we are iterating fast on row on the right side so, j is the first parameter on right side
     and we are moving backward on the column on the right side , so le - 1 - j is the second parameter on the right side
     (0, le - 1) moves to (le - 1, le - 1)
     (1, le - 1) moves to (le - 1, le - 1 - 1)
     (2, le - 1) moves to (le - 1, le - 1 - 2) ---> so far we have moved [j][le - 1] to [le - 1][le - 1 - j]

     And after this we move the second last column to the second last row, so the second parameter on the left moves slowly
     and the first parameter on the right moves slowly in reverse order (since we are doing the second last column after first
     column and so on) --> so far we have moved [j][le - 1 - i] to [le - 1 - i][le - 1 - j]
     ..

     To move last row to first column we move fast in reverse on column wise on the left side and fast in reverse row wise on the right side
     (le - 1, le - 1) moves to (le - 1)(0)
     (le - 1, le - 1 - 1) moves to (le - 1 - 1)(0)
     (le - 1, le - 1 - 2) moves to (le - 1 - 2)(0)  ---> so far we have moved [le - 1, le - 1 - j] to [le - 1 - j][0]
     ..

     To move the first column to first row we move fast in reverse row wise on the left side and move fast column wise on the right
     (le - 1)(0) moves to (0)(0)
     (le - 1 - 1)(0) moves to (0)(1)
     (le - 1 - 2)(0) moves to (0)(2) ---> so far we have moved (le - 1 - j)(0) to (0)(j)
     */
    public static void rotateClockwiseBy90(Integer[][] a) {
        for (int i = 0; i < a.length/2; i++) {
            for (int j = i; j < a.length - i; j++) {
                int le = a.length - 1;

                int temp = a[j][le - i];
                a[j][le - i] = a[i][j];

                int temp2 = a[le - i][le - j];
                a[le - i][le - j] = temp;

                int temp3 = a[le - j][i];
                a[le - j][i] = temp2;

                a[i][j] = temp3;
            }
        }
    }

    public static void printInSpiralOrder(Integer[][] a) {

    }

    public static void printZigZag(Integer[][] a) {

    }
}
