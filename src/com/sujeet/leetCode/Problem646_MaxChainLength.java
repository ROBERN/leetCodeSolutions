package com.sujeet.leetCode;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Problem646_MaxChainLength {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
        int N = pairs.length;

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for(int j = 1; j < N; j++) {
            for(int i = 0; i < j; i++) {
                if(pairs[i][1] < pairs[j][0]) {
                    dp[j] = Math.max(dp[j], dp[i]+1);
                }
            }
        }

        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String[] args) {
        Problem646_MaxChainLength obj = new Problem646_MaxChainLength();
        System.out.println(obj.findLongestChain(new int[][]{
                {1,2}, {2,3}, {3,4}
        }));
    }
}
