package com.sujeet.leetCode;

public class Problem1140_StoneGame {
    public static int stoneGameII(int[] piles) {
        int len = piles.length;
        if(len ==0)
            return 0;
        int[] dp = new int[len+1];
        int[] jump = new int[len+1];
        dp[len] = 0;
        jump[len] = 0;
        for(int idx= len-2; idx>= 0; idx--) {
            int take1 = 0;
            int take2 = 0;
            int take3 = 0;
            int jumpTakenByNext = 0;
            if(idx < len) {
                take1 = piles[idx]; // draw first.
                jumpTakenByNext = jump[idx+1];
                take1 += dp[idx+1+jumpTakenByNext];
            }

            if(idx < len-1) {
                take2 = (piles[idx] + piles[idx+1]); // draw two values
                jumpTakenByNext = jump[idx+2];
                take2 += dp[idx+2+jumpTakenByNext];
            }
            if(idx < len-2) {
                take3 = (piles[idx] + piles[idx+1] + piles[idx+1]); // draw two values
                jumpTakenByNext = jump[idx+3];
                take2 += dp[idx+2+jumpTakenByNext];
            }
            if(take3 > take1 && take3 > take2) {
                jump[idx] = 3;
                dp[idx] = take3;
            } else if(take2 > take1) {
                jump[idx] = 2;
                dp[idx] = take2;
            } else {
                jump[idx] = 1;
                dp[idx] = take1;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(Problem1140_StoneGame.stoneGameII(new int[] {2,7,9,4,4}));
    }
}
