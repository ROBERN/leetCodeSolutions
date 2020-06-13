package com.sujeet.Practise;

import java.util.Arrays;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        for(int i = len-2; i >= 0; i--) {
            if(nums[i] < nums[i+1]) {
                // find the next highest for i and swap
                int idxToSwap = i+1;
                for(int j= i+1; j< len; j++) {
                    if(nums[idxToSwap] >= nums[j] && nums[j] > nums[i]) {
                        idxToSwap = j;
                    }
                }
                nums[idxToSwap] ^= nums[i];
                nums[i] ^= nums[idxToSwap];
                nums[idxToSwap] ^= nums[i];

                Arrays.sort(nums, i+1, len);
                return;
            }
        }
        Arrays.sort(nums);
    }
}
