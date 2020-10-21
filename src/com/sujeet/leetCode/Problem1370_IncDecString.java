package com.sujeet.leetCode;

import java.util.Arrays;

public class Problem1370_IncDecString {
    public String sortString(String s) {
        char[] str = s.toCharArray();
        Arrays.sort(str);

        int st = 0;
        int end = s.length()-1;
        StringBuilder sb = new StringBuilder();
        boolean inc = true;
        while (st <= end) {
            if(inc) {
                int stNew = incrementAppend(st, str, sb);
                if (stNew == st)
                    break;
                else
                    st = stNew;
            } else {
                int endNew = decrementAppend(end, str, sb);
                if (endNew == end)
                    break;
                else
                    end = endNew;
            }
            inc = !inc;
        }
        return sb.toString();
    }

    private int incrementAppend(int st, char[] str, StringBuilder sb) {
        while (st < str.length && str[st] == ' ')
            st++;
        if (st >= str.length)
            return st;

        int idx = st;
        char c = ' ';
        while (idx < str.length) {
            while (idx < str.length && (str[idx] == c || str[idx] == ' ')) {
                idx++;
            }
            if (idx < str.length) {
                sb.append(str[idx]);
                c = str[idx];
                str[idx] = ' ';
                idx++;
            }
        }
        return st+1;
    }
    private int decrementAppend(int end, char[] str, StringBuilder sb) {
        while (end >=  0 && str[end] == ' ')
            end--;
        if (end < 0)
            return end;
        int idx = end;
        char c = ' ';
        while (idx >= 0) {
            while (idx >= 0 && (str[idx] == c || str[idx] == ' ')) {
                idx--;
            }
            if (idx >= 0) {
                sb.append(str[idx]);
                c = str[idx];
                str[idx] = ' ';
                idx--;
            }
        }
        return end-1;
    }

    public static void main(String[] args) {
        Problem1370_IncDecString obj = new Problem1370_IncDecString();
        System.out.println(obj.sortString("aaaabbbbcccc"));
    }
}
