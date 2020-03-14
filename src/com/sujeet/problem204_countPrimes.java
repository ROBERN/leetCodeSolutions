package com.sujeet;

public class problem204_countPrimes {
    public int countPrimes(int n) {
        int count = 0;
        boolean[] isPrimeArr = new boolean[n];
        for(int i = 2; i < n; i++) {
            isPrimeArr[i] = true;
        }
        for(int i = 2; i < n; i++) {
            if(isPrimeArr[i]) {
                count++;
                for(int k = i; k < n; k+=i) {
                    isPrimeArr[k] = false;
                }
            }
        }
        return count;
    }
}
