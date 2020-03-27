package com.sujeet.leetCode;

import java.util.*;

public class Problem1197_KnightMoves {
    private class Pair {
        int x;
        int y;
        Pair(int x1, int y1) {
            x = x1; y = y1;
        }
        @Override
        public int hashCode() {
            return x<<10 + y;
        }
    }

    int[][] arr = new int[][]{{1,2},{1,-2},{2,1},{2,-1},{-1,2},{-1,-2},{-2,1},{-2,-1}};

    int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        Set<Integer> visited = new HashSet<>();
        TreeSet<Integer> set = new TreeSet<>();
        int val = (int) set.toArray()[0];

        Queue<Integer> prevQ = new LinkedList<>();
        prevQ.add(0);

        int step = 0;

        while(!prevQ.isEmpty()) {
            Queue<Integer> currQ = new LinkedList<>();
            while (!prevQ.isEmpty()) {
                int key = prevQ.poll();
                int xVal = (key>>10);
                int yVal = key - (xVal<<10);
                if(xVal == x && yVal == y) {
                    return step;
                }
                for (int[] currStep : arr) {
                    int newX = xVal + currStep[0];
                    int newY = yVal + currStep[1];
                    if (Math.abs(newX) + Math.abs(newY) > 300) continue;
                    int newKey = (newX<<10) + newY;
                    if(visited.contains(newKey) || newX < -2 || newY < -2 ) continue;
                    visited.add(newKey);
                    currQ.add(newKey);
                }
            }
            step++;
            prevQ = currQ;
        }
        return -1;
    }

    public static void main(String[] args) {
        Problem1197_KnightMoves newObj = new Problem1197_KnightMoves();
        newObj.minKnightMoves(2,11);
    }
}
