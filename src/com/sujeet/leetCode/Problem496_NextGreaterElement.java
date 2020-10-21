package com.sujeet.leetCode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Problem496_NextGreaterElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new LinkedList<>();
        Map<Integer, Integer> nexGreater = new HashMap<>();
        for(int num2 : nums2) {
            if(stack.isEmpty() || stack.peekLast() > num2) {
                stack.addLast(num2);
            } else {
                while(!stack.isEmpty() && stack.peekLast() < num2) {
                    int num = stack.pollLast();
                    nexGreater.put(num, num2);
                }
                stack.addLast(num2);
            }
        }
        while(!stack.isEmpty()) {
            int num = stack.pollLast();
            nexGreater.put(num, -1);
        }

        for(int idx = 0; idx < nums1.length; idx++) {
            nums1[idx] = nexGreater.get(nums1[idx]);
        }
        return nums1;
    }
}
