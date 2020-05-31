package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem279_PerfectSquares {

    private int[] dp;
    public int numSquares(int n) {
        dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;

        List<Integer> perfectNums = getAllPerfectNumbers(n);
        for(int i = 2; i <= n; i++) {
            int minVal = Integer.MAX_VALUE;
            for(int k = 0; k < perfectNums.size() && i >= perfectNums.get(k); k++) {
                minVal = Math.min(minVal, dp[i-perfectNums.get(k)] + 1);
            }
            dp[i] = minVal;
        }

        return dp[n];
    }

    private List<Integer> getAllPerfectNumbers(int n) {
        List<Integer> nums = new ArrayList<>();
        for(int i = 1; i <= Math.sqrt(n); i++)
            nums.add(i*i);
        return nums;
    }

    public static void main(String[] args) {
        Problem279_PerfectSquares obj = new Problem279_PerfectSquares();
        System.out.println(obj.numSquares(43));
    }

}
