package com.sujeet.leetCode;

public class problem70_climbStairs {
    private int count = 0;
    private int[] arr;
    public int climbStairs(int n) {
        arr = new int[n+2];
        arr[0] = 1;
        arr[1] = 1;
        for(int i = 2; i <= n; i++) {arr[i] =-1;}
        return climb(n);
    }

    private int climb(int idx){
        if(arr[idx] != -1) {
            return arr[idx];
        }
        if(idx == 0) {
            count++;
            return 1;
        }
        if(idx < 0) {
            return 0;
        }
        arr[idx]= climb(idx-1) + climb(idx-2);
        return arr[idx];
    }
}
