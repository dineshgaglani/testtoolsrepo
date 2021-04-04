package interviewcamp.time1;

import java.util.*;

/*
BFS Questions - Whether an operation needs to be performed on dequeue or enqueue and when is it appropriate to enqueue
 */
public class Graph {

    List<Node> topLevelNodes = new ArrayList<>();

    static Integer UNVISITED = 0;
    static Integer VISITED = 2;
    static Integer VISITING = 1;

    //Why do we need this?
    class Node {
        Integer data;
        List<Node> children = new ArrayList<>();
        //UNVISITED - 0, VISTED - 2, VISITINIG - 1
        Integer state = 0;

        Node(Integer data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data + "";
        }
    }


    //Input is {0, 1} {0, 3} {1, 2} {2, 1} {3, 4} output is 0 --> {1,3}, 1 --> {0, 2}, 2 ---> {1}, 3 ---> {0, 4}, 4---> 3 {
    public List<Integer>[] getAdjacencyList(Integer nodesNum, Integer[][] edgePairs) {
        List<Integer>[] adjacencyList = new ArrayList[nodesNum];
        Arrays.fill(adjacencyList, new ArrayList<>());
        for (Integer[] edgePair : edgePairs) {
            if (!adjacencyList[edgePair[0]].contains(edgePair[1])) { adjacencyList[edgePair[0]].add(edgePair[1]); }
            if (!adjacencyList[edgePair[1]].contains(edgePair[0])) { adjacencyList[edgePair[1]].add(edgePair[0]); }
        }

        return adjacencyList;
    }

    public Node findNodeDFS(Integer target) {
        Node found = null;
        for (Node n : topLevelNodes) {
            found = findNodeRecur(n, target);
        }
        return found;
    }

    private Node findNodeRecur(Node n, Integer target) {
        n.state = 1;
        if (n.data == target) {
            return n;
        }
        Node f = null;
        for (Node child : n.children) {
            if (child.state == 0) {
                f = findNodeRecur(child, target);
            }
        }
        return f;
    }

    public Node findNodeBFS(Integer target) {
        Queue<Node> bfs = new LinkedList<>();
        for (Node n : topLevelNodes) {
            bfs.add(n);
            while (!bfs.isEmpty()) {
                n = bfs.remove();
                //MISTAKE - Not checking for unvisited while adding to queue
                if (n.data == target) { return n; }
                bfs.addAll(n.children);
            }
        }
        return null;
    }

    public Node cloneGraphDFS(Node startNode) {
        /*
            Naive approach -
                recursion -
                base case: startNode has no neighbors (if node.neighbors.size == 0, return clone of startNode)
                regular case: if startNode has neighbors, return cloned neighbor sub-graph (proceeding to base case) and attach that to startNode as neighbor
            The above will work if there are no cycles in the graph.

            (If there are cycles - which there are in undirected graphs)
            When I get to a node that is part of a cycle (a <--> b = I get to b from a and then see I've visited 'a', so I
            don't need to clone it.. but what value do I put on b's clone as neighbor. I need a reference to a's clone
            so I can add it as b's clone's neighbor. so, I'll need a map of originals and clones that I pass into the
            recursive function.

            Recommended solution:
            Recursive function that takes in a node and a map of originals to clones and returns a clone.
            Base-case: if a node has no children and is not in the map then add it to map and return it (if already in map,
            return from map).
            Regular case: clone the node, add the clone to the map, Iterate the node's neighbors. If the neighbor is
            present in the map, get clone from the map. Otherwise, recursively call clone on the neighbor, when the
            cloned neighbor graph is returned add it as a neighbor to this current stack's node's clone
         */
        HashMap<Node, Node> clonesMap = new HashMap<>();
        Node cloned = cloneGraphDFSRecur(startNode, clonesMap);
        return cloned;
    }

    private Node cloneGraphDFSRecur(Node startNode, HashMap<Node, Node> clonesMap) {
        if (!clonesMap.containsKey(startNode)) {
            Node clone = new Node(startNode.data);
            clonesMap.put(startNode, clone);
        }
        Node clone = clonesMap.get(startNode);
        for (Node child : startNode.children) {
            Node childClone;
            if(!clonesMap.containsKey(child)) {
                childClone = cloneGraphDFSRecur(child, clonesMap);
                clonesMap.put(child, childClone);
            } else {
                childClone = clonesMap.get(child);
            }
            clone.children.add(childClone);
        }
        return clone;
    }

    public Node cloneGraphBFS(Node startNode) {
        /*
           Naive: Put start node in a queue, and then while queue has elements dequeue the head, clone it, queue all it's
           nodes (these will go at the end of the queue after the other children of the parent).
           Question: How to assign neighbors, how do which the neighbors are to the current node's clone?
           Neighbor clones are assigned before we move the node to the queue (when we iterate to the child from the parent),
           this way we know both the parent and the child.
           This was works as long as we don't have a cycle, with a cycle as with dfs if we don't have a reference of the clone,
           then

           Points to remember: Should clone node before enquing since we have the parent reference only at enqueue time
         */
        Queue<Node> bfs = new LinkedList();
        HashMap<Node, Node> clonesMap = new HashMap<>();
        bfs.add(startNode);
        Node clone = new Node(startNode.data);
        clonesMap.put(startNode, clone);
        while (!bfs.isEmpty()) {
            Node n = bfs.remove();
            //MISTAKE - Cloning node on deque
//            if (!clonesMap.containsKey(n)) {
//                clonesMap.put(n, new Node(n.data));
//            }
            Node nClone = clonesMap.get(n);
            for (Node child : n.children) {
                Node childClone;
                if (clonesMap.containsKey(child)) {
                    childClone = clonesMap.get(child);
                } else {
                    childClone = new Node(child.data);
                    bfs.add(child);
                    clonesMap.put(child, childClone);
                }
                nClone.children.add(childClone);
            }
        }
        return clone;
    }

    public void wordLadder(String source, String destination, Set<String> dictionary) {
        /*
            Populate the neighbor list (dfs queue) using the dictionary given
         */
         Queue<String> bfs = new LinkedList<>();
         bfs.add(source);
         int level = 0;
         bfs.add("\n");
         Map<String, List<String>> neighborsMap = getNeighborsMap(dictionary);
         //Correction of mistake 1 (line 186)
         Map<String, Integer> vistedWords = new HashMap<>();
         vistedWords.put(source, 0);
         //End
         while(!bfs.isEmpty()) {
                String currWord = bfs.remove();
                if (currWord.equals("\n")) {
                    level++;
                }
                if (currWord.equals(destination)) {
                    System.out.println("Word " +  currWord + " found at level " + level);
                }
                for (int i = 0; i < currWord.length(); i++) {
                    String currKey = currWord.substring(0, i) + "*" + currWord.substring(i+1);
                    //MISTAKE 1 - this will only remove current word, not everything that has been seen before
                    //Solution: Either replace the list for the current key after removing the word, or maintain a list of visited words
                    //neighborsMap.get(currKey).remove(currWord);

                    //CORRECTION
                    List<String> neighbors = neighborsMap.get(currKey);
                    for (String neighbor : neighbors) {
                        if (!vistedWords.containsKey(neighbor)) {
                            if (!vistedWords.containsKey(neighbor)) {
                                vistedWords.put(neighbor, vistedWords.get(currWord) + 1);
                                bfs.add(neighbor);
                            }
                        }
                    }

                }
                bfs.add("\n");
         }
    }

    private Map<String, List<String>> getNeighborsMap(Set<String> dictionary) {
        /*
         For every word in the dictionary, turn the character at each index into *, make the string with the * the key
         and add the word in the value list for that key
         */
        Map<String, List<String>> neighborsMap = new HashMap<>();
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1);
                if (!neighborsMap.containsKey(key)) {
                    neighborsMap.put(key, new ArrayList<>());
                }
                neighborsMap.get(key).add(word);
            }
        }
        return neighborsMap;
    }

    public List<Integer> topologicalSort(List<Integer>[] adjacencyLists) {
        List<Integer> topologicallySorted = new ArrayList<>();
        Boolean[] visited = new Boolean[adjacencyLists.length];
        Arrays.fill(visited, false);
        for (int i = 0; i < adjacencyLists.length; i++) {
            if (!visited[i]) {
                fillTopologicallySortedDfs(i, adjacencyLists, topologicallySorted, visited);
            }
        }
        return topologicallySorted;
    }

    private void fillTopologicallySortedDfs(Integer currNodeIndex, List<Integer>[] adjacencyLists, List<Integer> topologicallySorted, Boolean[] visited) {
        if (visited[currNodeIndex]) {
            return;
        }
        visited[currNodeIndex] = true;
        for (Integer neighbor : adjacencyLists[currNodeIndex]) {
            fillTopologicallySortedDfs(neighbor, adjacencyLists, topologicallySorted, visited);
            topologicallySorted.add(neighbor);
        }
        topologicallySorted.add(currNodeIndex);
    }

    public List<List<Integer>> getConnectedComponents() {
        return null;
    }


    public Integer getShortestDistanceDFS(Node from, Node to) {
        return null;
    }

    public Integer getShortestDistanceBFS(Node from, Node to) {
        return null;
    }

    public boolean isCycleExists(Graph directedGraph) {
        boolean isCycleExists = false;
        for (Node topLevelNode : topLevelNodes) {
            isCycleExists = isCycleExists(topLevelNode);
            if (isCycleExists) {return true;}
        }
        return false;
    }

    private boolean isCycleExists(Node node) {
        if (node.state == VISITING) {
            return true;
        }
        boolean isCycleExists = false;
        node.state = VISITING;
        for (Node neighbor : node.children) {
            if (neighbor.state == VISITED) {
                return false;
            }
            isCycleExists = isCycleExists(neighbor);
            if (isCycleExists) {
                break;
            }
        }
        node.state = VISITED;
        return isCycleExists;
    }

    public static void main(String args[]) {
        Graph graph = new Graph();
        Node a = graph.new Node(0);
        Node b = graph.new Node(1);
        Node c = graph.new Node(2);
        Node d = graph.new Node(3);

        a.children.add(b);
        a.children.add(c);
        b.children.add(a);
        b.children.add(c);
        c.children.add(b);
        c.children.add(a);
        c.children.add(d);
        d.children.add(c);

        graph.topLevelNodes.add(a);
        /*
             0 ----> 1
               \     |
                \    |
                  -> 2 -----> 3
         */
        Node foundDfs = graph.findNodeDFS(2);
        Node foundBfs = graph.findNodeBFS(2);
        System.out.println(" found dfs neighbors: " + foundDfs.children + " should be 0, 1, 3");
        System.out.println(" found bfs neighbors: " + foundBfs.children + " should be 0, 1, 3");

        Node clonedDfs = graph.cloneGraphDFS(a);
        Node clonedBfs = graph.cloneGraphBFS(a);
        //Inspect cloneddfs and clonedbfs

        Node clonedDfsC = graph.cloneGraphDFS(d);
        Node clonedBfsC = graph.cloneGraphBFS(c);
        //Inspect cloneddfsc and clonedbfsc
    }

}
