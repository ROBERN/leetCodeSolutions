package com.sujeet.leetCode;

public class Problem81_SearchInRotatedDuplicates {
    public static boolean search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length-1, target);
    }

    private static boolean binarySearch(int[] nums, int st, int end, int target) {
        if(st >= end) {
            return st <nums.length && nums[st] == target;
        }
        int midIdx = st + (end-st+1)/2;
        int midVal = nums[midIdx];
        if(midVal == target) {
            return true;
        }
        if(nums[st] < midVal) {
            if(target < midVal && target >= nums[st] && binarySearch(nums, st, midIdx-1, target))
                return true;
        } else {
            if(binarySearch(nums, st, midIdx-1, target))
                return true;
        }
        if(midVal < nums[end]) {
            if(target > midVal && target <= nums[end] && binarySearch(nums, midIdx+1, end, target)) {
                return true;
            }
        } else {
            return binarySearch(nums, midIdx+1, end, target);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Problem81_SearchInRotatedDuplicates.search(
                new int[] {1,1},
                0
        ));
    }
}
