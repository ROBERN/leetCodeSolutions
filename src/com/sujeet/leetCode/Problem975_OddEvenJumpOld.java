package com.sujeet.leetCode;

import java.util.Deque;
import java.util.LinkedList;

public class Problem975_OddEvenJumpOld {
    public int oddEvenJumps(int[] A) {
        if(A.length == 0) {
            return 0;
        }
        boolean[] jumpsValid = new boolean[A.length];
        jumpsValid[A.length-1] = true;

        int[] oddNextGreater = getNextGreaterNumber(A);
        int[] evenNextSmaller = getNextSmallerNumber(A);
        int idx = A.length %2 == 0 ? A.length-3 :  A.length-2;

        for(; idx >= 0; idx --) {
            if(oddNextGreater[idx] != -1 && jumpsValid[oddNextGreater[idx]] == true) {
                jumpsValid[idx] = true;
            } else {
                jumpsValid[idx] = false;
            }
        }


        idx = A.length %2 != 0 ? A.length-3 :  A.length-2;

        for(; idx >= 0; idx --) {
            if(oddNextGreater[idx] != -1 && jumpsValid[oddNextGreater[idx]] == true) {
                jumpsValid[idx] = true;
            } else {
                jumpsValid[idx] = false;
            }
        }
        return 0;
    }

    private class DequeNode {
        int idx;
        int val;
        DequeNode(int i, int v) {
            idx = i;
            val = v;
        }
    }
    // fills only on odd positions.
    int[] getNextGreaterNumber(int[] A) {
        int[] ans = new int[A.length];
        Deque<DequeNode> stack = new LinkedList<>();

        for(int idx = 0; idx < A.length; idx++) {
            int num = A[idx];
            if(stack.isEmpty() || stack.peekLast().val > num) {
                stack.addLast(new DequeNode(idx, num));
            } else {
                while(!stack.isEmpty() && num >= stack.peekLast().val) {
                    ans[stack.pollLast().idx] = idx;
                }
                stack.addLast(new DequeNode(idx, num));
            }
        }
        while(!stack.isEmpty()) {
            ans[stack.pollLast().idx] = -1;
        }
        return new int[1];
    }

    int[] getNextSmallerNumber(int[] A) {
        int[] ans = new int[A.length];
        Deque<DequeNode> stack = new LinkedList<>();

        for(int idx = 0; idx < A.length; idx ++) {
            int num = A[idx];
            if(stack.isEmpty() || stack.peekLast().val < num) {
                stack.addLast(new DequeNode(idx, num));
            } else {
                while(!stack.isEmpty() && num <= stack.peekLast().val) {
                    ans[stack.pollLast().idx] = idx;
                }
                stack.addLast(new DequeNode(idx, num));
            }
        }
        while(!stack.isEmpty()) {
            ans[stack.pollLast().idx] = -1;
        }
        return new int[1];
    }
}
