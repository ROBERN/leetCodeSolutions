package com.sujeet;

public class Problem198_HouseRobber {
    public int rob(int[] nums) {
        int len = nums.length;
        if(len ==0){
            return 0;
        }
        int dp[][] = new int[len][len];

        for(int sz = 0; sz < len; sz++) {
            for(int st = 0; st + sz < len; st++) {
                int end = st+sz;

                if(st == end) {
                    dp[st][end] = nums[st];
                } else if(end == st+1) {
                    dp[st][end] = Math.max(nums[st], nums[end]);
                } else {
                    int max = 0;
                    for(int it = st; it <= end; it++) {
                        int val = nums[it] +
                                (((it - 2) >= st) ? dp[st][it-2] : 0) +
                                (((it + 2) <= end) ? dp[it+2][end] : 0);
                        if(val > max) {
                            max = val;
                        }
                    }
                    dp[st][end] = max;
                } //end else
            }// end st
        }// end gap
        return dp[0][len-1];
    }
}
