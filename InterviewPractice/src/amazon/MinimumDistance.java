package amazon;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Dinesh on 9/8/2019.
 */
public class MinimumDistance {

    class CoOrd {
        int x, y;

        CoOrd(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    class Path {
        CoOrd currCoord;
        List<CoOrd> track;

        Path(CoOrd coOrd, List<CoOrd> track) {
            this.currCoord = coOrd;
            this.track = track;
        }
    }


    List<CoOrd> getNeighbors(CoOrd currCoord, List<List<Integer>> area) {
        List<CoOrd> neighbors = new ArrayList<>();
        if (currCoord.y > 0 && area.get(currCoord.x).get(currCoord.y - 1) > 0) {
            //left coord
            neighbors.add(new CoOrd(currCoord.x, currCoord.y - 1));
        }
        if (currCoord.x > 0 && area.get(currCoord.x - 1).get(currCoord.y) > 0) {
            //Upper coord
            neighbors.add(new CoOrd(currCoord.x - 1, currCoord.y));
        }
        if (currCoord.y < area.get(currCoord.x).size() - 1 && area.get(currCoord.x).get(currCoord.y + 1) > 0) {
            //right coord
            neighbors.add(new CoOrd(currCoord.x, currCoord.y + 1));
        }
        if (currCoord.x < area.size() - 1 && area.get(currCoord.x + 1).get(currCoord.y) > 0) {
            //lower coord
            neighbors.add(new CoOrd(currCoord.x + 1, currCoord.y));
        }
        return neighbors;
    }

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int minimumDistance(int numRows, int numColumns, List<List<Integer>> area)
    {
        // WRITE YOUR CODE HERE
        List<Path> pathQ = new ArrayList<>();
        CoOrd target = getTarget(area);
        pathQ.add(new Path(target, new ArrayList<>()));

        while(pathQ.size() != 0) {
            Path targetTrack = pathQ.get(0);
            target = targetTrack.currCoord;
            if(target.x == 0 && target.y == 0) {
                return targetTrack.track.size();
            }
            area.get(target.x).set(target.y, 9);
//            printGrid(area);
            pathQ.remove(0);
            area.get(target.x).set(target.y, -1);
            List<CoOrd> neighbors = getNeighbors(target, area);
            for (CoOrd neighbor : neighbors) {
                //Setting target to -1 when visited
                List<CoOrd> track = new ArrayList<>(targetTrack.track);
                track.add(target);
                pathQ.add(new Path(neighbor, track));
            }
        }
        return -1;
    }

    private void printGrid(List<List<Integer>> area) {
        area.forEach( row -> System.out.println(row));
        System.out.println();
        System.out.println();
    }


    private CoOrd getTarget(List<List<Integer>> area) {
        for (int i = 0; i < area.size(); i++) {
            for (int j = 0; j < area.get(0).size(); j++) {
                if (area.get(i).get(j) == 9) {
                    return new CoOrd(i, j);
                }
            }
        }
        return null;
    }
    // METHOD SIGNATURE ENDS

    public static void main (String args[]) {
        MinimumDistance md = new MinimumDistance();
        List<List<Integer>> grid = new ArrayList<>();

        List<Integer> row1 = Arrays.asList(new Integer[] {1, 1, 1, 1} );
        List<Integer> row2 = Arrays.asList(new Integer[] {0, 1, 1, 1} );
        List<Integer> row3 = Arrays.asList(new Integer[] {0, 1, 0, 1} );
        List<Integer> row4 = Arrays.asList(new Integer[] {1, 1, 9, 1} );
        List<Integer> row5 = Arrays.asList(new Integer[] {0, 0, 1, 1} );
        grid.add(row1);
        grid.add(row2);
        grid.add(row3);
        grid.add(row4);
        grid.add(row5);
        System.out.println(md.minimumDistance(3, 3, grid));

        List<List<Integer>> grid2 = new ArrayList<>();

        List<Integer> row21 = Arrays.asList(new Integer[] {1, 0, 0} );
        List<Integer> row22 = Arrays.asList(new Integer[] {1, 0, 0} );
        List<Integer> row23 = Arrays.asList(new Integer[] {1, 9, 1} );
        grid2.add(row21);
        grid2.add(row22);
        grid2.add(row23);
        System.out.println(md.minimumDistance(3, 3, grid2));

    }
}
