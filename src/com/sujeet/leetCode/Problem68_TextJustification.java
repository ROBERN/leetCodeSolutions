package com.sujeet.leetCode;

import java.util.*;

public class Problem68_TextJustification {
    SortedMap<Integer, Integer> map = new TreeMap<>();

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int wordLen = words.length;
        for(int currIdx = 0; currIdx < wordLen; currIdx++) {
            int left = currIdx;
            int right = left;
            int charCounts = 0;
            while(right < wordLen && charCounts < maxWidth) {
                charCounts += words[right].length()+1;
                right++;
            }
            if(right >= wordLen) {
                right--;
            }
            //fix right
            if(charCounts > maxWidth+1) {
                right--;
            }
            res.add(justify(words, left, right, maxWidth));
            currIdx = right;
        }
        return res;
    }

    private String justify(String[] words, int left, int right, int maxWidth) {
        int wordCount = right - left + 1;
        if (wordCount == 1) {
            return getOneWordString(words[right], maxWidth);
        }
        int countOfLetter = 0;
        for (int i = left; i <= right; i++) {
            countOfLetter += words[i].length();
        }
        int extra = maxWidth - countOfLetter;
        int evenSpace = extra / (wordCount - 1);
        int extraSpace = extra % (wordCount - 1);

        StringBuilder sb = new StringBuilder();
        if (right == words.length - 1) {
            return getLastString(words, left, right, maxWidth);
        }
        for (int i = left; i <= right; i++) {
            sb.append(words[i]);
            int space = evenSpace + (extraSpace > 0 ? 1 : 0);
            if (extraSpace > 0) extraSpace--;
            while (space > 0 && (i != right || right == words.length - 1)) {
                space--;
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private String getOneWordString(String word, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        sb.append(maxWidth);
        for(int i = 0; i < maxWidth - word.length(); i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    private String getLastString(String[] words, int left, int right, int maxWidth) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for(int i = left; i <= right; i++) {
            sb.append(words[i]);
            idx += words[i].length();
            if(idx < maxWidth){sb.append(" ");idx++;}
        }
        for(int i = idx; i<maxWidth; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }
    private static String sanitize(String S) {
        StringBuilder sb = new StringBuilder();
        for(int idx = 0; idx < S.length(); idx++){
            if(S.charAt(idx) != '#') {
                sb.append(S.charAt(idx));
            } else {
                sb.substring(0, sb.length()-1);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Problem68_TextJustification.sanitize("ab#c");
    }
}
