package com.sujeet.leetCode;

public class Problem557_ReverseWords {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();

        int st = 0;
        StringBuilder sbTemp = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                st++;
                if (sbTemp.length() > 0 ) {
                    sb.append(sbTemp.reverse());

                    sbTemp = new StringBuilder();
                }
                sb.append(c);
            } else {
                sbTemp.append(c);
            }
        }
        if (sbTemp.length() > 0 ) {
            sb.append(sbTemp.reverse());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Problem557_ReverseWords obj = new Problem557_ReverseWords();

        System.out.println(obj.reverseWords("Let's take LeetCode contest"));
    }
}
