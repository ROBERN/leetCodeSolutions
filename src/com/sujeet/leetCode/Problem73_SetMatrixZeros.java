package com.sujeet.leetCode;

import java.util.Arrays;

public class Problem73_SetMatrixZeros {
    public void setZeroes(int[][] matrix) {
        int rowSz = matrix.length;
        if(rowSz == 0)
            return;
        int colSz = matrix[0].length;
        boolean isZeroRow0 = false;
        boolean isZeroCol0 = false;
        for(int r = 0; r < rowSz; r++) {
            for(int c = 0; c < colSz; c++) {
                if(matrix[r][c] == 0) {
                    if(c == 0) {
                        isZeroCol0 = true;
                    }
                    if(r == 0) {
                        isZeroRow0 = true;
                    }
                    matrix[0][c] = 0;
                    matrix[r][0] = 0;
                }
            }
        }
        print(matrix);

        // rows

        for(int r = 1; r < rowSz; r++) {
            if(matrix[r][0] == 0) {
                makeAllZeroRow(matrix, r);
            }
        }
        System.out.println("After rows");
        print(matrix);

        // cols

        for(int c = 1; c < colSz; c++) {
            if(matrix[0][c] == 0) {
                makeAllZeroCol(matrix, c);
            }
        }
        if(isZeroCol0)
            makeAllZeroCol(matrix, 0);
        if(isZeroRow0)
            makeAllZeroRow(matrix, 0);
        System.out.println("After cols");
        print(matrix);
    }

    private void print(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    private void makeAllZeroRow(int[][] matrix, int r) {
        for(int c = 0; c < matrix[0].length; c++) {
            matrix[r][c] = 0;
        }
    }

    private void makeAllZeroCol(int[][] matrix, int c) {
        for(int r = 0; r < matrix.length; r++) {
            matrix[r][c] = 0;
        }
    }

    public static void main(String[] args) {
        Problem73_SetMatrixZeros obj = new Problem73_SetMatrixZeros();
        obj.setZeroes(new int[][] {
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}
        });
    }
}
