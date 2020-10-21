package com.sujeet.leetCode;

public class Problem136_SingleNumber {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int num : nums) {
            ans ^= num;
        }
        return ans;
    }
}
