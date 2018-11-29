package backtracking;

public class Utils {

    public static void swapChars(char[] string, int swapPos1, int swapPos2) {
        char temp = string[swapPos1];
        string[swapPos1] = string[swapPos2];
        string[swapPos2] = temp;
    }

}
