package com.sujeet.leetCode;

public class Problem1266_MinTimePointsVisit {
    public int minTimeToVisitAllPoints(int[][] points) {
        if(points.length <= 1)
            return 0;
        int totalTime = 0;

        int[] prevPoint = points[0];
        for(int idx = 1; idx < points.length; idx++) {
            int xDiff = Math.abs(prevPoint[0] - points[idx][0]);
            int yDiff = Math.abs(prevPoint[1] - points[idx][1]);

            int time = Math.min(xDiff, yDiff);
            time += (xDiff - time) + (yDiff -time);
            totalTime += time;

            prevPoint = points[idx];
        }
        return totalTime;
    }
}
