package com.sujeet;

public class Problem34_FindFirstAndLastInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int[] ret = new int[2];
        ret[0] = searchLeft(nums, 0, nums.length, target);
        ret[1] = searchRight(nums, 0, nums.length, target);
        return ret;
    }

    private int searchLeft(int[] nums, int st, int end, int target)  {
        if(st >= end) {
            return -1;
        }
        int mid = (st+end)/2;
        if(nums[mid] == target) {
            if(mid ==0) {
                return mid;
            }
            if(nums[mid - 1] != target) {
                return mid;
            }
            return searchLeft(nums, st, mid, target);
        }
        if(nums[mid] > target) {
            return searchLeft(nums, st, mid, target);
        }
        if(nums[mid] < target) {
            return searchLeft(nums, mid+1, end, target);
        }
        return -1;
    }

    private int searchRight(int[] nums, int st, int end, int target)  {
        if(st >= end) {
            return -1;
        }
        int mid = (st+end)/2;
        if(nums[mid] == target) {
            if(mid == nums.length-1) {
                return mid;
            }
            if(nums[mid + 1] != target) {
                return mid;
            }
            return searchRight(nums, mid+1, end, target);
        }
        if(nums[mid] > target) {
            return searchRight(nums, st, mid, target);
        }
        if(nums[mid] < target) {
            return searchRight(nums, mid+1, end, target);
        }
        return -1;
    }
}
