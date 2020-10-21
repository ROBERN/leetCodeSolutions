package com.sujeet.leetCode;

public class Problem463_IslandPerimeter {
    private int N;
    private int M;
    private int[][] store;
    public int islandPerimeter(int[][] grid) {

        N = grid.length;
        if(N == 0)
            return 0;
        M = grid[0].length;

        store = new int[N][M];
        int r = 0;
        for(int[] rows : grid) {
            int c = 0;
            for(int item : rows) {
                if(item == 1)
                    return getPerimeter(grid, r, c);
                c++;
            }
            r++;
        }
        return 0;
    }

    private int getPerimeter(int[][] grid, int r, int c) {
        if(r < 0 || r >= N || c < 0 || c >= M)
            return 1;
        if(grid[r][c] == 2)
            return 0;
        if(grid[r][c] == 0) {
            return 1;
        }
        grid[r][c] = 2; //mark as visited

        return store[r][c] =
                getPerimeter(grid, r-1, c) +
                        getPerimeter(grid, r+1, c) +
                        getPerimeter(grid, r, c-1) +
                        getPerimeter(grid, r, c+1);
    }
}
