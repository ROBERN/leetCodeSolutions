package com.sujeet.leetCode;

public class Problem1371_VowelsEven {

    public  static int findTheLongestSubstring(String s) {
        int length = s.length();
        int[][] vowelsSeenCounts = new int[length][5];

        int idx = 0;
        for(char c : s.toCharArray()) {
            int vowelIdx = vowelIndex(c);
            for(int i = 0; i < 5; i++) {
                vowelsSeenCounts[idx][i] = (idx == 0 ? 0 : vowelsSeenCounts[idx-1][i]);
            }
            if(vowelIdx!=-1)
                vowelsSeenCounts[idx][vowelIdx]++;
            idx++;
        }

        for(int len = length; len > 0; len--) {
            for(int st = 0; st <= length - len; st++) {
                int end = st+len-1;
                boolean allEven = true;
                for(int iter = 0; iter < 5; iter++) {
                    int prev = (st == 0 ? 0 : vowelsSeenCounts[st-1][iter]);
                    if((vowelsSeenCounts[end][iter] - prev) % 2 == 1) {
                        allEven = false;
                        break;
                    }
                }
                if(allEven){
                    return len;
                }
            }
        }
        return 0;
    }

    private static int vowelIndex(char c) {
        switch(c) {
            case 'a':
                return 0;
            case 'e':
                return 1;
            case 'i':
                return 2;
            case 'o':
                return 3;
            case 'u':
                return 4;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        findTheLongestSubstring("a");
    }
}
