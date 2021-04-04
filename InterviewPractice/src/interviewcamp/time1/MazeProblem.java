package interviewcamp.time1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
(Level: Medium) Maze Problem: You are given a 2D array that represents a maze. It can have 2 values - 0 and 1. 1 represents a wall and 0 represents a path. The objective of the maze is to reach the bottom right corner, or A[A.length-1][A.length-1]. You start from A[0][0] and can only go in 4 directions - up, down, left or right. Find if a path exists.

Mistake - forgot to set visited path
 */
public class MazeProblem {

    public static void printPath(Integer[][] maze) {
        List<Integer[]> pathsTraversed = new ArrayList<>();
        printPath(maze, pathsTraversed, 0, 0, maze.length - 1, maze[0].length - 1);
    }

    public static void printPath(Integer[][] maze, List<Integer[]> pathsTraversed, Integer currX, Integer currY, Integer targetX, Integer targetY) {
        if (maze[currX][currY] == 1) {
            return;
        } else if (currX == targetX && currY == targetY) {
            pathsTraversed.stream().forEach(e -> System.out.println(Arrays.asList(e)));
        }
        Integer[][] directions = new Integer[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int i = 0; i < directions.length; i++) {
            Integer newX = currX + directions[i][0];
            Integer newY = currY + directions[i][1];

            if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length) {
                pathsTraversed.add(new Integer[] { currX, currY });
                maze[currX][currY] = 1;
                printPath(maze, pathsTraversed, newX, newY, targetX, targetY);
                pathsTraversed.remove(pathsTraversed.size() - 1);
            }
        }
    }

    public static void main (String args[]) {
//        printPath(new Integer[][] { {0, 1, 1, 1}, {0, 0, 0, 1}, {1, 0, 0, 1}, {1, 1, 0, 0} });
        System.out.println();
        printPath(new Integer[][] { {0, 0, 0, 1, 1}, {0, 1, 0, 0, 0}, {0, 0, 1, 1, 0}, {0, 0, 0, 1, 0} });
    }
}
