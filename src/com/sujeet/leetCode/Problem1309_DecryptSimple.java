package com.sujeet.leetCode;

public class Problem1309_DecryptSimple {
    public String freqAlphabets(String s) {

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while(idx < s.length()) {
            char c;
            if(idx+2 < s.length() && s.charAt(idx+2) == '#') {
                c = (char)(Integer.parseInt(s.substring(idx, idx+2)) + 'a' -1);
                idx = idx+3;
            } else {
                c = (char)(Integer.parseInt(s.substring(idx, idx+1)) + 'a'- 1);
                idx++;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
