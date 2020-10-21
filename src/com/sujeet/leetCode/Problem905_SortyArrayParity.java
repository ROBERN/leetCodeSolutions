package com.sujeet.leetCode;

public class Problem905_SortyArrayParity {
    public int[] sortArrayByParity(int[] A) {
        int l = 0;
        int r = A.length-1;
        while(r>l) {
            if(A[r] %2 == 0 && A[l]%2==1) {
                int tmp =A[r];
                A[r] = A[l];
                A[l] = tmp;
                r--;l++;
                continue;
            } else {
                while(r > l && A[r]%2 == 1) {
                    r--;
                }
                while(r>l && A[l]%2==0){
                    l++;
                }
            }
        }
        return A;
    }
}
