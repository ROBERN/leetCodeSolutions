package com.sujeet.leetCode;

import java.util.Arrays;

public class Problem486_PredictTheWinner {
    private int N;
    private int[][][]dp;
    private int[] nums;
    public boolean PredictTheWinner(int[] nums) {
        N = nums.length;
        dp = new int[N][N][2];
        this.nums = nums;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                Arrays.fill(dp[i][j], -1);

        int[] sol = backTrackWithMemo(0, N-1);
        return (sol[0] > sol[1]);
    }

    private int[] backTrackWithMemo(int st, int end) {
        if(dp[st][end][0] != -1)
            return dp[st][end];
        if(st == end) {
            return dp[st][end] = new int[]{nums[st], 0};
        }
        if(st == end-1) {
            return dp[st][end] = new int[]{Math.max(nums[st], nums[end]), Math.min(nums[st], nums[end])};
        }

        //sol1 :
        int[] sol1 = backTrackWithMemo(st+1, end);
        sol1 = new int[]{sol1[0], sol1[1]};
        swap(sol1, 0, 1);
        sol1[0] += nums[st];

        int[] sol2 = backTrackWithMemo(st, end-1);
        sol2 = new int[]{sol2[0], sol2[1]};
        swap(sol2, 0, 1);
        sol2[0] += nums[end];


        return dp[st][end] = (sol1[0] > sol2[0]) ? sol1 : sol2;
    }

    private void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public static void main(String[] args) {
        Problem486_PredictTheWinner obj = new Problem486_PredictTheWinner();
        obj.PredictTheWinner(new int[] {1, 5, 233, 7});
    }
}
