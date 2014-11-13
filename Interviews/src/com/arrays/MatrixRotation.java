package com.arrays;

import com.stacks.Stack;

/**
 * Created by dgaglani on 7/3/14.
 */
public class MatrixRotation {

    public static void main(String args[]){
        int[][] matrix = new int[][] {{1, 2, 3, 4, 5}, {16, 17, 18, 19, 6}, {15, 24, 25, 20, 7}, {14, 23, 22, 21, 8}, {13, 12, 11, 10, 9}};
        rotateMatrixAndPrint(matrix);
    }

    public static void rotateMatrixAndPrint(int[][] inputMatrix) {
        System.out.println("Input matrix: ");
        displayMatrix(inputMatrix);
        System.out.println("\nRotated Matrix");
        int[][] rotatedMatrix = rotateMatrix(inputMatrix);
        rotatedMatrix = rotateMatrix(rotatedMatrix);
        rotatedMatrix = rotateMatrix(rotatedMatrix);
        displayMatrix(rotatedMatrix);
        System.out.println("\nRotating input the other way");
        int[][] otherwayRotatedMatrix = rotateMatrixTheOtherWay(inputMatrix);
        displayMatrix(otherwayRotatedMatrix);
    }

    public static void displayMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static int[][] rotateMatrix(int[][] inputMatrix) {
        /*
            First row to first column:
            a[0][0] becomes a[0][a[0].length - 0 - 1]
            a[0][1] becomes a[1][a[0].length - 0 - 1]
            a[0][2] becomes a[2][a[0].length - 0 - 1]
            ...
            a[0][a[0].length - 1] becomes a[length - 1][a[0].length - 1]

            a[1][0] becomes a[0][a[0].length - 1 - 1]
            a[1][1] becomes a[1][a[0].length - 1 - 1]
            a[1][2] becomes a[2][a[0].length - 1 - 1]
            ...
            a[1][a[0].length - 1] becomes a[length - 1][length - 1 - 1]
            ...
            a[length - 1][a[0].length - 1] becomes a[length - 1][a[0].length - (length - 1) - 1]
         */
        int[][] rotatedMatrix = new int[inputMatrix.length][inputMatrix[0].length];
        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix[0].length; j++) {
                rotatedMatrix[i][j] = inputMatrix[j][inputMatrix[0].length - i - 1];
            }
        }
        return rotatedMatrix;
    }

    public static int[][] rotateMatrixTheOtherWay(int[][] inputMatrix) {
        /*
            First row to first column:
            a[0][0] becomes a[0][a[0].length - 0 - 1]
            a[0][1] becomes a[1][a[0].length - 0 - 1]
            a[0][2] becomes a[2][a[0].length - 0 - 1]
            ...
            a[0][a[0].length - 1] becomes a[length - 1][a[0].length - 1]

            a[1][0] becomes a[0][a[0].length - 1 - 1]
            a[1][1] becomes a[1][a[0].length - 1 - 1]
            a[1][2] becomes a[2][a[0].length - 1 - 1]
            ...
            a[1][a[0].length - 1] becomes a[length - 1][length - 1 - 1]
            ...
            a[length - 1][a[0].length - 1] becomes a[length - 1][a[0].length - (length - 1) - 1]
         */
        int[][] rotatedMatrix = new int[inputMatrix.length][inputMatrix[0].length];
        for (int i = 0; i < inputMatrix.length; i++) {
            for (int j = 0; j < inputMatrix[0].length; j++) {
                rotatedMatrix[j][inputMatrix[0].length - i - 1] = inputMatrix[i][j];
            }
        }
        return rotatedMatrix;
    }
}
