package com.sujeet.leetCode;

public class Problem922_SortArrayByParity_2 {
    public int[] sortArrayByParityII(int[] A) {
        int l = 0;
        int r = 1;
        while(r < A.length && l < A.length) {
            if(A[r] %2 == 0 && A[l]%2==1) {
                int tmp =A[r];
                A[r] = A[l];
                A[l] = tmp;
                r+=2;l+=2;
            } else {
                while(r < A.length && A[r]%2 == 1) {
                    r+=2;
                }
                while(l < A.length && A[l]%2==0){
                    l+=2;
                }
            }
        }
        return A;
    }
}
