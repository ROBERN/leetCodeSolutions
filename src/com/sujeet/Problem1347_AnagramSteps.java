package com.sujeet;

public class Problem1347_AnagramSteps {
    public int minSteps(String s, String t) {
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        for(char c : s.toCharArray()) {
            arr1[c-'a']++;
        }
        for(char c : t.toCharArray()) {
            arr2[c-'a']++;
        }
        int count = 0;
        for(int i = 0; i < 26; i++) {
            count += Math.abs(arr1[i]-arr2[i]);
        }
        return count/2;
    }
}
