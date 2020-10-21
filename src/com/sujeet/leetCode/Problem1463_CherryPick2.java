package com.sujeet.leetCode;

public class Problem1463_CherryPick2 {
    private int[][] grid;
    private int rowSz;
    private int colSz;
    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        rowSz= grid.length;
        colSz= grid[0].length;

        memo = new Integer[rowSz][colSz][colSz];

        return backTrack(0, colSz-1, 0);
    }

    private Integer[][][] memo;

    private int[] colMoves = new int[]{-1, 0, 1};
    private int backTrack(int leftCol, int rightCol, int row) {

        if(row == rowSz) {
            return 0;
        }

        if(leftCol >= colSz || leftCol < 0 || rightCol >= colSz || rightCol < 0) {
            return 0;
        }
        if(memo[row][leftCol][rightCol] != null)
            return memo[row][leftCol][rightCol];

        int ans = 0;
        for(int leftInc : colMoves) {
            for(int rightInc : colMoves) {
                ans = Math.max(ans, backTrack(leftCol + leftInc, rightCol + rightInc, row+1));
            }
        }
        return memo[row][leftCol][rightCol] = getCheryPicks(leftCol, rightCol, row) + ans;
    }

    private int getCheryPicks(int leftCol, int rightCol, int row) {
        int count = grid[row][leftCol];

        return leftCol == rightCol ? count : count + grid[row][rightCol];
    }
}
