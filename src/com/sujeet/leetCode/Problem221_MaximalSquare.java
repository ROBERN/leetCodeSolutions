package com.sujeet.leetCode;

public class Problem221_MaximalSquare {
    public static int maximalSquare(char[][] matrix) {
        int rowSz = matrix.length;
        if(rowSz == 0)
            return 0;
        int colSz = matrix[0].length;
        int maxSide = 0;
        for(int r=0; r < rowSz; r++) {
            for(int c=0; c < colSz; c++) {
                if(r == 0 || c == 0 || matrix[r][c] == '0') {
                    if(matrix[r][c] == '1')
                        maxSide = Math.max(maxSide,1);
                    continue;
                }
                int left = Character.getNumericValue(matrix[r][c-1]);
                int up = Character.getNumericValue(matrix[r-1][c]);
                int dia = Character.getNumericValue(matrix[r-1][c-1]);

                int val = 1+Math.min(left, Math.min(up, dia));
                maxSide = Math.max(maxSide, val);
                matrix[r][c]  = (char)(val+'0');
            }
        }
        return  maxSide*maxSide;

    }

    public static void main(String[] args) {
        Problem221_MaximalSquare.maximalSquare(new char[][]{});
    }
}
