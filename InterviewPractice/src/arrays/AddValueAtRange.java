package arrays;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class AddValueAtRange {

    // Complete the arrayManipulation function below.
//     static long arrayManipulation(int n, int[][] queries) {
//         long[] arr = new long[n];
//         //Brute force - change the array elements everytime
//         for (int i = 0; i < n; i++) {
//             arr[i] = 0l;
//         }

//         for (int qc = 0; qc < queries.size; qc++) {
//             long startIndex = queries[qc][0];
//             long endIndex = queries[qc][1];
//             long k = queries[qc][2];

//             for (long i = startIndex; i < endIndex; i++) {
//                 arr[i] = arr[i] + k;
//             }
//         }

//         return findMax(arr, 0l);

//     }

    // static Long findMax(long[] arr, long index) {
    //     return Math.max(arr[index], findMax(arr, index + 1));
    // }

    // Complete the arrayManipulation function below.
//     static long arrayManipulation(int n, int[][] queries) {
//         //Part Optimized - store only the indexes that change as a hashmap
//         Map<Long, Long> rangesMap = new HashMap<>();
//         for (int qc = 0; qc < queries.length; qc++) {
//             long startIndex = queries[qc][0];
//             long endIndex = queries[qc][1];
//             long k = queries[qc][2];

//             for (Long i = startIndex; i <= endIndex; i++) {
//                 if (rangesMap.containsKey(i)) {
//                     rangesMap.put(i, rangesMap.get(i) + k);
//                 } else {
//                     rangesMap.put(i, k);
//                 }
//             }
//         }

//         return findMaxInMap(rangesMap);
//     }

    static long arrayManipulation(int n, int[][] queries) {
        //Optimized - store differences array, this way there is no need to bulk change
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = 0l;
        }

        for (int qc = 0; qc < queries.length; qc++) {
            int startIndex = queries[qc][0] - 1;
            int endIndex = queries[qc][1];
            long k = queries[qc][2];

            arr[startIndex] = arr[startIndex] + k;
            //pos 0 is 3
            //pos 3 is 7
            if (endIndex < arr.length) {
                arr[endIndex] = arr[endIndex] - k;
                //pos 5 is -3
                //pos 7 is -7
            }

            // array is 3 0 0 7 0 -3 0 -7 0 0 0
            // values are 3 3 3 10 10 7 7 0 0 0 0

        }

        return findMax(arr);
    }

    static long findMax(long[] arr) {
        long max = -1;
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(sum, sum + arr[i]);
            sum = sum + arr[i];
        }

        return max;
    }

//     public static Long findMaxInMap(Map<Long, Long> rangesMap) {
//         Long max = -1l;
//         for (Long key : rangesMap.keySet()) {
//             max = Math.max(rangesMap.get(key), max);
//         }

//         return max;
//     }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
