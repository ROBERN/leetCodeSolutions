package com.sujeet.leetCode;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Problem1231_DivideChocolate {
    private int[] arr;
    private int k;
    private int arrLen;

    private int globalMaxForMinSum;
    public int maximizeSweetness(int[] sweetness, int K) {
        arr = sweetness;
        k = K;
        arrLen = sweetness.length;
        globalMaxForMinSum = 0;
        dfs(0, 0, 0, Integer.MAX_VALUE);
        return globalMaxForMinSum;
    }
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int idx = 0; idx < arr2.length; idx++) {
            map.put(arr2[idx], idx);
        }

        Integer[] arr11 = new Integer[arr1.length];
        int idx = 0;
        for (int num1  : arr1) {
            arr11[idx++] = num1;
        }
        Arrays.sort(arr11, (a, b) -> {
            if(map.containsKey(a) && map.containsKey(b)) {
                return map.get(a) - map.get(b);
            }
            if(map.containsKey(a) || map.containsKey(b)) {
                return map.getOrDefault(a, -1) - map.getOrDefault(b, -1);
            }
            return 0;
        });
        idx = 0;
        for (int num1  : arr11) {
            arr1[idx++] = num1;
        }
        return arr1;
    }
    private void dfs(int currIdx, int currSplit, int currSum, int minSum) {
        if(currIdx == arrLen) {
            if(currSplit == k)
                globalMaxForMinSum = Math.max(minSum, globalMaxForMinSum);
            return;
        }
        // take in the current split, unless currSplit = 0
        if(currSplit > 0) {
            int sum = currSum+arr[currIdx];
            dfs(currIdx+1, currSplit, sum, minSum);
        }
        // take in the next split unless currSplit == K
        if(currSplit < k ) {
            int sum = arr[currIdx];
            if(currSplit != 0)
                minSum = Math.min(minSum, currSum);
            dfs(currIdx+1, currSplit+1, sum, minSum);
        }
    }
}
