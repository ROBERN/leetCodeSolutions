package com.sujeet.leetCode;

public class Problem348_TicTacToe {
    private int[][] playerRow;
    private int[][] playerCol;

    private int[] playerDiagForward;
    private int[] playerDiagBack;

    private int N;
    /** Initialize your data structure here. */
    public Problem348_TicTacToe(int n) {
        N = n;
        playerRow = new int[2][n];
        playerCol = new int[2][n];

        playerDiagForward = new int[2];
        playerDiagBack = new int[2];
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int playerIndex = player-1;
        playerRow[playerIndex][row]++;
        playerCol[playerIndex][col]++;
        if(row+col == N-1) {
            playerDiagBack[playerIndex]++;
        }
        if(row == col) {
            playerDiagForward[playerIndex]++;
        }
        if (playerWins(playerIndex, row, col))
            return player;

        return 0;
    }

    private boolean playerWins(int playerIndex, int row, int col) {
        return playerRow[playerIndex][row] == N || playerCol[playerIndex][col] == N ||
                playerDiagForward[playerIndex] == N || playerDiagBack[playerIndex] == N;
    }
}
