package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem728_SelfDividing {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> answer = new ArrayList<>();

        for(int num = left; num <= right; num++) {
            if(isSelfDividing(num)) {
                answer.add(num);
            }
        }
        return answer;
    }

    private static boolean isSelfDividing(int num) {
        int val = num;
        while(num > 0) {
            int digit = num % 10;
            if(digit == 0 || val%digit != 0) {
                return false;
            }
            num /= 10;
        }
        return true;
    }
}
