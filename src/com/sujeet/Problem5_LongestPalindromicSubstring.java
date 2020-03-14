package com.sujeet;

public class Problem5_LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        String res = "";
        if (s == null || s.length() == 0) {
            return res;
        }
        int maxLen = 0;
        int bestStart = 0;
        int bestEnd = 0;
        for (int i = 0; i < s.length(); i++) {
            int st = i;
            int end = i;
            while (st >= 0 && end < s.length() && s.charAt(st) == s.charAt(end)) {
                int curLen = end - st + 1;
                if (maxLen < curLen) {
                    maxLen = curLen;
                    bestStart = st;
                    bestEnd = end;
                }
                st--;
                end++;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            int st = i;
            int end = i + 1;
            while (st >= 0 && end < s.length() && s.charAt(st) == s.charAt(end)) {
                int curLen = end - st + 1;
                if (maxLen < curLen) {
                    maxLen = curLen;
                    bestStart = st;
                    bestEnd = end;
                }
                st--;
                end++;
            }
        }
        return s.substring(bestStart, bestEnd + 1);
    }
}
