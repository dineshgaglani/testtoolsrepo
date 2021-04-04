package interviewcamp.time2;

import java.util.*;

public class Graph {

    List<Node> topLevelNodes = new ArrayList<>();

    class Node {
        Integer data;
        List<Node> neighbors = new ArrayList<>();
        //Unvisited = 0, visited = 1, visiting = 2
        Integer state = 0;

        Node(Integer data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data + "";
        }
    }

    public List<Integer>[] getAdjacencyList(Integer numNodes, List<Integer[]> edgePairs) {
        List<Integer>[] adjList = new ArrayList[numNodes];
        Arrays.fill(adjList, new ArrayList<>());
        for (Integer[] edgePair : edgePairs) {
            if (!adjList[edgePair[0]].contains(edgePair[1])) { adjList[edgePair[0]].add(edgePair[1]); }
            if (!adjList[edgePair[1]].contains(edgePair[0])) { adjList[edgePair[1]].add(edgePair[0]); }
        }
        return adjList;
    }

    public Node findDfs(Integer target) {
        Node found = null;
        for (Node topLevelNode : topLevelNodes) {
            found = findDfsRecur(topLevelNode, target);
        }
        return found;
    }

    private Node findDfsRecur(Node node, Integer target) {
        node.state = 1;
        if (node.data == target) {
            return node;
        }
        Node found = null;
        for (Node neighbor : node.neighbors) {
            if (neighbor.state == 0) {
                found = findDfsRecur(neighbor, target);
            }
        }
        return found;
    }

    public Node findBfs(Integer target) {
        for (Node topLevelNode : topLevelNodes) {
            Queue<Node> bfs = new LinkedList<>();
            topLevelNode.state = 1;
            bfs.add(topLevelNode);
            while (!bfs.isEmpty()) {
                Node n = bfs.remove();
                for (Node neighbor : n.neighbors) {
                    if (neighbor.data == target) {
                        return neighbor;
                    }
                    if (neighbor.state == 0) {
                        bfs.add(neighbor);
                    }
                }
            }
        }
        return null;
    }

    public Node cloneDfs(Node startNode) {
        HashMap<Node, Node> cloneMap = new HashMap<>();
        Node cloned = cloneDfsRecur(startNode, cloneMap);
        return cloned;
    }

    private Node cloneDfsRecur(Node startNode, HashMap<Node, Node> cloneMap) {
        Node clone;
        if (!cloneMap.containsKey(startNode)) {
            cloneMap.put(startNode, new Node(startNode.data));
        }
        clone = cloneMap.get(startNode);
        for (Node neighbor : startNode.neighbors) {
            Node neighborClone;
            if(!cloneMap.containsKey(neighbor)) {
                cloneDfsRecur(neighbor, cloneMap);
            }
            neighborClone = cloneMap.get(neighbor);
            clone.neighbors.add(neighborClone);
        }

        return clone;
    }

    public void wordLadder(String source, String destination, Set<String> dictionary) {
        Map<String, List<String>> neighborsMap = getNeighborsMap(dictionary);
        Queue<String> bfs = new LinkedList<>();
        HashMap<String, Integer> visitedWords = new HashMap<>();
        bfs.add(source);
        visitedWords.put(source, 0);

        while (!bfs.isEmpty()) {
            String word = bfs.remove();
            if (word.equals(destination)) {
                System.out.println("Destination " + destination + " found at level: " + visitedWords.get(word));
            }
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i+1);
                List<String> neighbors = neighborsMap.get(key);
                for (String neighbor : neighbors) {
                    if (!visitedWords.containsKey(neighbor)) {
                        bfs.add(neighbor);
                        visitedWords.put(neighbor, visitedWords.get(word) + 1);
                    }
                }
            }
        }
    }

    private Map<String, List<String>> getNeighborsMap(Set<String> dictionary) {
        Map<String, List<String>> neighborsMap = new HashMap<>();
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0,i) + "*" + word.substring(i+1);
                if (!neighborsMap.containsKey(key)) {
                    neighborsMap.put(key, new ArrayList<>());
                }
                neighborsMap.get(key).add(word);
            }
        }
        return neighborsMap;
    }


    public Node cloneBfs(Node startNode) {
        HashMap<Node, Node> clonesMap = new HashMap<>();
        Queue<Node> bfs = new LinkedList();
        bfs.add(startNode);
        Node startClone = new Node(startNode.data);
        clonesMap.put(startNode, startClone);

        while (!bfs.isEmpty()) {
            Node n = bfs.remove();
            Node nClone = clonesMap.get(n);
            for (Node neighbor : n.neighbors) {
                Node neighborClone;
                if (clonesMap.containsKey(neighbor)) {
                    neighborClone = clonesMap.get(neighbor);
                } else {
                    neighborClone = new Node(neighbor.data);
                    clonesMap.put(neighbor, neighborClone);
                    bfs.add(neighbor);
                }
                nClone.neighbors.add(neighborClone);
            }
        }

        return startClone;
    }

    public List<Integer> topologicalSort(List<Integer>[] adjacencyLists) {
        List<Integer> topologicallySortedList = new ArrayList<>();
        Boolean[] visited = new Boolean[adjacencyLists.length];
        for (int i = 0; i < adjacencyLists.length; i++) {
            fillTopologicalList(i, adjacencyLists, topologicallySortedList, visited);
        }
        return topologicallySortedList;
    }

    private void fillTopologicalList(int currIndex, List<Integer>[] adjacencyLists, List<Integer> topologicallySortedList, Boolean[] visited) {
        if (visited[currIndex]) {
            return;
        }
        visited[currIndex] = true;
        for (Integer neighbor : adjacencyLists[currIndex]) {
            fillTopologicalList(neighbor, adjacencyLists, topologicallySortedList, visited);
            topologicallySortedList.add(neighbor);
        }
        topologicallySortedList.add(currIndex);
    }

    public List<List<Integer>> getConnectedComponents() {
        return null;
    }

    public static void main(String args[]) {
        Graph graph = new Graph();
        Graph.Node a = graph.new Node(0);
        Graph.Node b = graph.new Node(1);
        Graph.Node c = graph.new Node(2);
        Graph.Node d = graph.new Node(3);

        a.neighbors.add(b);
        a.neighbors.add(c);
        b.neighbors.add(a);
        b.neighbors.add(c);
        c.neighbors.add(b);
        c.neighbors.add(a);
        c.neighbors.add(d);
        d.neighbors.add(c);

        graph.topLevelNodes.add(a);
        /*
             0 ----> 1
               \     |
                \    |
                  -> 2 -----> 3
         */
        Graph.Node foundDfs = graph.findDfs(2);
        Graph.Node foundBfs = graph.findBfs(2);
        System.out.println(" found dfs neighbors: " + foundDfs.neighbors + " should be 0, 1, 3");
        System.out.println(" found bfs neighbors: " + foundBfs.neighbors + " should be 0, 1, 3");

        Graph.Node clonedDfs = graph.cloneDfs(a);
        Graph.Node clonedBfs = graph.cloneBfs(a);
        //Inspect cloneddfs and clonedbfs

        Graph.Node clonedDfsC = graph.cloneDfs(c);
        Graph.Node clonedBfsC = graph.cloneBfs(c);
        //Inspect cloneddfsc and clonedbfsc
    }

}
