package Graphs;
//
//import java.util.*;
//
///*
//    Mistakes - The Graph should have a list of nodes with the key as node content for retrieval, with each node having its own neighbors.
//                Generate the graph using breadth first
//                Create the graph structure as a map with other maps inside it - but you cannot put in a cycle that way
//    So a map with all nodes is the best way to go because if a child node eventually turns out to have its own child then we need immediate access to ot
//    So constructing it's own key in the map is necessary, this key and the child of another node should be a pointer to the same object
// */
public class BasicSearch {

}
//
//    class Graph {
//        Map<String, Node> nodes;
//    }
//
//    enum State {VISITED, UNVISITED, VISITING};
//
//    class Node {
//        String content;
//        Node (String content) {
//            this.content = content;
//            neighbors = new HashMap<>();
//        }
//
//        Map<String, Node> neighbors;
//        State nodeState = State.UNVISITED;
//
//        @Override
//        public String toString() {
//            return content;
//        }
//
////        @Override
////        public boolean equals(Object obj) {
////            return toString().equals(obj.toString());
////        }
//
////        @Override
////        public int hashCode() {
////            return content;
////        }
//    }
//
//    /*
//        Mistakes - Did not create a graph object, thinking I would return a Node
//        Consequence - The Node doesn't represent the entire graph, disconnected nodes may not be present in nodes, but still will be part of the entire graph
//
//        Was initializing the children list in this block
//        Consequence - If a node was created as a part of the children creation and encountered as a parent node later on then it's children list was not being initialized
//     */
//
//    public Graph buildFromAdjacencyList(String[][] nodesAndNeighbors) {
//        /*
//            1 -> {2, 3}
//            3 -> {2, 4}
//         */
//        Graph graph = new Graph();
//        Map<String, Node> createdNodes = new HashMap<>();
//        graph.nodes = createdNodes;
//
//        Node parentNode = null;
//        for (int i = 0; i < nodesAndNeighbors.length; i++) {
//            String nodeContent = nodesAndNeighbors[i][0];
//
//            parentNode = new Node(nodeContent);
//            if (createdNodes.containsKey(nodeContent)) {
//                parentNode = createdNodes.get(parentNode);
//            } else {
//                createdNodes.put(nodeContent, parentNode);
//            }
//
//            for (int j = 1; j < nodesAndNeighbors[i].length; j++) {
//                String childNodeContent = nodesAndNeighbors[i][j];
//                Node childNode = new Node(childNodeContent);
//                if (createdNodes.containsKey(childNodeContent)) {
//                    childNode = createdNodes.get(childNodeContent);
//                } else {
//                    createdNodes.put(childNodeContent, childNode);
//                }
//
//                parentNode.neighbors.put(childNodeContent, childNode);
//            }
//        }
//        return graph;
//    }
//
//    public Node search (String target, Node node) {
//        node.nodeState = State.VISITED;
//        if (node.content == target) { return  node; }
//        for (Node child : node.neighbors) {
//            if (child.nodeState == State.UNVISITED) {
//                Node found = search(target, child);
//                if (found != null) {
//                    return found;
//                }
//            }
//        }
//        return null;
//    }
//
//    public boolean detectCycles (Graph g) {
//        for (Node parent : g.nodes) {
//            detectCycle(parent);
//        }
//        return false;
//    }
//
//    public boolean detectCycle(Node n) {
//            return false;
//    }
//
//    public Node buildFromAdjacencyMatrix (int[][] nodesAndNeighbors) {
//        return null;
//    }
//
//    public void printTopology (Node node) {
//        for (Node child : node.neighbors) {
//            printTopology(child);
//        }
//        System.out.print(node.content + " <-- ");
//    }
//
//    public void printLongestDistance (Node node) {
//        //Do Toplogical sort, then do a bfs using a queue, marking the level of each node in an array and then pick the max of the array
//    }
//
//    public static void main (String args[]) {
//        Integer[][] adjacencyList = { {1 , 2, 3} , {3, 4}, {4, 5, 6, 3} };
//        BasicSearch basicSearch = new BasicSearch();
//        Graph g = basicSearch.buildFromAdjacencyList(adjacencyList);
//        Node node = basicSearch.search(5, g.nodes.get(1));
//        System.out.println(node.content);
//        basicSearch.printTopology(g.nodes.get(1));
//        String s = "";
//        StringBuilder sb = new StringBuilder();
//
//    }
//
//}
