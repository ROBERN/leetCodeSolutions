package com.sujeet.leetCode;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class Problem909_snakesAndLadder {
    private Map<Integer, Integer> visited = new HashMap<>();
    private int minCost = Integer.MAX_VALUE;
    private int boardSize;
    public int snakesAndLadders(int[][] board) {
        int rowSz = board.length;
        if(rowSz == 0)
            return 0;
        int colSz = board[0].length;
        if(colSz == 0)
            return 0;
        boardSize = rowSz * colSz;
        dfs(board, 1, 0);

        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }
    void dfs(int[][] board, int pos, int costYet) {
        if(pos < 1 || pos > boardSize || visited.getOrDefault(pos, Integer.MAX_VALUE) <= costYet) {
            return;
        }

        if(pos == boardSize) {
            minCost = Math.min(minCost, costYet);
            return;
        }
        visited.put(pos, costYet);
        Pair<Integer, Integer> rowCol = getRowCol(pos, board);
        int r = rowCol.getKey();
        int c = rowCol.getValue();
        if(board[r][c] != -1) {
            pos = board[r][c];
        }
        if(pos == boardSize) {
            minCost = Math.min(minCost, costYet);
            return;
        }
        for(int i = 1; i <= 6; i++)
            dfs(board, pos+i, costYet+1);
    }

    Pair<Integer, Integer> getRowCol(int pos, int[][] board) {
        int rowSz = board.length;
        int colSz = board[0].length;
        pos--;
        int row = pos/(rowSz);
        int rowInverted = rowSz - pos/(rowSz)-1;
        int col = pos%colSz;
        if(row %2 == 1)
            col = colSz-col-1;
        return new Pair<>(rowInverted, col);
    }

    int getPos(int r, int c, int[][] board) {
        int rowSz = board.length;
        return (rowSz-r)* rowSz + c+1;
    }

    public static void main(String[] args) {
        Problem909_snakesAndLadder obj = new Problem909_snakesAndLadder();
        System.out.println(obj.snakesAndLadders(new int[][] {
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},
                {1,-1,-1,-1,-1,-1},
                {-1,15,-1,-1,-1,-1}
        }));
    }
}
