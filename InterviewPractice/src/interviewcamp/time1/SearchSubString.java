package interviewcamp.time1;

import java.util.ArrayList;
import java.util.List;

public class SearchSubString {

    static List<Integer> getIndexesOfSubstring(String mainString, String subString) {
        int mainSCtr = 0;
        List<Integer> indexesOfSubString = new ArrayList<>();
        while (mainSCtr < mainString.length()) {
            int subSCtr = 0;
            int fCtr = mainSCtr;
            while(subSCtr < subString.length() && fCtr < mainString.length() && mainString.charAt(fCtr) == subString.charAt(subSCtr)) {
                fCtr++;
                subSCtr++;
                if (subSCtr == subString.length()) {
                    indexesOfSubString.add(fCtr - subSCtr);
                }
            }
            subSCtr = 0;
            mainSCtr++;
        }

        return indexesOfSubString;
    }

    public static void main(String args[]) {
        System.out.println(getIndexesOfSubstring("aaanbbbbbccddeerrrttgjbbkpoppomfnubiuaaaddbbb", "bbb"));
    }
}
