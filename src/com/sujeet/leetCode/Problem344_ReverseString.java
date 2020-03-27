package com.sujeet.leetCode;

public class Problem344_ReverseString {
    public void reverseString(char[] s) {
        if(s.length == 0) {
            return;
        }
        reverse(s, 0);
    }

    private void reverse(char[] s, int idx) {
        if(idx > s.length/2 || (s.length % 2==0 && idx == s.length/2)) {
            return;
        }
        char tmp = s[idx];
        s[idx] = s[s.length-idx-1];
        s[s.length-idx-1] = tmp;
        reverse(s, idx+1);
    }
}
