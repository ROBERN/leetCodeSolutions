package com.sujeet.leetCode;

import java.util.Arrays;


public class Problem556_NextGreater {
    public int nextGreaterElement(int n) {
        String str = Integer.toString(n);

        for(int i = 0; i < str.length(); i++) {
            if(i == str.length() - 1)
                return -1;
            if(str.charAt(i+1) > str.charAt(i)) {
                int prevSmallestIdx = getPrevSmallestIndex(str, i+1);
                str = swap(str, prevSmallestIdx, i+1);
                str = sort(str, prevSmallestIdx, str.length());
                return Integer.parseInt(str);
            }
        }
        return -1;
    }

    private int getPrevSmallestIndex(String s, int i) {
        char candidate = s.charAt(i);

        int idx = i-1;
        for(; idx >= 0; idx--) {
            if(s.charAt(idx) > candidate) {
                idx++;
                break;
            }
        }
        return idx;
    }

    // exclusive of end
    private String sort(String str, int st, int end) {
        char[] chars = new char[end-st];
        StringBuilder sb = new StringBuilder();
        sb.append(str, 0, st);
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

    public static void main(String[] args) {
        Problem556_NextGreater obj = new Problem556_NextGreater();
        System.out.println(obj.nextGreaterElement(5324));
    }

}
