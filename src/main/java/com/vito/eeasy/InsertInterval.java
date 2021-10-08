package com.vito.eeasy;

import java.util.ArrayList;
import java.util.List;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class InsertInterval {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();

        int index = -1;
        while(index + 1 < intervals.size() && intervals.get(index + 1).start < newInterval.start) {
            index++;
        }
        if(intervals.isEmpty() || index + 1 == intervals.size()) {
            intervals.add(newInterval);
        } else {
            intervals.add(index + 1, newInterval);
        }


        Integer start = null, end = null;
        for(int i = 0; i < intervals.size(); i++) {
            start = intervals.get(i).start;
            end = intervals.get(i).end;

            while(i + 1 < intervals.size() && intervals.get(i + 1).start <= end) {
                i++;
                Interval next = intervals.get(i);
                end = Math.max(end, next.end);
            }

            result.add(new Interval(start, end));
        }

        return result;
    }
}
