package com.sujeet;

public class Problem760_anagramMappings {
    public int[] anagramMappings(int[] A, int[] B) {
        int[] res = new int[A.length];
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < B.length; j++) {
                if(A[i] == B[j]) {
                    res[i] = j;
                    break;
                }
            }
        }
        return res;
    }
}
