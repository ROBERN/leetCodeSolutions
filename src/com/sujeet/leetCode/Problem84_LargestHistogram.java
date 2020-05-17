package com.sujeet.leetCode;

import java.util.Deque;
import java.util.LinkedList;

public class Problem84_LargestHistogram {

    public static int largestRectangleArea(int[] heights) {
        int[] prevSmallerIdx = getPrevSmallerIdx(heights);
        int[] nextSmallerIdx = getNextSmallerIdx(heights);

        int maxHeight = 0;

        for(int i = 0; i < heights.length; i++) {
            int currVal = heights[i] * ((i-prevSmallerIdx[i])+(nextSmallerIdx[i]-i-1));
            if(currVal > maxHeight)
                maxHeight = currVal;
        }
        return maxHeight;
    }

    private static int[] getPrevSmallerIdx(int[] heights) {
        int len = heights.length;

        int[] prevSmallerIdx = new int[len];
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < len; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.poll();
            }
            prevSmallerIdx[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        return prevSmallerIdx;
    }

    private static int[] getNextSmallerIdx(int[] heights) {
        int len = heights.length;

        int[] nextSmallerIdx = new int[len];
        Deque<Integer> stack = new LinkedList<>();
        for(int i = len-1; i >= 0; i--) {
            while(!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.poll();
            }
            nextSmallerIdx[i] = stack.isEmpty() ? len : stack.peek();
            stack.add(i);
        }
        return nextSmallerIdx;
    }

    public static void main(String[] args) {
        largestRectangleArea(new int[] {
                2,1,5,6,2,3
        });
    }
}
