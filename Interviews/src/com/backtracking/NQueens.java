package com.backtracking;

/**
 * Created by dgaglani on 7/12/14.
 */
public class NQueens {

    static int nSize = 8;
    static int[] positions = new int[nSize];

    public static boolean place(int rowNum, int currentColumn) {
        /*
            Checks all rows to find the position at which queen should be placed in this particular row.
         */
        for(int i = 0; i < rowNum; i++) {
            if(positions[i] == currentColumn || Math.abs(i - rowNum) == Math.abs(positions[i] - currentColumn)) {
                return false;
            }
        }
        positions[rowNum] = currentColumn;
        return true;
    }

    public static void nQueensSolution(int rowNum) {
        if(rowNum == nSize) {
            //Print here
            for(int i = 0; i < positions.length; i++) {
                System.out.println("RowNum: " + i + ", position: " + positions[i]);
            }
            return;
        }
        for(int i = 0; i < nSize; i++) {
            if(place(rowNum, i)) {
                nQueensSolution(rowNum + 1);
            }
        }
    }

    public static void main(String args[]) {
        nQueensSolution(0);
    }
}
