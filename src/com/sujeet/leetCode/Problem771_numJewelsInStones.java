package com.sujeet.leetCode;

import java.util.HashSet;
import java.util.Set;

public class Problem771_numJewelsInStones {
    public int numJewelsInStones(String J, String S) {
        int count = 0;
        Set<Character> jewels = new HashSet<>();
        for(int i = 0; i < J.length(); i++) {
            jewels.add(J.charAt(i));
        }
        for(int i = 0; i < S.length(); i++) {
            if(jewels.contains(S.charAt(i))) {
                count++;
            }
        }
        return count;
    }
}
