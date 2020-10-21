package com.sujeet.leetCode;

public class Problem1004_MaxConsecutiveOnes3 {
    public int longestOnes(int[] A, int K) {
        int left = 0;
        int right = 0;
        int usedK = 0;
        int maxWindow = 0;
        while (right < A.length && left <= right) {
            if (A[right] == 0) {
                if (usedK < K) {
                    usedK++;
                    right++;
                } else {
                    while (left <= right && A[left] != 0) {
                        left++;
                    }
                    left++;
                    usedK--;
                }
            } else {
                right++;
                maxWindow = Math.max(maxWindow, right - left + 1);
            }
        }
        return maxWindow;
    }

    public static void main(String[] args) {
        Problem1004_MaxConsecutiveOnes3 obj = new Problem1004_MaxConsecutiveOnes3();
        obj.longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2);
    }
}
