package com.sujeet.leetCode;

import java.util.Arrays;

public class Problem276_Fencepaint {
    int[] dp;
    public int numWays(int n, int k) {
        // f(n) = k * (k-1)(f(n-1)+f(n-2))
        if(n < 0 || k == 0)
            return 0;

        dp = new int[n+1];
        Arrays.fill(dp, -1);

        dp[0] = 0;
        dp[1] = k;
        dp[2] = k*k;
        recur(n, k);
        return dp[n];
    }

    private int recur(int n, int k) {
        if(dp[n] != -1)
            return dp[n];

        return dp[n] = k*(k-1)*(recur(n-2, k) + recur(n-3, k));
    }

    public static void main(String[] args) {
        Problem276_Fencepaint obj = new Problem276_Fencepaint();
        System.out.println(obj.numWays(3, 2));
    }
}
