package com.sujeet.Practise;

import java.util.HashMap;
import java.util.Map;

public class DynamicProgramming {
    private static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    Map<Integer, Integer> nToFibVal = new HashMap<>();
    private int fibonacciTopDownWithMemo(int n) {
        if (n <= 1) {
            return n;
        }
        if (!nToFibVal.containsKey(n))
            nToFibVal.put(n, fibonacci(n - 1) + fibonacci(n - 2));
        return nToFibVal.get(n);
    }

    // three attributes: the optimal substructure property, no aftereffect, overlapping subproblems properties.
    private int fibonacciBottomUp(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for(int idx = 2; idx <= n; idx++)
            dp[idx] = dp[idx-1] + dp[idx-2];

        return dp[n];
    }

    private int fibonacciBottomUpWithLessSpace(int n) {
        if(n <= 1 )
            return n;
        int prev = 0;
        int prevPrev = 1;
        int current = 1;

        for(int idx = 1; idx <= n; idx++) {
            current = prev + prevPrev;
            prevPrev = prev;
            prev = current;
        }

        return current;
    }
    // 904 40 523 12     -1335 385 -124 481 -31
    // 904 944 1467 1479  144  539
    // 2 to 4 is 0to 4 - 0 to 1
    // i to j is equal to (0 to j) - (0 to i-1).
    // minSum seenYet and currSum.
    // 144 - 944 = (j-i) 2 4
    private int maxSubArraySum(int[] nums) {
        int len = nums.length;
        int bestSum = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) { // i =1           i =0
            for (int j = i; j < len; j++) { // j = 3 ,     j =2
                int sum = 0;
                for(int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                bestSum = Math.max(bestSum, sum);
            }
        }
        return bestSum;
    }
    // i = 1,  j= 3 equalsTo i =1, j =2 + nums[3]

    private int maxSubArraySumDP(int[] nums) {
        int len = nums.length;
        int bestSum = Integer.MIN_VALUE;
        int[][] sums = new int[len+1][len+1];

        for (int i = 0; i < len; i++) { // i =1           i =0
            for (int j = i; j < len; j++) { // j = 3 ,     j =2
                int val = (j > 0 ? sums[i][j-1] : 0);
                sums[i][j] = val + nums[j];
                bestSum = Math.max(bestSum, sums[i][j]);
            }
        }
        return bestSum;
    }

    private int maxSumArrayUsingPrefix(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return 0;

        int currPrefixSum = nums[0];
        int minPrefixSumYet = nums[0];
        int bestRangeSum = nums[0];
        for (int idx = 1; idx < len; idx++) {
            int num = nums[idx];

            currPrefixSum += num;
            if (currPrefixSum >= minPrefixSumYet) {
                int maxSumAtThisPos = currPrefixSum- minPrefixSumYet;
                bestRangeSum = Math.max(maxSumAtThisPos, bestRangeSum);
            }
            minPrefixSumYet = Math.min(minPrefixSumYet, currPrefixSum);
        }
        return bestRangeSum;
    }

    // 0, 1, 2, -5, 1, 2,
    // -1, -2, -4
    //
    public static void main(String[] args) {
        DynamicProgramming dp = new DynamicProgramming();
        System.out.println(dp.fibonacciBottomUp(8));
    }
}