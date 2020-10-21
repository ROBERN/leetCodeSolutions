package com.sujeet.leetCode;

public class Problem13_RomanToInt {
    private static String[] romans = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    private static int[] val = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    public int romanToInt(String s) {
        int i = 0;
        int res = 0;
        while(s.length() > 0) {
            if(s.startsWith(romans[i])) {
                res += val[i];
                s = s.substring(romans[i].length());
            } else {
                i++;
            }
        }
        return res;
    }
}
