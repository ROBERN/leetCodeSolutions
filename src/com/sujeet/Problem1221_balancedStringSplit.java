package com.sujeet;

public class Problem1221_balancedStringSplit {
    public int balancedStringSplit(String s) {
        int L = 0;
        int R = 0;
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'L') {
                L++;
            } else {
                R++;
            }
            if(R==L) {
                count++;
                R=0;
                L=0;
            }
        }
        return count;
    }
}
