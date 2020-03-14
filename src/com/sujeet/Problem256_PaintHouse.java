package com.sujeet;

public class Problem256_PaintHouse {

    public int minCost(int[][] costs) {
        if(costs.length == 0) {return 0;}
        int colCount = costs[0].length;
        int rowCount = costs.length;
        int[][] optimal = new int[rowCount+1][colCount];
        for(int i = 0; i < colCount; i++) {
            optimal[rowCount][i] = 0;
        }
        for(int row = rowCount-1; row >= 0; row--) {
            for(int col = 0; col < colCount; col++) {
                optimal[row][col] = Integer.MAX_VALUE;
            }
        }

        for(int row = rowCount-1; row >= 0; row--) {
            for(int col = 0; col < colCount; col++) {
                for(int i = 0; i < colCount; i++) {
                    if(i!=col) {
                        optimal[row][col] =
                                Math.min(optimal[row][col], optimal[row+1][i]);
                    }
                }
                optimal[row][col] += costs[row][col]; // cost at the point
            }
        }
        int res = Integer.MAX_VALUE;
        for(int col = 0; col < colCount; col++) {
            res= Math.min(optimal[0][col], res);
        }
        return res;
    }
}
