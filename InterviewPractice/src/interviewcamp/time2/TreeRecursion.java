package interviewcamp.time2;

public class TreeRecursion {

    public static void recur(int n) {
        if (n > 0) {
            System.out.print(n + " ");
            recur(n-1);
            recur(n-1);
        }
    }

    public static void main(String[] args) {
        recur(3);
        System.out.println();
        recur(4);
    }
}
