package com.sujeet.leetCode;

import java.util.Arrays;

public class Problem498_DiagonalMatrixPrint {

    public int[] findDiagonalOrder(int[][] matrix) {
        int rowSz = matrix.length;
        if(rowSz == 0)
            return new int[0];
        int colSz = matrix[0].length;
        int totalLen = rowSz*colSz;
        int[] result = new int[totalLen];

        int row = 0;
        int col = 0;
        boolean forward = true;
        int idx = 0;

        while(idx < totalLen) {
            if(forward) {
                while(row >= 0 && col < colSz) {
                    result[idx++] = matrix[row--][col++];
                }
                if (col >= colSz){
                    col--;
                    row+= 2;
                } else {
                    row++;
                }
            }
            else {
                while(row < rowSz && col >= 0) {
                    result[idx++] = matrix[row++][col--];
                }

                if (row >= rowSz){
                    row--;
                    col +=2;
                } else {
                    col++;
                }
            }
            forward = !forward;
        }

        return result;
    }

    public static void main(String[] args) {
        Problem498_DiagonalMatrixPrint obj = new Problem498_DiagonalMatrixPrint();
        int[] result = obj.findDiagonalOrder(new int[][] {
                {1,2,3},{4,5,6},{7,8,9}
        });
        System.out.println(Arrays.toString(result));
    }
}
