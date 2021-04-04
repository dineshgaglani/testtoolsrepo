package interviewcamp.time2;

import java.util.ArrayList;
import java.util.List;

public class SearchSubstring {

    static List<Integer> getSubstringIndices(String mainString, String subString) {
        List<Integer> substringIndices = new ArrayList<>();
        Integer mCtr = 0;
        while(mCtr < mainString.length()) {
            int fCtr = mCtr;
            int sCtr = 0;
            while(sCtr < subString.length() && fCtr < mainString.length() && mainString.charAt(fCtr) == subString.charAt(sCtr)) {
                fCtr++;
                sCtr++;
                if (sCtr == subString.length()) {
                    substringIndices.add(fCtr - sCtr);
                }
            }
            mCtr++;
        }
        return substringIndices;
    }

    public static void main(String args[]) {
        System.out.println(getSubstringIndices("aaanbbbbbccddeerrrttgjbbkpoppomfnubiuaaaddbbb", "bbb"));
    }
}
