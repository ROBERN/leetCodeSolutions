package com.sujeet.leetCode;

public class Problem1011_CapacityOfShip {
    public int shipWithinDays(int[] weights, int D) {
        int high = Integer.MAX_VALUE;
        int low = 0;
        while(low < high) {
            int mid = low + (high-low)/2;
            if(daysNeeded(weights, mid) <= D)
                high = mid;
            else
                low = mid+1;
        }
        if(daysNeeded(weights, low) != D)
            throw new IllegalArgumentException();
        return low;
    }

    private int daysNeeded(int[] weights, int W) {
        int days = 1;
        int weightedValue = 0;
        for (int weight : weights) {
            if (W < weight)
                return Integer.MAX_VALUE;
            if (weightedValue + weight <= W)
                weightedValue += weight;
            else {
                weightedValue = weight;
                days++;
            }
        }
        return days;
    }

    public static void main(String[] args) {
        Problem1011_CapacityOfShip obj = new Problem1011_CapacityOfShip();
        obj.shipWithinDays(new int[]{1,2,3,1, 1}, 4);
    }
}
