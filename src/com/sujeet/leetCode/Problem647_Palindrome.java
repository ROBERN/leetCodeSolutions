package com.sujeet.leetCode;

public class Problem647_Palindrome {
    public int countSubstrings(String s) {
        int len1 = s.length();
        boolean[][] dp = new boolean[len1][len1];
        int count = 0;
        for(int sz = 0; sz < len1; sz++) {
            for(int st = 0; st+sz < len1; st++) {
                int end = st+sz;
                if(st == end) {
                    dp[st][end] = true;
                } else if(sz == 1) {
                    dp[st][end] = s.charAt(st) == s.charAt(end);
                } else {
                    dp[st][end] =  s.charAt(st) == s.charAt(end) && dp[st+1][end-1];
                }
                if(dp[st][end]) {
                    count++;
                }
            }
        }
        return count;
    }
}
