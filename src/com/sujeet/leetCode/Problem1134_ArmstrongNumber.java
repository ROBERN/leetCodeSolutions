package com.sujeet.leetCode;

public class Problem1134_ArmstrongNumber {
    public boolean isArmstrong(int N) {
        int K = Integer.toString(N).length();
        int sum = 0;
        int num = N;
        while(num > 0){
            int digit = num%10;
            sum += Math.pow(digit, K);
            if(sum > N) {
                return false;
            }
            num /= 10;
        }
        return sum == N;
    }
}
