package com.sujeet.leetCode;

public class Problem385_NumOfCornerRect {
    public int countCornerRectangles(int[][] grid) {
        int count = 0;
        for(int leftC = 0; leftC < grid[0].length-1; leftC++) {
            for(int upC = 0; upC < grid.length-1; upC++) {
                if(grid[upC][leftC] != 1) continue;
                for(int rightC = leftC+1; rightC < grid[0].length; rightC++) {
                    if(grid[upC][rightC] != 1) continue;
                    for(int downC = upC+1; downC < grid.length; downC++) {
                        if(grid[upC][leftC] == 1 &&
                                grid[downC][leftC] == 1 &&
                                grid[upC][rightC] == 1 &&
                                grid[downC][rightC] == 1
                        ) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
