package com.sujeet.leetCode;

import java.util.PriorityQueue;

public class Problem406_QueueReconstrution {
    static class HeightKey implements Comparable<HeightKey>{
        int h;
        int k;

        HeightKey(int h1, int k1) {
            h = h1;
            k = k1;
        }

        @Override
        public int compareTo(HeightKey other) {
            if(this.h != other.h){
                return this.h-other.h;
            } else {
                return this.k - other.k;
            }
        }
    }

    public int[][] reconstructQueue(int[][] people) {
        int[][] sol = new int[people.length][2];
        for(int i = 0; i < sol.length; i++) {
            sol[i][0]=-1;
        }
        PriorityQueue<HeightKey> q = new PriorityQueue<>();
        for (int[] person : people) {
            q.add(new HeightKey(person[0], person[1]));
        }

        while(!q.isEmpty()) {
            HeightKey key = q.peek();
            q.poll();
            int numOfPeopleBefore = key.k;
            int count = 0;
            int idx = 0;
            while(count < numOfPeopleBefore) {
                if(sol[idx][0] == -1 || sol[idx][0] == key.h) { // is unoccupied (ie. some higher guys will take it or same height)
                    count++;
                }
                idx++;
            }
            while(sol[idx][0] != -1) {
                idx++;
            }
            sol[idx][0] = key.h;
            sol[idx][1] = key.k;
        }
        return sol;
    }
}
