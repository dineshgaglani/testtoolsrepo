package amazon;
/*
A Mars rover is directed to move within a square matrix. It accepts a sequence of commands to move in any of the four directions from each cell: [UP, DOWN, LEFT or RIGHT]. The rover starts from cell 0. and may not move diagonally or outside of the boundary.

Each cell in the matrix has a position equal to:
(row * size) + column
where row and column are zero-indexed, size = row length of the matrix.

Return the final position of the rover after all moves.

Example
n = 4
commands = [RIGHT, UP, DOWN, LEFT, DOWN, DOWN]

The rover path is shown below.

0	1	2	3
4	5 	6 	7
8 	9 	10 	11
12 	13 	14 	15

RIGHT: Rover moves to position 1
UP: Position unchanged, as the move would take the rover out of the boundary.
DOWN: Rover moves to Position 5.
LEFT: Rover moves to position 4
DOWN: Rover moves to position 8
DOWN: The rover ends up in position 12.

The function returns 12.

Function Description
Complete the function roverMove in the editor below.
roverMove has the following parameter(s):
int n: the size of the square matrix
string cmds[m]: the commands

Returns
int: the label of the cell the rover occupies after executing all commands

Constraints
2 <= n <= 20
1 <= |cmds| <= 20
 */
public class RoverControl {

    public static Integer roverMove(Integer n, String[] commands) {
        int pos = 0;
        for (String command : commands) {
            if (command.equals("UP")) {
                if(pos > n) {
                    pos = pos - n;
                }
            } else if (command.equals("DOWN")) {
                if (pos < n * (n - 1)) {
                    pos = pos + n;
                }
            } else if (command.equals("RIGHT")) {
                if ( (pos + 1) % n != 0) {
                    pos = pos + 1;
                }
            } else if (command.equals("LEFT")) {
                if (pos % n != 0) {
                    pos = pos - 1;
                }
            }
        }
        return pos;
    }

    public static void main (String args[]) {
        System.out.println(roverMove(4, new String[] {"RIGHT", "UP", "DOWN", "LEFT", "DOWN", "DOWN"} ));
    }
}
