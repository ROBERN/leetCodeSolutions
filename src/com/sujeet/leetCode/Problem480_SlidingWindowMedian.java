package com.sujeet.leetCode;

import java.util.PriorityQueue;

public class Problem480_SlidingWindowMedian {
    public static double[] medianSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if(len < k) {
            return new double[0];
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b)->b-a);

        double[] res = new double[nums.length - k +1];
        // prep
        for(int i = 0; i < k; i++) {
            addNum(minHeap, maxHeap, nums[i]);
        }
        res[0] = getMedian(minHeap, maxHeap, k);
        for(int i = k; i < len; i++) {
            int toRemove = nums[i-k];
            if(!minHeap.remove(toRemove)) {
                maxHeap.remove(toRemove);
            }
            addNum(minHeap, maxHeap, nums[i]);
            res[i-k+1] = getMedian(minHeap, maxHeap, k);
        }
        return res;
    }

    private static void addNum(PriorityQueue<Integer> minHeap,  PriorityQueue<Integer> maxHeap, int num) {
        minHeap.add(num);
        maxHeap.add(minHeap.poll());

        if(minHeap.size() < maxHeap.size()) {
            minHeap.add(maxHeap.poll());
        }
    }

    private static double getMedian( PriorityQueue<Integer> minHeap,  PriorityQueue<Integer> maxHeap, int k) {
        if(k %2 ==1) {
            return minHeap.peek();
        } else {
            return minHeap.peek()/2.0 + maxHeap.peek()/2.0;
        }
    }

    public static void main(String[] args) {
        Problem480_SlidingWindowMedian.medianSlidingWindow(new int[]{-2147483648,-2147483648, 2147483647,2147483647}, 3);
    }
}
