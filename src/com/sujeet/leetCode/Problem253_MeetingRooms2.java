package com.sujeet.leetCode;

import java.util.Arrays;
import java.util.Comparator;

public class Problem253_MeetingRooms2 {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int maxCount = 0;
        for(int idx1 = 0; idx1 < intervals.length; idx1++) {
            int count = 1;
            for(int idx2 = idx1+1; idx2 < intervals.length; idx2++) {
                if(intervals[idx1][1] < intervals[idx2][0])
                    break;
                count++;
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
}
