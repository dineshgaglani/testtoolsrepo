package interviewcamp.time2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OverlappingIntervals {

    class Time {
        boolean isStartTime;
        Integer time;

        @Override
        public String toString() {
            String s = " " + time;
            if (isStartTime) {
                s = s + " -";
            }
            return s;
        }
    }

    public List<Time> mergeOverlaps(List<Time> times) {
        Collections.sort(times, new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                return o1.time.compareTo(o2.time);
            }
        });

        List<Time> merged = new ArrayList<>();
        int currStartTime = Integer.MIN_VALUE;
        int overlapCtr = 0;
        for (Time time : times) {
            if (time.isStartTime) {
                if (overlapCtr == 0) {
                    currStartTime = time.time;
                }
                overlapCtr++;
            } else {
                overlapCtr--;
                if (overlapCtr == 0) {
                    Time s = new Time();
                    s.isStartTime = true;
                    s.time = currStartTime;

                    Time e = new Time();
                    e.isStartTime = false;
                    e.time = time.time;

                    merged.add(s);
                    merged.add(e);
                }

            }
        }
        return merged;
    }

    //TODO - get merged skyline

    public static void main(String[] args) {
        OverlappingIntervals o = new OverlappingIntervals();
        //{0, 6} {1, 2} {2, 4} {4, 7} {9, 10}
        Integer[][] intervals = new Integer[][] { {0, 6}, {1, 2}, {2, 4}, {4, 7}, {9, 10} };
        List<Time> times = new ArrayList<>();
        for (Integer[] interval : intervals) {
            Time s = o.new Time();
            s.time = interval[0];
            s.isStartTime = true;

            Time e = o.new Time();
            e.time = interval[1];
            e.isStartTime = false;

            times.add(s);
            times.add(e);
        }

        System.out.println(o.mergeOverlaps(times) + " should equal : {0, 7} {9, 10}" );

        Integer[][] intervals2 = new Integer[][] { {0, 1}, {2, 3}, {4, 5}, {7, 8}, {9, 10} };
        List<Time> times2 = new ArrayList<>();
        for (Integer[] interval : intervals2) {
            Time s = o.new Time();
            s.time = interval[0];
            s.isStartTime = true;

            Time e = o.new Time();
            e.time = interval[1];
            e.isStartTime = false;

            times2.add(s);
            times2.add(e);
        }

        System.out.println(o.mergeOverlaps(times2) + " should equal : {0, 1}, {2, 3}, {4, 5}, {7, 8}, {9, 10}" );
    }
}
