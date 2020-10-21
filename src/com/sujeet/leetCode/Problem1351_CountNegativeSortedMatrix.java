package com.sujeet.leetCode;

public class Problem1351_CountNegativeSortedMatrix {
    private int[][] matrix;
    public int countNegatives(int[][] grid) {
        this.matrix = grid;
        int rowCount = grid.length;
        if(rowCount == 0) return 0;
        int colCount = grid[0].length;
        if(colCount == 0) return 0;

        int totalNegative = 0;
        int minIdx = 0;
        for(int r = rowCount-1; r >= 0; r--) {
            int idx  = getFirstNegativeIdxInRow(r, minIdx, colCount-1);
            if(idx != -1) {
                totalNegative += colCount - idx;
            }
            minIdx = idx;
        }
        return totalNegative;
    }

    private int getFirstNegativeIdxInRow(int row, int left, int right) {
        if(matrix[row][right] >= 0) {
            return -1;
        }
        if(matrix[row][left] < 0) {
            return left;
        } else {
            int mid = left + (right - left)/2;
            if(matrix[row][mid] < 0) {
                right = mid;
            } else {
                left = mid+1;
            }
            return getFirstNegativeIdxInRow(row, left, right);
        }
    }
}
