package com.sujeet.leetCode;

public class Problem268_MissingNumber {
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int expectedSum = ((len)*(len+1))/2;
        int actualSum = 0;
        for(int i = 0; i < len; i++) {
            actualSum += nums[i];
        }
        return expectedSum - actualSum;
    }
}
