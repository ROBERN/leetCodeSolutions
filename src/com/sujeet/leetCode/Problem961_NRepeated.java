package com.sujeet.leetCode;

public class Problem961_NRepeated {
    public int repeatedNTimes(int[] A) {
        for(int idx = 0; idx < A.length; idx++) {
            for(int i = 1; i <= 3 && idx+i < A.length; i++) {
                if(A[idx] == A[idx+i])
                    return A[idx];
            }
        }

        throw null;
    }
}
