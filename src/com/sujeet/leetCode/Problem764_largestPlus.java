package com.sujeet.leetCode;

import java.util.*;

public class Problem764_largestPlus {
    Map<Integer, Set<Integer>> minesMap;
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        minesMap = new HashMap<>();
        for(int i = 0; i < N; i++)
            minesMap.put(i, new HashSet<>());
        for(int[] mine : mines) {
            minesMap.get(mine[0]).add(mine[1]);
        }

        int[][][] counts = new int[N][N][4];
        // l , u , r , d
        // sweep left and up:
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(minesMap.get(i).contains(j)) {
                    counts[i][j][0] = 0;
                    counts[i][j][1] = 0;
                } else if(i == 0 || j == 0) {
                    counts[i][j][0] = 1;
                    counts[i][j][1] = 1;
                } else {
                    counts[i][j][0] = counts[i][j-1][0]+1;
                    counts[i][j][1] = counts[i-1][j][1]+1;
                }
            }
        }

        // sweep right and down:
        for(int i = N-1; i >= 0; i--) {
            for(int j = N-1; j >= 0; j--) {
                if(minesMap.get(i).contains(j)) {
                    counts[i][j][2] = 0;
                    counts[i][j][3] = 0;
                } else if(i == N-1 || j == N-1) {
                    counts[i][j][2] = 1;
                    counts[i][j][3] = 1;
                } else {
                    counts[i][j][2] = counts[i][j+1][2]+1;
                    counts[i][j][3] = counts[i+1][j][3]+1;
                }
            }
        }

        int maxCross = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                maxCross = Math.max(maxCross, Arrays.stream(counts[i][j]).min().getAsInt());
            }
        }
        return maxCross;
    }

    public static void main(String[] args) {
        Problem764_largestPlus obj = new Problem764_largestPlus();
        obj.orderOfLargestPlusSign(2, new int[][]{
                {0,0},{1,1}
        });
    }
}
