package dynamicprogramming;

public class EditDistanceStringSimilarity {

    /*
         Given 2 strings identify deleting which chars will make them equal

         Approach:
         If we have both as null strings then both are equal
         1. If we have 1 char in a string and the other as null, then we need to remove 1 char
         2. If we have 1 char in both strings and both are equal then we don't need to remove/substitute any
         3. If we have 1 char in both strings and both are not equal then we have to make both null, that is remove
         how many ever were needed to make the one string equal to null + how many ever were needed to make another
         string equal to null (which is 2), this can be an extension to point (1) that is whenever we encounter a string
         and we see it being different from the other string we need to remove + 1 of how many ever we needed to remove
         earlier, note that we only add + 1 which means we are only removing 1 character.
         4. We add the + 1 at this step because previous step (top and left cell - will explain later) will already have
         accounted for making the other string equal to this. Example: Imagine we have 2 1 char strings. When we start
         we will already have marked 1 for the first String to become null, and will have marked 1 for the other string
         to become null
                    null aString
            null      0     1  ---> This 1 marks number of deletions to make aString equal to null (first row)
            bString   1    <toFill>
                      |
                      |---> This 1 marks number of deletions to make bString equal to null (first column)
         So, toFill picks the minimum out of the two top and left rows, we choose the minimum because it indicates which
         of the charsToRemove we choose to add this char to and if in 1 string we have to remove more chars then we
         choose the other.
         5. Essentially the value at each cell indicates how many chars need to be removed to make that strings that form
         that cell equal to one another


         Algorithm:
         1. We run an outer loop from 1 string for all chars + 1 (for null string)
         2. We run an inner loop from the string for all chars + 1 (for null string)
         3. We first set the nulls to incrementing numbers starting from 0, since these many chars have to be removed
         to make the string equal to null
         4. When the chars are equal we copy the value at [i - 1][j - 1], so a[i][j] = a[i-1][j-1], this is because
         when chars match the number of deletions remains the same as when the chars were not counted
         5. When chars don't match we fill with this a[i][j] = min(a[i-1][j], a[i][j-1]) + 1 - Reasoning explained above
     */
    public void removeCharsToMakeEqual (String a, String b){
       String[][] abCells = new String[a.length() + 1][b.length() + 1];

       for (int i = 0; i < abCells.length; i++) {
            for (int j = 0; j < abCells[0].length; j++) {
                abCells[i][j] = "";
            }
       }

       for (int i = 0; i < abCells.length; i++) {
            abCells[i][0] = a.substring(0, i);
       }

       for (int j = 0; j < abCells[0].length; j++) {
            abCells[0][j] = b.substring(0, j);
       }

        for (int i = 1; i < abCells.length; i++) {
            for (int j = 1; j < abCells[0].length; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    abCells[i][j] = abCells[i - 1][j - 1];
                } else {
                    String topCellCharsToDel = abCells[i - 1][j];
                    String leftCellCharsToDel = abCells[i][j - 1];

                    //Choosing min length and recording char - debug to understand
                    if (topCellCharsToDel.length() > leftCellCharsToDel.length()) {
                        //Top Cell has more chars so choose the left cell and append the col char to it
                        abCells[i][j] = abCells[i][j - 1] + b.charAt(j - 1);
                    } else {
                        //Left cell has more chars so choose the top cell and append row char to it
                        abCells[i][j] = abCells[i-1][j] + a.charAt(i - 1);
                    }
                }
            }
        }
        System.out.println(abCells[abCells.length - 1][abCells[0].length - 1]);
    }

    /*
         Addendum: If we can substitute chars to make 2 strings equal, then (in addition to the deletion above) we can
         also decide to replace one character for the other. Imagining we have 2 strings that are equal upto point i
         in stringA and point j in stringB (i will be equal to j because string are equal upto this point), so now we
         have a character that doesn't match, (since we use a double dimensional array to track each char of stringA
         present in string stringB, we will have 0 at i - 1 and j - 1 position on both (diagonally above) and we will have
         1 on the left because this current character on the vertical axis (which j is pointing to) because it has to be
         removed for the strings to become equal) same with the one above because the char that is pointed to by the header
         in the horizontal axis will need to be removed. So, for this current one we pick the min out of left, top and
         diagonal (diagonal is for substitution since we pick the cost until not including either of the chars pointed by
         i and j and then adding 1 to it assuming 1 is the cost of substitution)
     */
    public int getEditsOfAllTypes(String stringA, String stringB) {
        //Create a 2D array with 0s at every position of size [stringASize + 1][stringBSize + 1]
        Integer[][] numEdits = new Integer[stringA.length() + 1][stringB.length() + 1];
        for (int i = 0; i < numEdits.length; i++) {
            for (int j = 0; j < numEdits[0].length; j++) {

                if (i == 0) {
                    numEdits[i][j] = j;
                }
                else if (j == 0) {
                    numEdits[i][j] = i;
                } else {
                    numEdits[i][j] = 0;
                }
            }
        }

        //Populate the first row with incrementing values
        // Done above

        //Populate first column with incrementing values
        // Done above

        //Calculate edit distance for the rest of the values
        for (int i = 0; i <= stringA.length(); i++) {
            for (int j = 0; j <= stringB.length(); j++) {
                if (i == 0 || j == 0) {
                    continue;
                }
                if (stringA.charAt(i - 1) == stringB.charAt(j - 1)) {
                    //This applies for both repeated same char
                    numEdits[i][j] = numEdits[i-1][j-1];
                } else {
                    //i-1, j-1 is a substitution
                    //i - 1, j is adding the char on vertical axis to edit distance until the previous char on vertical axis
                    //i, j - 1 is adding char on horizontal axis to edit distance until the previous char on horizontal axis
                    // Example to convert a to ab, with a on vertical axis, and j on character "b", the i - 1, j - 1
                    // will be on null on vertical and a on horizontal, (and will have value 1 by recursion), choosing this
                    // will mean we are choosing edit distance between null and a and substituting a with b, making the total
                    // edit distance 2
                    // The i - 1, j will have value 2, choosing this will mean we are choosing the edit distance between
                    // null and ab, and then adding a to the result (essentially converting ab to null and then adding
                    // current char "a")
                    // The i, j - 1 would mean we are choosing edit distance between a and a and then adding curr char (on
                    // h-axis "b" to it
                    numEdits[i][j] = Math.min(numEdits[i-1][j-1], Math.min(numEdits[i][j-1], numEdits[i - 1][j])) + 1;
                }
            }
        }

        return numEdits[stringA.length()][stringB.length()];
    }

    public static void main (String args[]) {
        EditDistanceStringSimilarity o = new EditDistanceStringSimilarity();
        o.removeCharsToMakeEqual("goat", "gate");
        o.removeCharsToMakeEqual("ago", "aga");
        o.removeCharsToMakeEqual("ago", "ago");
        o.removeCharsToMakeEqual("ago", "agog");

        System.out.println();
        System.out.println(o.getEditsOfAllTypes("kitten", "sitting"));
        System.out.println(o.getEditsOfAllTypes("sitting", "kitten"));
    }

}
