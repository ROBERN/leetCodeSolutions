package com.sujeet.leetCode;

import javafx.util.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

public class Problem227_BasicCalc2 {
    public int calculate(String s) {
        Deque<String> stack = new LinkedList<>();
        int idx = 0;
        while(idx < s.length()) {
            char c = s.charAt(idx);
            if(c == ' ') {
                idx++;
                continue;
            }
            if(Character.isDigit(c)) {
                Pair<String, Integer> valToIndex = getNum(s, idx);
                stack.addLast(valToIndex.getKey());
                idx = valToIndex.getValue();
            } else if(c == '*' || c == '/') {
                long prev = Long.parseLong(Objects.requireNonNull(stack.pollLast()));
                Pair<String, Integer> valToIndex = getNum(s, idx+1);
                long next = Long.parseLong(valToIndex.getKey());
                long ans = c == '*' ? prev*next : prev/next;
                stack.addLast(Long.toString(ans));
                idx = valToIndex.getValue();
            } else if(c == '+' || c == '-') {
                stack.addLast(Character.toString(c));
                idx++;
            }
        }

        return evaluate(stack);
    }

    private Pair<String, Integer> getNum(String s, int idx) {
        StringBuilder sb = new StringBuilder();
        while(idx < s.length()) {
            if (s.charAt(idx) == ' ') {
                idx++;
                continue;
            }
            if(Character.isDigit(s.charAt(idx))) {
                sb.append(s.charAt(idx++));
            } else {
                break;
            }
        }
        return new Pair<>(sb.toString(), idx);
    }

    private int evaluate(Deque<String> stack) {
        int res = 0;
        int prev = 0;
        while(!stack.isEmpty()) {
            String str = stack.pollLast();
            if(str.equals("+") || str.equals("-")) {
                res += prev;
            } else {
                prev = Integer.parseInt(str);
            }
        }
        return res + prev;
    }

    public static void main(String[] args) {
        Problem227_BasicCalc2 obj = new Problem227_BasicCalc2();
        System.out.println(obj.calculate("3+2*2"));
    }
}
