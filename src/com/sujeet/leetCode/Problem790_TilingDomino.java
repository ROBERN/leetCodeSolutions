package com.sujeet.leetCode;

public class Problem790_TilingDomino {
    private static final int MOD = 1_000_000_007;
    public int numTilings(int N) {
        long[] dp = new long[N+4];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;

        for(int i = 4; i <= N; i++) {
            dp[i] = 2*dp[i-1] + dp[i-3];
            dp[i] %= MOD;
        }
        return (int)dp[N];
    }
}
