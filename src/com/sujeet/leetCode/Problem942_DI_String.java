package com.sujeet.leetCode;

public class Problem942_DI_String {
    public int[] diStringMatch(String S) {
        int len = S.length()+1;
        int[] res = new int[len];
        if(len == 1) {
            return res;
        }

        int low = 0;
        int high = len-1;

        int idx = 0;
        for(char c : S.toCharArray()) {
            if(c == 'I')
                res[idx++] = low++;
            else
                res[idx++] = high--;
        }
        res[idx] = low;
        return res;
    }
}
