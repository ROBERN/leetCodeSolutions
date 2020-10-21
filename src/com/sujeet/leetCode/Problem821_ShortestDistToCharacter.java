package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem821_ShortestDistToCharacter {
    public int[] shortestToChar(String S, char C) {
        List<Integer> cIndexes = new ArrayList<>();
        int idx = 0;
        for(char ch : S.toCharArray()) {
            if(ch == C) {
                cIndexes.add(idx);
            }
            idx++;
        }

        int[] sol = new int[S.length()];
        int solIdx = 0;
        for(idx = 0; idx < S.length(); idx++) {
            sol[solIdx++] = Math.abs(idx- findClosest(cIndexes, idx));
        }
        return sol;
    }

    private int findClosest(List<Integer> cIndexes, int val) {
        int len = cIndexes.size();
        if(val > cIndexes.get(len-1))
            return cIndexes.get(len-1);

        if(val < cIndexes.get(0))
            return cIndexes.get(0);

        int l = 0;
        int r = len-1;
        while(l < r) {
            int mid = l +(r-l)/2;
            int midVal = cIndexes.get(mid);
            if(midVal >= val) {
                if(mid > 0 && cIndexes.get(mid-1) < val) {
                    return getClosest(cIndexes.get(mid-1), midVal, val);
                }
                r = mid;
            } else {
                if(mid < len-1 && cIndexes.get(mid+1) > val) {
                    return getClosest(midVal, cIndexes.get(mid+1), val);
                }
                l = mid+1;
            }
        }
        return cIndexes.get(l);
    }

    private int getClosest(int val1, int val2, int target) {
        if(Math.abs(val1-target) < Math.abs(val2-target))
            return val1;
        else
            return val2;
    }
}
