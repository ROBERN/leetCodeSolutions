package com.sujeet.leetCode;

public class Problem300_LISubSequence {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if(len == 0) {
            return 0;
        }

        int[] dp = new int[len];
        int LIS = 0;

        for(int i = 0; i < len; i++) {
            dp[i]  = 1;
            for(int bk = i-1; bk >=0; bk--) {
                if(nums[bk] < nums[i] && dp[i]  < dp[bk]+1) {
                    dp[i] = dp[bk]+1;
                }
            }//end bk
            if(dp[i] > LIS) {
                LIS = dp[i];
            }
        }//end i
        return LIS;
    }
}
