package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem218_Skyline {
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        Arrays.sort(buildings, (a, b) -> {
            if(a[0] != b[0])
                return a[0]-b[0];
            else
                return b[1]-a[1];
        });
        List<List<Integer>> result = new ArrayList<>();
        if(buildings.length == 0)
            return result;
        int idx = 0;
        int end = 0;
        int prevHeight = 0;
        for(int[] building : buildings) {
            if(building[1] < end)
                continue;

            int st = Math.max(building[0], end);
            int ht = building[2];
            if(prevHeight == ht || ht == 0) {
                if(ht != 0)
                    end = building[1];
                idx++;
                continue;
            }
            if (st == building[1] && ht <= prevHeight)
                continue;
            prevHeight = ht;
            boolean fFoundBiggerStart = false;
            boolean endSmaller = false;
            for(int index = idx+1; index < buildings.length; index++) {
                if(buildings[index][0] > building[1]) {
                    break;
                }
                if(buildings[index][2] <= ht) {
                    continue;
                }
                if(buildings[index][0] == st && buildings[index][2] >= ht) {
                    fFoundBiggerStart = true;
                }
                end = buildings[index][0];
                endSmaller = true;
                break;
            }
            if(fFoundBiggerStart) {
                idx++;
                continue;
            }
            if (!endSmaller)
                end = building[1];
            result.add(Arrays.asList(st, ht));
            if(idx < buildings.length-1 &&  buildings[idx][1] < buildings[idx+1][0])
                result.add(Arrays.asList(buildings[idx][1], 0));
            idx++;
        }
        result.add(Arrays.asList(end, 0));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Problem218_Skyline.getSkyline(new int[][]{
                {3,7,8},{3,8,7},{3,9,6},{3,10,5},{3,11,4},{3,12,3},{3,13,2},{3,14,1}
        }));
    }
}
