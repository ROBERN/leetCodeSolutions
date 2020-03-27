package com.sujeet.leetCode;

import com.sun.tools.javac.util.Pair;

public class Problem994_rottingOranges {
    public static int orangesRotting(int[][] grid) {
        int rSz = grid.length;
        if(rSz == 0)
            return -1;
        int cSz = grid[0].length;


        boolean[][] visited = new boolean[rSz][cSz];
        for(int r = 0; r < rSz; r++) {
            for(int c = 0; c < cSz; c++) {
                if(grid[r][c] == 2) { // rotten
                    int time = 0;
                    dfs(grid, r, c, time);
                }
            }
        }
        int maxExpiry = -1;
        for (int[] ints : grid) {
            for (int c = 0; c < cSz; c++) {
                if (ints[c] < 0) {
                    maxExpiry = Math.max(maxExpiry, -ints[c]);
                }
                if (ints[c] == 1) {
                    return -1;
                }
            }
        }

        return maxExpiry;
    }

    private static void dfs(int[][] grid, int r, int c, int time) {
        int rSz = grid.length;
        int cSz = grid[0].length;
        Pair<Integer, Integer> pr = new Pair<>(r, c);
        if(r >= rSz || c >= cSz || r < 0 || c < 0)
            return;
        if(grid[r][c] > 2 || grid[r][c] == 0) {
            return; // visited empty place
        }
        if(grid[r][c] < 0 || grid[r][c] == 1) {
            int expiryTime = Integer.MAX_VALUE;
            if(grid[r][c] < 0) {
                expiryTime = -grid[r][c];
            }
            if(expiryTime < time) {
                return;
            }
            grid[r][c] = -time;
        } else {
            grid[r][c] = 3; // visited
        }
        dfs(grid, r+1, c, time+1);
        dfs(grid, r, c+1, time+1);
        dfs(grid, r-1, c, time+1);
        dfs(grid, r, c-1, time+1);
    }

    public static void main(String[] args) {
        System.out.println(Problem994_rottingOranges.orangesRotting(new int[][]{
                {2,2,2,1,1}
        }));
    }
}
