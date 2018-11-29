package backtracking;

/*
    Intuition: Looking to find connected 1s in the grid and returning the value of number of connected clusters of 1s
    When I find a zero I still explore looking for a 1 and when I do find the 1, I only look for interconnected 1s
    marking them visited as I see them. This works because after a 1 if I continue exploring for both 1s and 0s, I will
    have found 0s between 1s and may not know whether the next 1 I find is part of a connected group
    example 1 0 1 --> here I move right and spot a 0, I cannot reset my counter yet because the next 1 I spot is also part
            1 1 1
    of the same cluster. So, I exhaust all the 1s before I again look to find the zeros

    Mistakes made before: This required 2 bfs traversals, the first on finding a 1 and the other after exhausting 1s.
    During the "searchMode" BFS if we encounter a 0, we must return since we are only looking for 1s to mark them visited
    And after we return back to the 1 we started from (after all 4 recursions complete) is when we mark "searchModeEnded"
    and restart searching for other connected components.

    Another way to intuitively understand the "End of search mode" is when we find the "First 1 of the cluster" our
    searchMode is turned on and for the subsequent 1s  "0 neighbors" we return before the line where we switch it off.
    And only when we get to the first one (where we are already past the switch to searchModeOn) do we explore with searchMode
    off.

    General Takeaway: We can do a partial scan of the grid, return back to the position where we started the partial scan and go
    back and do the full scan again if we remember to flip a switch (after we receive a trigger) to start partial search and
    we check for the switch before the flipping, so that the switch remains in the subsequent calls, and when we come back
    to the call that initiated the partial scan we are already ahead of the check (for partial scan) and so we go ahead and
    do a full scan
 */
public class CountIslands {


    public static void main (String args[]) {
        int size = 4;
        int islandCount = 0;
        CountIslands ci = new CountIslands();
        NavigateGrid ng = new NavigateGrid();
        NavigateGrid.Cell[][] grid = new NavigateGrid.Cell[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                NavigateGrid.Cell c = ng.new Cell();
                c.rowNum = i;
                c.colNum = j;
                if (i == 0 || (i == 3 && j == 3)) {
                    c.value = 1;
                } else {
                    c.value = 0;
                }
                grid[i][j] = c;
            }
        }

        ci.countIslands(grid[0][0], grid, false, false, 0);
        System.out.println("Island count : " + islandCount);
    }

    public void countIslands(NavigateGrid.Cell currCell, NavigateGrid.Cell[][] grid, boolean isSearching, boolean isIsland, int islandCount) {


        if (currCell != null && currCell.state == NavigateGrid.State.NOT_VISITED) {
            System.out.println("CurrCell cords : " + currCell.rowNum + ", " + currCell.colNum + ". value: " + currCell.value + ", islandCount: " + islandCount + ", isSearching: " + isSearching);
            if (isSearching && currCell.value == 0) {
                //Check if switch is flipped so that subsequent calls return ahead of resetting to original value
                return;
            }
            if (currCell.value == 1) {
                if (!isIsland) {
                    islandCount++;
                    isIsland = true;
                }
                //Flip switch for partial scan
                isSearching = true;
            }
            currCell.state = NavigateGrid.State.VISITED;
            countIslands(NavigateGrid.getLeft(grid, currCell), grid, isSearching, isIsland, islandCount);
            countIslands(NavigateGrid.getUp(grid, currCell), grid, isSearching, isIsland, islandCount);
            countIslands(NavigateGrid.getRight(grid, currCell), grid, isSearching, isIsland, islandCount);
            countIslands(NavigateGrid.getDown(grid, currCell), grid, isSearching, isIsland, islandCount);
            if (isSearching) {
                //Flip switch for full scan
                isSearching = false;
                isIsland = false;
            }
            countIslands(NavigateGrid.getLeft(grid, currCell), grid, isSearching, isIsland, islandCount);
            countIslands(NavigateGrid.getUp(grid, currCell), grid, isSearching, isIsland, islandCount);
            countIslands(NavigateGrid.getRight(grid, currCell), grid, isSearching, isIsland, islandCount);
            countIslands(NavigateGrid.getDown(grid, currCell), grid, isSearching, isIsland, islandCount);
        }

    }
}
