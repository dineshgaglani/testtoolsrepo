package interviewcamp.time3;

import java.util.*;

public class Graph {
    //Get adjacency list
    //findDfs
    //findBfs
    //cloneDfs
    //cloneBfs
    //Word ladder
    //Topological sort
    //connected components

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

    public List<Integer>[] getAdjacencyList(Integer nodesCount, List<Integer[]> edgePairs) {
        List<Integer>[] adjacencyList = new List[nodesCount];
        Arrays.fill(adjacencyList, new ArrayList<>());
        for (Integer[] edgePair : edgePairs) {
            if (!adjacencyList[edgePair[0]].contains(edgePair[1]))
                adjacencyList[edgePair[0]].add(edgePair[1]);
            if (!adjacencyList[edgePair[1]].contains(edgePair[0]))
                adjacencyList[edgePair[1]].add(edgePair[0]);
        }
        return adjacencyList;
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
            if(neighbor.state == 0) {
                found = findDfsRecur(neighbor, target);
                if (found != null) {
                    break;
                }
            }
        }
        return found;
    }

    public Node findBfs(Integer target, Node startNode) {
        Queue<Node> bfs = new LinkedList();
        bfs.add(startNode);
        startNode.state = 1;
        while (!bfs.isEmpty()) {
            Node node = bfs.remove();
            if (node.data == target) {
                return node;
            }
            for(Node neighbor : node.neighbors) {
                if (neighbor.state == 0) {
                    neighbor.state = 1;
                    bfs.add(neighbor);
                }
            }
        }
        return null;
    }

    public Node cloneDfs(Node startNode) {
        Map<Node, Node> clones = new HashMap<>();
        return cloneDfsRecur(startNode, clones);
    }

    private Node cloneDfsRecur(Node node, Map<Node, Node> clones) {
        Node clone;
        //MISTAKE - THIS CAN BE OPTIMIZED - CHECK time2/graph
        if (clones.containsKey(node)) {
            clone = clones.get(node);
        } else {
            clone = new Node(node.data);
            clones.put(node, clone);
        }
        for (Node neighbor : node.neighbors) {
            Node neighborClone;
            if (clones.containsKey(neighbor)) {
                neighborClone = clones.get(neighbor);
            } else {
                neighborClone = cloneDfs(neighbor);
                //MISTAKE - THE DFS CALL ALREADY ADDS THE CLONE TO THE MAP
                clones.put(neighbor, neighborClone);
            }
            clone.neighbors.add(neighborClone);
        }
        return clone;
    }

    public Node cloneBfs(Node startNode) {
        Map<Node, Node> clones = new HashMap<>();
        Node clonedStart = new Node(startNode.data);
        clones.put(startNode, clonedStart);
        Queue<Node> bfs = new LinkedList<>();
        bfs.add(startNode);
        while(!bfs.isEmpty()) {
            Node node = bfs.remove();
            //WE CAN DO THIS BECAUSE WE CLONE WHEN WE ENCOUNTER THE NEIGHBOR TO ADD IT TO THE QUEUE, SO ITS THERE IN THE CLONES MAP
            Node clone = clones.get(node);
            for (Node neighbor : node.neighbors) {
                Node neighborClone;
                if (!clones.containsKey(neighbor)) {
                    neighborClone = new Node(neighbor.data);
                    clones.put(neighbor, neighborClone);
                    bfs.add(neighbor);
                }
                neighborClone = clones.get(neighbor);
                clone.neighbors.add(neighborClone);
            }
        }
        return clonedStart;
    }

    public Integer wordLadder(String source, String destination, Set<String> dictionary) {
        Map<String, List<String>> wordMap = getWordMap(dictionary);
        Queue<String> bfs = new LinkedList<>();
        Map<String, Integer> visited = new HashMap<>();
        visited.put(source, 0);
        bfs.add(source);
        while (!bfs.isEmpty()) {
            String word = bfs.remove();
            Integer wordLevel = visited.get(word);
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i+1);
                List<String> neighbors = wordMap.get(key);
                for (String neighbor : neighbors) {
                    if (neighbor == destination) {
                        return wordLevel + 1;
                    }
                    if (!visited.containsKey(neighbor)) {
                        visited.put(neighbor, wordLevel + 1);
                        bfs.add(neighbor);
                    }
                }
            }
        }
        return -1;
    }

    private Map<String, List<String>> getWordMap(Set<String> dictionary) {
        Map<String, List<String>> wordMap = new HashMap<>();
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i+1);
                if (!wordMap.containsKey(key)) {
                    wordMap.put(key, new ArrayList<>());
                }
                wordMap.get(key).add(word);
            }
        }
        return wordMap;
    }

    public List<Integer> getTopologicallySorted(List<Integer>[] adjacencyList) {
        List<Integer> topological = new ArrayList<>();
        Boolean[] visited = new Boolean[adjacencyList.length];
        for (int i = 0; i < adjacencyList.length; i++) {
            if (!visited[i]) {
                fillTopologicalBfs(i, adjacencyList, visited, topological);
            }
        }
        return topological;
    }

    private void fillTopologicalBfs(int currNode, List<Integer>[] adjacencyList, Boolean[] visited, List<Integer> topological) {
        visited[currNode] = true;
        for (Integer neighbor : adjacencyList[currNode]) {
            if(!visited[neighbor]) {
                fillTopologicalBfs(neighbor, adjacencyList, visited, topological);
            }
        }
        topological.add(currNode);
    }
}
