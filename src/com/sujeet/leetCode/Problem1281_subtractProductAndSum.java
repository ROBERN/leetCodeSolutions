package com.sujeet.leetCode;

public class Problem1281_subtractProductAndSum {
    public int subtractProductAndSum(int n) {
        return getProduct(n)-getSum(n);
    }
    private int getProduct(int n) {
        int sol = 1;
        while(n > 0) {
            sol *= n%10;
            n /= 10;
        }
        return sol;
    }
    private int getSum(int n) {
        int sum = 0;
        while(n > 0) {
            sum += n %10;
            n/=10;
        }
        return sum;
    }
}
