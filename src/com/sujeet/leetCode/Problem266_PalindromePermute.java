package com.sujeet.leetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem266_PalindromePermute {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) +1);
        }

        boolean oneOdd = false;
        for(Integer count : map.values()) {
            if(count % 2 == 1) {
                if(oneOdd)
                    return false;
                else
                    oneOdd = true;
            }
        }
        if(oneOdd && s.length() % 2 == 0)
            return false;

        return true;
    }
}
