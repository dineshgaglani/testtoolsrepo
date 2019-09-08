package dailyCodingProblem;

import java.util.*;

/**
 * Created by Dinesh on 9/5/2019.
 *
 * This problem was asked by Facebook.

 Given an unordered list of flights taken by someone, each represented as (origin, destination) pairs, and a starting airport, compute the person's itinerary. If no such itinerary exists, return null. If there are multiple possible itineraries, return the lexicographically smallest one. All flights must be used in the itinerary.

 For example, given the list of flights [('SFO', 'HKO'), ('YYZ', 'SFO'), ('YUL', 'YYZ'), ('HKO', 'ORD')] and starting airport 'YUL', you should return the list ['YUL', 'YYZ', 'SFO', 'HKO', 'ORD'].

 Given the list of flights [('SFO', 'COM'), ('COM', 'YYZ')] and starting airport 'COM', you should return null.

 Given the list of flights [('A', 'B'), ('A', 'C'), ('B', 'C'), ('C', 'A')] and starting airport 'A', you should return the list ['A', 'B', 'C', 'A', 'C'] even though ['A', 'C', 'A', 'B', 'C'] is also a valid itinerary. However, the first one is lexicographically smaller.
 */
public class Problem41IteniraryFromFlights {

    public static List<String> getIteniraryFromFlights(String[][] flights, String first) {

        Map<String, List<String>> sdMap = getMapOfFlights(flights);
        List<List<String>> itens = recurOnSource(sdMap, first, new ArrayList<>());

        return itens.get(0);
    }

    /*
        A -> C, B
        B -> C
        C -> A

        A(C, B) -> C(A) -> A(B) -> B(C) -> C()
        A, C, A, B, C
     */
    private static List<List<String>> recurOnSource(Map<String, List<String>> sdMap, String source, List<String> acc) {
        List<List<String>> ret = new ArrayList<>();

        if (sdMap.containsKey(source)) {
            List<String> des = Collections.synchronizedList(sdMap.get(source));
            for(String de : new ArrayList<>(des)) {
                ret.add(new ArrayList<>());
                des.remove(de);
                acc.clear();
                recurOnSource(sdMap, de, acc);
                acc.add(de);
                des.add(de);
                ret.get(ret.size() - 1).addAll(acc);
            }
        }
        return ret;
    }

    private static Map<String,List<String>> copyOf(Map<String, List<String>> sdMap) {
        Map<String,List<String>> clone = new HashMap<>();
        for (String s : sdMap.keySet()) {
            List<String> values = new ArrayList<>();
            clone.put(s, values);
            for (String des : sdMap.get(s)) {
                values.add(des);
            }
        }
        return clone;
    }


    private static Map<String,List<String>> getMapOfFlights(String[][] flights) {
        Map<String, List<String>> mapOfFlights = new HashMap<>();
        for(int i = 0; i < flights.length; i++) {
            if (mapOfFlights.containsKey(flights[i][0])) {
                mapOfFlights.get(flights[i][0]).add(flights[i][1]);
            } else {
                List<String> des = new ArrayList<>();
                des.add(flights[i][1]);
                mapOfFlights.put(flights[i][0], des);
            }
        }
        return mapOfFlights;
    }


    public static void main(String args[]) {

        List<String> itineraryFromFlights = getIteniraryFromFlights(new String[][] {{"A", "B"}, {"A", "C"}, {"B", "C"}, {"C", "A"} }, "A");
        itineraryFromFlights.equals(Arrays.asList(new String[] {"A", "C", "A", "B", "C"} ));
    }
}
