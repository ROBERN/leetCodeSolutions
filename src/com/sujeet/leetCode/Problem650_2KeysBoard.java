package com.sujeet.leetCode;

public class Problem650_2KeysBoard {
    public static int minSteps(int n) {
        if(n == 0 || n == 1)
            return 0;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + 1;
        }
        for(int i = 2; i <= n/2; i++) {
            boolean[] endsWithClipboardValueI = new boolean[n+1];
            for(int j = 2*i; j <= n; j ++) {
                int costPastedWithI = dp[j-i] + ((endsWithClipboardValueI[j-i]) ? 1 : 2);
                if(dp[j] >= costPastedWithI) {
                    dp[j] = costPastedWithI;
                    endsWithClipboardValueI[j] = true;
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(minSteps(9));
    }
}
