package com.sujeet.leetCode;

import java.util.Arrays;

public class Problem883_ProjectionArea {
    public int projectionArea(int[][] grid) {
        int rowSz = grid.length;
        if(rowSz == 0)
            return 0;
        int colSz = grid[0].length;
        if(colSz == 0)
            return 0;
        int[] maxInCol = new int[colSz];
        Arrays.fill(maxInCol, 0);
        int xySum = 0;
        int yzSum = 0;
        for (int[] gridRow : grid) {
            int maxInRow = 0;
            for (int c = 0; c < colSz; c++) {
                if (gridRow[c] > 0) {
                    xySum++;
                    maxInRow = Math.max(maxInRow, gridRow[c]);
                    maxInCol[c] = Math.max(maxInCol[c], gridRow[c]);
                }
            }
            yzSum += maxInRow;
        }
        int xzSum = 0;
        for(int idx = 0; idx < colSz; idx++) {
            xzSum += maxInCol[idx];
        }

        return xySum+yzSum+xzSum;
    }
}
