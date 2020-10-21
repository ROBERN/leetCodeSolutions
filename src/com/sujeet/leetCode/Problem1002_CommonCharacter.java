package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem1002_CommonCharacter {
    public List<String> commonChars(String[] A) {
        int len = A.length;
        if(len == 0) {
            return new ArrayList<String>();
        }
        int[][] charCounts = new int[len][26];

        int idx = 0;
        for(String item : A) {
            for(char c: item.toCharArray()) {
                charCounts[idx][c-'a']++;
            }
            idx++;
        }

        List<String> result = new ArrayList<>();

        for(int charIdx = 0; charIdx < 26; charIdx++) {
            int minCount = Integer.MAX_VALUE;
            for(int strIdx = 0; strIdx < len; strIdx++) {
                minCount = Math.min(minCount, charCounts[strIdx][charIdx]);
            }
            if(minCount > 0) {
                char c = (char)('a'+charIdx);
                for(int i = 0; i < minCount; i++) {
                    result.add(Character.toString(c));
                }
            }
        }
        return result;
    }
}
