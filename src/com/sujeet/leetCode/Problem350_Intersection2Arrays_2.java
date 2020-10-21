package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem350_Intersection2Arrays_2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int idx1 = 0;
        int idx2 = 0;

        List<Integer> result = new ArrayList<>();
        while(idx1 < nums1.length && idx2 < nums2.length) {
            if(nums1[idx1] == nums2[idx2]) {
                result.add(nums1[idx1]);
                idx1++;
                idx2++;
            } else {
                if(nums1[idx1] < nums2[idx2]) {
                    idx1++;
                } else {
                    idx2++;
                }
            }

        }

        int[] ret = new int[result.size()];
        int idx = 0;
        for(int item : result) {
            ret[idx++] = item;
        }
        return ret;
    }
}
