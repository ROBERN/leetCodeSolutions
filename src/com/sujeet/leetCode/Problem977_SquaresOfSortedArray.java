package com.sujeet.leetCode;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Problem977_SquaresOfSortedArray {

    public int[] sortedSquares(int[] A) {
         return Arrays.stream(A).map(a -> a*a).sorted().toArray();
    }
}
