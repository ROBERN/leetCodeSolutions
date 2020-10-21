package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem243_ShortestDist {
    public int shortestDistance(String[] words, String word1, String word2) {
        List<Integer> indices1 = new ArrayList<>();
        List<Integer> indices2 = new ArrayList<>();
        int idx = 0;
        for(String word : words) {
            if(word.equals(word1))
                indices1.add(idx);
            else if(word.equals(word2))
                indices2.add(idx);
            idx++;
        }

        int minDist = Integer.MAX_VALUE;
        for(int idx1 : indices1) {
            for(int idx2 : indices2) {
                minDist = Math.min(minDist, Math.abs(idx1-idx2));
            }
        }
        return minDist;


    }
}
