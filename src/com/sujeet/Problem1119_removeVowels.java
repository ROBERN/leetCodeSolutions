package com.sujeet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Problem1119_removeVowels {
    public String removeVowels(String S) {
        StringBuilder sb = new StringBuilder();
        Set<Character> vowels = new HashSet<>();
        vowels.addAll(Arrays.asList(new Character[]{'a','e','i','o', 'u'}));
        for(int i = 0; i < S.length(); i++) {
            if(!vowels.contains(S.charAt(i))) {
                sb.append(S.charAt(i));
            }
        }
        return sb.toString();
    }
}
