package dailyCodingProblem;

import java.util.ArrayList;
import java.util.List;

/*
Suppose we represent our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext
The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext
The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1. subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute path to a file within our file system. For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format, return the length of the longest absolute path to a file in the abstracted file system. If there is no file in the system, return 0.
 */
public class Problem17FileGraph {

    class FileNode {
        String fileName;
        List<FileNode> children = new ArrayList<>();
    }

    /* mistake committed - reassigned to a new string but while was checking on older string
    * If counting for sibling I am stripping the level delim off, after which I am counting for calculating if node is child again after returning
    * stripping and counting should be made separate*/
    public Integer countDelim (String strWithDelim, String delim) {
        strWithDelim = strWithDelim.substring(strWithDelim.indexOf(delim));
        int delimsCount = 0;
        while (strWithDelim.substring(0, delim.length()).equals(delim)) {
            strWithDelim = strWithDelim.substring(strWithDelim.indexOf(delim) + delim.length());
            delimsCount++;
        }

        return delimsCount;
    }

    public Boolean isNextNodeChild (String[] fileString, String levelDelim, int currLevel) {
            return countDelim(fileString[0], levelDelim) > currLevel;
    }

    /*
        Mistake committed (2nd time) did not take care for base case when there is no next item delim or level delim
     */
    private FileNode getFileTree(String[] fileString, String nextItemDelim, String levelDelim, int currLevel) {
        if (fileString[0].length() < 1) {
            return  null;
        }

        FileNode fileNode = new FileNode();

        if (!fileString[0].contains(nextItemDelim)) {
            fileNode.fileName = fileString[0];
            fileString[0] = fileString[0].substring(fileNode.fileName.length());
            return fileNode;
        }

        fileNode.fileName = fileString[0].substring(0, fileString[0].indexOf(nextItemDelim));
        //Remaining file str
        fileString[0] = fileString[0].substring(fileString[0].indexOf(nextItemDelim) + nextItemDelim.length());

        while (fileString[0].length() > 0) {
            if (isNextNodeChild(fileString, levelDelim, currLevel)) {
                stripLevelDelims(fileString, levelDelim, currLevel + 1);
                FileNode childNode = getFileTree(fileString, nextItemDelim, levelDelim, currLevel+1);
                fileNode.children.add(childNode);
            } else {
                break;
            }
        }

        return fileNode;
    }

    private void stripLevelDelims(String[] fileString, String levelDelim, int numLevelDelims) {
        fileString[0] = fileString[0].substring(levelDelim.length() * numLevelDelims);
    }

    private String getMaxDepthLength(FileNode rootDir) {
        return "";
    }

    public static void main (String args[]) {

        Problem17FileGraph driver = new Problem17FileGraph();
        String fileString = "dir\\n\\tsubdir1\\n\\t\\tfile1.ext\\n\\t\\tsubsubdir1\\n\\tsubdir2\\n\\t\\tsubsubdir2\\n\\t\\t\\tfile2.ext";
        FileNode rootDir = driver.getFileTree(new String[] {fileString}, "\\n", "\\t", 0);

        System.out.println("root file name: " + rootDir.fileName + " should be 'dir'");
        System.out.println("root first child: " + rootDir.children.get(0).fileName + " should be 'subdir1' " );
        System.out.println("root second child: " + rootDir.children.get(1).fileName + " should be 'subdir2' ");
        System.out.println("root second child child: " + rootDir.children.get(1).children.get(0).fileName + " should be 'subsubdir2'");
        System.out.println("root second child child child: " + rootDir.children.get(1).children.get(0).children.get(0).fileName + " should be 'file2.ext'");

        System.out.println("max depth length: " + driver.getMaxDepthLength(rootDir) + " should be " + 3);
    }



}
