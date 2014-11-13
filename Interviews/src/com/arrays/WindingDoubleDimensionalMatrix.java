package com.arrays;

/**
 * Created by dgaglani on 7/6/14.
 */
public class WindingDoubleDimensionalMatrix {

    public static void main(String args[]) {
         int[][] matrix = new int[5][5];
         showWindingMatrix(matrix);
    }

    public static void showWindingMatrix(int[][] matrix) {
        boolean columnForward = true;
        boolean rowForward = true;
        int endNum = 1;
        int startRowNum = 0;
        int startColNum = 0;
        int endRowNum = matrix.length - 1;
        int endColNum = matrix[0].length - 1;
        endNum = populateColumnWise(matrix, columnForward, endNum, startRowNum, startColNum, endColNum);
        columnForward = !columnForward;
        endNum = populateRowWise(matrix, rowForward, endNum + 1, endColNum, startRowNum + 1, endRowNum);
        rowForward = !rowForward;
        endNum = populateColumnWise(matrix, columnForward, endNum + 1, endRowNum, startColNum, endColNum - 1);
        columnForward = !columnForward;
        endNum = populateRowWise(matrix, rowForward, endNum + 1, startColNum, startRowNum + 1, endRowNum - 1);
        rowForward = !rowForward;
        endNum = populateColumnWise(matrix, columnForward, endNum + 1, startRowNum + 1, startColNum + 1, endColNum - 1);
        columnForward = !columnForward;
        endNum = populateRowWise(matrix, rowForward, endNum + 1, endColNum - 1, startRowNum + 1, endRowNum - 1);
        rowForward = !rowForward;
        MatrixRotation.displayMatrix(matrix);
    }

    public static int populateColumnWise(int[][] matrix, boolean isForward, int startNum, int rowNum, int startColumn, int stopColumn) {
        int columnIndex = startColumn;
        if(isForward) {
            while(columnIndex <= stopColumn) {
                matrix[rowNum][columnIndex] = startNum;
                startNum++;
                columnIndex++;
            }
        }
        else {
            while(columnIndex >= stopColumn) {
                matrix[rowNum][columnIndex] = startNum;
                startNum++;
                columnIndex--;
            }
        }
        return startNum;
    }

    public static int populateRowWise(int[][] matrix, boolean isForward, int startNum, int columnNum, int startRow, int stopRow) {

        if(isForward) {
            int rowIndex = startRow;
            while(rowIndex <= stopRow) {
                matrix[rowIndex][columnNum] = startNum;
                startNum++;
                rowIndex++;
            }
        }
        else {
            int rowIndex = stopRow;
            while(rowIndex >= startRow) {
                matrix[rowIndex][columnNum] = startNum;
                startNum++;
                rowIndex--;
            }
        }
        return startNum;
    }

}
