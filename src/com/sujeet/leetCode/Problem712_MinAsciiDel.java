package com.sujeet.leetCode;

public class Problem712_MinAsciiDel {
    public static int minimumDeleteSum(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();

        String[][] dp = new String[l1+1][l2+1];

        for(int idx1 = 0; idx1 <= l1; idx1++) {
            for(int idx2 = 0; idx2 <= l2; idx2++) {
                if(idx1 == 0 && idx2== 0) {
                    dp[idx1][idx2] = "";
                } else if(idx2 == 0) {
                    dp[idx1][0] = Character.toString(s1.charAt(idx1-1));
                } else if(idx1 == 0) {
                    dp[0][idx2] = Character.toString(s2.charAt(idx2-1));
                } else {
                    if(s1.charAt(idx1-1) == s2.charAt(idx2-1)) {
                        dp[idx1][idx2] = dp[idx1-1][idx2-1];
                    } else if(dp[idx1-1][idx2].length() < dp[idx1][idx2-1].length()) {
                        dp[idx1][idx2] = s1.charAt(idx1-1) + dp[idx1-1][idx2];
                    } else {
                        dp[idx1][idx2] = dp[idx1][idx2-1] + s2.charAt(idx2-1);
                    }
                }
            }
        }
        String res = dp[l1][l2];
        int ans = 0;
        for(int i = 0; i < res.length(); i++)
            ans += (int)res.charAt(i);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(minimumDeleteSum("sea", "eat"));
    }
}
