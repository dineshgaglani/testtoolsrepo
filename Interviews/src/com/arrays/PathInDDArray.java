package com.arrays;

import java.lang.reflect.Array;

/**
 * Created by dgaglani on 6/27/14.
 */
public class PathInDDArray {

    public static void main(String args[]) {
        /*{0, 1, 1, 0, 0, 1}
        {0, 0, 1, 1, 1, 0}
        {0, 0, 0, 0, 1, 0}
        {1, 1, 1, 0, 1, 0}
        {1, 0, 1, 1, 1, 0}
        {1, 1, 1, 1, 1, 1}*/
        int[][] maze = new int[][] {{1, 1, 1, 0, 0, 1, 0},
                                    {0, 0, 1, 1, 1, 0, 0},
                                    {0, 0, 0, 0, 1, 0, 1},
                                    {1, 1, 1, 0, 1, 1, 1},
                                    {1, 0, 1, 1, 1, 0, 0},
                                    {1, 0, 0, 0, 0, 0, 0},
                                    {1, 1, 1, 1, 1, 1, 1}};
        int[][] path = getPath(maze, 0, 0);
        System.out.println("Path is : ");
        displayPath(path);
    }

    public static int[][] getPath(int[][] maze, int x, int y) {
        int[][] path = new int[maze.length][maze[0].length];
        getPathRecurse(maze, path, x, y);
        return path;
    }

    public static boolean getPathRecurse(int[][] maze, int[][] path, int x, int y) {
        if(x == maze.length - 1 && y == maze[0].length - 1) {
            return true;
        }
        if(isValid(maze, path, x, y)) {
            path[x][y] = 1;
            if(!getPathRecurse(maze, path, x, y + 1) && !getPathRecurse(maze, path, x + 1, y) && !getPathRecurse(maze, path, x - 1, y) && !getPathRecurse(maze, path, x, y - 1)) {
                path[x][y] = 0;
                return false;
            }//go right//go down//go up//go left
            return true;
        }
        else {
            return false;
        }
    }

    public static void displayPath(int[][] path) {
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[0].length; j++) {
                System.out.print(path[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static boolean isValid(int[][] maze, int[][] path, int x, int y) {
        if (x < maze.length && y < maze[0].length && x >= 0 && y >= 0) {
            try{
                return maze[x][y] == 1 && path[x][y] != 1;
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.print("x is :" + x +", y is :" + y);
            }
        }
        return false;
    }
}
