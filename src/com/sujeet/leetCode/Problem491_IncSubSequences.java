package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem491_IncSubSequences {
    private Set<List<Integer>> incSubSequences;
    public List<List<Integer>> findSubsequences(int[] nums) {
        incSubSequences = new HashSet<>();
        getIncSubsequences(nums, 0, new ArrayList<>());

        return new ArrayList<>(incSubSequences);

    }

    private void getIncSubsequences(int[] nums, int idx, List<Integer> currList) {
        if(idx == nums.length)  {
            if(currList.size() >= 2) {
                incSubSequences.add(new ArrayList<>(currList));
            }
            return;
        }

        // include
        if(currList.size() == 0 || nums[idx] >= currList.get(currList.size()-1)) {
            currList.add(nums[idx]);
            getIncSubsequences(nums, idx+1, currList);
            currList.remove(currList.size()-1);
        }

        // do not include
        getIncSubsequences(nums, idx+1, currList);
    }
}
