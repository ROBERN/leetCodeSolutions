package com.sujeet.leetCode;

public class Problem14_LCPrefix {
    public String longestCommonPrefix(String[] strs) {
        int sz = strs.length;
        if(sz == 0) {
            return "";
        }
        String res = strs[0];
        for(int i = 1; i < sz; i++) {
            if(strs[i].length() < res.length()) {
                res = res.substring(0, strs[i].length());
            }
            for(int j = 0; j < res.length(); i++) {
                if(strs[i].charAt(j) != res.charAt(j)){
                    res = res.substring(0, j);
                }
            }
        }
        return res;
    }
}
