package backtracking;

public class PrintBinary {

    public static void printBinary(int digitIndexesRemaining, String currBinary) {
        if (digitIndexesRemaining == 0) {
            System.out.println(currBinary);
        } else {
            printBinary(digitIndexesRemaining - 1, currBinary + "0");
            printBinary(digitIndexesRemaining - 1, currBinary + "1");
        }

    }

    public static void main(String args[]) {
        printBinary(2, "");
    }
}
