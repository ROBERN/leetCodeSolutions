package com.sujeet.leetCode;

public class Problem1085_SumOfDigitsOfMin {
    public int sumOfDigits(int[] A) {
        int minNum = Integer.MAX_VALUE;
        for(int a : A) {
            minNum = Math.min(a, minNum);
        }
        if(minNum < 0)
            throw new RuntimeException();
        int sumOfDigits = 0;
        while(minNum > 0) {
            sumOfDigits += minNum %10;
            minNum /= 10;
        }
        return sumOfDigits  % 2 == 1 ? 0 : 1;
    }
}
