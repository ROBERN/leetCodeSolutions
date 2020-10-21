package com.sujeet.leetCode;

import java.util.*;

public class Problem1213_Intersect3Array {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int idx1 = 0;
        int idx2 = 0;
        int idx3 = 0;

        int len1 = arr1.length;
        int len2 = arr2.length;
        int len3 = arr3.length;

        List<Integer> result = new ArrayList<>();
        while(idx1 < len1 && idx2 < len2 && idx3 < len3) {
            int val1 = arr1[idx1];
            int val2 = arr2[idx2];
            int val3 = arr3[idx3];
            if(val1 == val2 && val1 == val3) {
                result.add(val1);
                idx1++;idx2++;idx3++;
            } else {
                if(val1 <= val2 && val1 <= val3) {
                    idx1++;
                } else if(val2 <= val3 && val2 <= val1) {
                    idx2++;
                } else {
                    idx3++;
                }
            }
        }

        return result;
    }
}
