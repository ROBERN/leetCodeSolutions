package com.sujeet.leetCode;

public class Problem476_NumberCompliment {
    public int findComplement(int num) {
        int len = (int)(Math.log(num)/Math.log(2)) + 1;
        int bitmask = (1<<len) -1;

        return bitmask^num;

    }
}
