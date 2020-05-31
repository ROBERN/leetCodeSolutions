package com.sujeet.leetCode;

import java.util.Arrays;

public class Problem673_NoOfLIS {
    public static int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        int[] maxCountStore = new int[len];
        int[] maxValue = new int[len];

        int maxSoFar = 1;
        Arrays.fill(maxCountStore, 1);
        Arrays.fill(maxValue, 1);

        for(int end = 1; end < len; end++) {
            for (int k = 0; k < end; k++) {
                if (nums[k] >= nums[end])
                    continue;
                int val = maxValue[k] + 1;
                if (maxValue[end] <= val) {
                    if (val > maxValue[end]) {
                        maxValue[end] = val;
                        maxCountStore[end] = maxCountStore[k];
                    } else if (maxValue[end] == val) {
                        maxCountStore[end] += maxCountStore[k];
                    }
                }
            }
            if(maxSoFar < maxValue[end])
                maxSoFar =maxValue[end];
        }
        if (maxSoFar == 1)
            return len;
        int ans = 0;
        for(int idx = 0; idx < len; idx++) {
            if (maxValue[idx] == maxSoFar)
                ans += maxCountStore[idx];
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findNumberOfLIS(new int[]{1,2}));
    }
}
