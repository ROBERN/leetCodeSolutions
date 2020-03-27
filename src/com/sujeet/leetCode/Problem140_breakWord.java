package com.sujeet.leetCode;

import java.util.*;

public class Problem140_breakWord {
    class SolutionList {
        List<String> words;
        SolutionList(List<String> list) {
            words = list;
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);

        int len = s.length();
        SolutionList[][] solution = new SolutionList[len][len];
        for(int strLen = 1; strLen <= len; strLen++) {
            for(int stIdx = 0; stIdx <= len - strLen; stIdx++) {
                int endIdx = stIdx + strLen-1;
                // now iterate k from st to end-1 and see if this is a valid list so
                // strings : st to k and k+1 to end. If both are valid. Add them to SolutionList
                String completeString = s.substring(stIdx, endIdx+1);
                Set<String> currResults = new HashSet<>();
                if(dict.contains(completeString)) {
                    currResults.add(completeString);
                }
                for(int k = stIdx; k < endIdx; k++) {
                    if(!solution[stIdx][k].words.isEmpty() && !solution[k+1][endIdx].words.isEmpty()) {
                        List<String> list1 = solution[stIdx][k].words;
                        List<String> list2 = solution[k+1][endIdx].words;
                        currResults.addAll(createStringList(list1, list2));
                    }
                }
                solution[stIdx][endIdx]  = new SolutionList(new ArrayList<>(currResults));
            }
        }

        return solution[0][len-1].words;
    }

    private List<String> createStringList(List<String> strList1, List<String> strList2) {
        List<String> res = new ArrayList<>();
        for(String str1 : strList1) {
            for(String str2 : strList2) {
                res.add(str1 + " " + str2);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Problem140_breakWord obj = new Problem140_breakWord();
        System.out.println(obj.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
    }
}
