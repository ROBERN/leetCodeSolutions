package com.sujeet.leetCode;

public class Problem304_RangeSum2D {

    private int[][] sumFromOrigin;
    public Problem304_RangeSum2D(int[][] matrix) {
        int rowSz = matrix.length;
        if(rowSz == 0)
            return;
        int colSz = matrix[0].length;
        if(colSz == 0)
            return;
        sumFromOrigin = new int[rowSz][colSz];
        sumFromOrigin[0][0] = matrix[0][0];
        for(int r = 1; r < rowSz; r++) {
            sumFromOrigin[r][0] = sumFromOrigin[r-1][0] + matrix[r][0];
        }

        for(int c = 1; c < colSz; c++) {
            sumFromOrigin[0][c] = sumFromOrigin[0][c-1] + matrix[0][c];
        }

        for(int r = 1; r < rowSz; r++) {
            for(int c = 1; c < colSz; c++) {
                sumFromOrigin[r][c] = sumFromOrigin[r-1][c] + sumFromOrigin[r][c-1] + matrix[r][c] - sumFromOrigin[r-1][c-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sumFromOrigin[row2][col2] -
                (row1 > 0 ? sumFromOrigin[row1-1][col2] : 0) -
                (col1 > 0 ? sumFromOrigin[row2][col1-1] : 0) +
                (row1 > 0 && col1 > 0 ? sumFromOrigin[row1-1][col1-1] : 0);
    }

    public static void main(String[] args) {
        Problem304_RangeSum2D obj =  new Problem304_RangeSum2D(new int[][]{
                {-1}
        });
        System.out.println(obj.sumRegion(0, 0, 0, 0));
    }
}

/**
 * ["NumMatrix","sumRegion","sumRegion","sumRegion"]
 * [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],
 * [2,1,4,3],[1,1,2,2],[1,2,2,4]]
 **/