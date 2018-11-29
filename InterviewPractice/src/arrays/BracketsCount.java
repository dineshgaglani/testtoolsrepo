package arrays;

import java.util.Stack;

public class BracketsCount {

    public static void main (String[] args) {
        Stack<String> s = new Stack<>();

        int r = 0;
        String b = ")(" ;
        for (int i = 0; i < b.length(); i++) {
            if ((b.charAt(i) + "").equals("(")) {
                s.push("");
            } else {
                try {
                    s.pop();
                } catch (Exception e) {
                    r++;
                }
            }
        }
        System.out.println(s.size() + r);
    }
}
