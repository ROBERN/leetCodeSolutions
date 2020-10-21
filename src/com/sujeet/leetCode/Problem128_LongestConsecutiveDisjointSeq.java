package com.sujeet.leetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem128_LongestConsecutiveDisjointSeq {
    public int longestConsecutive(int[] nums) {

        Map<Integer, Integer> numToIdx = new HashMap<>();

        int realIdx = 0;
        for(int idx = 0; idx < nums.length; idx++) {
            if (!numToIdx.containsKey(nums[idx]))
                numToIdx.put(nums[idx], realIdx++);
        }

        Subset[] sets = new Subset[numToIdx.size()];

        for (Map.Entry<Integer, Integer> entry : numToIdx.entrySet()) {
            sets[entry.getValue()] = new Subset(entry.getKey(), entry.getValue(), 0);
        }

        for(int idx = 0; idx < sets.length; idx++) {
            int val = sets[idx].val;
            if(numToIdx.containsKey(val-1)) {
                int prevIdx = numToIdx.get(val-1);
                union(sets, idx, prevIdx);
            }
            if(numToIdx.containsKey(val+1)) {
                union(sets, idx, numToIdx.get(val+1));
            }
        }

        //flatten
        for(int idx = 0; idx < sets.length; idx++) {
            find(sets, idx);
        }

        return getLongestSet(sets);
    }

    private int getLongestSet(Subset[] sets) {
        int[] counts = new int[sets.length];
        for (Subset set : sets) {
            counts[set.parent]++;
        }
        int maxCount = 0;
        for (int count : counts) {
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }

    private static class Subset {
        int parent;
        int rank;
        int val;
        Subset(int v, int p, int r) {
            val = v;
            parent = p;
            rank = r;
        }
    }

    private int find(Subset[] sets, int i) {
        if(sets[i].parent != i) {
            sets[i].parent = find(sets, sets[i].parent);
        }
        return sets[i].parent;
    }

    private void union(Subset[] sets, int x, int y) {
        int xRoot = find(sets, x);
        int yRoot = find(sets, y);

        if(sets[xRoot].rank < sets[yRoot].rank) {
            sets[xRoot].parent = yRoot;
        } else if(sets[yRoot].rank < sets[xRoot].rank) {
            sets[yRoot].parent = xRoot;
        } else {
            sets[xRoot].parent = yRoot;
            sets[yRoot].rank++;
        }
    }

    public static void main(String[] args) {
        Problem128_LongestConsecutiveDisjointSeq obj =  new Problem128_LongestConsecutiveDisjointSeq();
        System.out.println(obj.longestConsecutive(new int[]{0, 0, -1}));
    }
}
