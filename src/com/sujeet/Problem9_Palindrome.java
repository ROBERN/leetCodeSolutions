package com.sujeet;

public class Problem9_Palindrome {
    public boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        }
        int reverseNumber = reverse(x);
        return reverseNumber == x;
    }

    private int reverse(int x) {
        int val = 0;
        boolean isNegative = x < 0;
        if(isNegative) {
            x = -1 * x;
        }
        while(x > 0) {
            if(val > Integer.MAX_VALUE/10 || (val == Integer.MAX_VALUE/10 && x%10 > 7)) {
                return 0;
            }
            val = val*10 + x % 10;
            x = x/10;
        }
        if(isNegative) {
            val = -1 * val;
        }
        return val;
    }
}
