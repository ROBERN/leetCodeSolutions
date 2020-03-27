package com.sujeet.Practise;

import javafx.util.Pair;

import java.util.Arrays;

class Solution {

    static int[] findArrayQuadruplet(int[] arr, int target) {
        // your code goes here
        // pick two number. And this happen in n2 ways.
        // then I run findTwoSUm. which run in linear time.
        Arrays.sort(arr);
        for (int first = 0; first < arr.length - 3; first++) {
            for (int second = first + 1; second < arr.length - 2; second++) {
                int sum = arr[first] + arr[second];
                if (sum > target)
                    return new int[]{};
                Pair<Integer, Integer> pairIndex = findTwoSumIndex(arr, second + 1, target - sum);
                if (pairIndex.getKey() != -1) { // found
                    return new int[]{arr[first], arr[second], arr[pairIndex.getKey()], arr[pairIndex.getValue()]};
                }
            }
        }
        return new int[]{};
    }

    static private Pair<Integer, Integer> findTwoSumIndex(int[] arr, int leftIdx, int target) {
        int left = leftIdx;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return new Pair<Integer, Integer>(left, right);
            }
            if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new Pair<Integer, Integer>(-1, -1);
    }

    public static void main(String[] args) {

    }
}