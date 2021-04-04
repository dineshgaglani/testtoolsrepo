package interviewcamp.time2;


import java.util.ArrayList;
import java.util.Arrays;

public class MazeProblem {

    public static void printPath(Integer[][] maze) {
        ArrayList<Integer[]> paths = new ArrayList<>();
        printPath(maze, paths, 0, 0, maze.length - 1, maze[0].length - 1);
    }

    public static void printPath(Integer[][] maze, ArrayList<Integer[]> paths, Integer currX, Integer currY, Integer targetX, Integer targetY) {
        if (currX == targetX && currY == targetY) {
            paths.stream().forEach(e -> System.out.println(Arrays.asList(e)));
            return;
        }
        if (maze[currX][currY] == 1) {
            return;
        }
        maze[currX][currY] = 1;
        Integer[][] dir = new Integer[][] { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        for (int i = 0; i < dir.length; i++) {
            Integer newX = currX + dir[i][0];
            Integer newY = currY + dir[i][1];

            if (newX >= 0 && newX < maze.length && newY >=0 && newY < maze[0].length) {
                paths.add(new Integer[] {newX, newY});
                printPath(maze, paths, newX, newY, targetX, targetY);
                paths.remove(paths.size() - 1);
            }
        }
    }

    public static void main (String args[]) {
        printPath(new Integer[][] { {0, 1, 1, 1}, {0, 0, 0, 1}, {1, 0, 0, 1}, {1, 1, 0, 0} });
        System.out.println();
        printPath(new Integer[][] { {0, 0, 0, 1, 1}, {0, 1, 0, 0, 0}, {0, 0, 1, 1, 0}, {0, 0, 0, 1, 0} });
    }
}
