package com.sujeet.leetCode;

import java.util.*;

public class Problem945_IncrementToMakeUnique {
    public static int minIncrementForUnique(int[] A) {
        Map<Integer, Integer> valToCount = new HashMap<>();

        for(int a : A) {
            valToCount.put(a, valToCount.getOrDefault(a, 0)+1);
        }
        int increments = 0;
        Set<Integer> addedNumber = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : valToCount.entrySet()) {
            int valCounts = entry.getValue();
            while (valCounts > 1) {
                int key = entry.getKey();
                valCounts--;
                while (valToCount.containsKey(key) || addedNumber.contains(key)) {
                    key++;
                    increments++;
                }
                addedNumber.add(key);
            }
        }


        return increments;
    }

    public static int minIncrementForUnique2(int[] A) {
        if(A.length == 0)
            return 0;
        Arrays.sort(A);
        int duplicates = 0;
        int increments = 0;
        for(int idx = 1; idx < A.length; idx++) {
            if(A[idx] == A[idx-1]) {
                duplicates++;
                increments -= A[idx];
            } else {
                if(A[idx] > A[idx-1]+1 && duplicates > 0) {
                    int countOfItemsToUse = Math.min(A[idx]-A[idx-1]-1, duplicates);
                    int maxValOfItemToUse = A[idx-1]+countOfItemsToUse;
                    duplicates -= countOfItemsToUse;
                    increments += (maxValOfItemToUse*(maxValOfItemToUse+1))/2 - (A[idx-1]*(A[idx-1]+1))/2;
                }
            }
        }

        if(duplicates > 0) {
            int lastVal = A[A.length-1];
            int maxVal = lastVal+duplicates;
            increments += (maxVal*(maxVal+1))/2 - (lastVal*(lastVal+1))/2;
        }

        return increments;
    }

    public static void main(String[] args) {
        Problem945_IncrementToMakeUnique.minIncrementForUnique2(new int[]{3,2,1,2,1,7});
    }
}
