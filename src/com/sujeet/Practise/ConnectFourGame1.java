package com.sujeet.Practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ConnectFourGame1 {

    public enum STATE {
        UN_DECIDED,
        PLAYER_A_WON,
        PLAYER_B_WON,
        NO_WINNER
    }

    private static final int ROW_COUNT = 6;
    private static final int COL_COUNT = 7;

    private static final Character PLAYER_A = 'A';
    private static final Character PLAYER_B = 'B';
    private static final Character EMPTY = 'E';

    private char[][] board;
    private List<Integer> validColumnIndexes;
    private STATE state;

    public ConnectFourGame1() {
        this.board = new char[ROW_COUNT][COL_COUNT];
        validColumnIndexes = new ArrayList<>();
        state = STATE.UN_DECIDED;
        for (int idx = 0; idx < COL_COUNT; idx++)
            validColumnIndexes.add(idx);

        for (int row = 0; row < ROW_COUNT; row++) {
            Arrays.fill(board[row], EMPTY); //
        }
    }

    public void printBoard() {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                System.out.print(board[row][col] + ", ");
            }
            System.out.println();
        }
    }

    public List<Integer> getValidColumnsIndexes() {
        return validColumnIndexes;
    }

    public STATE playMove(char player, int col) {
        if (state != STATE.UN_DECIDED)
            return state;
        if (player != PLAYER_A && player != PLAYER_B)
            throw new IllegalArgumentException("player " + player + " not valid");
        if (board[0][col] != EMPTY)
            throw new IllegalArgumentException("Column " + col + " is full");
        int row;
        // high-water for large board.

        for(row = ROW_COUNT - 1; row >= 0; row--) {
            if (board[row][col] == EMPTY) {
                board[row][col] = player;
                if (row == 0) {
                    validColumnIndexes.remove(new Integer(col));
                    if (validColumnIndexes.size() == 0) {
                        state = STATE.NO_WINNER;
                    }
                }
                break;
            }
        }
        if (isGameWon(row, col)) {
            if (player == PLAYER_A)
                state = STATE.PLAYER_A_WON;
            else
                state = STATE.PLAYER_B_WON;
        }
        return  state;

        // isVictory done, set the status, return status.
    }

    private boolean isGameWon(int row, int col) {
        char player = board[row][col];
        if(isHorizontalWon(player, row, col)) {
            System.out.println("Horizon Game won by " + player + " at row " + row + " and col " + col);
            return true;
        }
        if (isVerticalWon(player, row, col)) {
            System.out.println("Vertical Game won by " + player + " at row " + row + " and col " + col);
            return true;
        }
        if (isDiagonalWon(player, row, col)) {
            System.out.println("Diagonal Game won by " + player + " at row " + row + " and col " + col);
            return true;
        }
        return false;
    }

    private boolean isDiagonalWon(char player, int row, int col) {
        int rightDown = 0;
        int r = row+1;
        int c = col+1;
        while (r < ROW_COUNT && c < COL_COUNT && board[r][c] == player) {
            rightDown++;
            r++;
            c++;
        }

        r = row-1;
        c = col-1;
        int leftUp = 0;
        while (r >= 0 && c >=0 && board[r][c] == player) {
            leftUp++;
            r--;
            c--;
        }
        if (leftUp+rightDown+1 >= 4)
            return true;

        int rightUp = 0;
        r = row-1;
        c = col+1;
        while (r >= 0 && c < COL_COUNT && board[r][c] == player) {
            rightUp++;
            r--;
            c++;
        }

        int leftDown = 0;
        r = row+1;
        c = col-1;
        while (r < ROW_COUNT && c >= 0 && board[r][c] == player) {
            leftDown++;
            r++;
            c--;
        }

        return rightUp + leftDown + 1 >= 4;
    }

    private boolean isVerticalWon(char player, int row, int col) {
        int upCount = 0;
        int r = row-1;
        while (r >= 0 && board[r][col] == player) {
            upCount++;
            r--;
        }

        int downCount = 0;
        r = row+1;
        while (r < ROW_COUNT && board[r][col] == player) {
            downCount++;
            r++;
        }
        return upCount + downCount + 1 >= 4;
    }

    private boolean isHorizontalWon(char player, int row, int col) {
        int leftCount = 0;
        int c = col-1;
        while (c >= 0 && board[row][c] == player) {
            leftCount++;
            c--;
        }

        int rightCount = 0;
        c = col+1;
        while (c < COL_COUNT && board[row][c] == player) {
            rightCount++;
            c++;
        }
        return rightCount + leftCount + 1 >= 4;
    }


    public static void main(String[] args) {
        ConnectFourGame1 connectFourGame = new ConnectFourGame1();
//        connectFourGame.playMove(PLAYER_A, 0);
//        connectFourGame.playMove(PLAYER_B, 0);
//
//        connectFourGame.playMove(PLAYER_A, 1);
//        connectFourGame.playMove(PLAYER_B, 1);
//
//        connectFourGame.playMove(PLAYER_A, 2);
//        connectFourGame.playMove(PLAYER_B, 2);
//
//        connectFourGame.playMove(PLAYER_A, 3);
//        connectFourGame.printBoard();
//        connectFourGame.playMove(PLAYER_B, 3);

        STATE state = STATE.UN_DECIDED;
        char player =  PLAYER_A;
        while (state == STATE.UN_DECIDED) {
            List<Integer> validColumns = connectFourGame.getValidColumnsIndexes();
            Random random = new Random();
            int randomIndex =  random.nextInt(validColumns.size());
            int columnIndex = validColumns.get(randomIndex);
            state = connectFourGame.playMove(player, columnIndex);
            if (state != STATE.UN_DECIDED) {
                System.out.println(state);
                connectFourGame.printBoard();
            }
            if (player == PLAYER_A)
                player = PLAYER_B;
            else
                player = PLAYER_A;
        }
    }

}

