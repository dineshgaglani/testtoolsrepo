package dailyCodingProblem;

import java.util.Arrays;
import java.util.Collections;

/*
Given an array of time intervals (start, end) for classroom lectures (possibly overlapping), find the minimum number of rooms required.

For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.
 */
public class Problem21OverlappingIntervals {

    class Interval {
        int startTime;
        int endTime;

        Interval(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    class TaggedInterval implements Comparable<TaggedInterval> {
        Integer time;
        boolean isStartTimeInterval;

        @Override
        public int compareTo(TaggedInterval o) {
            return time.compareTo(o.time);
        }
    }

    public Integer findMaxOverlaps (Interval[] intervals) {
        int maxOverlaps = 0;
        TaggedInterval[] tis = new TaggedInterval[intervals.length * 2];
        int counter = 0;
        for (Interval i : intervals) {
            TaggedInterval ti = new TaggedInterval();
            ti.time = i.startTime;
            ti.isStartTimeInterval = true;
            tis[counter] = ti;
            counter++;

            TaggedInterval ti2 = new TaggedInterval();
            ti2.time = i.endTime;
            ti2.isStartTimeInterval = false;
            tis[counter] = ti2;
            counter++;
        }

        Arrays.sort(tis);

        int tracker = 0;
        for (TaggedInterval ti : tis) {
            if (ti.isStartTimeInterval) {
                tracker++;
            } else {
                tracker--;
            }
            maxOverlaps = Math.max(maxOverlaps, tracker);
        }

        return maxOverlaps;
    }

    public static void main (String[] args) {
        Problem21OverlappingIntervals oi = new Problem21OverlappingIntervals();
        Interval i1 = oi.new Interval(30, 75);
        Interval i2 = oi.new Interval(0, 50);
        Interval i3 = oi.new Interval(60, 150);

        Interval[] intervals = new Interval[3];
        intervals[0] = i1;
        intervals[1] = i2;
        intervals[2] = i3;

        System.out.println(oi.findMaxOverlaps(intervals) + " should return 2 ");
    }
}
