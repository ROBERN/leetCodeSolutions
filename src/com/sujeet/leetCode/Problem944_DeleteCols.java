package com.sujeet.leetCode;

public class Problem944_DeleteCols {
    private String[] A;
    public int minDeletionSize(String[] A) {
        if(A.length == 0 || A[0].length() == 0)
            return 0;
        this.A = A;
        int deletedCount = 0;
        for(int charIdx = 0; charIdx < A[0].length(); charIdx++) {
            if(!isValidCol(charIdx))
                deletedCount++;
        }

        return deletedCount;
    }

    private boolean isValidCol(int col) {
        for(int strIdx = 1; strIdx < A.length; strIdx++) {
            if(A[strIdx-1].charAt(col) > A[strIdx].charAt(col))
                return false;
        }

        return true;
    }
}
