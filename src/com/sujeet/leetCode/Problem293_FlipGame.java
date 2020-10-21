package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem293_FlipGame {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> solution = new ArrayList<>();

        for(int idx = 0; idx < s.length()-1; idx++) {
            if(s.charAt(idx) == '+' && s.charAt(idx+1) == '+') {
                String newS;
                if(idx > 0) {
                    newS = s.substring(0, idx) + "--" + s.substring(idx+2, s.length());
                } else {
                    newS = "--" + s.substring(idx+2, s.length());
                }
                solution.add(newS);
            }
        }

        return solution;
    }
}
