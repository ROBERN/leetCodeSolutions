package com.sujeet.leetCode;

public class problem1299_HighestOnRightArr {
    public int[] replaceElements(int[] arr) {

        int maxYet = -1;
        for(int idx = arr.length -1; idx >= 0; idx--) {
            int val = arr[idx];
            arr[idx] = maxYet;
            maxYet = Math.max(maxYet, val);
        }
        return arr;
    }
}
