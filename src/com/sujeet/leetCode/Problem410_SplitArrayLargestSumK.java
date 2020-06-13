package com.sujeet.leetCode;

import java.util.Arrays;

public class Problem410_SplitArrayLargestSumK {
    // bruteForce

    private int k;
    private int[] nums;
    private int minSumKParts;
    public int splitArrayBrute(int[] nums, int k) {
        this.k = k;
        this.nums = nums;
        minSumKParts = Integer.MAX_VALUE;

        split(0, 0, 0, 0);
        return minSumKParts;
    }

    private void split(int idx, int currArrays, int currSum, int currSumMax) {
        if (currArrays == k && idx == nums.length) {
            minSumKParts = Math.min(currSumMax, minSumKParts);
            return;
        }

        if (idx == nums.length)
            return;

        if (currArrays > 0)
            split(idx+1, currArrays, currSum+nums[idx], Math.max(currSumMax, currSum+nums[idx]));

        if (currArrays < k)
             split(idx+1, currArrays+1, nums[idx], Math.max(currSumMax, nums[idx]));
    }

    public int splitArrayDP(int[] nums, int m) {
        int[][] dp = new int[nums.length+1][m+1];
        int[] prefixSum = new int[nums.length+1];
        for (int[] dpArr : dp)
            Arrays.fill(dpArr, Integer.MAX_VALUE);
        int idx = 0;
        for (int num : nums) {
            prefixSum[idx + 1] = prefixSum[idx] + num;
            idx++;
        }
        dp[0][0] = 0;

        for(int numIdx = 1; numIdx <= nums.length; numIdx++) {
            for (int partIdx = 1; partIdx <= m; partIdx++) {
                for (int k = 0; k < numIdx; k++) {
                    dp[numIdx][partIdx] = Math.min(dp[numIdx][partIdx], Math.max(dp[k][partIdx-1], prefixSum[numIdx] - prefixSum[k]));
                }
            }
        }
        return dp[nums.length][m];
    }

    public int splitArrayUsingBinSearch(int[] nums, int m) {
        int l = 0;
        int h = Arrays.stream(nums).sum();
        if (h == 0)
            return 0;

        int ans = Integer.MAX_VALUE;
        while(h >= l) {
            int mid = l + (h-l)/2;
            int minSplitsWithMidPartitions = getSplitsToGetMin(nums, mid);
            if (minSplitsWithMidPartitions <= m) {
                ans = Math.min(ans, mid);
                h = mid - 1;
            }
            else
                l = mid+1;
        }

        return l;
    }

    private int getSplitsToGetMin(int[] nums, int maxAllowedSum) {
        int currSum = 0;
        if (nums[0] > maxAllowedSum)
            return Integer.MAX_VALUE;
        int splits = 1;
        for (int num : nums) {
            if (num + currSum > maxAllowedSum) {
                splits++;
                currSum = 0;
            }
            currSum += num;
        }
        return splits;
    }


    public static void main(String[] args) {
        Problem410_SplitArrayLargestSumK obj = new Problem410_SplitArrayLargestSumK();
        System.out.println(obj.splitArrayUsingBinSearch(new int[]{7,2,5,10,8}, 2));
    }
}
