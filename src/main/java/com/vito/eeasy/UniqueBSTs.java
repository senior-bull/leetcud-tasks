package com.vito.eeasy;

public class UniqueBSTs {

    private long[][] cache;

    public int numTrees(int n) {
        cache = new long[n + 1][n + 1];
        return (int) numTrees(1, n);
    }

    public long numTrees(int l, int r) {
        if(l == r) {
            return 1;
        }

        if(cache[l][r] != 0) {
            return cache[l][r];
        }

        long result = 0;
        for(int newRoot = l; newRoot <= r; newRoot++) {
            long leftCount = 1, rightCount = 1;
            if(newRoot > l) {
                leftCount = numTrees(l, newRoot - 1);
            }
            if(newRoot < r) {
                rightCount = numTrees(newRoot + 1, r);
            }
            result += leftCount * rightCount;
        }
        return cache[l][r] = result;
    }
}
