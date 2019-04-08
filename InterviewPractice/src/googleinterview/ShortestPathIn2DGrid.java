package googleinterview;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestPathIn2DGrid {

    class Cell {
        int x;
        int y;
        boolean visited;

        boolean content;

        Cell prev;

        public Cell(int x, int y, boolean content) {
            this.x = x;
            this.y = y;
            this.content = content;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public Cell getUpper(Cell sourceCell, Cell[][] grid) {
        if (sourceCell.x > 0) {
            return grid[sourceCell.x - 1][sourceCell.y];
        }
        return null;
    }

    public Cell getLower(Cell sourceCell, Cell[][] grid) {
        if (sourceCell.x < grid.length - 1) {
            return grid[sourceCell.x + 1][sourceCell.y];
        }
        return null;
    }

    public Cell getRight(Cell sourceCell, Cell[][] grid) {
        if (sourceCell.y < grid[0].length - 1) {
            return grid[sourceCell.x][sourceCell.y + 1];
        }
        return null;
    }

    public Cell getLeft(Cell sourceCell, Cell[][] grid) {
        if (sourceCell.y > 0) {
            return grid[sourceCell.x][sourceCell.y - 1];
        }
        return null;
    }

    public boolean isWalkable (Cell cell) {
        return cell.content;
    }

    public void printShortestPath (Cell[][] grid, Cell startCell, Cell endCell) {
        Queue<Cell> q = new ArrayDeque();
        startCell.visited = true;
        q.add(startCell);

        while (!q.isEmpty()) {
            Cell currCell = q.remove();

            if (currCell == endCell) {
                Cell backCell = currCell;
                while (backCell != null) {
                    System.out.print(backCell + " --> ");
                    backCell = backCell.prev;
                }
                return;
            }

            Cell rightCell = getRight(currCell, grid);
            if (rightCell != null && !rightCell.visited && isWalkable(rightCell)) {
                rightCell.visited = true;
                rightCell.prev = currCell;
                q.add(rightCell);
            }

            Cell leftCell = getLeft(currCell, grid);
            if (leftCell != null && !leftCell.visited && isWalkable(leftCell)) {
                leftCell.visited = true;
                leftCell.prev = currCell;
                q.add(leftCell);
            }

            Cell upperCell = getUpper(currCell, grid);
            if (upperCell != null && !upperCell.visited && isWalkable(upperCell)) {
                upperCell.visited = true;
                upperCell.prev = currCell;
                q.add(upperCell);
            }

            Cell lowerCell = getLower(currCell, grid);
            if (lowerCell != null && !lowerCell.visited && isWalkable(lowerCell)) {
                lowerCell.visited = true;
                lowerCell.prev = currCell;
                q.add(lowerCell);
            }
        }

        System.out.println("Couldn't get to the Target!");
    }

    public static void main (String args[]) {
        ShortestPathIn2DGrid c = new ShortestPathIn2DGrid();
        ShortestPathIn2DGrid.Cell startCell =  c.new Cell(3, 0, true);
        ShortestPathIn2DGrid.Cell endCell =  c.new Cell(0, 3, true);
        ShortestPathIn2DGrid.Cell[][] grid = new ShortestPathIn2DGrid.Cell[][] { {c.new Cell(0, 0, true), c.new Cell(0, 1, true), c.new Cell(0, 2, true), endCell},
                                                                                 {c.new Cell(1, 0, false), c.new Cell(1, 1, false), c.new Cell(1, 2, true), c.new Cell(1, 3, false)},
                                                                                 {c.new Cell(2, 0, true), c.new Cell(2, 1, true), c.new Cell(2, 2, true), c.new Cell(2, 3, true)},
                                                                                 {startCell, c.new Cell(3, 1, true), c.new Cell(3, 2, true), c.new Cell(3, 3, true)} };
        c.printShortestPath(grid, startCell, endCell);
    }
}
