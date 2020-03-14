package com.sujeet;

import java.util.ArrayList;
import java.util.List;

public class Problem78_subSet {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> sol = new ArrayList<>();
        int n = nums.length;
        int nthBit = 1 << n;
        for (int i = 0; i < (int)Math.pow(2, n); ++i) {
            // generate bitmask, from 0..00 to 1..11
            String str = Integer.toBinaryString(i | nthBit).substring(1);
            List<Integer> curr = new ArrayList<>();
            for(int j = 0; j < n; j++) {
                if(str.charAt(j) == '1'){
                    curr.add(nums[j]);
                }
            }
            sol.add(curr);
        }
        return sol;
    }
}
