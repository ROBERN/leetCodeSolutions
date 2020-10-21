package com.sujeet.leetCode;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Problem282_ExpressionAddOperator {

    private List<String> results;
    private int target;
    private String source;
    public List<String> addOperators(String num, int target) {
        this.target = target;
        this.results = new ArrayList<>();
        this.source = num;
        backTrack("", 0);

        return results;
    }

    private void backTrack(String currStr, int currIdx) {
        if (currIdx == source.length()) {
            if(calculate(currStr) == target) {
                results.add(currStr);
            }
            return;
        }
        if (currStr.length() > 0 && Character.isDigit(currStr.charAt(currStr.length()-1))) {
            backTrack(currStr + "+", currIdx);
            backTrack(currStr + "-", currIdx);
            backTrack(currStr + "*", currIdx);
        }
        backTrack(currStr + source.charAt(currIdx), currIdx+1);
    }

    private int calculate(String s) {
        Deque<String> stack = new LinkedList<>();
        int idx = 0;
        while (idx < s.length()) {
            char c = s.charAt(idx);
            if (Character.isDigit(c)) {
                Pair<String, Integer> pr = getNextNum(s, idx);
                if(pr.getKey().length() > 1 && pr.getKey().startsWith("0")) {
                    return Integer.MAX_VALUE;
                }
                stack.addLast(pr.getKey());
                idx = pr.getValue();
            } else{
                if (c == '*') {
                    int prev = Integer.parseInt(stack.pollLast());
                    Pair<String, Integer> pr = getNextNum(s, idx+1);
                    idx = pr.getValue();
                    int next = Integer.parseInt(pr.getKey());
                    if(pr.getKey().length() > 1 && pr.getKey().startsWith("0")) {
                        return Integer.MAX_VALUE;
                    }
                    stack.addLast(Integer.toString(prev*next));
                } else{
                    stack.addLast(Character.toString(c));
                    idx++;
                }
            }
        }
        return evaluate(stack);
    }

    private int evaluate(Deque<String> stack) {
        int prev = 0;
        int res = 0;
        while (!stack.isEmpty()) {
            String item = stack.pollLast();
            if (item.equals("+")) {
                res += prev;
            } else if (item.equals("-")) {
                res -= prev;
            } else{
                if(item.length() > 1 && item.startsWith("0")) {
                    return Integer.MAX_VALUE;
                }
                try {
                    prev = Integer.parseInt(item);
                } catch (NumberFormatException e) {
                    return Integer.MAX_VALUE;
                }
            }
        }
        return res+prev;
    }

    private Pair<String, Integer> getNextNum(String s, int stIdx) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (idx = stIdx; idx < s.length(); idx++) {
            char c = s.charAt(idx);
            if(!Character.isDigit(c)) {
                break;
            }
            sb.append(c);
        }
        return new Pair<>(sb.toString(), idx);
    }

    public static void main(String[] args) {
        Problem282_ExpressionAddOperator obj = new Problem282_ExpressionAddOperator();
        System.out.println(obj.addOperators("000", 0));
    }
}
