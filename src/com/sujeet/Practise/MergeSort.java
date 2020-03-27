package com.sujeet.Practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    public static void mergeSort(int[] list, int st, int end) {
        if (st >= end) return;
        int mid = st + (end-st)/2;
        mergeSort(list, st, mid);
        mergeSort(list, mid+1, end);
        merge(list, st, mid, end);
    }

    private static void merge(int[] list, int st, int mid, int end) {
        int[] result = new int[list.length];
        int idx1 = st;
        int idx2 = mid+1;
        int currIdx = st;
        while (idx1 <= mid && idx2 <= end) {
            if (list[idx1] < list[idx2]) {
                result[currIdx++] = list[idx1++];
            } else {
                result[currIdx++] = list[idx2++];
            }
        }
        while (idx1 <= mid) {
            result[currIdx++] = list[idx1++];
        }
        while (idx2 <= end) {
            result[currIdx++] = list[idx2++];
        }
        for(int i = st; i  <= end; i++) {
            list[i] = result[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 6, 1, 234, 122, 122, 12334, 12, 444};
        mergeSort(arr, 0, 9);
        System.out.println(Arrays.toString(arr));
    }
}
