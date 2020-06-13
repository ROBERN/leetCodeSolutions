package com.sujeet.Practise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PermuteWithRepeition {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
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
            if (isSeen(nums, st, i)) {
                continue;
            }
            Collections.swap(nums, st, i);
            permute(nums, st+1);
            Collections.swap(nums, st, i);
        }
    }

    // Do not swap with the same number
    private boolean isSeen(List<Integer> nums, int st, int currIdx) {
        for (int j = st; j < currIdx; j++) {
            if (nums.get(j).equals(nums.get(currIdx))) {
                return true;
            }
        }
        return false;
    }
}
