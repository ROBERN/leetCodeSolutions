package com.sujeet.leetCode;

import java.util.Arrays;

public class Problem307_RangeSumQuery {
    private int[] rangeSumArray;
    private int size;
    private int[] nums;
    public Problem307_RangeSumQuery(int[] nums) {
        this.nums = nums;
        size = nums.length;
        final int logBase2 = (int)(Math.ceil(Math.log(size)/Math.log(2)));
        rangeSumArray = new int[(int)(2*Math.pow(2, logBase2) - 1)];

        createSegmentTree(0 /*st*/, size-1 /*end*/, 0 /*pos*/ );
    }

    private int createSegmentTree(int st, int end, int pos) {
        if(st == end) {
            return rangeSumArray[pos] = nums[st];
        }
        int mid = st + (end-st)/2;
        int leftSum = createSegmentTree(st, mid, 2*pos+1);
        int rightSum = createSegmentTree(mid+1, end, 2*pos+2);

        return rangeSumArray[pos] = leftSum+rightSum;
    }

    public void update(int i, int val) {
        int diff = val - this.nums[i];
        updateValue(i, 0, size-1, 0 /*pos*/, diff);
    }

    private void updateValue(int index, int st, int end, int pos, int diff) {
        if(index >= st && index <= end) {
            rangeSumArray[pos] += diff;
            int mid = st + (end-st)/2;
            if(st == end)
                return;
            updateValue(index, st, mid, 2*pos+1, diff);
            updateValue(index, mid+1, end, 2*pos+2, diff);
        }
    }

    public int sumRange(int i, int j) {
        return getSumRange(i, j, 0 /*st*/, size-1 /*end*/, 0 /*pos*/);
    }

    private int getSumRange(int querySt, int queryEnd, int st, int end, int pos) {
        if(querySt <= st && queryEnd >= end)
            return rangeSumArray[pos];
        if(querySt > end || queryEnd < st)
            return 0;
        int mid = st + (end-st)/2;
        return getSumRange(querySt, queryEnd, st, mid, 2*pos+1) +
                getSumRange(querySt, queryEnd, mid+1, end, 2*pos+2);
    }

    public static void main(String[] args) {
        Problem307_RangeSumQuery obj = new Problem307_RangeSumQuery(new int[]{7,2,7,2,0});
        obj.update(4, 6);
        System.out.println(obj.sumRange(0, 4));
        obj.update(0, 2);
        System.out.println(obj.sumRange(0, 4));
        obj.update(0, 9);
        System.out.println(obj.sumRange(4, 4));
        obj.update(3, 8);
        System.out.println(obj.sumRange(0, 4));
    }
}

/**
 * ["NumArray","update","update","update","sumRange","update","sumRange","update","sumRange","sumRange","update"]
 * [[[7,2,7,2,0]],[4,6], [0,2],   [0,9],   [4,4],    [3,8],    [0,4],    [4,1],   [0,3],      [0,4],     [0,4]]
 */
