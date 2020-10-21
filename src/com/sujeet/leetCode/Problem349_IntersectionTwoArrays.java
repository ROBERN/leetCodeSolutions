package com.sujeet.leetCode;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Problem349_IntersectionTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for(int num1 : nums1) {
            set1.add(num1);
        }

        Set<Integer> set2 = new HashSet<>();
        for(int num2 : nums2) {
            set2.add(num2);
        }
        set1.retainAll(set2);
        int[] ret = new int[set1.size()];
        int idx = 0;
        for(int val : set1) {
            ret[idx++] = val;
        }
        return ret;
    }
}
