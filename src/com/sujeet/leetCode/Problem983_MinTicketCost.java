package com.sujeet.leetCode;

public class Problem983_MinTicketCost {

    public static int mincostTickets(int[] days, int[] costs) {
        int D = days.length;
        int C = costs.length;

        int[] prevDp = new int[D+1];
        int[] duration = new int[]{1,7,30};
        for(int i = 1; i <= D; i++) {
            prevDp[i] = prevDp[i-1] + costs[0];
        }
        for(int i = 1; i <= D; i++) {
            int d = days[i-1];
            for(int j = 0; j < duration.length; j++) {
                int idx = getFloor(d-duration[j], days);
                prevDp[i] = Math.min(prevDp[i], costs[j] + prevDp[idx+1]);
            }
        }

        return prevDp[D];
    }

    private static int getFloor(int targetDay, int[] days) {
        if(targetDay <= 0)
            return -1;
        int l = 0;
        int h = days.length-1;
        while(h > l) {
            int mid = l + (h-l+1)/2;
            if(days[mid] == targetDay) {
                return mid;
            }
            if(days[mid] > targetDay) {
                h = mid-1;
            } else {
                l = mid;
            }
        }
        return h;
    }

    public static void main(String[] args) {
       System.out.println(mincostTickets(new int[]{2,3,4,6,8,12,14,18,19,26,27,28}, new int[]{2,9,31}));
    }
}

/**
 * [2,3,4,6,8,12,14,18,19,26,27,28]
 * [2,9,31]
 */
