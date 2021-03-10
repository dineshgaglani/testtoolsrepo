package amazon;

import java.awt.geom.Point2D;
import java.util.*;

/*
AWS CloudFront wants to build an algo to measure the efficiency of its caching network. The network is represented as a number of nodes and a list of connected pairs. The efficiency of this network can be estimated by first summing the cost of each isolated set of nodes where each individual node has a cost of 1. To account for the increase in efficiency as more nodes are connected, update the cost of each isolated set to be the ceiling of the square root of the original cost and return the final sum of all costs.

Example:
n = 10 nodes
edges = [[1 2] , [1 3] , [2 4] , [3 5] , [7 8]]

There are 2 isloated sets with more than one node {1,2,3,4,5} and {7,8}. The ceilings of their square roots are:
5^1/2 = 2.236 and ceil(2.236) = 3
2^1/2 = 1.414 and ceil(1.414) = 2

The other three isolated nodes are separate and the square root of their weights is 1^1/2 = 1 respectively.
The sum is 3+2+(3*1) = 8

Function Description
Complete the function connectedSum in the editor below
connectedSum has the following parameter(s):
int n: the number of nodes
str edges[m]: an array of strings that consist of a space-separated integer pair that denotes two connected nodes, p and q
Returns:
int: an integer that denotes the sum of the values calculated

Constraints:
2 <= n <= 10^5
1 <= m <=10^5
1 <= p,q <= n
p != n

Sample Input 0
n = 4
edges[] = ["1 2", "1 4"]

Sample Output 0
3

Explanation
The values to sum are:
Set {1,2,4}: c =ceil(sqrt(3)) = 2
Set {3}: c = ceil(sqrt(1)) = 1
Return 2+1=3

Sample Input 1
n = 8
edges[] = ["8 1",  "5 8", "7 3", "8 6"]

Sample Output 1
6

Explanation
The values to sum for each group are:
Set {2}: c =ceil(sqrt(1)) = 1
Set {4}: c = ceil(sqrt(1)) = 1
Set {1,5,6,8}: c = ceil(sqrt(4)) = 2
Set {3,7}: c = ceil(sqrt(2)) = 2
return 1+1+2+2 = 6

Approach: Create slots for each 'node' in the network, provide it's connection as a value of the node, if
a node is already connected then connect to it's connection, at the end if 2 nodes are connected to the same connection,
they belong to the same network
 */
public class CloudfrontCaching {

   public static Double getEfficiency(Integer nodes, List<List<Integer>> connections) {
        List<Integer>[] adjacents = createAdjacency(nodes, connections);
        List<List<Integer>> networks = createNetworksFromAdjacent(nodes, adjacents);
        Double res = 0d;
        for (List<Integer> network : networks) {
            res = res + Math.ceil(Math.sqrt(network.size()));
        }

        return res;
   }

   //edges = [[0 1] , [0 2] , [1 3] , [2 4] , [6 7], [9 8]], nodes = 10  gets converted to [1, 2] [0, 3] [0, 4] [1] [2] [] [7] [6] [9] [8]
   public static List<Integer>[] createAdjacency(Integer nodes, List<List<Integer>> connections) {
       List<Integer>[] adjacents = new List[nodes];
       for (int i = 0; i < nodes; i++) {
           adjacents[i] = new ArrayList<>();
       }

       for (List<Integer> connection : connections) {
            adjacents[connection.get(0)].add(connection.get(1));
            adjacents[connection.get(1)].add(connection.get(0));
       }

       return adjacents;
   }

   //[1, 2] [0, 3] [0, 4] [1] [2] [] [7] [6] [9] [8] gets converted to [0, 1, 3, 2, 4] [5] [6, 7] [9, 8]
   public static List<List<Integer>> createNetworksFromAdjacent(Integer nodes, List<Integer>[] adjacents) {
        Boolean[] visited = new Boolean[nodes];
        Arrays.fill(visited, false);
        List<List<Integer>> networks = new ArrayList<>();
        for (int i = 0; i < adjacents.length; i++) {
            if (!visited[i]) {
                List<Integer> currNetwork = new ArrayList<>();
                currNetwork.add(i);
                visited[i] = true;
                dfs(adjacents, visited, i, currNetwork);
                networks.add(currNetwork);
            }
        }
        return networks;
   }

    private static void dfs(List<Integer>[] adjacents, Boolean[] visited, int startIndex, List<Integer> network) {
       List<Integer> currAdjacents = adjacents[startIndex];
       for (Integer adjacent : currAdjacents) {
            if (!visited[adjacent]) {
                network.add(adjacent);
                visited[adjacent] = true;
                dfs(adjacents, visited, adjacent, network);
            }
       }
    }

    public static void main(String args[]) {
        List<Integer> adj1 = Arrays.asList(new Integer[] { 1, 2 });
        List<Integer> adj2 = Arrays.asList(new Integer[] { 0, 3 });
        List<Integer> adj3 = Arrays.asList(new Integer[] { 0, 4 });
        List<Integer> adj4 = Arrays.asList(new Integer[] { 1 });
        List<Integer> adj5 = Arrays.asList(new Integer[] { 2 });
        List<Integer> adj6 = Arrays.asList(new Integer[] { 5 });
        List<Integer> adj7 = Arrays.asList(new Integer[] { 7 });
        List<Integer> adj8 = Arrays.asList(new Integer[] { 6 });
        List<Integer> adj9 = Arrays.asList(new Integer[] { 9 });
        List<Integer> adj10 = Arrays.asList(new Integer[] { 8 });

        List<Integer>[] adjacents = new List[10];
        adjacents[0] = adj1;
        adjacents[1] = adj2;
        adjacents[2] = adj3;
        adjacents[3] = adj4;
        adjacents[4] = adj5;
        adjacents[5] = adj6;
        adjacents[6] = adj7;
        adjacents[7] = adj8;
        adjacents[8] = adj9;
        adjacents[9] = adj10;

        System.out.println(createNetworksFromAdjacent(10, adjacents));

        //[0 1] , [0 2] , [1 3] , [2 4] , [6 7], [9 8]
        List<Integer> conn1 = Arrays.asList(new Integer[] { 0, 1} );
        List<Integer> conn2 = Arrays.asList(new Integer[] { 0, 2} );
        List<Integer> conn3 = Arrays.asList(new Integer[] { 1, 3} );
        List<Integer> conn4 = Arrays.asList(new Integer[] { 2, 4} );
        List<Integer> conn5 = Arrays.asList(new Integer[] { 6, 7} );
        List<Integer> conn6 = Arrays.asList(new Integer[] { 9, 8} );

        List<List<Integer>> connections = new ArrayList<>();
        connections.add(conn1);
        connections.add(conn2);
        connections.add(conn3);
        connections.add(conn4);
        connections.add(conn5);
        connections.add(conn6);

        System.out.println("Adjacency: ");
        Arrays.stream(createAdjacency(10, connections)).forEach(System.out::println);
        System.out.println(getEfficiency(10, connections));
    }
}
