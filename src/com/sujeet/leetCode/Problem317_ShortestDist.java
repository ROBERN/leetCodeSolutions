package com.sujeet.leetCode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem317_ShortestDist {
    private static class Entry {
        int dist;
        int row;
        int col;
        Entry(int d, int r, int c) {
            dist = d;
            row = r;
            col = c;
        }
    }

    private int[][] grid;
    private int rowSz;
    private int colSz;
    private int onesSeen;

    public int shortestDistance(int[][] grid) {
        rowSz = grid.length;
        if(rowSz == 0)
            return -1;
        colSz = grid[0].length;
        this.grid = grid;
        int ones = getOneCounts(grid);
        int minDist = Integer.MAX_VALUE;
        for(int r= 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                if(grid[r][c] == 0) {
                    onesSeen = 0;
                    int distSum = bfs(grid, r, c);
                    if(onesSeen == ones) {
                        if (minDist > distSum) {
                            System.out.println("row :" + r + " col: " + c);
                        }
                        minDist = Math.min(minDist, distSum);
                    }
                }
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 :minDist;
    }

    private int bfs(int[][] grid, int r, int c) {
        boolean[][] visited = new boolean[rowSz][colSz];
        int sumDist = 0;

        Queue<Entry> queue = new LinkedList<>();
        queue.add(new Entry(0, r, c));
        visited[r][c] = true;

        while(!queue.isEmpty()) {
            Queue<Entry> tempQueue = new LinkedList<>();
            while(!queue.isEmpty()) {
                Entry entry = queue.poll();
                sumDist += addNeighborsToQueue(entry, tempQueue, visited);
            }
            queue = tempQueue;
        }
        return sumDist;
    }
    private int[] rowInc = new int[]{0, 1, 0 , -1};
    private int[] colInc = new int[]{1, 0, -1, 0};

    private int addNeighborsToQueue(Entry entry, Queue<Entry> queue, boolean[][] visited) {
        int row = entry.row;
        int col = entry.col;
        int dist = entry.dist;

        int distSum = 0;
        for (int idx = 0; idx < rowInc.length; idx++) {
            int rowI = rowInc[idx];
            int colI = colInc[idx];
            distSum += addToQueue(row+rowI, col+colI, queue, visited, entry.dist);
        }
        return distSum;
    }

    private int addToQueue(int row, int col, Queue<Entry> queue, boolean[][] visited, int dist) {
        if(row < 0 || col < 0 || row >= rowSz || col >= colSz || grid[row][col] == 2 || visited[row][col]) {
            return 0;
        }
        visited[row][col] = true;
        dist++;
        int distSum = 0;
        if(grid[row][col] == 1) {
            distSum += dist;
            onesSeen++;
        } else {
            queue.add(new Entry(dist, row, col));
        }
        return distSum;
    }

    private int getOneCounts(int[][] grid) {
        int count = 0;
        for(int[] row : grid) {
            for(int item : row) {
                if(item == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Problem317_ShortestDist obj =  new Problem317_ShortestDist();
        System.out.println(obj.shortestDistance(new int[][]{
                {1,0,2,0,1},
                {0,0,0,0,0},
                {0,0,1,0,0}
        }));
    }
}
