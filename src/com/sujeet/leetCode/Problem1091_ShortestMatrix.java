package com.sujeet.leetCode;

import com.sun.tools.javac.util.Pair;

import java.util.*;

public class Problem1091_ShortestMatrix {

    private int[][] cache;
    private int N;
    private int[][] grid;
    public int shortestPathBinaryMatrix(int[][] grid) {
        N = grid.length;
        if(N==0)
            return 0;
        if(grid[0][0] == 1 || grid[N-1][N-1] == 1)
            return -1;
        this.grid = grid;
        cache = new int[N][N];
        for(int i = 0; i < N; i++)
            Arrays.fill(cache[i], Integer.MAX_VALUE);

        int val = getShortestPath();
        return val == Integer.MAX_VALUE ? -1 : val;
    }

    private int[] rm = new int[]{0, 0, 1, -1,  1, -1, 1, -1};
    private int[] cm = new int[]{1, -1, 0, 0,  1, -1, -1, 1};

    private Set<Pair<Integer, Integer>> visited = new HashSet<>();
    private int getShortestPath() {
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();

        Pair<Integer, Integer> node = new Pair<>(0, 0);
        q.add(node);
        visited.add(node);
        int level = 1;
        while(!q.isEmpty()) {

            Queue<Pair<Integer, Integer>> nextLevel = new LinkedList<>();

            while(!q.isEmpty()) {
                Pair<Integer, Integer> qNode = q.poll();
                int row = qNode.fst;
                int col = qNode.snd;
                if(row == col && row == N-1)
                    return level;

                for(int mvIdx = 0; mvIdx < 8; mvIdx++) {
                    Pair<Integer, Integer> newPair = new Pair<>(row + rm[mvIdx], col + cm[mvIdx]);
                    if(isValidPos(newPair)) {
                        nextLevel.add(newPair);
                        visited.add(newPair);
                    }
                }
            }
            level++;
            q = nextLevel;
        }
        return -1;
    }

    private boolean isValidPos(Pair<Integer, Integer> pos) {
        int row = pos.fst;
        int col = pos.snd;
        if(row < 0 || row >= N || col < 0 || col >= N || visited.contains(pos) || grid[row][col]==1)
            return false;
        return true;
    }

    public static void main(String[] args) {
        Problem1091_ShortestMatrix obj = new Problem1091_ShortestMatrix();
        System.out.println(obj.shortestPathBinaryMatrix(new int[][]{
                {1,0,0},{1,1,0},{1,1,0}
        }));
    }
}
