package trees;

import java.util.ArrayList;
import java.util.List;

/*
Given a file system such as:
Folder
    File
    FolderInFolder
        FileInFolderInFolder
    File2
    FolderInFolder2
        FileInFolderInFolder2

 The above will be provided as a string - folder\n\tfile\n\tfolderinfolder.....

 find the file at max depth.

 Approach: This is a pre-order traversal representation of graph.
 The only trick is managing the input string.

 Tricky Algo:
 We can see that the level of the node can be determined by
 the "\t"s preceeding it. Once that is found, we could backtrack to find the next largest \t closest to this node
 and then go from there until we reach the root.

 Proper Algo:
 Construct a graph out of the entire string and find the max depth. To construct the graph create a node
 and then there are 3 cases
 1. The number of delimiters is larger than was there on this node means the next sequence is of a child (recursive call)
 2. The number of delimiters is smaller than was there on this node - The next sequence is of the parent's sibling, return
 3. The number of delimiters is equal to what was on this - The next sequence is this node's sibling, return so parent can make
 it's recursive call

 Lets dial it back a bit and consider hyphens to be our delimiters to represent levels

 a-aa--aaa--bbb-bb
 would be a
            aa
               aaa
               bbb
             bb
   function gets a string, delimiter count value, the delimiter itself and the position to start parsing from, function
   starts with parsing the string until it encounters a delimiter it creates a node out of the string, it counts the
   occurance of the delimiter before the next string and depending on the number it recurses or returns, during recursion
   the node returned from the recursed function becomes this node's child and the recursion happens until the delimter count
   value is lower than the one passed to this function call.

   // The logic above was correct, except when returning from 2 levels below, (from bbb back to aa back to a in our case)
   the substring upto bbb must be removed.

 */
public class MaxDepthFileSystem {

    class Res {
        String rem;
        Node node;

        @Override
        public String toString() {
            return "Res{" +
                    "rem='" + rem + '\'' +
                    ", node=" + node +
                    '}';
        }
    }

    class Node {
        String name;
        List<Node> children;

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", children=" + children +
                    '}';
        }
    }

    public Res createGraph(String fs, Integer currentDelimiterCount, String delimiter) {
        Res res = new Res();
        if (fs == "" || fs == null) {
            return null;
        }
        String nodeName = fs;
        if (fs.contains(delimiter)) {
            //For when only the name of the node is provided and also for last recursive call
            nodeName = fs.substring(0, fs.indexOf(delimiter));
            res.rem = fs.substring(fs.indexOf(delimiter));
        } else {
            nodeName = fs;
            Res nodeRes = new Res();
            Node node = new Node();
            node.name = nodeName;
            nodeRes.node = node;
            nodeRes.rem = "";
            return nodeRes;
        }

//        positionToParseFrom = fs.indexOf(delimiter);
        Node node = new Node();
        node.name = nodeName;
        res.node = node;
        int nextDelimterCount = countDelimiters(res.rem, delimiter);
        ArrayList children = new ArrayList();

        while (nextDelimterCount > currentDelimiterCount) {
            //cRes is child result
            Res cRes = createGraph(res.rem.substring(delimiter.length() * nextDelimterCount), nextDelimterCount, delimiter);
            Node cNode = cRes.node;
            children.add(cNode);
            res.rem = cRes.rem;
            nextDelimterCount = countDelimiters(res.rem, delimiter);
        }
        node.children = children;
        return res;
    }

    private int countDelimiters(String fs, String delimiter) {
        int delimCount = 0;
        while (fs.startsWith(delimiter)) {
            fs = fs.substring(fs.indexOf(delimiter) + delimiter.length());
            delimCount++;
        }
        return delimCount;
    }

    public static void main (String args[]){
        MaxDepthFileSystem o = new MaxDepthFileSystem();
        Res topLevelFileRes = o.createGraph("a-aa--aaa--bbb-bb", 0, "-");
        System.out.println(topLevelFileRes.node);
    }

}
