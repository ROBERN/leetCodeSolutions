package com.sujeet;

import java.util.LinkedList;
import java.util.Queue;

public class Problem346_MovingAvg {
    Queue<Integer> q = new LinkedList<>();
    int capacity = 0;
    int size = 0;
    int sum = 0;
    /** Initialize your data structure here. */
    public Problem346_MovingAvg(int cap) {
        capacity = cap;
    }

    public double next(int val) {
        if(size == capacity) {
            sum -= q.peek();
            size--;
            q.poll();
        }
        sum += val;
        size++;
        q.add(val);
        return sum/(double)size;
    }
}
