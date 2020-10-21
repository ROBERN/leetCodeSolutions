package com.sujeet.leetCode;

import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class Problem980_UniquePaths {
    private int uniquePaths = 0;
    private Set<Pair<Integer, Integer>> visited;
    private int rowSz;
    private int colSz;
    private int visitedCountNeeded = 0;
    public int uniquePathsIII(int[][] grid) {
        rowSz = grid.length;
        if(rowSz == 0) {
            return 0;
        }
        colSz = grid[0].length;

        int stRowIdx = -1;
        int stColIdx = -1;
        visited = new HashSet<>();
        for(int r = 0; r < rowSz; r++) {
            for(int c = 0; c < colSz; c++) {
                if(grid[r][c] == 0) {
                    visitedCountNeeded++;
                } else if (grid[r][c] == 1) {
                    stRowIdx = r;
                    stColIdx = c;
                }
            }
        }
        getUniquePaths(grid, stRowIdx, stColIdx);
        return uniquePaths;
    }

    private void getUniquePaths(int[][] grid, int r, int c) {
        if(r < 0 || c < 0 || r >= rowSz || c >= colSz || grid[r][c] == -1) {
            return;
        }
        if (visited.contains(new Pair<>(r, c))) {
            return;
        }
        if(grid[r][c] == 2) {
            if (visitedCountNeeded+1 == visited.size())
                uniquePaths++;
            return;
        }
        visited.add(new Pair<>(r, c));

        getUniquePaths(grid, r+1, c);
        getUniquePaths(grid, r-1, c);
        getUniquePaths(grid, r, c+1);
        getUniquePaths(grid, r, c-1);

        visited.remove(new Pair<>(r, c));
    }

    public static void main(String[] args) {
        Problem980_UniquePaths obj = new Problem980_UniquePaths();
        System.out.println(obj.uniquePathsIII(new int[][]{
                //{1,0,0,0},{0,0,0,0},{0,0,2,-1}
                {0,1},{2,0}
        }));
    }
}
