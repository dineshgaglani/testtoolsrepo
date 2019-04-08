package intervalpartitioning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
    Main insight is that before a interval ends if another interval begins then we have an overlapping interval
    and we keep a count of number of such events. So for this we need to identify what "type" of time we encounter is

    Algorithm: Put all intervals (both start and end times in a list), sort by time value
    Sort this list and iterate over it, when we see a start time increment a counter and when we see an end time
    decrement it. At the end return the count.

    Mistake: The counter keeps track of whether an interval is already in progress, we need an overlap counter to identify
    when the counter > 0, which tells us when an overlap is in progress
 */
public class OverlappingIntervals {

    class Time {
        boolean isStartTime;
        int time;

        Time(int time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Time{" +
                    "isStartTime=" + isStartTime +
                    ", time=" + time +
                    '}';
        }
    }

    class Interval {
        Time startTime;
        Time endTime;

        Interval (int startTime, int endTime) {
            this.startTime = new Time(startTime);
            this.startTime.isStartTime = true;

            this.endTime = new Time(endTime);
            this.endTime.isStartTime = false;
        }
    }

    public int getOverlappingIntervalsCount(List<Interval> intervals) {
        List<Time> times = new ArrayList<>();
        for (Interval i : intervals) {
            times.add(i.startTime);
            times.add(i.endTime);
        }

        Collections.sort(times, new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                return o1.time > o2.time ? 1:-1;
            }
        });

        int counter = 0;
        int overlapCtr = 0;

        for (Time t : times) {
            if (t.isStartTime) {
                if (counter > 0) {
                    overlapCtr++;
                }
                counter++;

            } else {
                counter--;
            }
        }

        return overlapCtr;
    }

    public void mergeOverlaps(List<Interval> intervals) {

    }

    public static void main (String args[]) {
        OverlappingIntervals oi = new OverlappingIntervals();
        Interval i1 = oi.new Interval(1, 5);
        Interval i2 = oi.new Interval(4, 9);
        Interval i3 = oi.new Interval(3, 7);
        Interval i4 = oi.new Interval(10, 12);
        List<Interval> iList = new ArrayList<>();
        iList.add(i1);
        iList.add(i2);
        iList.add(i3);
        iList.add(i4);
        System.out.println(oi.getOverlappingIntervalsCount(iList));
    }
}
