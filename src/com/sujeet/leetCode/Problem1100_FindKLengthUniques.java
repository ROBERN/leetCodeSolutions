package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem1100_FindKLengthUniques {
    public int numKLenSubstrNoRepeats(String S, int K) {
        int unique = 0;
        int[] arr = new int[26];
        int len = S.length();
        if(len < K) {
            return 0;
        }
        for(int i = 0; i < K; i++) {
            char c = S.charAt(i);
            if(arr[c-'a'] == 0) {
                unique++;
            }
            arr[c-'a']++;
        }
        int count = 0;
        List<String> strList = new ArrayList<>();
        for(int i = K; i <= len; i++) {
            if(unique == K) {
                count++;
                strList.add(S.substring(i-K, i));
            }
            if(i==len) break;
            char curr = S.charAt(i);
            char prev = S.charAt(i-K);
            arr[prev-'a']--;
            if(arr[prev-'a'] == 0) {
                unique--;
            }
            if(arr[curr -'a'] == 0) {
                unique++;
            }
            arr[curr -'a']++;
        }
        return count;
    }
}
