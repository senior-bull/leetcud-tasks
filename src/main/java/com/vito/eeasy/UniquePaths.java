package com.vito.eeasy;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[][] val  = new int[m][n];

        val[0][0] = 1;

        for (int y = 0; y < m; y++) {
            for (int x = 0; x < n; x++) {
                if (x == 0 && y == 0) {
                    continue;
                }

                int moves = 0;
                if (x - 1 >= 0) {
                    moves += val[y][x - 1];
                }
                if (y - 1 >= 0) {
                    moves += val[y - 1][x];
                }
                val[y][x] = moves;
            }
        }

        return val[m - 1][n - 1];
    }
}
