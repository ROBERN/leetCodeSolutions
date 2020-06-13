package com.sujeet.leetCode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Problem246_StrobogrammaticNum {
    public boolean isStrobogrammatic(String num) {
        Map<Integer, Integer> stroboNums = new HashMap<>();
        stroboNums.put(0, 0);
        stroboNums.put(1, 1);
        stroboNums.put(6, 9);
        stroboNums.put(8, 8);
        stroboNums.put(9, 6);
        int len = num.length();

        int left = 0;
        int right = len-1;
        while(left < right) {
            int numLeft = (int)(num.charAt(left) -'0');
            int numRight = (int)(num.charAt(right));

            if(stroboNums.getOrDefault(numLeft, -1) != numRight)
                return false;
            left++;
            right--;
        }
        return true;
    }
}
