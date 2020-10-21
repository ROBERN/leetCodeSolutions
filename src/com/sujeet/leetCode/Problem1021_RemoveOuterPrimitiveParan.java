package com.sujeet.leetCode;

import java.util.Deque;
import java.util.LinkedList;

public class Problem1021_RemoveOuterPrimitiveParan {
    public String removeOuterParentheses(String S) {

        Deque<Character> stack = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        int stIdx = 0;
        int currIdx = 0;
        for(char c : S.toCharArray()) {
            if(c == '(') {
                stack.addLast(c);
            } else {
                stack.pollLast();
            }
            if(stack.isEmpty()) {
                sb.append(S.substring(stIdx + 1, currIdx));
                stIdx = currIdx+1;
            }

            currIdx++;
        }
        return sb.toString();
    }
}
