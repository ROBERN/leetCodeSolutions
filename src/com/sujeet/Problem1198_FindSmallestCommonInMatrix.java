package com.sujeet;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Problem1198_FindSmallestCommonInMatrix {
    private static class Key implements Comparable<Key> {
        int val;
        int r;
        int c;

        Key(int v, int r, int c) {
            val = v;
            this.r = r;
            this.c = c;
        }
        @Override
        public int compareTo(Key other) {
            return this.val - other.val;
        }
    }

    public static int smallestCommonElement(int[][] mat) {
        int rowSz = mat.length;
        int colSz = mat[0].length;
        PriorityQueue<Key> q = new PriorityQueue<>();
        Map<Integer, Integer> valToCount = new HashMap<>();
        for(int r = 0;  r < rowSz; r++) {
            int val = mat[r][0];
            valToCount.put(val, valToCount.getOrDefault(val, 0)+1);
            q.add(new Key(mat[r][0], r, 0));
        }
        int v = q.peek().val;
        if(valToCount.get(v) == rowSz) {
            return q.peek().val;
        }

        while (!q.isEmpty()) {
            Key minKey = q.peek();
            int r = minKey.r;
            int c = minKey.c;
            if(c < colSz-1) {
                int newVal = mat[r][c+1];
                valToCount.put(newVal, valToCount.getOrDefault(newVal, 0)+1);
                if (valToCount.get(newVal) == rowSz) {
                    return newVal;
                }
                q.poll();
                q.add(new Key(newVal, r, c+1));
            } else {
                return -1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {1,2,3,4,5},
                {2,4,5,8,10},
                {3,5,7,9,11},
                {1,3,5,7,9}
        };
        Problem1198_FindSmallestCommonInMatrix.smallestCommonElement(matrix);

    }
}
