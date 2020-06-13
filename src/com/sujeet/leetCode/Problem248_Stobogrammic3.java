package com.sujeet.leetCode;

public class Problem248_Stobogrammic3 {

    private static final char[][] PAIRS = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};

    private String low;
    private String high;
    public int strobogrammaticInRange(String low, String high) {
        if (Long.parseLong(high) < Long.parseLong(low))
            return 0;
        this.low = low;
        this.high = high;
        int result = 0;
        for (int len = low.length()+1; len < high.length(); len++) {
            result += getCountOfLen(len);
        }
        char[] lowChar = new char[low.length()];
        result += dfs(lowChar, 0, low.length()-1);

        if (high.length() != low.length()) {
            char[] highChar = new char[high.length()];
            result += dfs(highChar, 0, high.length() - 1);
        }

        return result;
    }

    public int dfs(char[] c, int left, int right) {
        if (left > right) {
            String s = new String(c);
            if (Long.parseLong(s) < Long.parseLong(low) || Long.parseLong(s) > Long.parseLong(high)) {
                return 0;
            };
            return 1;
        }

        int result = 0;
        for (char[] pair : PAIRS) {
            c[left] = pair[0];
            c[right] = pair[1];
            if (c.length != 1 && c[0] == '0') {
                continue;
            }
            if (left == right && pair[0] != pair[1]) {
                continue;
            }
            result += dfs(c, left + 1, right - 1);
        }
        return result;
    }

    private int getCountOfLen(int len) {
        if (len == 1)
            return 3;
        if (len == 2)
            return 4;
        if(len == 3)
            return 4*3;

        int fullPositions;
        if (len%2==0)
            fullPositions = (len/2) -1;
        else
            fullPositions = ((len-1)/2-1)*3;
        return 4*5*fullPositions; // we consider first as non-Zero. 4 options left. And then 5 options other than middle, which has 3 options
    }

    public static void main(String[] args) {
        Problem248_Stobogrammic3 obj = new Problem248_Stobogrammic3();
        System.out.println(obj.strobogrammaticInRange("0", "2147483647"));
    }
}
