package com.sujeet.leetCode;

import java.util.Arrays;

public class Problem1300_MinDiffToRange {

    private int minDiff = Integer.MAX_VALUE;
    private int optimalIdx = -1;
    public int findBestValue(int[] arr, int target) {
        int len = arr.length;
        Arrays.sort(arr);
        int leftSumYet = 0;
        int[] leftSum = new int[len];
        for(int idx = 0; idx < len; idx++) {
            leftSumYet += arr[idx];
            leftSum[idx] = leftSumYet;
        }

        for(int idx = len-2; idx >= 0; idx--) {
            int sumTillNow = leftSum[idx];
            int range = len -1-idx;
            int st = arr[idx];
            int end = idx != len-1 ? arr[idx+1] : Integer.MAX_VALUE;
            if(Math.abs(target - arr[idx]*range) < minDiff)
                binarySearch(range, st, end, target-sumTillNow);
            else
                break;
        }
        binarySearch(len, 0, arr[0], target);
        return optimalIdx;
    }

    void binarySearch(int range, int st, int end, int target) {
        int minDiffYet = minDiff;

        if(end < st) {
            return;
        }
        int mid = st + (end-st)/2;
        if(Math.abs(target - mid*range) < minDiff || Math.abs(target - mid*range) ==  minDiff && mid < optimalIdx) {
            minDiff = Math.abs(target - mid*range);
            optimalIdx = mid;
        }
        if(Math.abs(target - (mid+1)*range) < minDiff) {
            binarySearch(range, mid+1, end, target);
        }
        if(Math.abs(target - (mid-1)*range) <= minDiff || Math.abs(target - (mid-1)*range) ==  minDiff && mid-1 < optimalIdx) {
            binarySearch(range, st, mid-1, target);
        }
    }

}
