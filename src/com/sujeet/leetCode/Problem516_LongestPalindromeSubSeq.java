package com.sujeet.leetCode;

public class Problem516_LongestPalindromeSubSeq {
    public static int longestPalindromeSubseq(String s) {
        int len = s.length();
        if(len == 0 || len == 1)
            return len;
        int[][] dp = new int[len][len];

        for(int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }

        int maxSize = 1;
        for(int sz = 2; sz <= len; sz++) {
            for(int st = 0; st <= len - sz; st++) {
                int end = st +sz-1;
                if(sz == 2) {
                    dp[st][end] = s.charAt(st) == s.charAt(end) ? 2 : 1;
                } else {
                    dp[st][end] = Math.max(
                            dp[st+1][end-1] + (s.charAt(st) == s.charAt(end) ? 2 : 0),
                            Math.max(dp[st+1][end], dp[st][end-1])
                    );
                }
                maxSize = Math.max(maxSize, dp[st][end]);
            }
        }
        return maxSize;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("bbbab"));
    }
}
