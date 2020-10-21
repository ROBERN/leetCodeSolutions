package com.sujeet.leetCode;

public class Problem1323_Max69Number {
    public int maximum69Number (int num) {
        String n = Integer.toString(num);
        int idx = 0;
        for(char c : n.toCharArray()) {
            if(c == '6') {
                return Integer.parseInt(n.substring(0, idx) + "9" + n.substring(idx+1, n.length()));
            }
            idx++;
        }

        return num;
    }
}
