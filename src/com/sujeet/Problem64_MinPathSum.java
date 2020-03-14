package com.sujeet;

public class Problem64_MinPathSum {
    public int minPathSum(int[][] grid) {

        // For each entry in first row, the only minimum sum path it's left element.
        for (int c = 1; c < grid[0].length; c++) {
            grid[0][c] += grid[0][c-1];
        }

        // For each entry in first col, the only minimum sum path it's upper element.
        for (int r = 1; r < grid.length; r++) {
            grid[r][0] += grid[r-1][0];
        }


        for (int r = 1; r < grid.length; r++) {
            for (int c = 1; c < grid[0].length; c++) {
                grid[r][c] += Math.min(grid[r-1][c], grid[r][c-1]);
            }
        }

        return grid[grid.length-1][grid[0].length-1];

    }
}
