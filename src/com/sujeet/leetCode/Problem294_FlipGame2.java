package com.sujeet.leetCode;

public class Problem294_FlipGame2 {
    public boolean canWin(String s) {
        for(int idx = 0; idx < s.length()-1; idx++) {
            if(s.charAt(idx) == '+' && s.charAt(idx+1) == '+') {
                if(!canWin(s.substring(0, idx) + "--" + s.substring(idx+2))) {
                    return true;
                }
            }
        }
        return false;
    }
}
