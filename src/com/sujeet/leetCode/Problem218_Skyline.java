package com.sujeet.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Problem218_Skyline {
    private static class BuildingPoint implements Comparable<BuildingPoint>{
        int point;
        boolean isStart;
        int height;
        BuildingPoint(int p, int h, boolean isStart) {
            this.point = p;
            this.height = h;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(BuildingPoint other) {
            if(other.point != point) {
                return point - other.point;
            } else {
                if(other.isStart != isStart) {
                    return other.isStart ? 1 : -1;
                } else {
                    if(other.isStart) {
                        return other.height - height;
                    } else {
                        return height-other.height;
                    }
                }
            }
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        int len = buildings.length;
        BuildingPoint[] points = new BuildingPoint[len*2];

        int idx = 0;
        for(int[] building : buildings) {
            points[idx++] = new BuildingPoint(building[0], building[2], true);
            points[idx++] = new BuildingPoint(building[1], building[2], false);
        }

        Arrays.sort(points);

        List<List<Integer>> result = new ArrayList<>();

        TreeMap<Integer, Integer> queue = new TreeMap<>();
        int prevMax = 0;
        queue.put(0, 1);



        for(BuildingPoint point : points) {
            if(point.isStart) {
                queue.compute(point.height, (key, value) -> {
                    if(value != null) {
                        return value+1;
                    }
                    return 1;
                });
            } else {
                queue.compute(point.height, (key, value) -> {
                    if(value == 1) {
                        return null;
                    } else {
                        return value-1;
                    }
                });
            }
            int currMax = queue.lastKey();
            if(currMax != prevMax) {
                result.add(Arrays.asList(point.point, currMax));
                prevMax = currMax;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Problem218_Skyline obj = new Problem218_Skyline();
        System.out.println(obj.getSkyline(new int[][]{
                {3,7,8},{3,8,7},{3,9,6},{3,10,5},{3,11,4},{3,12,3},{3,13,2},{3,14,1}
        }));
    }
}
