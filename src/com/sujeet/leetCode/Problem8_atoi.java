package com.sujeet.leetCode;

public class Problem8_atoi {
    public int myAtoi(String str) {
        int val = 0;
        boolean st = false;
        boolean isNegative = false;
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == ' ' && !st) {
                continue;
            }
            if(!st && (c == '-' || c == '+')) {
                if(c == '-') {
                    isNegative = true;
                }
                st = true;
                continue;
            }
            if(c < '0' || c> '9') {
                break;
            }
            st = true;
            if(val > Integer.MAX_VALUE/10 || (val == Integer.MAX_VALUE/10 && (c-'0') > 7)) {
                if(isNegative) {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            } else {
                val = val * 10 + (c -'0');
            }
        }
        return val * (isNegative ? -1 : 1);
    }
}
