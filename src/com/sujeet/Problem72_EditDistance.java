package com.sujeet;

public class Problem72_EditDistance {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[] dp = new int[len2 + 1];
        int[] dpPrev = new int[len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp = new int[len2 + 1];
            for (int j = 0; j <= len2; j++) {
                if (i == 0 && j == 0) {
                    dp[j] = 0;
                } else if (i == 0) {
                    dp[j] = j;
                } else if (j == 0) {
                    dp[j] = i;
                } else {
                    dp[j] = Math.min(dp[j - 1], dpPrev[j]);
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[j] = Math.min(dp[j], dpPrev[j - 1] - 1);
                    } else {
                        dp[j] = Math.min(dp[j], dpPrev[j - 1]);
                    }
                    dp[j]++;
                }
            }// end j
            dpPrev = dp;
        }// end i
        return dp[len2];
    }
}
