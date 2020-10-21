package com.sujeet.leetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem325_MaxSizeSubArray {
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        int sum = 0;
        sumToIndex.put(0, -1);
        int idx = 0;
        int maxLength = 0;
        for(int num : nums) {
            sum += num;
            if(sumToIndex.containsKey(sum-k)) {
                maxLength = Math.max(maxLength, idx - sumToIndex.get(sum-k));
            }
            int finalIdx = idx;
            sumToIndex.computeIfAbsent(sum, empty -> finalIdx);
            idx++;
        }

        return maxLength;
    }
}
