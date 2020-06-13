package com.sujeet.Practise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintAllPermutation_NoRepetition {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> num = new ArrayList<>();
        for(int i : nums) {
            num.add(i);
        }
        permute(num, 0);
        return res;
    }

    private void permute(List<Integer> nums, int st) {
        if(st == nums.size()) {
            res.add(new ArrayList<>(nums));
            return;
        }
        for(int i = st; i <  nums.size(); i++) {
            Collections.swap(nums, st, i);
            permute(nums, st+1);
            Collections.swap(nums, st, i);
        }
    }
}
