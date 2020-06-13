package com.sujeet.leetCode;

public class Problem166_fractionRecur {
    public String fractionToDecimal(int n, int d) {
        double ans = n/(double)d;
        String s = Double.toString(ans);
        String[] strs = s.split("\\.");
        if(strs.length > 1) {
            if(isRecurring(strs[1]))
                strs[1] = "(" + strs[1].charAt(0) + ")";
            s = strs[0] + "." + strs[1];
        }
        return s;
    }

    private boolean isRecurring(String s) {
        if(s.length() < 12)
            return false;
        for(int i = 1; i < 12; i++) {
            if(s.charAt(i) != s.charAt(i-1))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Problem166_fractionRecur obj = new Problem166_fractionRecur();
        System.out.println(obj.fractionToDecimal(2, 3));
    }
}
