package com.sujeet.leetCode;

public class Problem509_fib {
    private int[] fibArr;
    public int fib(int N) {
        fibArr = new int[N+2];
        fibArr[0] = 0;
        fibArr[1] = 1;
        for(int i = 2; i <=N; i++) {
            fibArr[i]=-1;
        }
        return fibs(N);
    }

    private int fibs(int k) {
        if(fibArr[k] != -1) {
            return fibArr[k];
        }
        fibArr[k] = fibs(k-1)+fibs(k-2);
        return fibArr[k];
    }
}
