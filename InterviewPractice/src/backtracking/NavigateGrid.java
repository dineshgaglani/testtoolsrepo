package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NavigateGrid {


    class Cell {
        int value;
        int rowNum;
        int colNum;
        State state;

        public Cell() {
            state = State.NOT_VISITED;
        }

        @Override
        public String toString() {
            return "[" + rowNum + ", " + colNum + "]";
        }
    }

    enum State { VISITED, NOT_VISITED }

    public static Cell getLeft(Cell[][] grid, Cell currCell) {
        if (currCell.colNum - 1 > -1) {
            return grid[currCell.rowNum][currCell.colNum - 1];
        }
        return null;
    }

    public static Cell getRight(Cell[][] grid, Cell currCell) {
        if (currCell.colNum + 1 < grid.length) {
            return grid[currCell.rowNum][currCell.colNum + 1];
        }
        return null;
    }

    public static Cell getUp(Cell[][] grid, Cell currCell) {
        if (currCell.rowNum - 1 > -1) {
            return grid[currCell.rowNum - 1][currCell.colNum];
        }
        return null;
    }

    public static Cell getDown(Cell[][] grid, Cell currCell) {
        if (currCell.rowNum + 1 < grid.length) {
            return grid[currCell.rowNum + 1][currCell.colNum];
        }
        return null;
    }


    public static void navigateGrid(List<Cell> path, Cell[][] grid, Cell currCell, Cell destinationCell) {
        if (currCell != null && currCell.state == State.NOT_VISITED && currCell.value == 0) {
            if (currCell == destinationCell) {
                System.out.println("Reached!!");
                System.out.println(path);
            }
            path.add(currCell);
            currCell.state = State.VISITED;
            navigateGrid(path, grid, getLeft(grid, currCell), destinationCell);
            navigateGrid(path, grid, getRight(grid, currCell), destinationCell);
            navigateGrid(path, grid, getDown(grid, currCell), destinationCell);
            navigateGrid(path, grid, getUp(grid, currCell), destinationCell);
            path.remove(path.size() - 1);
        }
    }

    public static void main (String args[]) {
        Cell[][] grid = new Cell[4][4];
        NavigateGrid ng = new NavigateGrid();
        for (int i = 0 ; i < grid.length; i++) {
            for (int j = 0 ; j < grid.length; j++) {
                Cell c = ng.new Cell();
                c.colNum = j;
                c.rowNum = i;
                c.value = 0;
                grid[i][j] = c;
            }
        }

        for (int col = 1; col < grid.length; col++) {
            grid[0][col].value = 1;
        }

        for (int col = 0; col < grid.length - 1; col++) {
            grid[3][col].value = 1;
        }

        grid[1][2].value = 1;

        grid[2][0].value = 1;

        navigateGrid(new ArrayList<Cell>(), grid, grid[0][0], grid[grid.length - 1][grid.length - 1]);
    }
}
