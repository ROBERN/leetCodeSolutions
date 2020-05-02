package com.sujeet.leetCode;

import java.util.PriorityQueue;

public class Problem295_FindMedianStream {
    private PriorityQueue<Integer> hi;
    private PriorityQueue<Integer> low;
    /** initialize your data structure here. */
<<<<<<< HEAD
    private Problem295_FindMedianStream() {
=======
    public Problem295_FindMedianStream() {
>>>>>>> 446f9d0f5a3da39573e110796fc9ef6aa77ed852
        hi = new PriorityQueue<>((a, b) -> b-a);
        low = new PriorityQueue<>();
    }

<<<<<<< HEAD
    private void addNum(int num) {
        low.add(num);
        hi.add(low.poll());

=======
    public void addNum(int num) {
        low.add(num);
        low.remove(num);
        hi.add(low.poll());
>>>>>>> 446f9d0f5a3da39573e110796fc9ef6aa77ed852
        if(low.size() < hi.size()) {
            low.add(hi.poll());
        }
    }

<<<<<<< HEAD
    private double findMedian() {
=======
    public double findMedian() {
        if (low.isEmpty()) {
            return 0;
        }
        if (hi.isEmpty()) {
            return low.peek();
        }
>>>>>>> 446f9d0f5a3da39573e110796fc9ef6aa77ed852
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
<<<<<<< HEAD
        obj.findMedian();
=======
>>>>>>> 446f9d0f5a3da39573e110796fc9ef6aa77ed852
    }
}
