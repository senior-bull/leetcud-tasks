package com.vito.dsu;

import java.util.*;
import java.util.stream.Collectors;

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

    private static class DisjointUnionSets<T>  implements Iterable<DisjointUnionSets<T>.DsuSet> {

        private final Map<T, Integer> treeSize;
        private final Map<T, T> parent;

        public DisjointUnionSets() {
            this.treeSize = new HashMap<>();
            this.parent = new HashMap<>();
        }

        void add(T item) {
            treeSize.put(item, 0);
            parent.put(item, item);
        }

        public T find(T item) {
            if (parent.get(item) != item) {
                parent.put(item, find(parent.get(item)));
            }

            return parent.get(item);
        }

        void union(T x, T y) {
            T xRoot = find(x), yRoot = find(y);

            if (xRoot == yRoot)
                return;

            int xCount = treeSize.get(xRoot);
            int yCount = treeSize.get(yRoot);

            if (xCount < yCount)
                parent.put(xRoot, yRoot);
            else if (yCount < xCount)
                parent.put(yRoot, xRoot);
            else {
                parent.put(yRoot, xRoot);
                treeSize.put(xRoot, treeSize.get(xRoot) + 1);
            }
        }

        public List<DsuSet> getSets() {
            Map<T, List<T>> sets = new HashMap<>();

            for (var item : parent.keySet()) {
                sets.computeIfAbsent(find(item), i -> new ArrayList<>()).add(item);
            }

            return sets.entrySet()
                    .stream()
                    .map(kv -> new DsuSet(kv.getKey(), kv.getValue()))
                    .collect(Collectors.toList());
        }

        @Override
        public Iterator<DsuSet> iterator() {
            return getSets().iterator();
        }

        public class DsuSet {
            private final T root;
            private final List<T> items;

            public DsuSet(T root, List<T> items) {
                this.root = root;
                this.items = items;
            }

            public T getRoot() {
                return root;
            }

            public List<T> getItems() {
                return items;
            }
        }
    }
}
