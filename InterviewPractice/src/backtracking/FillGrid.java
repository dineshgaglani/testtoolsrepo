package backtracking;


public class FillGrid {

    class Cell {
        int color;
        boolean isVisited;

        @Override
        public String toString() {
            return color + "";
        }
    }

     class Grid {

        private Cell[][] cells;

        public Grid(int size) {
            cells = new Cell[size][size];
            init();
        }

        public void init() {
            for(int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    cells[i][j] = new Cell();
                }
            }
        }

        public boolean isCellValid(int row, int col) {
            if (row < 0 || col < 0 || row > cells.length - 1 || col > cells[0].length - 1 || cells[row][col].isVisited) {
                return false;
            }
            return true;
        }

        public Cell getCellAt(int row, int col) {
            if (isCellValid(row, col)) {
                return cells[row][col];
            }
            return null;
        }

        public void displayGrid() {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    System.out.print(cells[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }

    public void fillGrid(int row, int col, Grid grid, int color) {
        Cell cell = grid.getCellAt(row, col);
        if (cell == null || cell.color == color) {
            return;
        } else {
            cell.isVisited = true;
            //Going right
            fillGrid(row, col + 1, grid, color);

            //Going left
            fillGrid(row, col - 1, grid, color);

            //Going up
            fillGrid(row - 1, col, grid, color);

            //Going down
            fillGrid(row + 1, col, grid, color);

            cell.color = color;

            grid.displayGrid();
            System.out.println();
        }
    }

    /*
            Mistakes -
            1. Was trying to encapsulate every movement on Grid, and so did not have a reference to the Grid's previous state
            2. Was then passing the previous position as an alternative to passing the entire Grid's previous state
            3. The color was just reversing the current color, but this was a problem because the terminal condition is also when colors of prev cell and current cell become same.
            4. Step 2 did not work because the grid's previous state does not apply on the first step (when both states are the same).
            5. Passing color instead of previous state now.
            6. The arguments only are Grid and Color, because everything is encapsulated by current position in grid
            7. Forgot to add -1 for both row and col in getCurrentCell validations
            8. Backtracking ALWAYS needs to have a reference to the previous state, a better strategy would be to pass position and change in the backtracker than the object itself.
            9. In backtracking either "Unplace" or "Creating a new object" strategy can be used



            10. Fresh perspective, have every stack frame hold a reference to it's state and the Grid Cell have a reference to whether it is visited
            11. Another question to answer is whether we color before or after (After shouldn't matter because we don't "uncolor")
    */
    public static void main (String args[]) {

        FillGrid fg = new FillGrid();
        Grid grid = fg.new Grid(4);

        //Set grid shape - vertical line in center (column 3)
//        for (int i = 0; i < grid.cells.length; i++) {
//            grid.getCellAt(i, 2).color = 1;
//        }
//        int color = grid.getCellAt(0, 0).color == 0? 1:0;

//        //Set grid shape - diagonal line in center
//        for (int i = 1; i < grid.cells.length; i++) {
//            grid.getCellAt(i, i).color = 1;
//        }
//        int color = grid.getCellAt(0, 0).color == 0? 1:0;

        //Set grid shape T - shape, given in test case
        for (int i = 0; i < grid.cells.length; i++) {
            grid.getCellAt(i, 1).color = 1;
        }
        for (int i = 0; i < grid.cells[0].length - 1; i++) {
            grid.getCellAt(1, i).color = 1;
        }
        grid.getCellAt(3, 2).color = 1;
        grid.getCellAt(0, 3).color = 1;

        int color = grid.getCellAt(0, 3).color == 0? 1:0;

        fg.fillGrid(0, 3, grid, color);
    }


}
