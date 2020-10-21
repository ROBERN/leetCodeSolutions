package com.sujeet.leetCode;

public class Problem1304_UniqueSumTo0 {
    public int[] sumZero(int n) {
        int[] result = new int[n];
        int left;
        int right;
        if(n%2 == 1) {
            int mid = (n-1)/2;
            result[mid] = 0;
            left = mid - 1;
            right = mid+1;
        } else {
            left = (n-1)/2;
            right = left+1;
        }
        int val = 1;
        while(left >= 0 && right < n) {
            result[left--] = -1*val;
            result[right++] = val;
            val++;
        }
        return result;
    }
}
