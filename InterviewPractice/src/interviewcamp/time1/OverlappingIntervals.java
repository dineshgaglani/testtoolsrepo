package interviewcamp.time1;

import java.util.*;

public class OverlappingIntervals {

    class Time {
        boolean isStartTime;
        Integer time;

        //To map both start and end time to the same interval if they belong to the same interval - only used in skyline
        Integer id;
        Integer height;

        @Override
        public String toString() {
            String s = " " + time;
            if (isStartTime) {
                s = s + " -";
            }
            return s;
        }
    }

    public boolean isOverlapExists(List<Time> times) {
        //THE TIMES WHEN SORTED HAVE TO BE IN INCREASING ORDER IF THERE ARE NO OVERLAPS = {0, 2} {3, 4} {6, 9}
        //IF THERE ARE OVERLAPS IT WILL LOOK LIKE THIS {0, 2} {1, 5} {6, 8} - HERE 1 IS COMING AFTER 2
        int overlapCtr = 0;
        Collections.sort(times, new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                return o1.time.compareTo(o2.time);
            }
        });
        for (Time t : times) {
            if (t.isStartTime) {
                overlapCtr++;
            } else {
                overlapCtr--;
            }
            if(overlapCtr > 1) {
                return true;
            }
        }

        return false;
    }

    public Integer getMaxOverlaps (List<Time> times) {
        Collections.sort(times, new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                return o1.time.compareTo(o2.time);
            }
        });

        Integer maxOverlap = Integer.MIN_VALUE;
        Integer currOverlap = 0;
        for (Time time : times) {
            if (time.isStartTime) {
                currOverlap++;
            } else {
                currOverlap--;
            }
            if (currOverlap > maxOverlap) {
                 maxOverlap = currOverlap;
            }
        }

        return maxOverlap;
    }

    //{0, 2} {1, 5} {6, 8} should become {0, 5} {6, 8}
    //{0, 6} {1, 2} {2, 4} {4, 7} should become {0, 7}
    public List<Time> fixOverlaps(List<Time> times) {
        List<Time> newIntervals = new ArrayList<>();
        Integer overlapCtr = 0;

        //{0, 6} {1, 2} {2, 4} {4, 7} {9, 10} becomes {0, 1, 2, 2, 4, 4, 6, 7, 9, 10}
        //{0, 2} {1, 5} {6, 8} becomes {0, 1, 2, 5, 6, 8}
        Collections.sort(times, new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                return o1.time.compareTo(o2.time);
            }
        });

        int currStartTime = Integer.MAX_VALUE;
        for (int i = 0; i < times.size(); i++) {
            Time t = times.get(i);
            if (t.isStartTime) {
                if (overlapCtr == 0) {
                    currStartTime = t.time;
                }
                overlapCtr++;
            } else {
                overlapCtr--;
            }
            if (overlapCtr == 0) {
                //Overlap counter has come back to 0 after having changed from 0
                //at the end of an interval.
                Time sInterval = new Time();
                sInterval.isStartTime = true;
                sInterval.time = currStartTime;

                Time eInterval = new Time();
                eInterval.isStartTime = false;
                eInterval.time = t.time;

                newIntervals.add(sInterval);
                newIntervals.add(eInterval);
            }
        }
        return newIntervals;
    }

    class SkyLine {
        Integer id;
        Integer start;
        Integer end;
        Integer height;

        SkyLine(Integer id, Integer start, Integer end, Integer height) {
            this.id = id;
            this.start = start;
            this.end = end;
            this.height = height;
        }
    }

    //TODO - come back later
    public List<SkyLine> getMergedSkyline(List<SkyLine> skylines) {
        PriorityQueue<Integer> maxSkylines = new PriorityQueue<Integer>((i1, i2) -> -1 * i1.compareTo(i2));

        //Convert skyline to intervals
        int idCtr = 0;
        List<Time> intervals = new ArrayList<>();
        for (SkyLine s : skylines) {
            Time st = new Time();
            st.isStartTime = true;
            st.time = s.start;
            st.id = idCtr;
            st.height = s.height;
            intervals.add(st);

            Time et = new Time();
            et.isStartTime = false;
            et.time = s.end;
            et.id = idCtr;
            et.height = s.height;
            intervals.add(et);

            idCtr++;
        }

        Collections.sort(intervals, (i1 , i2) -> i1.time.compareTo(i2.time));
        //This map is for quick access of start time when we get to the end time of an interval
        Map<Integer, Integer> startToEndMap = new HashMap<>();
        Map<Integer, Integer> newSkylineMap = new HashMap<>();
        Map<Integer, SkyLine> result = new HashMap<>();
        Integer currSkyline = 0;
        for (Time interval : intervals) {
            if(interval.isStartTime) {
                maxSkylines.add(interval.time);
                startToEndMap.put(interval.id, interval.time);
                newSkylineMap.put(interval.id, interval.height);
                SkyLine resSkyline = new SkyLine(interval.id, interval.time, null, maxSkylines.peek());
                if (interval.height > maxSkylines.peek()) {
                    resSkyline.height = interval.height;
                    currSkyline = interval.height;
                }
                result.put(interval.id, resSkyline);
            } else {
                Integer currMaxSkyline = maxSkylines.peek();
                maxSkylines.remove(newSkylineMap.get(interval.id));
                if (interval.height == currMaxSkyline) {
                    currSkyline = maxSkylines.peek();
                }
            }
        }
        return null;
    }

    public static void main (String args[]) {
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

        System.out.println(o.fixOverlaps(times) + " should equal : {0, 7} {9, 10}" );

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

        System.out.println(o.fixOverlaps(times2) + " should equal : {0, 1}, {2, 3}, {4, 5}, {7, 8}, {9, 10}" );
    }
}
