package arrays.doubledimensional;

/**
 * Created by Dinesh on 10/6/2019.
 */
public class AnagramsWithoutRecursion {

    public static void main(String[] args) {
        printAnagrams("abc");
    }

    private static void printAnagrams(String s) {
        for (int i = 0; i <= s.length(); i++) {
            String charToMove = s.charAt(i) + "";
            String prepre = s.substring(0, i);
            String subString = s.substring(i + 1, s.length());
            for (int j = 0; j <= subString.length(); j++) {
                // put ith character in jth position
                String pre = subString.substring(0, j);
                String post = subString.substring(j, subString.length());
                System.out.println(prepre + pre + charToMove + post);
            }
        }
    }
}
