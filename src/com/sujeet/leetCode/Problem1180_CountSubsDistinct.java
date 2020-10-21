package com.sujeet.leetCode;

public class Problem1180_CountSubsDistinct {
    public int countLetters(String S) {
        int totalCount = 0;
        int st = 0;
        char prev = ' ';
        int idx = 0;
        for(char c : S.toCharArray()) {
            if(c != prev) {
                int len = (idx - st);
                totalCount += (len*(len+1))/2;
                st = idx;
                prev = c;
            }
            idx++;
        }
        int len = (idx - st);
        totalCount += (len*(len+1))/2;

        return totalCount;
    }
}
