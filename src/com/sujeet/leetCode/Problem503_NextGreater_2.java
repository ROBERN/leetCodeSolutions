package com.sujeet.leetCode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Problem503_NextGreater_2 {
    private static class QueueNode {
        int idx;
        int val;
        QueueNode(int i, int v) {
            idx = i;
            val = v;
        }
    }

    public int[] nextGreaterElements(int[] nums) {
        Deque<QueueNode> stack = new LinkedList<>();
        Map<Integer, Integer> nextGreater = new HashMap<>();
        for(int iter = 0; iter < 2; iter++) {
            int index = 0;
            for(int num : nums) {
                if(stack.isEmpty() || stack.peekLast().val > num) {
                    stack.addLast(new QueueNode(index, num));
                } else {
                    while(!stack.isEmpty() && num > stack.peekLast().val) {
                        QueueNode stackTop = stack.pollLast();
                        int stackTopIdx = stackTop.idx;
                        nextGreater.computeIfAbsent(stackTop.idx, greater -> num);
                    }
                    stack.addLast(new QueueNode(index, num));
                }
                index++;
            }
        }

        for(int idx = 0; idx < nums.length; idx++) {
            nums[idx] = nextGreater.getOrDefault(idx, -1);
        }
        return nums;
    }
}
