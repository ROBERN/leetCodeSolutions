package com.sujeet.leetCode;

import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;

public class Problem772_BasicCalc3 {
    public int calculate(String s) {
        return Integer.parseInt(evaluate(s, 0).getKey());
    }

    private Pair<String, Integer> evaluate(String s, int idx) {
        Deque<String> stack = new LinkedList<>();
        while(idx < s.length()) {
            char c = s.charAt(idx);
            if(c == ' ') {
                idx++;
                continue;
            }
            if(Character.isDigit(c)) {
                Pair<String, Integer> valToIndex = getNum(s, idx);
                idx = valToIndex.getValue();
                stack.addLast(valToIndex.getKey());
            } else if(c == '*' || c == '/') {
                //evaluate right away
                Pair<String, Integer> valToIndex = getNum(s, idx+1);
                idx = valToIndex.getValue();
                long nextVal = Long.parseLong(valToIndex.getKey());
                long prev = Long.parseLong(stack.pollLast());
                long val = c == '*' ? prev*nextVal : prev/nextVal;

                stack.addLast(Long.toString(val));
            } else if(c == '(') {
                Pair<String, Integer> valToIndex = evaluate(s, idx+1);
                stack.addLast(valToIndex.getKey());
                idx = valToIndex.getValue();
            } else if(c == ')') {
                return new Pair<>(evaluate(stack), idx+1);
            }
            else {
                stack.addLast(Character.toString(c));
                idx++;
            }
        }
        return new Pair<>(evaluate(stack), idx);
    }

    private String evaluate(Deque<String> stack) {
        long prev = 0;

        long value = 0;
        while (!stack.isEmpty()) {
            String currStr = stack.pollLast();
            if (currStr.equals("+") ) {
                value += prev;
            } else if(currStr.equals("-")) {
                value -= prev;
            } else {
                prev = Long.parseLong(currStr);
            }
        }
        return Long.toString(value + prev);
    }
    private Pair<String, Integer> getNum(String s, int stIdx) {
        while (s.charAt(stIdx) == ' ')
            stIdx++;
        if (s.charAt(stIdx) == '('){
            return evaluate(s, stIdx+1);
        }
        StringBuilder sb = new StringBuilder();
        while(stIdx != s.length() && Character.isDigit(s.charAt(stIdx))) {
            sb.append(s.charAt(stIdx++));
        }
        return new Pair<>(sb.toString(), stIdx);
    }

    public static void main(String[] args) {
        Problem772_BasicCalc3 obj =  new Problem772_BasicCalc3();
        System.out.println(obj.calculate("0-2147483648"));
    }
}
