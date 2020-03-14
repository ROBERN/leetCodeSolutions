package com.sujeet;

public class Problem1165_Single_Row_Keyboard {
    public int calculateTime(String keyboard, String word) {
        int[] mp = new int[26];
        for(int i = 0; i <keyboard.length(); i++) {
            mp[keyboard.charAt(i) - 'a'] = i;
        }

        int prev = 0;
        int sum = 0;
        for(int idx = 0 ;idx < word.length(); idx++) {
            sum += Math.abs(prev - mp[word.charAt(idx)-'a']);
            prev = mp[word.charAt(idx)-'a'];
        }
        return sum;
    }
}
