package dailyCodingProblem;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dinesh on 9/7/2019.
 *
 * Sudoku is a puzzle where you're given a partially-filled 9 by 9 grid with digits. The objective is to fill the grid with the constraint that every row, column, and box (3 by 3 subgrid) must contain all of the digits from 1 to 9.

 Implement an efficient sudoku solver.
 */
public class Problem54SudokuBoard {

    class Coords {
        int i;
        int j;
    }

    public void solveSudoku(Coords currCoords , Integer[][] grid) {
        for (int i = 1; i < 9; i++) {
            //Choose
            Set<Integer> rowsVals = getAtRow(currCoords.i, grid);
            Set<Integer> colsVals = getAtCol(currCoords.j, grid);
            if (!rowsVals.contains(i) && !colsVals.contains(i)) {
                //Explore
                Coords nextCoords = new Coords();
                if (currCoords.i == grid.length) { nextCoords.i = 0; nextCoords.j = currCoords.j + 1; }
                else {nextCoords.i = currCoords.i + 1; nextCoords.j = currCoords.j; }
                solveSudoku(nextCoords, grid);
            }

        }
    }

    private Set<Integer> getAtRow(int i, Integer[][] grid) {
        Set<Integer> ret = new HashSet<>();
        for (int colCtr = 0; colCtr < grid[0].length; colCtr++) {
            ret.add(grid[i][colCtr]);
        }
        return ret;
    }

    private Set<Integer> getAtCol(int j, Integer[][] grid) {
        Set<Integer> ret = new HashSet<>();
        for (int rowCtr = 0; rowCtr < grid[0].length; rowCtr++) {
            ret.add(grid[rowCtr][j]);
        }
        return ret;
    }
}
