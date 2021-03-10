package amazon;

import java.util.Arrays;

/*
Given a 2D grid, each cell is either a zombie or a human. Zombies can turn adjacent (up/down/left/right) human beings into zombies every day. Find out how many days does it take to infect all humans?

Input:
matrix, a 2D integer array where a[i][j] = 1 represents a zombie on the cell and a[i][j] = 0 represents a human on the cell.

Output:
Return an integer represent how many days does it take to infect all humans.
Return -1 if no zombie exists.

Example :
Input:
[[0, 1, 1, 0, 1],
[0, 1, 0, 1, 0],
[0, 0, 0, 0, 1],
[0, 1, 0, 0, 0]]

Output:
2

Explanation:
At the end of the day 1, the status of the grid:
[[1, 1, 1, 1, 1],
[1, 1, 1, 1, 1],
[0, 1, 0, 1, 1],
[1, 1, 1, 0, 1]]

At the end of the day 2, the status of the grid:
[[1, 1, 1, 1, 1],
[1, 1, 1, 1, 1],
[1, 1, 1, 1, 1],
[1, 1, 1, 1, 1]]
 */
public class ZombieMatrix {

    public static boolean isZeroExists(Integer[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Integer getDays(Integer[][] initialMatrix) {
        Integer daysToComplete = 0;
        while(isZeroExists(initialMatrix)) {
            System.out.println("After " + daysToComplete + " days: ");
            for (int k = 0; k < initialMatrix.length; k++) {
                Arrays.stream(initialMatrix[k]).forEach(v -> System.out.print(v + " "));
                System.out.println();
            }
            for (int i = 0; i < initialMatrix.length; i++) {
                for (int j = 0; j < initialMatrix[0].length; j++) {
                    if (initialMatrix[i][j] == 1) {
                        markLeft(initialMatrix, i, j);
                        markRight(initialMatrix, i, j);
                        markTop(initialMatrix, i, j);
                        markBottom(initialMatrix, i, j);
                    }
                }
            }
            //Turn -1s to 1s
            for (int i = 0; i < initialMatrix.length; i++) {
                for (int j = 0; j < initialMatrix[0].length; j++) {
                    if (initialMatrix[i][j] == -1) {
                        initialMatrix[i][j] = 1;
                    }
                }
            }
            daysToComplete++;
        }

        return daysToComplete;

    }

    private static void markBottom(Integer[][] initialMatrix, int i, int j) {
        if (i < initialMatrix.length - 1) {
            if (initialMatrix[i+1][j]  != 1) {
                initialMatrix[i+1][j] = -1;
            }
        }
    }

    private static void markTop(Integer[][] initialMatrix, int i, int j) {
        if (i > 0) {
            if (initialMatrix[i-1][j]  != 1) {
                initialMatrix[i-1][j] = -1;
            }
        }
    }

    private static void markRight(Integer[][] initialMatrix, int i, int j) {
        if (j < initialMatrix[0].length - 1) {
            if (initialMatrix[i][j+1]  != 1) {
                initialMatrix[i][j+1] = -1;
            }
        }
    }

    private static void markLeft(Integer[][] initialMatrix, int i, int j) {
        if (j > 0) {
            if (initialMatrix[i][j-1]  != 1) {
                initialMatrix[i][j-1] = -1;
            }
        }
    }

    public static void main (String args[]) {
        Integer[] r1 = new Integer[] {0, 1, 1, 0, 1};
        Integer[] r2 = new Integer[] {0, 1, 0, 1, 0};
        Integer[] r3 = new Integer[] {0, 0, 0, 0, 1};
        Integer[] r4 = new Integer[] {0, 1, 0, 0, 0};

        System.out.println(getDays(new Integer[][] {r1, r2, r3, r4}));
    }
}
