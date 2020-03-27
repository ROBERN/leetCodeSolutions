package com.sujeet.leetCode;

import java.util.Arrays;

public class Problem1365_smallerNumbersThanCurrent {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] temp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            temp[i] = nums[i];
        }
        int[] sol = new int[nums.length];
        Arrays.sort(temp);
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            int idx = Arrays.binarySearch(temp, num);
            while(idx > 0 && temp[idx-1] == temp[idx]) {
                idx--;
            }
            sol[i] = idx;
        }
        return sol;
    }
}
