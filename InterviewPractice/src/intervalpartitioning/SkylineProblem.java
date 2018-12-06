package intervalpartitioning;

/*
    Given a set of intervals each having a height and we need to print the skyline
    (the max height in the current set of intervals).

    We need max heap with a reference to every node so that it can be deleted randomly

    We first sort the intervals by times (the first start time is the first entry).
    We iterate the interval starting with the first start time interval, we put the height for this in a max heap
    When we encounter another interval we put that in the max heap
    When we encounter an interval end we remove that entry from the max heap.

    So, we need a way to identify which Interval is being ended and the height to interval mapping has to be present
    in the heap

    Algorithm: Create a max heap where every node (of value Height) will be a hashmap value with the key being an interval (intervalToHeapNodeMap)
    Sort the input intervals by startTime
    Create another map that contains every Time mapped to it's respective interval (timeToIntervalMap)
    Create a hashmap entry with the first interval as the key and its height as value, put the height in the max heap.
    Start drawing the skyline with the value in the heap
    Iterate to the next Time in the sorted list, if its a startTime (Interval2 or i2), create a map and
    put that item in the max heap. If i2.height > i1.height then maxHeap top changes and it draws the current height,
    otherwise it remains at the existing height. If there is an end time, we get the interval for that node and through
    interval we get the heap node for the interval and remove it from the map. We display the max in the heap every time
    we encounter a new start node.
*/
public class SkylineProblem {

}
