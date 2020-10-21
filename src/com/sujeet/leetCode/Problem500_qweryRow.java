package com.sujeet.leetCode;

import java.util.*;

public class Problem500_qweryRow {
    public String[] findWords(String[] words) {
        String[] charsRow = new String[]{"qwertyuiop", "asdfghjkl", "zxcvbnm"};

        List<Set<Character>> keySets = new ArrayList<>();

        for (String keys : charsRow) {
            keySets.add(stringToSet(keys));
        }

        List<String> result = new ArrayList<>();
        for (String word : words) {
            Set<Character> charsInWord = stringToSet(word.toLowerCase());
            for (Set<Character> keySet : keySets) {
                if (keySet.containsAll(charsInWord)) {
                    result.add(word);
                    break;
                }
            }
        }

        String[] ans = new String[result.size()];
        int idx = 0;
        for (String str : result) {
            ans[idx++] = str;
        }
        return ans;
    }

    private Set<Character> stringToSet(String str) {
        Set<Character> keySet = new HashSet<>();
        for(char c : str.toCharArray()) {
            keySet.add(c);
        }
        return keySet;
    }
}
