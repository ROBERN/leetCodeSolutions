package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem1477_TwoNonOverlappingSubArraySum {

    public int minSumOfLengths(int[] arr, int target) {
        int minSum = Integer.MAX_VALUE;
        int[] dp = new int[arr.length];
        Arrays.fill(dp, -1);
        for (int idx = 0; idx < dp.length; idx++) {
            int currSum = arr[idx];
            int len = 1;
            while (currSum < target && idx+len < dp.length) {
                currSum += arr[idx+len];
                len++;
            }
            if (currSum == target) {
                dp[idx] = len;
            }
        }
        for (int firstIdx = 0; firstIdx < dp.length; firstIdx++) {
            for (int secondIdx = firstIdx+1; secondIdx < dp.length; secondIdx++) {
                if (dp[firstIdx] != -1 && dp[secondIdx] != -1 && secondIdx >= firstIdx + dp[firstIdx]) {
                    minSum = Math.min(minSum, dp[firstIdx]+dp[secondIdx]);
                }
            }
        }
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }

    public static void main(String[] args) {
        Problem1477_TwoNonOverlappingSubArraySum obj = new Problem1477_TwoNonOverlappingSubArraySum();
        System.out.println(obj.minSumOfLengths(new int[]{4,3,2,6,2,3,4}, 6));
    }
}
