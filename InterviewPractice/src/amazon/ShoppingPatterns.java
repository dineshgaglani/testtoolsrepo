package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Amazon is trying to understand customer shopping patterns and offer items that are regularly bought together to new customers. Each item that has been bought together can be represented as an undirected graph where edges join often bundled products. A group of n products is uniquely numbered from 1 of product_nodes. A trio is defined as a group of three related products that all connected by an edge. Trios are scored by counting the number of related products outside of the trio, this is referred as a product sum.

Given product relation data, determine the minimum product sum for all trios of related products in the group. If no such trio exists, return -1.

Example
products_nodes = 6
products_edges = 6
products_from = [1,2,2,3,4,5]
products_to = [2,4,5,5,5,6]

Product	Related Products
1	2
2	1, 4, 5
3	5
4	2, 5
5	2, 3, 4, 6
6	5
A graph of n = 6 products where the only trio of related products is (2, 4, 5).

The product scores based on the graph above are:

Product	Outside Products	Which Products Are Outside
2	1	1
4	0
5	2	3, 6
In the diagram above, the total product score is 1 + 0 + 2 = 3 for the trio (2, 4, 5).

Function Description

Complete the function getMinScore in the editor below.

getMinScore has the following parameter(s):

   int products_nodes: the total number of products

   int products_edges the total number of edges representing related products

   int products_from[products_nodes]: each element is a node of one side of an edge.

   int products_to[products edges]: each products_to[i] is a node connected to products_from[i]

Returns:

int: the minimum product sum for all trios of related products in the group. If no such trio exists, return -1.

Constraints

1 <= products_nodes <= 500
1 <= products_edges <= min(500, (products_nodes * (products_nodes - 1)) / 2)
1 <= products_from[i], products to[i] <= products_nodes
products_from[i] != products_to[i]

Sample Case 0

STDIN        Funtion
-------        ------------------------------------------------
5   6     ->  products_nodes = 5    products_edges = 6
1   2     ->  products_from[0] = 1   products_to[0] = 2
1   3     ->  products_from[1] = 1   products_to[1] = 3
2   3     ->  products_from[2] = 2   products_to[2] = 3
2   4     ->  products_from[3] = 2   products_to[3] = 4
3   4     ->  products_from[4] = 3   products_to[4] = 4
4   5     ->  products_from[5] = 4   products_to[5] = 5

Sample Output

2

Explanation

There are two possible trios: {1,2,3} and {2,3,4}

The score for {1,2,3} is 0 + 1 + 1 = 2.

The score for {2,3,4} is 1 + 1 + 1 = 3.

Return 2.
 */
public class ShoppingPatterns {

    public static Integer getMinTrio (Integer[] from, Integer[] to) {
        List<Integer>[] adjacency = getAdjacency(from, to);
        Boolean[] visited = new Boolean[adjacency.length];
        Arrays.fill(visited, false);
        List<List<Integer>> trios = new ArrayList<>();
        Integer parent = -1;
        Integer grandParent = -1;
        for (int i = 0; i < adjacency.length; i++) {
            if (!visited[i]) {
                dfs(adjacency, visited, trios, parent, grandParent, i);
            }
        }

        Integer minN = Integer.MAX_VALUE;
        for (int i = 0; i < trios.size(); i++) {
            int neighborsCount = 0;
            for(Integer trioMember : trios.get(i)) {
                List<Integer> trioMemberNeighbors = adjacency[trioMember];
                for (Integer trioMemberNeighbor : trioMemberNeighbors) {
                    if(!trios.get(i).contains(trioMemberNeighbor)) {
                        neighborsCount++;
                    }
                }
                minN = Math.min(neighborsCount, minN);
            }
        }

        return minN;
    }

//    0 =>	1
//    1 =>	0, 3, 4
//    2 =>	4
//    3 =>	1, 4
//    4 =>	1, 2, 3, 5
//    5 =>	4
    public static void dfs(List<Integer>[] adjacency, Boolean[] visited, List<List<Integer>> trios, Integer parent, Integer grandParent, int curr) {
        visited[curr] = true;
        for (Integer child : adjacency[curr]) {
            if (child.equals(grandParent)) {
                // We have a trio
                trios.add(Arrays.asList(new Integer[] {grandParent, parent, curr}));
            }
            if (!visited[child]) {
                dfs(adjacency, visited, trios, curr, parent, child);
            }
        }
    }

    public static List<Integer>[] getAdjacency(Integer[] from, Integer[] to) {
        List<Integer>[] adjacency = new List[from.length];
        Arrays.fill(adjacency, new ArrayList<Integer>());
        for (int i = 0; i < adjacency.length; i++) {
            adjacency[from[i]].add(to[i]);
            adjacency[to[i]].add(from[i]);
        }
        return adjacency;
    }

    public static void main (String args[]) {
//        List<Integer> adj1 = Arrays.asList(new Integer[] {1});
//        List<Integer> adj2 = Arrays.asList(new Integer[] {0, 3, 4});
//        List<Integer> adj3 = Arrays.asList(new Integer[] {4});
//        List<Integer> adj4 = Arrays.asList(new Integer[] {1, 4});
//        List<Integer> adj5 = Arrays.asList(new Integer[] {1, 2, 3, 5});
//        List<Integer> adj6 = Arrays.asList(new Integer[] {4});
//
//        List<Integer>[] adjacency = new List[6];
//        adjacency[0] = adj1;
//        adjacency[1] = adj2;
//        adjacency[2] = adj3;
//        adjacency[3] = adj4;
//        adjacency[4] = adj5;
//        adjacency[5] = adj6;
//
//        Boolean[] visited = new Boolean[6];
//        Arrays.fill(visited, false);
//        List<List<Integer>> trios = new ArrayList<>();
//        Integer parent = -1;
//        Integer grandParent = -1;
//        for (int i = 0; i < adjacency.length; i++) {
//            if (!visited[i]) {
//                dfs(adjacency, visited, trios, parent, grandParent, i);
//            }
//        }
//
//        System.out.println("Trios!");
//        trios.stream().forEach(System.out::println);

        List<Integer> adj1 = Arrays.asList(new Integer[] {1, 2});
        List<Integer> adj2 = Arrays.asList(new Integer[] {0, 2, 3});
        List<Integer> adj3 = Arrays.asList(new Integer[] {0, 1, 3});
        List<Integer> adj4 = Arrays.asList(new Integer[] {1, 2, 4});
        List<Integer> adj5 = Arrays.asList(new Integer[] {3});
//        List<Integer> adj6 = Arrays.asList(new Integer[] {4});

        List<Integer>[] adjacency = new List[5];
        adjacency[0] = adj1;
        adjacency[1] = adj2;
        adjacency[2] = adj3;
        adjacency[3] = adj4;
        adjacency[4] = adj5;
//        adjacency[5] = adj6;

        Boolean[] visited = new Boolean[5];
        Arrays.fill(visited, false);
        List<List<Integer>> trios = new ArrayList<>();
        Integer parent = -1;
        Integer grandParent = -1;
        for (int i = 0; i < adjacency.length; i++) {
            if (!visited[i]) {
                dfs(adjacency, visited, trios, parent, grandParent, i);
            }
        }

        System.out.println("Trios!");
        trios.stream().forEach(System.out::println);
    }
}
