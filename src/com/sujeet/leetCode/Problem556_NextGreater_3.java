package com.sujeet.leetCode;

import java.util.Arrays;

public class Problem556_NextGreater_3 {
    public int nextGreaterElement(int n) {
        String str = Integer.toString(n);

        for(int i = str.length()-1; i > 0; i--) {
            if(str.charAt(i-1) < str.charAt(i)) {
                int nextSmallestIndexHigherThanI = getNextSmallestIndexHigherThanI(str, i-1);
                str = swap(str, i-1, nextSmallestIndexHigherThanI);
                str = sort(str, i, str.length());
                try{
                    return Integer.parseInt(str);
                } catch(NumberFormatException e) {
                    return -1;
                }
            }
        }
        return -1;
    }

    private int getNextSmallestIndexHigherThanI(String s, int i) {
        char candidate = s.charAt(i);

        int idx = i+1;
        int minIdx = i+1;
        for(; idx < s.length(); idx++) {
            if(s.charAt(idx) < s.charAt(minIdx) && s.charAt(idx) > candidate) {
                minIdx = idx;
            }
        }
        return minIdx;
    }

    // exclusive of end
    private String sort(String str, int st, int end) {
        char[] chars = new char[end-st];
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, st));
        for(int idx = st; idx < end; idx++) {
            chars[idx-st] = str.charAt(idx);
        }
        Arrays.sort(chars);
        sb.append(new String(chars));
        return sb.toString();
    }

    private String swap(String str, int idx1, int idx2) {
        return str.substring(0, idx1) +
                str.charAt(idx2) +
                str.substring(idx1 + 1, idx2) +
                str.charAt(idx1) +
                str.substring(idx2 + 1);
    }
}
