package com.vito.utils.dsu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DisjointUnionSets<T>  implements Iterable<DisjointUnionSets<T>.DsuSet> {

    private final Map<T, Integer> treeSize;
    private final Map<T, T> parent;

    public DisjointUnionSets() {
        this.treeSize = new HashMap<>();
        this.parent = new HashMap<>();
    }

    public void add(T item) {
        treeSize.put(item, 0);
        parent.put(item, item);
    }

    public T find(T item) {
        if (parent.get(item) != item) {
            parent.put(item, find(parent.get(item)));
        }

        return parent.get(item);
    }

    public void union(T x, T y) {
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
