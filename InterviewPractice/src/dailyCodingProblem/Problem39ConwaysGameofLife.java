package dailyCodingProblem;

/**
 * Created by Dinesh on 9/5/2019.
 *
 * Conway's Game of Life takes place on an infinite two-dimensional board of square cells. Each cell is either dead or alive, and at each tick, the following rules apply:

 Any live cell with less than two live neighbours dies.
 Any live cell with two or three live neighbours remains living.
 Any live cell with more than three live neighbours dies.
 Any dead cell with exactly three live neighbours becomes a live cell.
 A cell neighbours another cell if it is horizontally, vertically, or diagonally adjacent.

 Create a Cell type that queries its neighbors for whether they are alive, the main program will go cell by cell and set the cell value


 This implementation is WRONG, because the new generation of cells affects its neighbors, we needed to create a "new" grid every tick
 */
public class Problem39ConwaysGameofLife {

    class Cell {
        int x;
        int y;

        char status = '.';
        String[][] grid;

        int leftStatus() {
            //Returns 1 when left is alive, returns 0 otherwise
          return x > 0 && grid[x - 1][y].equals('*') ? 1:0;
        }

        int rightStatus() {
            //Returns 1 when left is alive, returns 0 otherwise
            return x < grid.length - 1 && grid[x + 1][y].equals('*') ? 1:0;
        }

        int topStatus() {
            //Returns 1 when left is alive, returns 0 otherwise
            return y > 1 && grid[x][y - 1].equals('*') ? 1:0;
        }

        int bottomStatus() {
            //Returns 1 when left is alive, returns 0 otherwise
            return y < grid[0].length - 1 && grid[x][y + 1].equals('*') ? 1:0;
        }

        int leftTopStatus() {
            //Returns 1 when left is alive, returns 0 otherwise
            return x > 0 && y > 0 && grid[x - 1][y - 1].equals('*') ? 1:0;
        }

        int rightTopStatus() {
            //Returns 1 when left is alive, returns 0 otherwise
            return x > 0 && y < grid[0].length - 1 && grid[x +1][y - 1].equals('*') ? 1:0;
        }

        int leftBottomStatus() {
            //Returns 1 when left is alive, returns 0 otherwise
            return x < grid.length - 1 && y > 0 && grid[x - 1][y - 1].equals('*') ? 1:0;
        }

        int rightBottomStatus() {
            //Returns 1 when left is alive, returns 0 otherwise
            return x < grid.length - 1 && y > grid.length - 1 && grid[x + 1][y + 1].equals('*') ? 1:0;
        }

        @Override
        public String toString() {
            return status + "";
        }
    }

    public void playGame(int x, int y, Cell[][] grid) {

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Cell cell = grid[i][j];
                int livingNeighbors = cell.leftTopStatus() + cell.topStatus() + cell.rightTopStatus() + cell.rightStatus()
                        + cell.rightBottomStatus() + cell.bottomStatus() + cell.leftBottomStatus() + cell.leftStatus();

                if (livingNeighbors < 2) {
                    cell.status = '.';
                }
                else if (livingNeighbors == 2 || livingNeighbors == 3) {
                    cell.status = '*';
                }
                else if (livingNeighbors > 3) {
                    cell.status = '.';
                }
            }
        }

    }

}
