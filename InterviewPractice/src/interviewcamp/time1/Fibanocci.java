package interviewcamp.time1;

public class Fibanocci {

    public static Integer fibanocci (Integer n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        else {
            return fibanocci(n - 1) + fibanocci(n - 2);
        }

    }

    public static void main (String[] args) {
        System.out.println(fibanocci(3));
    }
}
