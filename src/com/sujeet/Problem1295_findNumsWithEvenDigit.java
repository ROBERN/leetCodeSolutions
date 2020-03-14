package com.sujeet;

public class Problem1295_findNumsWithEvenDigit {
    public int findNumbers(int[] nums) {
        int count = 0;
        for(int num : nums) {
            count += Integer.toString(num).length() % 2 == 0 ? 1 : 0;
        }
        return count;
    }
}
