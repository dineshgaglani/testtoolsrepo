package interviewcamp.time2;

public class FindIslands {

    public static Integer findIslands(Integer[][] matrix) {
        Integer islandCount = 0;
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix.length; c++) {
                if (matrix[r][c] == 1) {
                    visit(matrix, r, c);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    public static void visit(Integer[][] matrix, Integer r, Integer c) {
        matrix[r][c] = -1;
        Integer[][] dirs = new Integer[][] { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        for (Integer[] dir : dirs) {
            Integer newr = r + dir[0];
            Integer newc = c + dir[1];

            if (newr > -1 && newr < matrix.length && newc > -1 && newc < matrix[0].length) {
                if (matrix[newr][newc] == 1) {
                    visit(matrix, newr, newc);
                }

            }
        }
    }

    public static void main (String args[]) {

        Integer[][] tc1r1 = new Integer[][] {{1}};
        //tc1
        System.out.println(findIslands(tc1r1) + " should equal 1");

        Integer[][] tc2r1 = new Integer[][] {{0}};
        //tc2
        System.out.println(findIslands(tc2r1) + " should equal 0");

        Integer[][] tc3 = new Integer[][] { {1, 0} , { 0, 1} };
        //tc3
        System.out.println(findIslands(tc3) + " should equal 2");

        Integer[][] tc4 = new Integer[][] { {0, 1, 1}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(findIslands(tc4) + " should equal 2");
    }
}
