package com.sujeet.leetCode;

public class Problem1007_DominoRotate {
    public int minDominoRotations(int[] A, int[] B) {
        int minCount = Integer.MAX_VALUE;
        for(int i = 1; i <= 6; i++) {
            minCount = Math.min(minCount, countToMakeI(A, B, i));
            minCount = Math.min(minCount, countToMakeI(B, A, i));
        }

        if(minCount == Integer.MAX_VALUE) {
            return -1;
        }
        return minCount;
    }

    private int countToMakeI(int[] arr1, int[] arr2, int desired) {
        int count = 0;
        for(int i = 0; i < arr1.length; i++) {
            if(arr1[i] == desired) continue;
            if(arr2[i] == desired) {count++;}
            else{return Integer.MAX_VALUE;}
        }
        return count;
    }
}
