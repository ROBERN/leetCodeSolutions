package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.Arrays;

public class Problem937_ReorderLogFiles {
    public static String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (a, b) -> {
            String[] strs1 = a.split(" ");
            String[] strs2 = b.split(" ");
            if(!isLogDigit(strs1[1]) && isLogDigit(strs2[1])){
                return 1;
            }
            if(!isLogDigit(strs1[1]) && isLogDigit(strs2[1])){
                return -1;
            }
            StringBuilder sb1 = new StringBuilder();
            for(int i = 1; i < strs1.length; i++)
                sb1.append(strs1[i]);
            String str1 = sb1.toString();

            StringBuilder sb2 = new StringBuilder();
            for(int i = 1; i < strs2.length; i++)
                sb2.append(strs2[i]);
            String str2 = sb2.toString();

            if(str1.equals(str2)) {
                return strs1[0].compareTo(strs2[0]);
            }
            return str1.compareTo(str2);
        });
        return logs;
    }
    private static boolean isLogDigit(String str) {
        String[] logSeq = str.split(" ");
        return Character.isDigit(logSeq[1].charAt(0));
    }

    public static void main(String[] args) {
        Problem937_ReorderLogFiles.reorderLogFiles(new String[]{
                "dig1 8 1 5 1", "let1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"
        });
    }
}
