package com.sujeet.leetCode;

import com.sun.tools.javac.util.Pair;

import java.util.HashMap;

public class Problem44_WildCardMatching {
    private HashMap<Pair<String, String>, Boolean> cache;
    public boolean isMatch(String s, String p) {
        cache = new HashMap<>();
        cache.put(new Pair<>("", ""), true);
        p = cleanString(p);
        //return isMatchMemoization(p, s, 0, 0);
        return isMatchDP(p, s);
    }

    private boolean isMatchDP(String pattern, String str) {
        // base cases
        if (pattern.equals(str) || pattern.equals("*")) return true;
        if (pattern.isEmpty() || str.isEmpty()) return false;
        int lenPat = pattern.length();
        int lenStr = str.length();
        boolean[][] dp = new boolean[lenPat+1][lenStr+1];
        dp[0][0] = true;
        for (int pIdx = 1; pIdx < lenPat; pIdx++) {
            char pChar = pattern.charAt(pIdx-1);
            if (pChar != '*') {
                for (int sIdx = 1; sIdx < lenStr; sIdx++) {
                    dp[pIdx][sIdx] = dp[pIdx-1][sIdx-1] && (pChar == '?' || pChar == str.charAt(sIdx));
                }
            }
            else {
                int sIdx = 1;
                while (!dp[pIdx-1][sIdx-1] && sIdx < lenStr) {
                    sIdx++;
                }
                dp[pIdx][sIdx-1] = dp[pIdx-1][sIdx-1];
                while (sIdx < lenStr + 1) {
                    dp[pIdx][sIdx++] = true;
                }
            }
        }
        return dp[lenPat][lenStr];
    }

    private String cleanString(String p) {
        int len = p.length();
        if(len <= 1)
            return p;
        StringBuilder sb = new StringBuilder();
        sb.append(p.charAt(0));
        for(char idx = 1; idx < p.length(); idx++) {
            if(p.charAt(idx) == '*' && p.charAt(idx-1) == '*')
                continue;
            sb.append(p.charAt(idx));
        }
        return sb.toString();
    }

    private boolean isMatchMemoization(String pattern, String str, int idxPattern, int idxStr) {
        Pair<String, String> key = new Pair<>(str.substring(idxStr), pattern.substring(idxPattern));
        if(cache.containsKey(key)) {
            return cache.get(key);
        }
        if(idxStr == str.length()) {
            while(idxPattern < pattern.length() && pattern.charAt(idxPattern) == '*') {
                idxPattern++;
            }
            return idxPattern == pattern.length();
        }
        if(idxPattern >= pattern.length())
            return false;

        if(pattern.charAt(idxPattern) == '*' ) {
            boolean isMatch = isMatchMemoization(pattern, str, idxPattern, idxStr+1) ||  // multiple match
                    isMatchMemoization(pattern, str, idxPattern+1, idxStr);       // no match
            cache.put(key, isMatch);
        } else if(pattern.charAt(idxPattern) == '?') {
            cache.put(key, isMatchMemoization(pattern, str, idxPattern+1, idxStr+1));
        } else if(pattern.charAt(idxPattern) != str.charAt(idxStr)) {
            cache.put(key, false);
        } else {
            cache.put(key, isMatchMemoization(pattern, str, idxPattern+1, idxStr+1));
        }
        return cache.get(key);
    }
}
