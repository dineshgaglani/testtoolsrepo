package amazon;

/**
 * Created by Dinesh on 9/8/2019.
 */
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    static int minimumTime(int numOfParts, List<Integer> parts)
    {
        // WRITE YOUR CODE HERE

        List<Integer> accs = new ArrayList<>();
        List<Integer> sortedParts = parts.stream().sorted().collect(Collectors.toList());

        while (sortedParts.size() > 2) {
            Integer newPartSize = sortedParts.get(0) + sortedParts.get(1);
            accs.add(newPartSize);
            sortedParts.remove(0);
            sortedParts.remove(0);
            sortedParts.add(newPartSize);
            sortedParts = sortedParts.stream().sorted().collect(Collectors.toList());
        }
        Integer total = 0;
        for (Integer i = 0; i < accs.size(); i++) {
            total = total + accs.get(i);
        }
        return total + sortedParts.get(0) + sortedParts.get(1);
    }
    // METHOD SIGNATURE ENDS

    public static void main(String args[]) {
       Integer minT = minimumTime(4, Arrays.asList(new Integer[] {8, 4, 6, 12}) );
        System.out.println(minT);
    }
}
