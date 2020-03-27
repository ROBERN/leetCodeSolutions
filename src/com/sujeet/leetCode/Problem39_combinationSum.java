package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem39_combinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        ArrayList<Integer> cands = new ArrayList<>();
        for(int i = 0; i < candidates.length; i++) {
            cands.add(candidates[i]);
        }
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> curr = new ArrayList<>();
        combinationSum(cands, target, curr, 0, result);
        return result;
    }

    private void combinationSum(
            ArrayList<Integer> candidates,
            int target,
            ArrayList<Integer> curr,
            int idx,
            List<List<Integer>> result
    ) {
        if(target == 0) {
            result.add(new ArrayList<>(curr));
            return;
        }
        if(target < 0) {
            return;
        }
        for(int i = idx; i < candidates.size(); i++) {
            curr.add(candidates.get(i));
            combinationSum(candidates, target-candidates.get(i), curr, i, result);
            curr.remove(curr.size() - 1);
        }
    }
}
