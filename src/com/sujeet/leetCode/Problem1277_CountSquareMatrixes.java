package com.sujeet.leetCode;

public class Problem1277_CountSquareMatrixes {
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        if(m == 0) return 0;
        int n = matrix[0].length;

        int count = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < m; j++) {
                if(i == 0 || j == 0) {
                    count += matrix[i][j] == 0 ? 0 : 1;
                } else if(matrix[i][j] == 1) {
                    matrix[i][j] = 1+Math.min(matrix[i-1][j-1], Math.min(matrix[i][j-1],matrix[i-1][j]));
                    count += matrix[i][j];
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Problem1277_CountSquareMatrixes obj = new Problem1277_CountSquareMatrixes();
        obj.countSquares(new int[][]{
                {0,1,1,1},{1,1,1,1},{0,1,1,1}
        });
    }
}
