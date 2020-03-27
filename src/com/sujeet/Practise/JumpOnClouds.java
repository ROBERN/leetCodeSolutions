package com.sujeet.Practise;

import java.util.Arrays;

public class JumpOnClouds {
    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(int[] c) {
        int cloudLength = c.length;
        if(cloudLength == 0) {
            return 0;
        }
        int[] minJumpsDP = new int[cloudLength];
        // at the end, we have already reached to our destination, so no more jumps
        minJumpsDP[cloudLength-1] = 0;
        for(int idx = cloudLength-2; idx >= 0; idx--) {
            int currVal = c[idx];
            if(currVal == 1) {
                minJumpsDP[idx] = Integer.MAX_VALUE; // not possible to jump anymore
            } else {
                minJumpsDP[idx] = getMinJumpFrom(c, idx, minJumpsDP);
            }
        }

        return minJumpsDP[0];
    }

    private static int getMinJumpFrom(int[] c, int idx, int[] dp) {
        int currVal = c[idx]; // question is confusing as it says the value will always be 0 ?!
        int jumps1 = getMinStepsForIdx(dp, idx+currVal+1);
        int jumps2 = getMinStepsForIdx(dp, idx+currVal+2);
        int minNextJump = Math.min(jumps1, jumps2);
        return minNextJump == Integer.MAX_VALUE ? Integer.MAX_VALUE : minNextJump +1;
    }

    private static int getMinStepsForIdx(int[] dp, int idx) {
        return  idx < dp.length ? dp[idx] : Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        int[] list = new int[]{0, 0, 0, 1, 0, 0};
        JumpOnClouds.jumpingOnClouds(list);
    }
}
