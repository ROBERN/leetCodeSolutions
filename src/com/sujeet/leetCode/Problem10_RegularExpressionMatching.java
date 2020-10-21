package com.sujeet.leetCode;

public class Problem10_RegularExpressionMatching {
    public boolean isMatch(String str, String pattern) {
        return  isMatch(pattern, str, 0, 0);
    }

    private boolean isMatch(String pattern, String str, int idxPattern, int idxStr) {
        if(idxStr == str.length()) {
            if(idxPattern == pattern.length())
                return true;
            if(idxPattern+1 < pattern.length() && pattern.charAt(idxPattern+1) == '*')
                return isMatch(pattern, str, idxPattern+2, idxStr);
            return false;
        }
        if(idxPattern >= pattern.length())
            return false;

        if(idxPattern+1 < pattern.length() && pattern.charAt(idxPattern+1) == '*' ) {
            if(pattern.charAt(idxPattern) == str.charAt(idxStr) || pattern.charAt(idxPattern) == '.') {
                return isMatch(pattern, str, idxPattern, idxStr+1) ||  // multiple match
                        isMatch(pattern, str, idxPattern+2, idxStr)        // no match
                        ||  isMatch(pattern, str, idxPattern+2, idxStr+1);   // one match
            } else {
                return isMatch(pattern, str, idxPattern+2, idxStr);
            }
        }

        if(pattern.charAt(idxPattern) == '.') {
            return isMatch(pattern, str, idxPattern+1, idxStr+1);
        }

        if(pattern.charAt(idxPattern) != str.charAt(idxStr)) {
            return false;
        } else {
            return isMatch(pattern, str, idxPattern+1, idxStr+1);
        }

    }
}
