package com.vito.dsu;

import java.util.*;

/**
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals, and return an array of the non-overlapping
 * intervals that cover all the intervals in the input.
 */
public class MergeIntervals {

    public static class Interval {

        private final int begin;
        private final int end;

        public Interval(int[] value) {
            this.begin = value[0];
            this.end = value[1];
        }

        public int getBegin() {
            return begin;
        }

        int[] toArray() {
            return new int[]{begin, end};
        }

        public boolean overlaps(Interval other) {
            Interval leftmost = this.begin <= other.begin ? this : other;
            Interval rightmost = this.begin <= other.begin ? other : this;
            return (rightmost.begin >= leftmost.begin) && (rightmost.begin <= leftmost.end);
        }
    }

    public int[][] merge(int[][] in) {
        List<Interval> intervals = new ArrayList<>();
        for (int[] i : in) {
            intervals.add(new Interval(i));
        }
        intervals.sort(Comparator.comparing(Interval::getBegin));

        var dsu = new DisjointUnionSets<Interval>();

        Interval rightmostInterval = intervals.get(0);
        dsu.add(rightmostInterval);

        for (int i = 1; i < intervals.size(); i++) {
            var interval = intervals.get(i);
            dsu.add(interval);
            if (rightmostInterval.overlaps(interval)) {
                dsu.union(rightmostInterval, interval);
            }
            if (interval.end > rightmostInterval.end) {
                rightmostInterval = interval;
            }
        }

        List<int[]> result = new ArrayList<>();

        for (var set : dsu.getSets()) {
            long left = Long.MAX_VALUE;
            long right = Long.MIN_VALUE;

            for (var interval : set.getItems()) {
                if (interval.begin < left) {
                    left = interval.begin;
                }
                if (interval.end > right) {
                    right = interval.end;
                }
            }

            result.add(new Interval(new int[] {(int) left, (int) right}).toArray());
        }

        int[][] ans = new int[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }
}
