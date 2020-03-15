package com.sujeet;

public class Problem238_ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] sol = new int[len];
        if(len == 0) {
            return sol;
        }
        sol[len-1] = nums[len-1];
        for(int i = len - 2; i>=0; i--) {
            sol[i] = nums[i] * sol[i+1];
        }

        for(int i = 1; i < len; i++) {
            nums[i] = nums[i-1]*nums[i];
        }
        for(int i = 0; i < len; i++) {
            sol[i] = (i> 0 ? nums[i-1] : 1) *(i<len-1 ? sol[i+1]:1);
        }
        return sol;
    }
}
