package com.sujeet.leetCode;

import java.util.Arrays;

public class Problem361_BombEnemies {

    public int maxKilledEnemies(char[][] grid) {
        int N = grid.length;
        if(N == 0)
            return 0;
        int M = grid[0].length;

        int[] verticalEnemies = new int[M];
        int[][] killedEnemies = new int[N][M];

        for(int i = 0; i < N; i++) {
            int horizontalEnemies = 0;
            for(int j = 0; j < M; j++) {
                if(grid[i][j] == 'E') {
                    horizontalEnemies++;
                    verticalEnemies[j]++;
                } else if(grid[i][j] == 'W') {
                    horizontalEnemies = 0;
                    verticalEnemies[j] = 0;;
                } else if(grid[i][j] == '0') {
                    killedEnemies[i][j] = verticalEnemies[j] + horizontalEnemies;
                }
            }
        }

        int maxEnemies = 0;
        Arrays.fill(verticalEnemies, 0);
        for(int i = N-1; i >= 0; i--) {
            int horizontalEnemies = 0;
            for(int j = M-1; j >= 0; j--) {
                if(grid[i][j] == 'E') {
                    horizontalEnemies++;
                    verticalEnemies[j]++;
                } else if(grid[i][j] == 'W') {
                    horizontalEnemies = 0;
                    verticalEnemies[j] = 0;;
                } else if(grid[i][j] == '0') {
                    killedEnemies[i][j] += verticalEnemies[j] + horizontalEnemies;
                    maxEnemies = Math.max(maxEnemies, killedEnemies[i][j]);
                }
            }
        }
        return maxEnemies;

    }

    public static void main(String[] args) {
        Problem361_BombEnemies obj =  new Problem361_BombEnemies();
        System.out.println(obj.maxKilledEnemies(new char[][]{
                {'0','E','0','0'},{'E','0','W','E'},{'0','E','0','0'}
        }));
    }
}
