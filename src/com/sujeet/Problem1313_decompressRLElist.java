package com.sujeet;

import java.util.ArrayList;
import java.util.List;

public class Problem1313_decompressRLElist {
    public int[] decompressRLElist(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i+=2) {
            int freq = nums[i];
            int val = nums[i+1];
            for(int f = 0; f < freq; f++) {
                res.add(val);
            }
        }
        int[] sol = new int[res.size()];
        for(int i = 0; i < res.size(); i++) {
            sol[i] = res.get(i);
        }
        return sol;
    }
}
