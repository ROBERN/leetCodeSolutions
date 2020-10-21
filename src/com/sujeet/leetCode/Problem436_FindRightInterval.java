package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem436_FindRightInterval {
    static class Intervals implements Comparable<Intervals>{
        int st;
        int end;
        int idx;
        Intervals(int s, int e, int i) {
            st = s;
            end = e;
            idx = i;
        }

        @Override
        public int compareTo(Intervals other) {
            return this.st - other.st;
        }

    }
    public int[] findRightInterval(int[][] intervals) {
        List<Intervals> items = new ArrayList<>();
        int idx = 0;
        for(int[] interval : intervals) {
            items.add(new Intervals(interval[0], interval[1], idx++));
        }
        Collections.sort(items);
        int[] res = new int[intervals.length];
        for(int currIdx = 0; currIdx < items.size(); currIdx++) {
            int orinIdx = items.get(currIdx).idx;
            res[orinIdx] = -1;
            int end = items.get(currIdx).end;
            for(int nextIdx = currIdx; nextIdx < items.size(); nextIdx++) {
                int nextSt = items.get(nextIdx).st;
                if(nextSt >= end) {
                    res[orinIdx] = items.get(nextIdx).idx;
                    break;
                }
            }
        }
        return res;
    }
}
