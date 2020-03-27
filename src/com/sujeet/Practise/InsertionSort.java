package com.sujeet.Practise;

import java.util.Arrays;
import java.util.List;

public class InsertionSort {

    public static List<Integer> insertionSort(List<Integer> input) {
        for(int i = 1; i< input.size(); i++) {
            int key = input.get(i);
            int j = i-1;
            while (j >= 0 && input.get(j) > key) {
                input.set(j+1, input.get(j)); // set(idx, value)
                j--;
            }
            input.set(j+1, key);
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println(
            insertionSort(Arrays.asList(3,5,6,1,234,122,122,12334,12,444))
        );
    }
}
