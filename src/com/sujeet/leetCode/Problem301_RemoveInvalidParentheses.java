package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem301_RemoveInvalidParentheses {
    private int bestCount;
    private Set<String> bestSol;
    Problem301_RemoveInvalidParentheses() {
        bestCount = Integer.MAX_VALUE;
        bestSol = new HashSet<>();
    }

    public List<String> removeInvalidParentheses(String s) {
        getOptimalParens(0, s, 0, 0);
        return new ArrayList<>(bestSol);
    }

    private void getOptimalParens(int idx, String s, int openBraces, int removedBraces) {
        if(idx >= s.length()) {
            if(openBraces == 0) {
                if(removedBraces > bestCount)
                    return;
                if(removedBraces < bestCount) {
                    bestCount = removedBraces;
                    bestSol = new HashSet<>();
                }
                bestSol.add(s);
            }
            return;
        }
        if(s.charAt(idx) != '(' && s.charAt(idx) != ')') {
            getOptimalParens(idx+1,s, openBraces, removedBraces);
            return;
        }
        if(s.charAt(idx) == '(') {
            getOptimalParens(idx+1, s, openBraces+1, removedBraces); //include
            getOptimalParens(idx, s.substring(0, idx) + s.substring(idx+1) , openBraces, removedBraces+1); // exclude
        }
        if(s.charAt(idx) == ')') {
            if(openBraces > 0)
                getOptimalParens(idx+1, s, openBraces-1, removedBraces); //include
            getOptimalParens(idx, s.substring(0, idx) + s.substring(idx+1), openBraces, removedBraces+1); //exclude
        }
    }
}
