package com.sujeet.leetCode;

import java.util.PriorityQueue;

public class Problem295_FindMedianStream {
    private PriorityQueue<Integer> hi;
    private PriorityQueue<Integer> low;
    /** initialize your data structure here. */
    public Problem295_FindMedianStream() {
        hi = new PriorityQueue<>((a, b) -> b-a);
        low = new PriorityQueue<>();
    }

    public void addNum(int num) {
        low.add(num);
        low.remove(num);
        hi.add(low.poll());
        if(low.size() < hi.size()) {
            low.add(hi.poll());
        }
    }

    public double findMedian() {
        if (low.isEmpty()) {
            return 0;
        }
        if (hi.isEmpty()) {
            return low.peek();
        }
        if(low.size() > hi.size()) {
            return low.peek();
        } else {
            return (low.peek() + hi.peek())/(double)2;
        }
    }

    public static void main(String[] args) {
        Problem295_FindMedianStream obj = new Problem295_FindMedianStream();
        obj.addNum(1);
        obj.addNum(2);
        obj.addNum(4);
        obj.addNum(12);
        obj.addNum(33);
        obj.findMedian();
    }
}
