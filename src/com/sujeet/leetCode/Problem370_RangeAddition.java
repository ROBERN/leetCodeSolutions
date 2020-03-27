package com.sujeet.leetCode;

public class Problem370_RangeAddition {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] sol = new int[length];
        for(int i = 0; i < length; i++) {
            for(int k = 0; k < updates.length; k++) { // optimize by sorting
                if(updates[k][0] <= i && updates[k][1] >= i) {
                    sol[i] += updates[k][2];
                }
            }
        }
        return sol;
    }
}
