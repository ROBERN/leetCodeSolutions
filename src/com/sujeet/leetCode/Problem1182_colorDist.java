package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem1182_colorDist {
    public static List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int len = colors.length;
        int[][] dist = new int[3][len];

        dist[0] = fillDistances(colors, 1);
        dist[1] = fillDistances(colors, 2);
        dist[2] = fillDistances(colors, 3);

        List<Integer> result = new ArrayList<>();
        for(int[] query : queries) {
            result.add(dist[query[1]-1][query[0]]);
        }
        return result;
    }

    private static int[] fillDistances(int[] colors, int color) {
        int[] dist = new int[colors.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int distBefore = Integer.MAX_VALUE;
        int distAfter = Integer.MAX_VALUE;
        int len = colors.length;
        for(int front = 0, end = len-1; front < colors.length; front++, end--) {
            if(colors[front] == color) {
                distBefore = 0;
            }
            if(colors[end] == color) {
                distAfter = 0;
            }
            dist[front] = Math.min(dist[front], distBefore);
            dist[end] = Math.min(dist[end], distAfter);
            if(distBefore != Integer.MAX_VALUE) distBefore++;
            if(distAfter != Integer.MAX_VALUE) distAfter++;
        }
        return dist;
    }

    public static void main(String[] args) {
        Problem1182_colorDist.shortestDistanceColor(
                new int[]{1,1,2,1,3,2,2,3,3},
                new int[][]{
                        {1,3},{2,2},{6,1}
                });
    }
}
